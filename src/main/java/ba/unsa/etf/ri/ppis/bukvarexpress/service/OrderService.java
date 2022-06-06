package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookOrderEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.entity.OrderEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Order;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.BookOrderRepository;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookOrderRepository bookOrderRepository;

    public Order getOrderById(Long orderId) {
        return toModel(orderRepository.getById(orderId));
    }

    public List<Order> getUserOrders(Long userId) {
        List<OrderEntity> orderEntities = orderRepository.getUserOrders(userId);

        return orderEntities.stream().map(this::toModel).toList();
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAll().stream().map(this::toModel).toList();
    }

    public Order createOrder(Order order) {
        OrderEntity orderEntity = toEntity(order);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        order.getOrderedBooks()
                .forEach(bookOrder ->
                        bookOrderRepository.saveBookOrder(savedOrderEntity.getId(), bookOrder.getBookId(), bookOrder.getQuantity())
                );
        return toModel(savedOrderEntity);
    }

    public Order updateOrder(Order order) {
        orderRepository.deleteBookOrder(order.getId());
        OrderEntity orderEntity = toEntity(order);
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        order.getOrderedBooks()
                .forEach(bookOrder ->
                        bookOrderRepository.saveBookOrder( savedOrderEntity.getId(), bookOrder.getBookId(), bookOrder.getQuantity())
                );
        return toModel(savedOrderEntity);
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public OrderEntity toEntity(Order order) {
        return OrderEntity
                .builder()
                .id(order.getId())
                .userId(order.getUserId())
                .city(order.getCity())
                .totalPrice(order.getTotalPrice())
                .note(order.getNote())
                .address(order.getAddress())
                .createdAt(order.getCreatedAt())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public Order toModel(OrderEntity order) {
        List<BookOrderEntity> orderedBooks = bookOrderRepository.getBookOrdersByOrderId(order.getId());
        return Order
                .builder()
                .id(order.getId())
                .userId(order.getUserId())
                .city(order.getCity())
                .totalPrice(order.getTotalPrice())
                .note(order.getNote())
                .address(order.getAddress())
                .createdAt(order.getCreatedAt())
                .orderStatus(order.getOrderStatus())
                .orderedBooks(orderedBooks)
                .build();
    }
}
