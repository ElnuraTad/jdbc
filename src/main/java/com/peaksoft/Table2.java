package com.peaksoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Table2 {
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
                sql = "CREATE TABLE COUNTRIES " +
                        "(id INT PRIMARY KEY, " +
                        " country_name VARCHAR(255)," +
                        "population INT NOT NULL," +
                        "language VARCHAR(255)," +
                        "city_id int REFERENCES cities(id))";
            }
            stmt.executeUpdate(sql);
            System.out.println("Create table in given database...");
        } catch(SQLException e){
            e.printStackTrace();
        }

        return conn;
    }
    public static void addCountries(int id, String country_name, double population, String language, int city_id) {
        String SQL = "insert into countries (id, country_name, population, language, city_id ) values (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, country_name);
            statement.setDouble(3, population);
            statement.setString(4, language);
            statement.setInt(5, city_id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void printCountries() {
        String SQL = "SELECT * FROM countries";
        List<Country> countryList = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setCountryName(rs.getString("country_name"));
                country.setPopulation(rs.getDouble("population"));
                country.setLanguage(rs.getString("language"));
                country.setCityId(rs.getInt("city_id"));
                countryList.add(country);
                System.out.println(countryList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
