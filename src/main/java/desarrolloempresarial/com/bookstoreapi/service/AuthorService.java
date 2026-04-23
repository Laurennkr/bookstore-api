package desarrolloempresarial.com.bookstoreapi.service;

import desarrolloempresarial.com.bookstoreapi.dto.request.AuthorRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse create(AuthorRequest request);
    List<AuthorResponse> findAll();
    AuthorResponse findById(Long id);
    AuthorResponse update(Long id, AuthorRequest request);
    void delete(Long id);
}