package javabasic;

/**
 * Created by warn on 2/2/2016.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 * Utilities class for getting DB connection
 *
 * @author yklam2
 */
public class DB {
    private static final String JDBC_DRIVER = "org.postgresql.Driver";
    private static final String JDBC_URL = "jdbc:postgresql://bookstore.cwwood64onrg.us-west-2.rds.amazonaws.com:5432/book";
    private static final String JDBC_USER = "markwang";
    private static final String JDBC_PASS = "1212az1212az";

    private DB() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection db = DB.getConnection();
        PreparedStatement titlesQuery = db
                .prepareStatement("SELECT isbn, title, editionNumber, "
                        + "copyright, publisherID, imageFile, price "
                        + "FROM titles ORDER BY title");

        ResultSet results = titlesQuery.executeQuery();
        while (results.next()) {
            System.out.println(results.getString("isbn"));
        }

    }

}
