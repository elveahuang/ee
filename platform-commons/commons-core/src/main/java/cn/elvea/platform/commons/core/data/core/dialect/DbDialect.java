package cn.elvea.platform.commons.core.data.core.dialect;

import org.springframework.data.relational.core.dialect.Dialect;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface DbDialect extends Dialect {

    TemporaryTableClause temporaryTable();

    DateTimeClause dateTime();

}
