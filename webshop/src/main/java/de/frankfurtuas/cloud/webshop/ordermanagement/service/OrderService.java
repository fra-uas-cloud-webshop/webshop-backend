package de.frankfurtuas.cloud.webshop.ordermanagement.service;

import de.frankfurtuas.cloud.webshop.inventorymanagement.service.InventoryService;
import de.frankfurtuas.cloud.webshop.mailmanagement.EmailNotificationService;
import de.frankfurtuas.cloud.webshop.ordermanagement.dto.OrderDTO;
import de.frankfurtuas.cloud.webshop.ordermanagement.mapper.OrderMapper;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.Order;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderItem;
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

    private final InventoryService inventoryService;

    public OrderService(
            OrderRepository orderRepository,
            InventoryService inventoryService,
            EmailNotificationService emailNotificationService,
            OrderItemRepository orderItemRepository) {

        this.orderRepository = orderRepository;
        this.emailNotificationService = emailNotificationService;
        this.orderItemRepository = orderItemRepository;
        this.inventoryService = inventoryService;
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

        // If order is shipped, reduce stock for each product in the order
        if (status == OrderStatus.SHIPPED) {
            for (OrderItem item : order.getItems()) {
                inventoryService.updateQuantityByProductId(item.getProduct().getId(), item.getQuantity());
            }
        }

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
