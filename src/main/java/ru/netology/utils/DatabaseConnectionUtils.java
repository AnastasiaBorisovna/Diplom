package ru.netology.utils;

import lombok.Getter;
import org.aeonbits.owner.ConfigCache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DatabaseConnectionUtils {

    private static final Configuration configuration = ConfigCache.getOrCreate(Configuration.class);
    static String driver;
    static String url;

    static final String PASSWORD = configuration.databasePassword();
    static final String USERNAME = configuration.databaseUsername();

    @Getter
    static final String database = System.getProperty("database");

    static {
        if (database.equalsIgnoreCase("mysql")) {
            driver = configuration.mysqlDriver();
            url = configuration.mysqlUrl();
        } else {
            driver = configuration.postgresqlDriver();
            url = configuration.postgresqlUrl();
        }
    }

    private static DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(mysqlDataSource());
    }

}
