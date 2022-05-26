package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.Order;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order newOrder) {
        if (newOrder.getId() == null) {
            return null;
        }
        Order order = orderService.getOrderById(newOrder.getId());
        if (order == null) {
            return null;
        }

        Order updatedOrder = orderService.updateOrder(newOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteOrder(@RequestParam Long orderId) {
        if (orderId == null) {
            return null;
        }
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return null;
        }
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok("Successfully deleted a order!");
    }
}
