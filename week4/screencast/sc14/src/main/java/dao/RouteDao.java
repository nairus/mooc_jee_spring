package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import flights.Route;

// expose as component
@Component
public class RouteDao {

    // inject EntityManager
    @PersistenceContext
    private EntityManager em;

    public List<Route> findRoutesByCountries(String fromCountry, String toCountry) {
        // build a request an return routes
        List<Route> res = em.createQuery(
                "SELECT rt FROM Route as rt " + " JOIN FETCH rt.routeId.source as src "
                        + " JOIN FETCH rt.routeId.destination dst " + "WHERE src.country=:from AND dst.country=:to",
                Route.class).setParameter("from", fromCountry).setParameter("to", toCountry).getResultList();
        return res;
    }

}
