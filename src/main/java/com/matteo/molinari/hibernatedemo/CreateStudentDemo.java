package com.matteo.molinari.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.matteo.molinari.hibernatedemo.entity.Instructor;
import com.matteo.molinari.hibernatedemo.entity.InstructorDetail;

public class CreateStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating new Instructor object...");
			Instructor instructor = new Instructor("Paul", "Laup","lauppaul@test.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://youtube", "Coding");
			
			instructor.setDetail(instructorDetail);
			
			session.beginTransaction();
			System.out.println("Saving instructor: " + instructor);
			session.save(instructor);
			session.getTransaction().commit();
			System.out.println("Done.");
		} finally {
			factory.close();
		}
	}

}
