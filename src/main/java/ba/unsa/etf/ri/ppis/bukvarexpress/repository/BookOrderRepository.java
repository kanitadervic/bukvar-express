package ba.unsa.etf.ri.ppis.bukvarexpress.repository;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookOrderRepository extends JpaRepository<BookOrderEntity, Long> {

    @Query(value = "SELECT bo.* FROM book_order bo, orders o WHERE o.id = bo.order_id AND bo.order_id=?1", nativeQuery = true)
    List<BookOrderEntity> getBookOrdersByOrderId(Long orderId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO book_order(order_id, book_id, quantity) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void saveBookOrder(Long orderId, Long bookId, Integer quantity);
}
