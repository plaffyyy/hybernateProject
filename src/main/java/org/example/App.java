package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("Cascading test", 30);
            Item item = new Item("Cascading item", person);
            person.setItems(new ArrayList<>());
            person.getItems().add(item);

            session.persist( person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
