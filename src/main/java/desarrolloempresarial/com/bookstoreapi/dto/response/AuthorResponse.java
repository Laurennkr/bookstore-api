package desarrolloempresarial.com.bookstoreapi.dto.response;

import lombok.Data;

@Data
public class AuthorResponse {
    private Long id;
    private String name;
    private String biography;
    private String contact;
}