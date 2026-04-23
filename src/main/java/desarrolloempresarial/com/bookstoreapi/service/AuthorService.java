package desarrolloempresarial.com.bookstoreapi.service;

import desarrolloempresarial.com.bookstoreapi.dto.request.AuthorRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.AuthorResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Author;
import desarrolloempresarial.com.bookstoreapi.exception.custom.AuthorHasBooksException;
import desarrolloempresarial.com.bookstoreapi.exception.custom.DuplicateResourceException;
import desarrolloempresarial.com.bookstoreapi.exception.custom.ResourceNotFoundException;
import desarrolloempresarial.com.bookstoreapi.mapper.AuthorMapper;
import desarrolloempresarial.com.bookstoreapi.repository.AuthorRepository;
import desarrolloempresarial.com.bookstoreapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponse create(AuthorRequest request) {
        if (authorRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException(
                    "Ya existe un autor con el nombre: " + request.getName()
            );
        }
        Author saved = authorRepository.save(authorMapper.toEntity(request));
        return authorMapper.toResponse(saved);
    }

    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponse)
                .toList();
    }

    public AuthorResponse findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Autor no encontrado con id: " + id
                ));
        return authorMapper.toResponse(author);
    }

    public AuthorResponse update(Long id, AuthorRequest request) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Autor no encontrado con id: " + id
                ));
        author.setName(request.getName());
        author.setBiography(request.getBiography());
        author.setContact(request.getContact());
        return authorMapper.toResponse(authorRepository.save(author));
    }

    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Autor no encontrado con id: " + id);
        }
        if (bookRepository.existsByAuthorId(id)) {
            throw new AuthorHasBooksException(
                    "No se puede eliminar el autor con id: " + id + " porque tiene libros asociados"
            );
        }
        authorRepository.deleteById(id);
    }
}
