package cc.elvea.platform.commons.data.core.dialect;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface TemporaryTableClause {

    default String createSimpleTemporaryTable(String temporaryTableName) {
        return String.format("create table %s (id bigint); ", temporaryTableName);
    }

}
