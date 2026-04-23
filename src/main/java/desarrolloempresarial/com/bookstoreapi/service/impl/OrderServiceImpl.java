package desarrolloempresarial.com.bookstoreapi.service.impl;

import desarrolloempresarial.com.bookstoreapi.dto.request.OrderItemRequest;
import desarrolloempresarial.com.bookstoreapi.dto.request.OrderRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.OrderResponse;
import desarrolloempresarial.com.bookstoreapi.entity.*;
import desarrolloempresarial.com.bookstoreapi.exception.custom.InsufficientStockException;
import desarrolloempresarial.com.bookstoreapi.exception.custom.InvalidOrderStateException;
import desarrolloempresarial.com.bookstoreapi.exception.custom.ResourceNotFoundException;
import desarrolloempresarial.com.bookstoreapi.exception.custom.UnauthorizedAccessException;
import desarrolloempresarial.com.bookstoreapi.mapper.OrderMapper;
import desarrolloempresarial.com.bookstoreapi.repository.BookRepository;
import desarrolloempresarial.com.bookstoreapi.repository.OrderRepository;
import desarrolloempresarial.com.bookstoreapi.repository.UserRepository;
import desarrolloempresarial.com.bookstoreapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponse create(OrderRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado: " + email
                ));

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            Book book = bookRepository.findById(itemRequest.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Libro no encontrado con id: " + itemRequest.getBookId()
                    ));

            if (book.getStock() < itemRequest.getQuantity()) {
                throw new InsufficientStockException(
                        "Stock insuficiente para el libro: " + book.getTitle() +
                                ". Stock disponible: " + book.getStock()
                );
            }

            BigDecimal subtotal = book.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            total = total.add(subtotal);

            book.setStock(book.getStock() - itemRequest.getQuantity());
            bookRepository.save(book);

            items.add(OrderItem.builder()
                    .book(book)
                    .quantity(itemRequest.getQuantity())
                    .subtotal(subtotal)
                    .build());
        }

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .total(total)
                .createdAt(LocalDateTime.now())
                .items(items)
                .build();

        items.forEach(item -> item.setOrder(order));

        return orderMapper.toResponse(orderRepository.save(order));
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    public List<OrderResponse> findMyOrders(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado: " + email
                ));
        return orderRepository.findByUser(user)
                .stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    public OrderResponse findById(Long id, String email) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pedido no encontrado con id: " + id
                ));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado: " + email
                ));

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !order.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException(
                    "No tienes permisos para ver este pedido"
            );
        }

        return orderMapper.toResponse(order);
    }

    @Transactional
    public OrderResponse cancel(Long id, String email) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pedido no encontrado con id: " + id
                ));

        if (order.getStatus() == OrderStatus.CONFIRMED) {
            throw new InvalidOrderStateException(
                    "No se puede cancelar un pedido confirmado"
            );
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado: " + email
                ));

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !order.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException(
                    "No tienes permisos para cancelar este pedido"
            );
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderMapper.toResponse(orderRepository.save(order));
    }
}