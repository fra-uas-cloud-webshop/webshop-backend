package de.frankfurtuas.cloud.webshop.ordermanagement.service;

import de.frankfurtuas.cloud.webshop.mailmanagement.EmailNotificationService;
import de.frankfurtuas.cloud.webshop.ordermanagement.dto.OrderDTO;
import de.frankfurtuas.cloud.webshop.ordermanagement.mapper.OrderMapper;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.Order;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderStatus;
import de.frankfurtuas.cloud.webshop.ordermanagement.repository.OrderItemRepository;
import de.frankfurtuas.cloud.webshop.ordermanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final EmailNotificationService emailNotificationService;

    public OrderService(
            OrderRepository orderRepository,
            EmailNotificationService emailNotificationService,
            OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.emailNotificationService = emailNotificationService;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return OrderMapper.toOrderDTOList(orders);
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        return Optional.ofNullable(OrderMapper.toOrderDTO(order));
    }

    public List<OrderDTO> getOrdersByCustomerEmail(String customerEmail) {
        List<Order> orders = orderRepository.findByCustomerEmailEqualsIgnoreCase(customerEmail);
        return OrderMapper.toOrderDTOList(orders);
    }

    public OrderDTO placeOrder(Order order) {
        System.out.println("OrderService.placeOrder");
        System.out.println(order.getId());
        System.out.println(order.getCustomerName());
        System.out.println(order.getCustomerEmail());
        System.out.println(order.getPaymentMethod());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        orderItemRepository.saveAll(order.getItems());
        orderRepository.save(order);
        return OrderMapper.toOrderDTO(order);
    }

    public OrderDTO updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        emailNotificationService.sendOrderStatusEmail(order);
        return OrderMapper.toOrderDTO(order);
    }

    public OrderDTO cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        emailNotificationService.sendOrderStatusEmail(order);
        return OrderMapper.toOrderDTO(order);
    }
}
