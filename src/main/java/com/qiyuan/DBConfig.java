package com.qiyuan;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.validator.constraints.NotEmpty;
import org.mariadb.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.validation.Valid;

/**
 * Created by duxiutao duxiutao@gmail.com on 16/6/20.
 */
public class DBConfig {

    @Autowired
    @Bean
    public DataSource dataSource(DatabaseConfig config) {
        // Provide defaults
        HikariConfig hikari = new HikariConfig();
        hikari.setDriverClassName(Driver.class.getName());
        hikari.setMinimumIdle(16);
        hikari.setMaximumPoolSize(256);
        hikari.setConnectionTestQuery("SELECT 1");

        hikari.setJdbcUrl(getUrl(config.host, config.database));
        hikari.setUsername(config.username);
        hikari.setPassword(config.password);
        return new HikariDataSource(hikari);
    }

    @Component
    @ConfigurationProperties(prefix = "jdbc")
    public static class DatabaseConfig {
        @Valid
        @NotEmpty
        private String host;

        @Valid
        @NotEmpty
        private String database;

        @Valid
        @NotEmpty
        private String username;

        @Valid
        private String password;

        @Valid
        private Boolean showSql = false;


        public void setHost(String host) {
            this.host = host;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setShowSql(Boolean showSql) {
            this.showSql = showSql;
        }
    }

    private static String getUrl(String host, String database) {
        String url = "jdbc:mysql://${host}/${database}?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
        return url.replace("${host}", host).replace("${database}", database);
    }
}
