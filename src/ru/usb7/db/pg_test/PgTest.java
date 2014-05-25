package ru.usb7.db.pg_test;

import com.sun.xml.internal.ws.util.StringUtils;

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

    //stupid realization
    private static void generateCustomersV0(Connection connection, int count) throws SQLException {
        for (int i = 0; i < count; i++) {
            Statement st = connection.createStatement();
            st.execute("insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values ('" + NameGenerator.nextFirstName() + "', '" + NameGenerator.nextLastName() + "'');");
        }
    }

    //sql injection
    private static void generateCustomersV1(Connection connection, int count) throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < count; i++) {
            Statement st = connection.createStatement();
            st.execute("insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values ('" + NameGenerator.nextFirstName() + "', '" + NameGenerator.nextLastName() + "');");
        }
        connection.commit();
    }

    //without sql injections
    private static void generateCustomersV2(Connection connection, int count) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement pst = connection.prepareStatement("insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values (?, ?);");
        for (int i = 0; i < count; i++) {
            pst.setString(1, NameGenerator.nextFirstName());
            pst.setString(2, NameGenerator.nextLastName());
            pst.execute();
        }
        connection.commit();
    }

    //batch
    private static void generateCustomersV3(Connection connection, int count) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement pst = connection.prepareStatement("insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values (?, ?);");
        for (int i = 0; i < count; i++) {
            pst.setString(1, NameGenerator.nextFirstName());
            pst.setString(2, NameGenerator.nextLastName());
            pst.addBatch();
            if (0 == i % 100)
                pst.executeBatch();
        }
        pst.executeBatch();
        connection.commit();
    }

    //many values in a single VALUES
    private static void generateCustomersV4(Connection connection, int count) throws SQLException {
        connection.setAutoCommit(false);

        int bunches = count / 100;
        int modulo = count % 100;

        String repeated = repeateString("(?, ?),", 99) + "(?, ?)";
        String query = "insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values " + repeated + ";";
        PreparedStatement pst = connection.prepareStatement(query);

        for (int j = 0; j < bunches; j++) {
            for (int i = 0; i < 100; i++) {
                pst.setString(2 * i + 1, NameGenerator.nextFirstName());
                pst.setString(2 * i + 2, NameGenerator.nextLastName());
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
                pst.setString(2 * i + 1, NameGenerator.nextFirstName());
                pst.setString(2 * i + 2, NameGenerator.nextLastName());
            }
            pst.execute();
        }

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
                generateCustomersV1(connection, 100000);
                estimatedTime = System.nanoTime() - startTime;
                System.out.format("V1 done in %d ms.%n", estimatedTime / 1000000);

                startTime = System.nanoTime();
                generateCustomersV2(connection, 100000);
                estimatedTime = System.nanoTime() - startTime;
                System.out.format("V2 done in %d ms.%n", estimatedTime / 1000000);

                startTime = System.nanoTime();
                generateCustomersV3(connection, 100000);
                estimatedTime = System.nanoTime() - startTime;
                System.out.format("V3 done in %d ms.%n", estimatedTime / 1000000);

                startTime = System.nanoTime();
                generateCustomersV4(connection, 100000);
                estimatedTime = System.nanoTime() - startTime;
                System.out.format("V4 done in %d ms.%n", estimatedTime / 1000000);

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
