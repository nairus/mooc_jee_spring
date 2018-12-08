package dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import flights.Airline;

public interface AirlineRepository extends Repository<Airline, Integer> {

    // find airline by ID
    Airline findById(Integer id);

    // find all in a page
    Page<Airline> findAll(Pageable pageable);

    // find all by country
    Page<Airline> findByCountry(String country, Pageable pageable);

    // find all existing countries
    @Query("SELECT DISTINCT(al.country) FROM Airline al ORDER BY al.country")
    List<String> selectDistinctCountries();

}
