package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Review {
    private User user;
    private Book book;
    private Integer rating;
    private String comment;
}
