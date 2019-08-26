package com.example.hibernate_ud1.crud;

import com.example.hibernate_ud1.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudent {

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
            Student student1 = new Student("Bilge" , "Kurtoglu" , "bilge@gmail.com");
            Student student2 = new Student("Merve" , "Krtgl" , "merve@gmail.com");
            Student student3 = new Student("Brk" , "Kurtoglu" , "brk@gmail.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving student");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        }
        finally {
            factory.close();
        }




    }
}
