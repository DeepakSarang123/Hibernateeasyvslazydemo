package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			//create some courses
			Course tempCourse1 = new Course("Java Spring Complete",
					             tempInstructor);
			Course tempCourse2 = new Course("Angular Complete",
		             tempInstructor);
			Course tempCourse3 = new Course("Javascript Complete",
		             tempInstructor);
			
			//add courses to instructor
			tempCourse1.setInstructor(tempInstructor);
			tempCourse2.setInstructor(tempInstructor);
			tempCourse3.setInstructor(tempInstructor);
			
			//save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
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
