package de.frankfurtuas.cloud.webshop.ordermanagement.service;

import de.frankfurtuas.cloud.webshop.inventorymanagement.service.InventoryService;
import de.frankfurtuas.cloud.webshop.mailmanagement.EmailNotificationService;
import de.frankfurtuas.cloud.webshop.ordermanagement.dto.OrderDTO;
import de.frankfurtuas.cloud.webshop.ordermanagement.dto.OrderItemDTO;
import de.frankfurtuas.cloud.webshop.ordermanagement.mapper.OrderMapper;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.Order;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderItem;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderStatus;
import de.frankfurtuas.cloud.webshop.ordermanagement.repository.OrderRepository;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import de.frankfurtuas.cloud.webshop.productmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final EmailNotificationService emailNotificationService;

    private final InventoryService inventoryService;

    public OrderService(
            OrderRepository orderRepository,
            InventoryService inventoryService,
            EmailNotificationService emailNotificationService,
            ProductRepository productRepository) {

        this.orderRepository = orderRepository;
        this.emailNotificationService = emailNotificationService;
        this.productRepository = productRepository;
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

    public OrderDTO placeOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .customerName(orderDTO.getCustomerName())
                .customerEmail(orderDTO.getCustomerEmail())
                .phoneNumber(orderDTO.getPhoneNumber())
                .shippingAddress(orderDTO.getShippingAddress())
                .paymentMethod(orderDTO.getPaymentMethod())
                .totalAmount(orderDTO.getTotalAmount())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            // Produkt aus der DB laden
            Product product = productRepository.findByName(itemDTO.getProductName());

            // OrderItem erstellen
            OrderItem orderItem = OrderItem.builder()
                    .order(order) // Setze die Order-Referenz
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .totalPriceForItem(itemDTO.getTotalPriceForItem())
                    .build();

            orderItems.add(orderItem);
        }
        // Setze die Liste in der Order
        order.setItems(orderItems);

        // Speichern der Order (durch `CascadeType.ALL` werden auch die Items gespeichert)
        orderRepository.save(order);
        return OrderMapper.toOrderDTO(order);
    }

    public OrderDTO updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        // If order is shipped, reduce stock for each product in the order
        if (status == OrderStatus.SHIPPED) {
            for (OrderItem item : order.getItems()) {
                inventoryService.updateQuantityByProductId(item.getProduct().getId(), item.getQuantity());
            }
        }

        emailNotificationService.sendOrderStatusEmail(order);

        order.setStatus(status);
        orderRepository.save(order);

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
