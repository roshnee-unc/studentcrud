package com.roshnee.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.roshnee.bean.Student;
import com.roshnee.constants.Constants;
import com.roshnee.dao.StudentsDAO;
import com.roshnee.util.DbConnectionUtil;

public class MySQLCRUDImpl implements StudentsDAO {

	public int create(Student student) {
		Connection conn = null;
		Statement stmt = null;
		int id = 0;
		conn = DbConnectionUtil.getConnection();
		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String createStudentSQL = "INSERT INTO students (ssn, firstName, lastName, gender, phoneNumber, dateOfBirth, momName, dadName) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = null;

		try {
			statement = conn.prepareStatement(createStudentSQL);
			statement.setLong(1, student.getSsn());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setString(4, student.getGender());
			statement.setString(5, student.getPhoneNumber());
			statement.setString(6, student.getDateOfBirth());
			statement.setString(7, student.getMomName());
			statement.setString(8, student.getDadName());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rS = null;

		String getNewlyCreatedIDSQL = "SELECT * FROM students WHERE ssn="
				+ student.getSsn();
		try {
			rS = stmt.executeQuery(getNewlyCreatedIDSQL);
			while (rS.next()) {
				id = rS.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbConnectionUtil.closeConnection(conn, stmt, rS);
		return id;
	}

	public void delete(Student student) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = DbConnectionUtil.getConnection();
		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql;
		int studentID = student.getStudentId();
		sql = "DELETE FROM students WHERE id=" + studentID;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbConnectionUtil.closeConnection(conn, stmt, rs);
	}

	public Student retrieve(Student student) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = DbConnectionUtil.getConnection();
		String sql = "SELECT * FROM students WHERE id="
				+ student.getStudentId();

		System.out.println("Creating statement...");

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String gender = rs.getString("gender");
				String phoneNumber = rs.getString("phoneNumber");
				String dateOfBirth = rs.getString("dateOfBirth");
				String momName = rs.getString("momName");
				String dadName = rs.getString("dadName");
				int ssn = rs.getInt("ssn");

				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setGender(gender);
				student.setPhoneNumber(phoneNumber);
				student.setDateOfBirth(dateOfBirth);
				student.setMomName(momName);
				student.setDadName(dadName);
				student.setSsn(ssn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbConnectionUtil.closeConnection(conn, stmt, rs);
		return student;
	}

	public void update(Student student, String property) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		conn = DbConnectionUtil.getConnection();

		System.out.println("Creating statement...");
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String updateSQL;

		if (property.equals(Constants.SSN))
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getSsn() + "' WHERE id='"
					+ student.getStudentId() + "'";
		else if (property.equals(Constants.FIRST_NAME))
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getFirstName() + "' WHERE id='"
					+ student.getStudentId() + "'";
		else if (property.equals(Constants.LAST_NAME))
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getLastName() + "' WHERE id='"
					+ student.getStudentId() + "'";
		else if (property.equals(Constants.GENDER))
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getGender() + "' WHERE id='"
					+ student.getStudentId() + "'";
		else if (property.equals(Constants.PHONE_NUMBER))
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getPhoneNumber() + "' WHERE id='"
					+ student.getStudentId() + "'";
		else if (property.equals(Constants.DATE_OF_BIRTH))
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getDateOfBirth() + "' WHERE id='"
					+ student.getStudentId() + "'";
		else if (property.equals(Constants.MOM_NAME))
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getMomName() + "' WHERE id='"
					+ student.getStudentId() + "'";
		else
			updateSQL = "UPDATE students SET " + property + "= '"
					+ student.getDadName() + "' WHERE id='"
					+ student.getStudentId() + "'";
		try {
			stmt.executeUpdate(updateSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbConnectionUtil.closeConnection(conn, stmt, rs);
	}
	
	public List retrieveAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Student student = null;

		conn = DbConnectionUtil.getConnection();

		String query = "SELECT * FROM students";
		List students = new ArrayList();
		try {
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs = stmt.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (rs.next()) {
				student = new Student();
				student.setStudentId(rs.getInt("id"));
				student.setSsn(rs.getInt("SSN"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setGender(rs.getString("gender"));
				student.setPhoneNumber(rs.getString("phoneNumber"));
				student.setDateOfBirth(rs.getString("dateOfBirth"));
				student.setMomName(rs.getString("momName"));
				student.setDadName(rs.getString("dadName"));

				students.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbConnectionUtil.closeConnection(conn, stmt, rs);
		}
		return students;
	}
}
