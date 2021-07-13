package com.peaksoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "20SF05215";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static int getCustomerCount() {
        String SQL = "select count(*) from customer";
        int count = 0;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static void addCustomer(int id, String first_name, String last_name, String city, String country, String phone) {
        String SQL = "insert into customer (id, first_name, last_name, city, country, phone) values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, first_name);
            statement.setString(3, last_name);
            statement.setString(4, city);
            statement.setString(5, country);
            statement.setString(6, phone);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void printCustomer() {
        String SQL = "SELECT * FROM customer";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " "
                        + rs.getString("first_name") + " "
                        + rs.getString("last_name") + " "
                        + rs.getString("city") + " "
                        + rs.getString("country") + " "
                        + rs.getString("phone"));
                System.out.println("***********************************");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}





