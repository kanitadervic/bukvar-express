package ba.unsa.etf.ri.ppis.bukvarexpress.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="book_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private Long orderId;
    private Long bookId;
    private Integer quantity;
}
