package cc.elvea.platform.commons.utils.excel.data;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@ExcelIgnoreUnannotated
public abstract class AbstractExcelData implements Serializable {

    @ExcelIgnore
    protected int lineNo;

}
