package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.OrderEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.Order;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final UserService userService;

    public Order getOrderById(Long orderId) {
        return toModel(orderRepository.getById(orderId));
    }

    public Order createOrder(Order order) {
        OrderEntity orderEntity = toEntity(order);

        return toModel(orderRepository.save(orderEntity));
    }

    public OrderEntity toEntity(Order order) {
        return OrderEntity
                .builder()
                .bookId(order.getBook().getId())
                .userId(order.getUser().getId())
                .city(order.getCity())
                .address(order.getAddress())
                .build();
    }

    public Order toModel(OrderEntity order) {
        return Order
                .builder()
                .book(bookService.getBookById(order.getBookId()))
                .user(userService.getUserById(order.getUserId()))
                .city(order.getCity())
                .address(order.getAddress())
                .build();
    }
}
