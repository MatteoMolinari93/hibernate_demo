package com.matteo.molinari.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.matteo.molinari.hibernatedemo.entity.Course;
import com.matteo.molinari.hibernatedemo.entity.Instructor;
import com.matteo.molinari.hibernatedemo.entity.InstructorDetail;
import com.matteo.molinari.hibernatedemo.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			Course tempCourse = new Course("Spring Framework");
			tempCourse.addReview(new Review("Really nice!!"));
			tempCourse.addReview(new Review("Not bad."));
			tempCourse.addReview(new Review("Niiiiiiice!!"));
			
			System.out.println("Saving course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			session.save(tempCourse);

			session.getTransaction().commit();
			System.out.println("Done.");
		} finally {
			session.close();
			factory.close();
		}
	}

}
