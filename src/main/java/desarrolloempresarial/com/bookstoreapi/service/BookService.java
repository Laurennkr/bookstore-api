package desarrolloempresarial.com.bookstoreapi.service;

import desarrolloempresarial.com.bookstoreapi.dto.request.BookRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.BookResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Author;
import desarrolloempresarial.com.bookstoreapi.entity.Book;
import desarrolloempresarial.com.bookstoreapi.entity.Category;
import desarrolloempresarial.com.bookstoreapi.exception.custom.DuplicateResourceException;
import desarrolloempresarial.com.bookstoreapi.exception.custom.ResourceNotFoundException;
import desarrolloempresarial.com.bookstoreapi.mapper.BookMapper;
import desarrolloempresarial.com.bookstoreapi.repository.AuthorRepository;
import desarrolloempresarial.com.bookstoreapi.repository.BookRepository;
import desarrolloempresarial.com.bookstoreapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public BookResponse create(BookRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException(
                    "Ya existe un libro con el ISBN: " + request.getIsbn()
            );
        }
        Book book = bookMapper.toEntity(request);

        if (request.getAuthorId() != null) {
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Autor no encontrado con id: " + request.getAuthorId()
                    ));
            book.setAuthor(author);
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Categoría no encontrada con id: " + request.getCategoryId()
                    ));
            book.setCategory(category);
        }

        return bookMapper.toResponse(bookRepository.save(book));
    }

    public Page<BookResponse> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toResponse);
    }

    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Libro no encontrado con id: " + id
                ));
        return bookMapper.toResponse(book);
    }

    public BookResponse update(Long id, BookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Libro no encontrado con id: " + id
                ));
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPrice(request.getPrice());
        book.setStock(request.getStock());

        if (request.getAuthorId() != null) {
            Author author = authorRepository.findById(request.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Autor no encontrado con id: " + request.getAuthorId()
                    ));
            book.setAuthor(author);
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Categoría no encontrada con id: " + request.getCategoryId()
                    ));
            book.setCategory(category);
        }

        return bookMapper.toResponse(bookRepository.save(book));
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Libro no encontrado con id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<BookResponse> findByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId)
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    public List<BookResponse> findByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId)
                .stream()
                .map(bookMapper::toResponse)
                .toList();
    }
}