package ba.unsa.etf.ri.ppis.bukvarexpress.entity;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private Long userId;
    private String city;
    private Double totalPrice;
    private String note;
    private String address;
    private String createdAt;
    private OrderStatus orderStatus;
}
