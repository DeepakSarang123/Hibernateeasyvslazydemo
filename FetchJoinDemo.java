package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			Query<Instructor> query = 
					session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId",
					Instructor.class);
			
			//Set parameter on query
			query.setParameter("theInstructorId",theId );
			
			//execute query and get Instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luv2code: Instructor is:" + tempInstructor);
			
			//Hibernate query with HQL
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("luv2code: Done!");
			
			
			
		}finally {
			//add  clean up code
			session.close();
			factory.close();
		}
	}

}
