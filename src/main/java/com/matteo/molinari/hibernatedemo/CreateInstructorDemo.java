package com.matteo.molinari.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.matteo.molinari.hibernatedemo.entity.Student;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating new Student object...");
			Student student = new Student("Paul", "Laup","lauppaul@test.com");
			
			session.beginTransaction();
			System.out.println("Saving the student...");
			session.save(student);
			session.getTransaction().commit();
			System.out.println("Done.");
		} finally {
			factory.close();
		}
	}

}
