package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		
		//create Sessionfactory and Session 
		SessionFactory factory = new Configuration().
				                 configure("hibernate.cfg.xml").
				                 addAnnotatedClass(Instructor.class).
				                 addAnnotatedClass(InstructorDetail.class).
				                 addAnnotatedClass(Course.class).
				                 buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			
			
			//start transaction
			session.beginTransaction();
			
			//get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			System.out.println("Instructor is:" + tempInstructor);
			
			//get Instructor Courses
			List<Course> courseList = tempInstructor.getCourses();
			System.out.println("Courses for our Instructor are:" + courseList);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
			
		}finally {
			//add  clean up code
			session.close();
			factory.close();
		}
	}

}
