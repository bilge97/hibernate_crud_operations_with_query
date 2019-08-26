package com.example.hibernate_ud1.crud;

import com.example.hibernate_ud1.models.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;

public class QueryStudent {
    public static void main(String[] args){

        //create Session Factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create Session
        Session session = factory.getCurrentSession();

        try{

            //start a transaction
            session.beginTransaction();

            //query students
            Criteria cr = session.createCriteria(Student.class);
            List<Student> studentList = cr.list();

            //query students: firstname like Merv
            cr = session.createCriteria(Student.class);
            cr.add(Restrictions.like("firstName", "Merv%"));
            List<Student> studentListMerv = cr.list();

            //query student: firstname Bilge or firstname Brk
            Criterion bilge = Restrictions.like("firstName" , "Bilg%");
            Criterion brk = Restrictions.like("firstName" , "Brk%");

            //to get records matching with OR conditions

            LogicalExpression orExp = Restrictions.or(bilge , brk);
            cr.add(orExp);
            List<Student> studentListOr = cr.list();

            cr=session.createCriteria(Student.class);

            //with query
            String hql = "FROM Student";
            Query query = session.createQuery(hql , Student.class);
            List<Student> students = query.getResultList();



            //display students
            for(Object tempStudent : students)
            {
                System.out.println(tempStudent);
            }

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done.");
        }
        finally {
            factory.close();
        }




    }
}
