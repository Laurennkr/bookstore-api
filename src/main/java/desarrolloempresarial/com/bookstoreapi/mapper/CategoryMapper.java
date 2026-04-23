package desarrolloempresarial.com.bookstoreapi.mapper;

import desarrolloempresarial.com.bookstoreapi.dto.request.CategoryRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.CategoryResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .build();
    }

    public CategoryResponse toResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}