package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Movie movie = session.get(Movie.class, 6);
            System.out.println("Get movie");

            movie.getActors().forEach(e -> System.out.println(e.getName()));
//            Hibernate.initialize(movie.getActors()); подгружаем связанные ленивые сущности

            session.getTransaction().commit();

            //открываем сессию и транзакцию еще раз ( в любом месте в коде)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Внутри второй транзакции");

            movie = session.merge(movie);

            Hibernate.initialize(movie.getActors());

            session.getTransaction().commit();

            System.out.println("Вне второй сессии");

            movie.getActors().forEach(e -> System.out.println(e.getName()));


        } finally {
            sessionFactory.close();
        }

    }
}
