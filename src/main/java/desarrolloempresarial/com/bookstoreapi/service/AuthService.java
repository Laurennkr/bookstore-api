package desarrolloempresarial.com.bookstoreapi.service;

import desarrolloempresarial.com.bookstoreapi.dto.request.LoginRequest;
import desarrolloempresarial.com.bookstoreapi.dto.request.RegisterRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}