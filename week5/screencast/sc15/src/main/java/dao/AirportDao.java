package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import flights.Airport;

@Component
public class AirportDao {

    @PersistenceContext
    EntityManager em;

    // should add a transaction manager
    @Autowired
    PlatformTransactionManager txManager;

    public List<String> existingCountries() {
        return em.createQuery("Select distinct country From Airport " + "Order by country", String.class)
                .getResultList();
    }

    public List<Airport> byCountry(String country) {
        return em.createQuery("From Airport where country = :country " + "Order by city, iata", Airport.class)
                .setParameter("country", country).getResultList();
    }

    public Airport find(int id) {
        return em.find(Airport.class, id);
    }

    public void update(Airport airport) {
        TransactionStatus status = txManager
                .getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
        em.merge(airport);
        txManager.commit(status);
    }

}
