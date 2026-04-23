package desarrolloempresarial.com.bookstoreapi.service;

import desarrolloempresarial.com.bookstoreapi.dto.request.BookRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookResponse create(BookRequest request);
    Page<BookResponse> findAll(Pageable pageable);
    BookResponse findById(Long id);
    BookResponse update(Long id, BookRequest request);
    void delete(Long id);
    List<BookResponse> findByAuthor(Long authorId);
    List<BookResponse> findByCategory(Long categoryId);
}