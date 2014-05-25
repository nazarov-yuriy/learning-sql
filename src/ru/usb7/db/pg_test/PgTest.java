package ru.usb7.db.pg_test;

import java.sql.*;

/**
 * Created by firefish on 5/25/14.
 */
public class PgTest {

    private static void checkDriver() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    private static String repeateString(String str, int count) {
        return new String(new char[count]).replace("\0", str);
    }

    private static void generateCustomersV4(Connection connection, int count) throws SQLException {
        connection.setAutoCommit(false);

        int bunches = count / 100;
        int modulo = count % 100;

        String repeated = repeateString("(?, ?),", 99) + "(?, ?)";
        String query = "insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values " + repeated + ";";
        PreparedStatement pst = connection.prepareStatement(query);

        for (int j = 0; j < bunches; j++) {
            for (int i = 0; i < 100; i++) {
                Customer customer = Customer.randomCustomer();
                pst.setString(2 * i + 1, customer.firstName);
                pst.setString(2 * i + 2, customer.lastName);
            }
            pst.addBatch();
            if (j % 100 == 0)
                pst.executeBatch();
        }
        pst.executeBatch();

        if (modulo > 0) {
            repeated = "(?, ?)";
            if (modulo - 1 > 0)
                repeated = repeateString("(?, ?),", modulo - 1) + repeated;
            query = "insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values " + repeated + ";";
            pst = connection.prepareStatement(query);
            for (int i = 0; i < modulo; i++) {
                Customer customer = Customer.randomCustomer();
                pst.setString(2 * i + 1, customer.firstName);
                pst.setString(2 * i + 2, customer.lastName);
            }
            pst.execute();
        }

        connection.commit();
    }

    private static void generateAutoservices(Connection connection, int count) throws SQLException {
        connection.setAutoCommit(false);

        String query = "insert into AUTOSERVICE_AUTOSERVICE (NAME, ADDRESS) values (?, ?);";
        PreparedStatement pst = connection.prepareStatement(query);

        for (int i = 0; i < count; i++) {
            Autoservice autoservice = Autoservice.RandomAutoservice();
            pst.setString(1, autoservice.name);
            pst.setString(2, autoservice.address);
            pst.addBatch();
            if (i % 1000 == 0)
                pst.executeBatch();
        }
        pst.executeBatch();

        connection.commit();
    }

    public static void main(String[] argv) {
        try {
            checkDriver();
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }

        Connection connection = null;
        String username = "firefish";
        String password = "AsdfghjklLL1";

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/testjava", username, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            try {
                long startTime;
                long estimatedTime;

                startTime = System.nanoTime();
                generateCustomersV4(connection, 100);
                estimatedTime = System.nanoTime() - startTime;
                System.out.format("V4 done in %d ms.%n", estimatedTime / 1000000);

                generateAutoservices(connection, 10);

                connection.close();
            } catch (SQLException e) {
                System.out.println("Query Failed! Check output console");
                e.printStackTrace();
                return;
            }
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
