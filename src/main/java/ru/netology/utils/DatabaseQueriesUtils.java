package ru.netology.utils;

import lombok.Getter;

import static ru.netology.utils.DatabaseConnectionUtils.getJdbcTemplate;

public class DatabaseQueriesUtils {

    public static String getLastDebitStatusFromDB() {
        return getJdbcTemplate().queryForObject(SqlScripts.SELECT_LAST_STATUS_DEBIT.getSqlQuery(), String.class);
    }

    public static String getLastCreditStatusFromDB() {
        return getJdbcTemplate().queryForObject(SqlScripts.SELECT_LAST_STATUS_CREDIT.getSqlQuery(), String.class);
    }

    public static int getCreditRowsCountFromDB() {
        return Integer.parseInt(getJdbcTemplate()
                .queryForObject(
                        SqlScripts.SELECT_CREDIT_ROWS_COUNT.getSqlQuery(),
                        String.class));
    }

    public static int getDebitRowsCountFromDB() {
        return Integer.parseInt(getJdbcTemplate()
                .queryForObject(
                        SqlScripts.SELECT_DEBIT_ROWS_COUNT.getSqlQuery(),
                        String.class));
    }

    public static void deleteRowsFromMysql() {
        getJdbcTemplate().execute(SqlScripts.DELETE_ROWS_MYSQL1.getSqlQuery());
        getJdbcTemplate().execute(SqlScripts.DELETE_ROWS_MYSQL2.getSqlQuery());
        getJdbcTemplate().execute(SqlScripts.DELETE_ROWS_MYSQL3.getSqlQuery());
    }

    public static void deleteRowsFromPostgres() {
        getJdbcTemplate().execute(SqlScripts.DELETE_ROWS_POSTGRES.getSqlQuery());
    }

    @Getter
    public enum SqlScripts {

        SELECT_LAST_STATUS_DEBIT("SELECT pe.status FROM payment_entity pe ORDER BY pe.created DESC LIMIT 1;"),
        SELECT_LAST_STATUS_CREDIT("SELECT pe.status FROM credit_request_entity pe ORDER BY pe.created DESC LIMIT 1;"),
        SELECT_CREDIT_ROWS_COUNT("SELECT count(*) FROM credit_request_entity;"),
        DELETE_ROWS_POSTGRES("TRUNCATE credit_request_entity, payment_entity, order_entity;"),
        DELETE_ROWS_MYSQL1("TRUNCATE credit_request_entity"),
        DELETE_ROWS_MYSQL2("TRUNCATE payment_entity"),
        DELETE_ROWS_MYSQL3("TRUNCATE order_entity;"),
        SELECT_DEBIT_ROWS_COUNT("SELECT count(*) FROM payment_entity;");

        private final String sqlQuery;

        SqlScripts(String sqlQuery) {
            this.sqlQuery = sqlQuery;
        }

    }

}
