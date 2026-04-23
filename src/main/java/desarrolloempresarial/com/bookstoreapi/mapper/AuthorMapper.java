package desarrolloempresarial.com.bookstoreapi.mapper;

import desarrolloempresarial.com.bookstoreapi.dto.request.AuthorRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.AuthorResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequest request) {
        return Author.builder()
                .name(request.getName())
                .biography(request.getBiography())
                .contact(request.getContact())
                .build();
    }

    public AuthorResponse toResponse(Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getName());
        response.setBiography(author.getBiography());
        response.setContact(author.getContact());
        return response;
    }
}