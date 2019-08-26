package com.example.hibernate_ud1.crud;

import com.example.hibernate_ud1.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudent {

    public static void main(String[] args){

        //create Session Factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try{

            //create Student object
            System.out.println("Creating new student object..");
            Student student = new Student("Daffy" , "Duck" , "daffy@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving student");
            System.out.println(student);
            session.save(student);

            //commit transaction
            session.getTransaction().commit();

            //find out the student's id: primary key
            System.out.println("Saved student. Generated id: " + student.getId());

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id : " + student.getId());

            Student myStduent = session.get(Student.class , student.getId());//for primary key

            System.out.println("Get completed : " + student);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        }
        finally {
            factory.close();
        }




    }
}
