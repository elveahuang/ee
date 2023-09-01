package cn.elvea.platform.commons.core.data.core.dialect;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface TemporaryTableClause {

    default String createSimpleTemporaryTable(String temporaryTableName) {
        return String.format("create table %s (id bigint); ", temporaryTableName);
    }

}
