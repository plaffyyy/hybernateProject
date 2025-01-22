package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

//        session.beginTransaction();
//
//        Person person1 = new Person("Kostya", 19);
//        Person person2 = new Person("Kostya Zimin", 19);
//        Person person3 = new Person("Julia", 19);
//
//        session.save(person1);
//        session.save(person2);
//        session.save(person3);
//
//        session.getTransaction().commit();

        session.beginTransaction();

        Person person = session.get(Person.class, 2);
        person.setAge(29);

        session.getTransaction().commit();

        sessionFactory.close();

    }
}
