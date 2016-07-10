package com.roshnee.util;

import java.io.*;

import java.sql.*;

import java.util.Properties;

public class DbConnectionUtil {

	static String JDBC_DRIVER = null; // prop.getProperty("jdbcDriver");
	static String DB_URL = null; // prop.getProperty("dbUrl");

	// Database credentials
	static String USER = null; // prop.getProperty("username");
	static String PASS = null; // prop.getProperty("password");

	private static void readDbConnectionInfo() {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("config/db.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JDBC_DRIVER = prop.getProperty("jdbcDriver");
		DB_URL = prop.getProperty("dbUrl");
		USER = prop.getProperty("dbUsername");
		PASS = prop.getProperty("dbPassword");

	}

	public static Connection getConnection() {
		readDbConnectionInfo();
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// STEP 3: Open a connection
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
	}

}
