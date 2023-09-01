package cn.elvea.platform.commons.core.extensions.docs.excel;

import cn.elvea.platform.commons.core.extensions.docs.excel.data.AbstractExcelData;
import cn.elvea.platform.commons.core.extensions.docs.excel.listener.ReadWithExtraListener;
import cn.elvea.platform.commons.core.utils.CollectionUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.metadata.CellExtra;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class EasyExcelHelper<T extends AbstractExcelData> {

    /**
     *
     */
    public List<T> getList(File file, List<List<String>> headers, Class<T> clazz, Integer sheetNo, Integer headRowNumber) {
        ReadWithExtraListener<T> listener = new ReadWithExtraListener<>(headRowNumber);
        try {
            EasyExcel.read(file, clazz, listener).extraRead(CellExtraTypeEnum.MERGE)
                    .head(headers).sheet(sheetNo).doRead();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        List<CellExtra> mergeList = listener.getMergeList();
        if (CollectionUtils.isEmpty(mergeList)) {
            return listener.getDataList();
        }
        return handleMergeData(listener.getDataList(), mergeList, headRowNumber);
    }

    /**
     * 处理合并单元格
     */
    private List<T> handleMergeData(List<T> data, List<CellExtra> extraMergeInfoList, Integer headRowNumber) {
        extraMergeInfoList.forEach(cellExtra -> {
            int firstRowIndex = cellExtra.getFirstRowIndex() - headRowNumber;
            int lastRowIndex = cellExtra.getLastRowIndex() - headRowNumber;
            int firstColumnIndex = cellExtra.getFirstColumnIndex();
            int lastColumnIndex = cellExtra.getLastColumnIndex();
            // 获取初始值
            Object initValue = getInitValueFromList(firstRowIndex, firstColumnIndex, data);
            // 设置值
            for (int i = firstRowIndex; i <= lastRowIndex; i++) {
                for (int j = firstColumnIndex; j <= lastColumnIndex; j++) {
                    setInitValueToList(initValue, i, j, data);
                }
            }
        });
        return data;
    }

    public void setInitValueToList(Object filedValue, Integer rowIndex, Integer columnIndex, List<T> data) {
        T object = data.get(rowIndex);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
            if (annotation != null) {
                if (annotation.index() == columnIndex) {
                    try {
                        field.set(object, filedValue);
                        break;
                    } catch (Exception e) {
                        log.error("Fail to set init value.", e);
                    }
                }
            }
        }
    }

    /**
     *
     */
    private Object getInitValueFromList(Integer firstRowIndex, Integer firstColumnIndex, List<T> data) {
        Object filedValue = null;
        T object = data.get(firstRowIndex);
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
            if (annotation != null) {
                if (annotation.index() == firstColumnIndex) {
                    try {
                        filedValue = field.get(object);
                        break;
                    } catch (IllegalAccessException e) {
                        log.error("设置合并单元格的初始值异常：" + e.getMessage());
                    }
                }
            }
        }
        return filedValue;
    }

}
