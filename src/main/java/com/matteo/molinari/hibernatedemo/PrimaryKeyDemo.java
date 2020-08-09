package com.matteo.molinari.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.matteo.molinari.hibernatedemo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating 3 new Student objects...");
			Student student1 = new Student("Lou", "Laup","Lou@test.com");
			Student student2 = new Student("Mary", "Jane","Mary@test.com");
			Student student3 = new Student("John", "WaLL","John@test.com");
			
			session.beginTransaction();
			System.out.println("Saving the students...");
			session.save(student1);
			session.save(student2);
			session.save(student3);

			session.getTransaction().commit();
			System.out.println("Done.");
		} finally {
			factory.close();
		}
	}

}
