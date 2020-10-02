package com.matteo.molinari.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.matteo.molinari.hibernatedemo.entity.Course;
import com.matteo.molinari.hibernatedemo.entity.Instructor;
import com.matteo.molinari.hibernatedemo.entity.InstructorDetail;
import com.matteo.molinari.hibernatedemo.entity.Review;
import com.matteo.molinari.hibernatedemo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			Course tempCourse = new Course("Spring Framework");
			
			System.out.println("Saving course");
			System.out.println(tempCourse);
			session.save(tempCourse);
			
			Student student1 = new Student("Luke", "Skywalker", "luke.skywalker@theforce.com");
			Student student2 = new Student("Mary", "Public", "mary.public@yo.com");
			
			tempCourse.addStudent(student1);
			tempCourse.addStudent(student2);
			
			System.out.println("Saving students");
			session.save(student1);
			session.save(student2);
			System.out.println(tempCourse.getStudents());
			
			session.getTransaction().commit();
			System.out.println("Done.");
		} finally {
			session.close();
			factory.close();
		}
	}

}
