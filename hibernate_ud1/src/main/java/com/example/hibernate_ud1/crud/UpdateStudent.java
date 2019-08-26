package com.example.hibernate_ud1.crud;

import com.example.hibernate_ud1.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudent {

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

            Student myStudent = session.get(Student.class, studentId);//for primary key

            System.out.println("Updating student..");

            myStudent.setFirstName("updatedname");

            //commit transaction
            session.getTransaction().commit();

            //NEW UPDATE CODES
            session = factory.openSession();
            session.beginTransaction();

            //update email for all students
            System.out.println("Update email for all students");
            session.createSQLQuery("update Student set email= 'updatedemail@gmail.com'")
                    .executeUpdate();

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        } finally {
            factory.close();
        }


    }
}
