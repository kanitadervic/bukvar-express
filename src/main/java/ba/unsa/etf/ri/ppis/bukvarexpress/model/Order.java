package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Book book;
    private User user;
    private String city;
    private String address;
}
