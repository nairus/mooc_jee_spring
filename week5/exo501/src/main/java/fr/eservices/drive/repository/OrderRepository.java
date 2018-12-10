package fr.eservices.drive.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.eservices.drive.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByCustomerId(String customerId);
}
