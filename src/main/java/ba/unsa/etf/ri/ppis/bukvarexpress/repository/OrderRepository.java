package ba.unsa.etf.ri.ppis.bukvarexpress.repository;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookOrderEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT o FROM OrderEntity o WHERE o.userId=?1", nativeQuery = true)
    List<OrderEntity> getUserOrders(Long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM book_order WHERE order_id=?1", nativeQuery = true)
    void deleteBookOrder(Long orderId);
}
