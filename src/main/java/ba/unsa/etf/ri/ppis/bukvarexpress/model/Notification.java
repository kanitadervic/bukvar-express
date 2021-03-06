package ba.unsa.etf.ri.ppis.bukvarexpress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {
    private String bookInfo;
    private String message;
}
