package com.roshnee.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.roshnee.bean.Student;
import com.roshnee.constants.Constants;
import com.roshnee.dao.StudentsDAO;
import com.roshnee.dao.StudentsEtlDAO;
import com.roshnee.impl.EtlImpl;
import com.roshnee.impl.HibernateCRUDImpl;
import com.roshnee.impl.MySQLCRUDImpl;




public class Launch {
	// DML = data manipulation language
	public static void main(String[] args) {
		StudentsDAO crudImpl = null;

		Scanner reader = new Scanner(System.in);
		System.out.println("Welcome to Student Records CRUD App.");
		System.out.println("Would you like to upload, download, or DML?");
		String userInput = reader.nextLine();
		userInput = userInput.toLowerCase().trim();
		
		if (userInput.equals(Constants.UPLOAD)) {
			System.out.println("What is the name of the file you wish to upload?");
		//  /Users/roshnee/Documents/workspace/studentCRUD/data/StudentRecordsToUpload.csv
			String filename = reader.nextLine();
			StudentsEtlDAO etlImpl = new EtlImpl();
			etlImpl.upload(filename);
			System.out.println("Your file upload was a success!");
		}
		
		if (userInput.equals(Constants.DOWNLOAD)) {
			System.out.println("What would you like to call the file?");
			String filename = reader.nextLine();
			StudentsEtlDAO etlImpl = new EtlImpl();
			etlImpl.download(filename);
			System.out.println("Your file download was a success!");
		}

		if (userInput.equals(Constants.DML)) {

			System.out.println("Would you like to run in Hibernate or JDBC?");
			String impl = reader.nextLine();
			impl = impl.toLowerCase().trim();

			if (impl.equals("hibernate")) {
				crudImpl = new HibernateCRUDImpl();
			} else {
				crudImpl = new MySQLCRUDImpl();
			}

			while (true) {
				System.out.println("What do you want to do?");
				String ops = reader.nextLine();
				ops = ops.trim().toLowerCase();

				if (ops.equals(Constants.CREATE)) {
					System.out.println("You want to create a record!");
					System.out
							.println("\nWhat is the first name of the student you want to create?");
					String firstName = reader.nextLine();
					System.out
							.println("What is the last name of the student you want to create?");
					String lastName = reader.nextLine();
					System.out
							.println("What is the gender of the student you want to create?");
					String gender = reader.nextLine();
					System.out
							.println("What is the phone number of the student you want to create?");
					String phoneNumber = reader.nextLine();
					System.out
							.println("What is the birth date of the student you want to create?");
					String dateOfBirth = reader.nextLine();
					System.out
							.println("What is the name of the mother of the student you want to create?");
					String momName = reader.nextLine();
					System.out
							.println("What is the name of the father of the student you want to create?");
					String dadName = reader.nextLine();
					System.out
							.println("What is the SSN of the student you want to create?");
					int ssn = reader.nextInt();

					Student newStudent = new Student();
					newStudent.setFirstName(firstName);
					newStudent.setLastName(lastName);
					newStudent.setGender(gender);
					newStudent.setPhoneNumber(phoneNumber);
					newStudent.setDateOfBirth(dateOfBirth);
					newStudent.setMomName(momName);
					newStudent.setDadName(dadName);
					newStudent.setSsn(ssn);

					int newStudentID = crudImpl.create(newStudent);

					newStudent.setStudentId(newStudentID);
					System.out.println(newStudent.toString());

				}

				else if (ops.equals(Constants.RETRIEVE)) {
					System.out.println("You want to retrieve a record!");
					System.out
							.println("\nWhat is the ID of the student you want to retrieve?");
					int sid = reader.nextInt();
					Student studentRef = new Student();
					studentRef.setStudentId(sid);
					Student student = crudImpl.retrieve(studentRef);
					System.out.println(student.toString());
				} else if (ops.equals(Constants.UPDATE)) {
					Student studentUpdate = new Student();
					System.out.println("You want to update a record!");
					System.out
							.println("\nWhat is the ID of the student you wish to update?");
					int sid = Integer.parseInt(reader.nextLine());
					System.out
							.println("What is the property of the student you wish to update?");
					String property = reader.nextLine();
					property = property.toLowerCase().trim();
					property = property.replace(" ", "");

					System.out.println("What would you like to change the "
							+ property + " to?");
					String newProperty = reader.nextLine();
					newProperty = newProperty.replace("'", "''");

					if (property.equals(Constants.SSN)) {
						studentUpdate.setSsn(Integer.parseInt(newProperty));
					} else if (property.equals(Constants.FIRST_NAME)) {
						studentUpdate.setFirstName(newProperty);
					} else if (property.equals(Constants.LAST_NAME)) {
						studentUpdate.setLastName(newProperty);
					} else if (property.equals(Constants.GENDER)) {
						studentUpdate.setGender(newProperty);
					} else if (property.equals(Constants.PHONE_NUMBER)) {
						studentUpdate.setPhoneNumber(newProperty);
					} else if (property.equals(Constants.DATE_OF_BIRTH)) {
						studentUpdate.setDateOfBirth(newProperty);
					} else if (property.equals(Constants.MOM_NAME)) {
						studentUpdate.setMomName(newProperty);
					} else if (property.equals(Constants.DAD_NAME)) {
						studentUpdate.setDadName(newProperty);
					}

					studentUpdate.setStudentId(sid);

					crudImpl.update(studentUpdate, property);
					System.out.println("Student no." + sid + " updated!");
				} 
				
				else if (ops.equals(Constants.RETRIEVE_ALL)) {
					System.out.println("You want to retrieve all the records in students!");
					List students = new ArrayList();
					students = crudImpl.retrieveAll();
					System.out.println(students.toString());
				}
				
				
				else if (ops.equals(Constants.DELETE)) {
					System.out.println("You want to delete a record!");
					System.out.println("\nWhat is the ID of the student you wish to delete?");
					int sid = reader.nextInt();
					Student studentDel = new Student();
					studentDel.setStudentId(sid);
					crudImpl.delete(studentDel);
					System.out.println("Student ID no. " + sid + " deleted!");
				} else if (ops.equals(Constants.EXIT)) {
					System.exit(0);
				} else {
					System.out
							.println("The command you have entered is invalid.");
				}
			}
		}
	}
}
