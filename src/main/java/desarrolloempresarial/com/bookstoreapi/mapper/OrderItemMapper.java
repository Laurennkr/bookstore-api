package desarrolloempresarial.com.bookstoreapi.mapper;

import desarrolloempresarial.com.bookstoreapi.dto.response.OrderItemResponse;
import desarrolloempresarial.com.bookstoreapi.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemResponse toResponse(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();
        response.setId(item.getId());
        response.setBookId(item.getBook().getId());
        response.setBookTitle(item.getBook().getTitle());
        response.setQuantity(item.getQuantity());
        response.setSubtotal(item.getSubtotal());
        return response;
    }
}