package desarrolloempresarial.com.bookstoreapi.exception.custom;

public class InvalidOrderStateException extends RuntimeException {
    public InvalidOrderStateException(String message) {
        super(message);
    }
}
