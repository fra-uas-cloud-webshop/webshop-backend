package de.frankfurtuas.cloud.webshop.ordermanagement.service;

import de.frankfurtuas.cloud.webshop.mailmanagement.EmailNotificationService;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.Order;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderStatus;
import de.frankfurtuas.cloud.webshop.ordermanagement.repository.OrderRepository;
import de.frankfurtuas.cloud.webshop.paymentmanagement.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final EmailNotificationService emailNotificationService;
    private final PaymentService paymentService;

    public OrderService(
            OrderRepository orderRepository,
            EmailNotificationService emailNotificationService,
            PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.emailNotificationService = emailNotificationService;
        this.paymentService = paymentService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByCustomerEmail(String customerEmail) {
        return orderRepository.findByCustomerEmailEqualsIgnoreCase(customerEmail);
    }

    public Order placeOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
