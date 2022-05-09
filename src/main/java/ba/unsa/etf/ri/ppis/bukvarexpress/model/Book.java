package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import java.time.Instant;
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
}
