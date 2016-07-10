package com.roshnee.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.roshnee.bean.Student;
import com.roshnee.constants.Constants;
import com.roshnee.dao.StudentsDAO;
import com.roshnee.dao.StudentsEtlDAO;

public class EtlImpl implements StudentsEtlDAO {

	public void download(String fileName) {
		List students = new ArrayList();
		StudentsDAO mySQLCRUDImpl = new MySQLCRUDImpl();
		students = mySQLCRUDImpl.retrieveAll();
		
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(Constants.FILE_HEADER.toString());
			
			//Add a new line separator after the header
			fileWriter.append(Constants.NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (int i = 0; i < students.size(); i++) {
				Student student = (Student) students.get(i);
				
				fileWriter.append(String.valueOf(student.getStudentId()));
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(String.valueOf(student.getSsn()));
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(student.getFirstName());
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(student.getLastName());
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(student.getGender());
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(student.getPhoneNumber());
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(student.getDateOfBirth());
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(student.getMomName());
				fileWriter.append(Constants.COMMA_DELIMITER);
				
				fileWriter.append(student.getDadName());
				fileWriter.append(Constants.NEW_LINE_SEPARATOR);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
		
	}

	public void upload(String fileName) {
		BufferedReader fileReader = null;
	    Student newStudent = null;
	    
        try {
        	StudentsDAO mySQLCRUDImpl = new MySQLCRUDImpl();
        	//Create a new list of student to be filled by CSV file data 
        	List students = new ArrayList();
        	
        	
            String line = "";
            
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            
            //Read the CSV file header to skip it
            fileReader.readLine();
            
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(Constants.COMMA_DELIMITER);
                if (tokens.length > 0) {
					newStudent = new Student(
							Integer.parseInt(tokens[Constants.STUDENT_SSN_IDX]), 
							tokens[Constants.STUDENT_FIRSTNAME_IDX], 
							tokens[Constants.STUDENT_LASTNAME_IDX], 
							tokens[Constants.STUDENT_GENDER_IDX], 
							tokens[Constants.STUDENT_PHONENUMBER_IDX], 
							tokens[Constants.STUDENT_DATEOFBIRTH_IDX], 
							tokens[Constants.STUDENT_MOMNAME_IDX], 
							tokens[Constants.STUDENT_DADNAME_IDX]);                                                                                        
					students.add(newStudent);
				}
                
            }
            //creating object to interface
            
            
            //Print the new student list
           for (int i = 0; i < students.size(); i++) {
        	   Student student = (Student) students.get(i);
        	   mySQLCRUDImpl.create(student);
			} 
        } 
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        


	}
		
}


