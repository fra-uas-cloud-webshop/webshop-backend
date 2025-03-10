package de.frankfurtuas.cloud.webshop.ordermanagement.controller;

import de.frankfurtuas.cloud.webshop.common.controller.BaseController;
import de.frankfurtuas.cloud.webshop.ordermanagement.dto.OrderDTO;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderStatus;
import de.frankfurtuas.cloud.webshop.ordermanagement.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        Optional<OrderDTO> order = orderService.getOrderById(orderId);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerEmail(@RequestParam String email) {
        List<OrderDTO> orders = orderService.getOrdersByCustomerEmail(email);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    @PutMapping("/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@RequestParam Long orderId, @RequestBody OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    @PutMapping("/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@RequestParam Long orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }
}
