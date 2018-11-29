import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.AirlineCoverage;
import flights.Airline;

public class App {
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String argv[]) {
        System.out.println("Running App ...");

        log.debug("Create persistence manager");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
        EntityManager em = emf.createEntityManager();

        try {
            log.debug("Search for French Airlines");
            List<Airline> airlines = em.createQuery("FROM Airline al WHERE al.country = :country", Airline.class)
                    .setParameter("country", "France").getResultList();

            showAirlines(airlines);

            log.debug("Search for French Airlines going to Miami");
            airlines = em
                    .createQuery(
                            "SELECT distinct rt.airline FROM Route rt JOIN rt.airline as al "
                                    + " JOIN rt.destination dst" + " WHERE al.country = :country AND dst.city = :city",
                            Airline.class)
                    .setParameter("country", "France").setParameter("city", "Miami").getResultList();

            showAirlines(airlines);

            log.debug("Search Number of airlines from CDG");
            long count = em.createQuery(
                    "SELECT count(distinct rt.airline) FROM Route rt JOIN rt.source as ap " + " WHERE ap.iata = :code",
                    Long.class).setParameter("code", "CDG").getSingleResult();
            System.out.println(count + " airlines starts at CDG");

            log.debug("Search Best airlines with number of dest airports to USA from CDG, ordered by 'coverage'");
            List<AirlineCoverage> res = em
                    .createQuery("SELECT new data.AirlineCoverage( count(rt.destination), apD.country, al.name) "
                            + "FROM Route as rt " + "JOIN rt.destination as apD " + "JOIN rt.source as apS "
                            + "JOIN rt.airline as al " + "WHERE apD.country = :country " + "  AND apS.iata = :code "
                            + "GROUP BY apD.country, al.name " + "ORDER BY count(rt.destination) DESC",
                            AirlineCoverage.class)
                    .setParameter("country", "United States").setParameter("code", "CDG").getResultList();

            for (AirlineCoverage airlineCoverage : res) {
                System.out.println(String.format("%3d | %-20s | %-30s", airlineCoverage.getNbRoutes(),
                        airlineCoverage.getCountry(), airlineCoverage.getName()));
            }

        } finally {
            log.debug("Close Entity Manager");
            em.close();
            emf.close();
        }

    }

    private static void showAirlines(List<Airline> airlines) {
        System.out.println(String.format("%5s | %3s | %-30s", "Id", "ICAO", "Name"));

        for (Airline airline : airlines) {
            System.out
                    .println(String.format("%5s | %3s | %-30s", airline.getId(), airline.getIcao(), airline.getName()));
        }
    }
}