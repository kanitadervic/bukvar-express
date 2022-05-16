package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private Long id;
    private Long bookId;
    private Long userId;
    private String city;
    private String address;
}
