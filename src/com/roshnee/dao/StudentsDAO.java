package com.roshnee.dao;

import java.util.List;

import com.roshnee.bean.Student;
//DAO means Data Access Object. Allows you to map your objects to a database

public interface StudentsDAO {
	public int create(Student student);
	public Student retrieve(Student student);
	public List retrieveAll();
	public void update(Student student, String property);
	public void delete(Student student);
	
}
