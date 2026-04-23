package desarrolloempresarial.com.bookstoreapi.controller;

import desarrolloempresarial.com.bookstoreapi.dto.request.BookRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.ApiResponse;
import desarrolloempresarial.com.bookstoreapi.dto.response.BookResponse;
import desarrolloempresarial.com.bookstoreapi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> create(
            @Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(bookService.create(request), "Libro creado", 201));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BookResponse>>> findAll(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(bookService.findAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(bookService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(ApiResponse.success(bookService.update(id, request), "Libro actualizado", 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Libro eliminado", 200));
    }
}