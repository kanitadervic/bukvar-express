package ba.unsa.etf.ri.ppis.bukvarexpress.repository;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookOrderEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO book_order(order_id, book_id, quantity) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void saveBookOrder(Long orderId, Long bookId, Integer quantity);

    @Query(value = "SELECT bo FROM BookOrderEntity bo, OrderEntity o WHERE o.id = bo.orderId AND bo.orderId=?1", nativeQuery = true)
    List<BookOrderEntity> getBookOrdersByOrderId(Long orderId);

    @Query(value = "SELECT o FROM OrderEntity o WHERE o.userId=?1")
    List<OrderEntity> getUserOrders(Long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM book_order WHERE orderId=?1", nativeQuery = true)
    void deleteBookOrder(Long orderId);
}
