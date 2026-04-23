package desarrolloempresarial.com.bookstoreapi.controller;

import desarrolloempresarial.com.bookstoreapi.dto.request.OrderRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.ApiResponse;
import desarrolloempresarial.com.bookstoreapi.dto.response.OrderResponse;
import desarrolloempresarial.com.bookstoreapi.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @Valid @RequestBody OrderRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        orderService.create(request, userDetails.getUsername()),
                        "Pedido creado", 201));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(orderService.findAll()));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> findMyOrders(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(
                orderService.findMyOrders(userDetails.getUsername())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> findById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(
                orderService.findById(id, userDetails.getUsername())));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<OrderResponse>> cancel(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(
                orderService.cancel(id, userDetails.getUsername()),
                "Pedido cancelado", 200));
    }
}