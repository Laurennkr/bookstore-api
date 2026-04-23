package desarrolloempresarial.com.bookstoreapi.service;

import desarrolloempresarial.com.bookstoreapi.dto.request.CategoryRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);
    List<CategoryResponse> findAll();
    CategoryResponse findById(Long id);
    CategoryResponse update(Long id, CategoryRequest request);
    void delete(Long id);
}