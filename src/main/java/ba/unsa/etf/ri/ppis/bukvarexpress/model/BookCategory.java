package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookCategory {
    private UUID bookId;
    private UUID categoryId;
}
