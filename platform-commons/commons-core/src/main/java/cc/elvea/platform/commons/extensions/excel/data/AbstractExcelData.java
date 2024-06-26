package cc.elvea.platform.commons.extensions.excel.data;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
@ExcelIgnoreUnannotated
public abstract class AbstractExcelData implements Serializable {

    /**
     * 行号
     */
    @ExcelIgnore
    protected int lineNo;

}
