package com.roshnee.bean;

public class Student {
	private String firstName;
	private String lastName;
	private String gender;
	private String phoneNumber;
	private String dateOfBirth;
	private String momName;
	private String dadName;
	private int ssn;
	private int studentId;
	
	public Student(int ssn, String firstName, String lastName, String gender, String phoneNumber,
			String dateOfBirth, String momName, String dadName) {
		this.firstName = firstName;
		this.ssn = ssn;
		this.lastName = lastName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.momName = momName;
		this.dadName = dadName;
	}
	
	public Student() {
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public int getSsn() {
		return ssn;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMomName() {
		return momName;
	}
	public void setMomName(String momName) {
		this.momName = momName;
	}
	public String getDadName() {
		return dadName;
	}
	public void setDadName(String dadName) {
		this.dadName = dadName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + studentId;
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (studentId != other.studentId)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
	
	public String toString() {
		return "ID: " + studentId + ", SSN: " + ssn + ", First Name: " + firstName + ", Last Name: " + lastName
				+ ", Gender: " + gender + ", Phone Number: " + phoneNumber + ", Date of Birth: " + dateOfBirth +
				", Mom's Name: " + momName + ", Dad's Name: " + dadName;
		
	}	
	
}
