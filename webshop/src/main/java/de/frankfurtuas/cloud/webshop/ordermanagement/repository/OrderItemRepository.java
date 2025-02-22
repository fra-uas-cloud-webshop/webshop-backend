package de.frankfurtuas.cloud.webshop.ordermanagement.repository;

import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
