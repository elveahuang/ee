package cc.elvea.platform.commons.data.jdbc.dialect;

/**
 * @author elvea
 */
public interface TemporaryTableClause {

    default String createSimpleTemporaryTable(String temporaryTableName) {
        return String.format("create table %s (id bigint); ", temporaryTableName);
    }

}
