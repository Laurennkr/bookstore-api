package desarrolloempresarial.com.bookstoreapi.exception.custom;

public class AuthorHasBooksException extends RuntimeException {
    public AuthorHasBooksException(String message) {
        super(message);
    }
}