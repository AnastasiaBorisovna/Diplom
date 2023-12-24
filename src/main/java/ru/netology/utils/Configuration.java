package ru.netology.utils;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:configuration.properties"})
public interface Configuration extends Config {

    @Key("websiteUrl")
    String websiteUrl();

    @Key("mysqlUrl")
    String mysqlUrl();

    @Key("postgresqlUrl")
    String postgresqlUrl();

    @Key("databaseUsername")
    String databaseUsername();

    @Key("databasePassword")
    String databasePassword();

    @Key("goodCard")
    String goodCard();

    @Key("approvedStatus")
    String approvedStatus();

    @Key("badCard")
    String badCard();

    @Key("declinedStatus")
    String declinedStatus();

    @Key("mysqlDriver")
    @DefaultValue("com.mysql.cj.jdbc.Driver")
    String mysqlDriver();

    @Key("postgresqlDriver")
    @DefaultValue("org.postgresql.Driver")
    String postgresqlDriver();

}
