import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import library.Author;
import library.Book;

public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String argv[]) {
        System.out.println("Running App ...");

        log.debug("Create persistence manager");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
        EntityManager em = emf.createEntityManager();

        log.debug("Create entities");
        Author lionel = new Author();
        lionel.setFirstName("Lionel");
        lionel.setLastName("Seinturier");

        Author renaud = new Author();
        renaud.setFirstName("Renaud");
        renaud.setLastName("Pawlak");

        Author jeanPhilippe = new Author();
        jeanPhilippe.setFirstName("Jean Philippe");
        jeanPhilippe.setLastName("Retaillé");

        Book book = new Book();
        book.setIsbn("945-1-212-12405-9");
        book.setTitle("Programmation orienté aspect pour Java / J2EE");
        book.setPrice(45.0);
        book.setAuthors(Arrays.asList(lionel, renaud, jeanPhilippe));

        log.debug("Persist entities in a transaction");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(lionel);
        em.persist(renaud);
        em.persist(jeanPhilippe);
        em.persist(book);

        transaction.commit();

        log.debug("Close Entity Manager");
        em.close();
        emf.close();
    }
}
