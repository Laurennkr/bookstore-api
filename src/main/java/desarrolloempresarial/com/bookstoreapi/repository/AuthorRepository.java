package desarrolloempresarial.com.bookstoreapi.repository;

import desarrolloempresarial.com.bookstoreapi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByName(String name);
}