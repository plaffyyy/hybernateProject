package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

            Person person = session.get(Person.class, 7);
            Item item = new Item("NewItem", person);

            //не порождает SQL, но необходимо, чтобы в кэше все было верно
            person.getItems().add(item);

            session.persist(item);

//            Person person = session.get(Person.class, 7);
//            System.out.println(person);
//            List<Item> items = person.getItems();
//            System.out.println(items);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
