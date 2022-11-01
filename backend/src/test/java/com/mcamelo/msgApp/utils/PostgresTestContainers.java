package com.mcamelo.msgApp.utils;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.concurrent.atomic.AtomicBoolean;

public class PostgresTestContainers implements BeforeAllCallback {

    private static AtomicBoolean containerStarted = new AtomicBoolean(false);

    private static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres")
            .withDatabaseName("msgAppdb")
            .withUsername("postgres")
            .withPassword("postgres");

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if(!containerStarted.get()){
            postgres.start();

            System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
            System.setProperty("spring.datasource.username", postgres.getUsername());
            System.setProperty("spring.datasource.password" , postgres.getPassword());

//            var containerDelegate = new JdbcDatabaseDelegate(postgres, "");
//
//            ScriptUtils.runInitScript(containerDelegate, "file:src/main/resources/schema.sql");
            containerStarted.set(true);
        }
    }
}
