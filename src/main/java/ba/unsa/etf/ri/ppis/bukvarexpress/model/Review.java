package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private UUID userId;
    private UUID bookId;
    private Integer rating;
    private String comment;
}
