package com.roshnee.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnTest {

	/**
	 * @param args
	 */
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "letmein";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM students";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         String firstName = rs.getString("firstName");
	         String lastName = rs.getString("lastName");
	         String gender = rs.getString("gender");
	         String phoneNumber = rs.getString("phoneNumber");
	         String dateOfBirth = rs.getString("dateOfBirth");
	         String momName = rs.getString("momName");
	         String dadName = rs.getString("dadName");
	         
	         //Display values
	         System.out.println("ID: " + id);
	         System.out.println("First Name: " + firstName);
	         System.out.println("Last Name: " + lastName);
	         System.out.println("Gender: " + gender);
	         System.out.println("Phone Number: " + phoneNumber);
	         System.out.println("Date of Birth: " + dateOfBirth);
	         System.out.println("Mom's Name: " + momName);
	         System.out.println("Dad's Name: " + dadName);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Bye!");
	}//end main

}
