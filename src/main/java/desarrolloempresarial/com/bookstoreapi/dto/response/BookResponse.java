package desarrolloempresarial.com.bookstoreapi.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private BigDecimal price;
    private Integer stock;
    private AuthorResponse author;
    private List<CategoryResponse> categories;
}