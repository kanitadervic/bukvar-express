package ba.unsa.etf.ri.ppis.bukvarexpress.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private String author;
    private Date datePublished;
    private Integer stock;
    private String imageUrl;
    private Double price;
    private Double rating;
    private Integer totalReviews;
    private String description;
}
