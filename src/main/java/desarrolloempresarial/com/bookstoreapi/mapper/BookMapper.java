package desarrolloempresarial.com.bookstoreapi.mapper;

import desarrolloempresarial.com.bookstoreapi.dto.request.BookRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.AuthorResponse;
import desarrolloempresarial.com.bookstoreapi.dto.response.BookResponse;
import desarrolloempresarial.com.bookstoreapi.dto.response.CategoryResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    public Book toEntity(BookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
    }

    public BookResponse toResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setTitle(book.getTitle());
        response.setIsbn(book.getIsbn());
        response.setPrice(book.getPrice());
        response.setStock(book.getStock());

        if (book.getAuthor() != null) {
            AuthorResponse authorResponse = new AuthorResponse();
            authorResponse.setId(book.getAuthor().getId());
            authorResponse.setName(book.getAuthor().getName());
            response.setAuthor(authorResponse);
        }

        if (book.getCategories() != null) {
            List<CategoryResponse> categoryResponses = book.getCategories()
                    .stream()
                    .map(cat -> {
                        CategoryResponse cr = new CategoryResponse();
                        cr.setId(cat.getId());
                        cr.setName(cat.getName());
                        return cr;
                    })
                    .toList();
            response.setCategories(categoryResponses);
        }

        return response;
    }
}