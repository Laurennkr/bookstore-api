package desarrolloempresarial.com.bookstoreapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    private String biography;
    private String contact;
}