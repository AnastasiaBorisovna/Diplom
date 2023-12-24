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

    @Getter
    public enum SqlScripts {

        SELECT_LAST_STATUS_DEBIT("SELECT pe.status FROM payment_entity pe ORDER BY pe.created DESC LIMIT 1;"),
        SELECT_LAST_STATUS_CREDIT("SELECT pe.status FROM credit_request_entity pe ORDER BY pe.created DESC LIMIT 1;"),
        SELECT_CREDIT_ROWS_COUNT("SELECT count(*) FROM credit_request_entity;"),
        SELECT_DEBIT_ROWS_COUNT("SELECT count(*) FROM payment_entity;");

        private final String sqlQuery;

        SqlScripts(String sqlQuery) {
            this.sqlQuery = sqlQuery;
        }

    }

}
