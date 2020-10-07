package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			
			//create the objects
			Instructor tempInstructor = new
					    Instructor("Manoj","Sarang","Manoj@gmail.com");
			
			InstructorDetail tempInstructorDetail = new
					    InstructorDetail("https://www.youtube1.com","Coding");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start transaction
			session.beginTransaction();
			
			//save objects
			System.out.println("Saving instructor:" + tempInstructor);
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			
			
			
			
		}finally {
			//add  clean up code
			session.close();
			factory.close();
		}
	}

}
