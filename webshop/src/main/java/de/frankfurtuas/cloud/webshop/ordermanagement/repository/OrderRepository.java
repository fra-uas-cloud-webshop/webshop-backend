package de.frankfurtuas.cloud.webshop.ordermanagement.repository;

import de.frankfurtuas.cloud.webshop.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderRepository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerEmailEqualsIgnoreCase(String customerEmail);
}
