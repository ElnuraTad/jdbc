package com.peaksoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Table1 {
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
                sql = "CREATE TABLE CITIES " +
                        "(id serial PRIMARY KEY, " +
                        " city_name VARCHAR(255)," +
                        "mayor_id int REFERENCES mayors(id))";
            }
            stmt.executeUpdate(sql);
            System.out.println("Create table in given database...");
        } catch(SQLException e){
            e.printStackTrace();
        }

        return conn;
    }
    public static void addCities(int id, String city_name, int mayor_id) {
        String SQL = "insert into cities (id, city_name, mayor_id) values (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, city_name);
            statement.setInt(3, mayor_id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void printCities() {
        String SQL = "SELECT * FROM cities";
        List<City> cityList = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setCitiesName(rs.getString("city_name"));
                city.setId(rs.getInt("mayor_id"));
                cityList.add(city);
                System.out.println(cityList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
