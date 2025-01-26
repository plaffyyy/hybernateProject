package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
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

            Actor actor = session.get(Actor.class, 12);
            actor.getMovies().forEach(movie -> System.out.println(movie.getName()));

            Movie movieToRemove = actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor);



//            Movie movie = new Movie("ASdasdasd", 1984);
//            Actor actor1 = new Actor("si", 12);
//            Actor actor2 = new Actor("kostya", 19);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//
//            actor1.setMovies(new ArrayList<>(List.of(movie)));
//            actor2.setMovies(new ArrayList<>(List.of(movie)));
//
//            session.save(movie);
//
//            session.save(actor1);
//            session.save(actor2);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
