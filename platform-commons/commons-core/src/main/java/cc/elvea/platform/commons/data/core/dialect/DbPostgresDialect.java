package cc.elvea.platform.commons.data.core.dialect;

import org.springframework.data.relational.core.dialect.PostgresDialect;

/**
 * @author elvea
 * @since 24.1.0
 */
public class DbPostgresDialect extends PostgresDialect implements DbDialect {

    public static final DbPostgresDialect INSTANCE = new DbPostgresDialect();

    private static final TemporaryTableClause TEMPORARY_TABLE_CLAUSE = new TemporaryTableClause() {
    };

    private static final DateTimeClause DATE_TIME_CLAUSE = new DateTimeClause() {

        @Override
        public String currentDateTime() {
            return null;
        }

        @Override
        public String currentDate() {
            return null;
        }

        @Override
        public String currentTime() {
            return null;
        }

    };

    @Override
    public TemporaryTableClause temporaryTable() {
        return TEMPORARY_TABLE_CLAUSE;
    }

    @Override
    public DateTimeClause dateTime() {
        return DATE_TIME_CLAUSE;
    }

}
