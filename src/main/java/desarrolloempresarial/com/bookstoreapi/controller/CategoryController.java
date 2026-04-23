package desarrolloempresarial.com.bookstoreapi.controller;

import desarrolloempresarial.com.bookstoreapi.dto.request.CategoryRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.ApiResponse;
import desarrolloempresarial.com.bookstoreapi.dto.response.BookResponse;
import desarrolloempresarial.com.bookstoreapi.dto.response.CategoryResponse;
import desarrolloempresarial.com.bookstoreapi.service.BookService;
import desarrolloempresarial.com.bookstoreapi.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(
            @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(categoryService.create(request), "Categoría creada", 201));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.update(id, request), "Categoría actualizada", 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Categoría eliminada", 200));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<ApiResponse<List<BookResponse>>> getBooksByCategory(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(bookService.findByCategory(id)));
    }
}