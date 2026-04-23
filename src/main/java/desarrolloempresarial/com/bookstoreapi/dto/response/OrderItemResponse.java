package desarrolloempresarial.com.bookstoreapi.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
    private BigDecimal subtotal;
}