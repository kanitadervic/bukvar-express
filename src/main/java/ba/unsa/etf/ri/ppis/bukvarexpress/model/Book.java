package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private String name;
    private String author;
    private Instant datePublished;
    private Integer stock;
}
