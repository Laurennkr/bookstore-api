package desarrolloempresarial.com.bookstoreapi.mapper;

import desarrolloempresarial.com.bookstoreapi.dto.response.OrderResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setUserId(order.getUser().getId());
        response.setStatus(order.getStatus().name());
        response.setTotal(order.getTotal());
        response.setCreatedAt(order.getCreatedAt());
        response.setItems(order.getItems()
                .stream()
                .map(orderItemMapper::toResponse)
                .toList());
        return response;
    }
}