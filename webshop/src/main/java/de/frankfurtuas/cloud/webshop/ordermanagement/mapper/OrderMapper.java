package de.frankfurtuas.cloud.webshop.ordermanagement.mapper;

import de.frankfurtuas.cloud.webshop.ordermanagement.dto.OrderDTO;
import de.frankfurtuas.cloud.webshop.ordermanagement.dto.OrderItemDTO;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.Order;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Order Mapper
 */
public class OrderMapper {

    /**
     * Maps Order to OrderDTO
     * @param order Order
     * @return OrderDTO
     */
    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .shippingAddress(order.getShippingAddress())
                .phoneNumber(order.getPhoneNumber())
                .totalAmount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .status(order.getStatus())
                .items(order.getItems().stream()
                        .map(OrderMapper::toOrderItemDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * Maps OrderItem to OrderItemDTO
     * @param orderItem OrderItem
     * @return OrderItemDTO
     */
    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .orderId(orderItem.getOrder().getId())
                .productName(orderItem.getProduct().getName())
                .quantity(orderItem.getQuantity())
                .productPrice(orderItem.getProduct().getPrice())
                .totalPriceForItem(orderItem.getTotalPriceForItem())
                .build();
    }

    public static List<OrderDTO> toOrderDTOList(List<Order> orders) {
        return orders.stream().map(OrderMapper::toOrderDTO).collect(Collectors.toList());
    }
}
