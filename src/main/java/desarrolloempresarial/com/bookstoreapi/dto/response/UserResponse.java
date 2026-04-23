package desarrolloempresarial.com.bookstoreapi.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String role;
}