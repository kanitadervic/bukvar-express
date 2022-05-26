package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.BookOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private Long id;
    private Long userId;
    private String city;
    private Double totalPrice;
    private String note;
    private String address;
    private String createdAt;
    private OrderStatus orderStatus;
    private List<BookOrderEntity> orderedBooks;
}
