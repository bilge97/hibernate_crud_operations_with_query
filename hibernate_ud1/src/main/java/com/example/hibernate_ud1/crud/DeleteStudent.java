package com.example.hibernate_ud1.crud;

import com.example.hibernate_ud1.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudent {

    public static void main(String[] args) {

        //create Session Factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try {

            int studentId = 1;

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id : " + studentId);

            Student myStudent = session.get(Student.class, studentId);

            //delete student
//            System.out.println("Deleting student : " + myStudent);
//            session.delete(myStudent);

            //delete student query id:2
            System.out.println("Deleting query id=2");
            session.createSQLQuery("DELETE FROM Student WHERE id=2").executeUpdate();


            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            factory.close();
        }


    }
}
