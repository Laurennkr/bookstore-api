package desarrolloempresarial.com.bookstoreapi.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private BigDecimal price;
    private Integer stock;
    private AuthorResponse author;
    private CategoryResponse category;
}