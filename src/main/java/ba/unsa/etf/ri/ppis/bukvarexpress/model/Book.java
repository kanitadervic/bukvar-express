package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    private Long id;
    private String name;
    private String author;
    private Instant datePublished;
    private Integer stock;
    private Double price;
    private List<Long> categoryIds;
    private String imageUrl;
    private Double rating;
    private Integer totalReviews;
    private String description;
}
