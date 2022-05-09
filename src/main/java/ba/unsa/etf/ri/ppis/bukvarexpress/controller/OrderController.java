package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Order;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Order> getOrder(@RequestParam Long orderId) {
        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        Order order = orderService.createOrder(newOrder);

        return ResponseEntity.ok(order);
    }
}
