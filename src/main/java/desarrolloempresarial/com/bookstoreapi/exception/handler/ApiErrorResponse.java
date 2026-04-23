package desarrolloempresarial.com.bookstoreapi.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private String status;
    private int code;
    private String message;
    private List<String> errors;
    private String timestamp;
    private String path;
}