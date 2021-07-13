package com.peaksoft;

import java.sql.*;

public class DB3 {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "20SF05215";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String sql;
            {
                sql = "CREATE TABLE MAYORS " +
                        "(id INT PRIMARY KEY, " +
                        " name VARCHAR(255)," +
                        "age INT NOT NULL," +
                        "mayor_city VARCHAR(255))";
            }
            stmt.executeUpdate(sql);
            System.out.println("Create table in given database...");
        } catch(SQLException e){
            e.printStackTrace();
        }

        return conn;
    }
    public static void addMayors(int id, String name, int age, String mayor_city) {
        String SQL = "insert into mayors (id, name, age, mayor_city) values (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setString(4, mayor_city);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void printMayors() {
        String SQL = "SELECT * FROM mayors";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " "
                        + rs.getString("name") + " "
                        + rs.getInt("age") + " "
                        + rs.getString("mayor_city"));
                System.out.println("***********************************");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


