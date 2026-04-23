package desarrolloempresarial.com.bookstoreapi.repository;

import desarrolloempresarial.com.bookstoreapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByCategoryId(Long categoryId);
    boolean existsByAuthorId(Long authorId);
}