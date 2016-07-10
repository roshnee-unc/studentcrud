package com.roshnee.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.roshnee.bean.Student;
import com.roshnee.dao.StudentsDAO;
import com.roshnee.util.HibernateUtil;

public class HibernateCRUDImpl implements StudentsDAO {
//Hibernate is an ORM (Object Relational Mapping) that maps java objects to the database objects
	public int create(Student student) {
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
		return 0;
	}

	public void delete(Student student) {
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Student deletedStudent = (Student) session.load(Student.class, new Integer(student.getStudentId()));
            session.delete(deletedStudent);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
	}

	public Student retrieve(Student student) {
        Student retrievedStudent = null;
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Student where studentId = :studentId";
            Query query = session.createQuery(queryString);
            query.setInteger("studentId", student.getStudentId());
            retrievedStudent = (Student) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return retrievedStudent;
   	}

	public void update(Student student, String property) {
		Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }	
	}

	public List retrieveAll() {
		return null;
	}

}
