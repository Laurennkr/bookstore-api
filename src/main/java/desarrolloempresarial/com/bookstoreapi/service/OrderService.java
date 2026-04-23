package desarrolloempresarial.com.bookstoreapi.service;

import desarrolloempresarial.com.bookstoreapi.dto.request.OrderRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest request, String email);
    List<OrderResponse> findAll();
    List<OrderResponse> findMyOrders(String email);
    OrderResponse findById(Long id, String email);
    OrderResponse cancel(Long id, String email);
}