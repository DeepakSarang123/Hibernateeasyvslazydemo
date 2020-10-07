package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create Sessionfactory and Session 
		SessionFactory factory = new Configuration().
				                 configure("hibernate.cfg.xml").
				                 addAnnotatedClass(Instructor.class).
				                 addAnnotatedClass(InstructorDetail.class).
				                 buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {

			//start transaction
			session.beginTransaction();
			
			//get the instructor detail object
			int theId = 2;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail:"  + tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associated instructor:"  + tempInstructorDetail.getInstructor());
			
			//remove object ref to instructor
			//break bi-directionallink
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			
			//delete instructordetail
			System.out.println("Deleting tempInstructorDetail:" + tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
			//commit transaction
			session.getTransaction().commit();
	
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			//handling leak issue
			session.close();
			factory.close();
		}
	}

}
