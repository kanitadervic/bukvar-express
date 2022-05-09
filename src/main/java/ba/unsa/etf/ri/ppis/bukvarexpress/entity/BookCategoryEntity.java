package ba.unsa.etf.ri.ppis.bukvarexpress.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="book_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private Integer bookId;
    private Integer categoryId;
}
