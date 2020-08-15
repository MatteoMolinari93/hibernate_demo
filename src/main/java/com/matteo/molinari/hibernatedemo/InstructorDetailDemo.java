package com.matteo.molinari.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.matteo.molinari.hibernatedemo.entity.Instructor;
import com.matteo.molinari.hibernatedemo.entity.InstructorDetail;

public class InstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().
				configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			int id = 1;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			System.out.println("instructorDetail: " + instructorDetail);
			System.out.println("the associated instructor: " + instructorDetail.getInstructor());

			session.getTransaction().commit();
			System.out.println("Done.");
		} finally {
			factory.close();
		}
	}

}
