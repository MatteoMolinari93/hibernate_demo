package com.matteo.molinari.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.matteo.molinari.hibernatedemo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating new Student object...");
			Student student = new Student("Daffy", "Duck","daffy@test.com");
			
			session.beginTransaction();
			System.out.println("Saving the student...");
			System.out.println(student);

			session.save(student);
			session.getTransaction().commit();
			System.out.println("Saved. Generated id: " + student.getId());

			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Getting Student with id: " + student.getId());
			Student readStudent = session.get(Student.class, student.getId());
			System.out.println("Got: " + readStudent);

			session.getTransaction().commit();
			
			System.out.println("Done.");
		} finally {
			factory.close();
		}
	}

}
