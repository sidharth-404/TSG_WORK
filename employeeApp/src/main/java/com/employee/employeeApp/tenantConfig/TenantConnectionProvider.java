package com.employee.employeeApp.tenantConfig;


import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//@Component
//public  class TenantConnectionProvider implements MultiTenantConnectionProvider {
//
//    @Autowired
//    private DataSource dataSource;
//
//
//    @Override
//    public Connection getAnyConnection() throws SQLException{
//        return dataSource.getConnection();
//    }
//
//
//
//    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
//        if (connection != null) {
//            connection.setSchema("public"); // Reset schema to default
//            connection.close();
//        }
//    }
//
//    @Override
//    public void releaseAnyConnection(Connection connection) throws SQLException {
//        if (connection != null) {
//        connection.close();
//            }
//    }
//
//    @Override
//    public Connection getConnection(Object tenantIdentifier) throws SQLException {
//        if (tenantIdentifier == null || tenantIdentifier.toString().isEmpty()) {
//            throw new IllegalArgumentException("Tenant identifier cannot be null or empty");
//        }
//        Connection connection = dataSource.getConnection();
//
//        try (Statement stmt = connection.createStatement()) {
//            stmt.execute("CREATE SCHEMA IF NOT EXISTS " + tenantIdentifier.toString());
//            // Create table if it doesn't exist
//            stmt.execute("CREATE TABLE IF NOT EXISTS " + tenantIdentifier.toString() + ".employee (" +
//                    "id SERIAL PRIMARY KEY, " +
//                    "firstName VARCHAR(255), " +
//                    "lastname VARCHAR(255), " +
//                    "salary DOUBLE PRECISION" +
//                    ");");
//        }
//        connection.setSchema(tenantIdentifier.toString());  // Set schema for the specific tenant
//        return connection;
//    }
//
//    @Override
//    public void releaseConnection(Object tenantIdentifier, Connection connection) throws SQLException {
//
//    }
//
//
//    @Override
//    public boolean supportsAggressiveRelease() {
//        return true;
//    }
//
//    @Override
//    public boolean isUnwrappableAs(Class unwrapType) {
//        return false;
//    }
//
//    @Override
//    public <T> T unwrap(Class<T> unwrapType) {
//        return null;
//    }
//}


@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider<String> {

    @Autowired
    private DataSource dataSource;


    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();

    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection=dataSource.getConnection();
        String tenantType=TenantIdentifierResolver.getTenantType();
        if ("dedicated".equals(tenantType)) {
             //Switch to the tenant's dedicated schema
            connection.createStatement().execute("CREATE SCHEMA IF NOT EXISTS " + tenantIdentifier);
            connection.createStatement().execute("SET SCHEMA '" + tenantIdentifier + "'");
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS " + tenantIdentifier + ".customer (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "tenant_id VARCHAR(255))");

        } else {
            // Use the shared schema
            connection.createStatement().execute("CREATE SCHEMA IF NOT EXISTS shared");
            connection.createStatement().execute("SET SCHEMA 'shared'");
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS shared.customer (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "tenant_id VARCHAR(255))");


        }

        return connection;


    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {

        connection.close();

    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }


}
