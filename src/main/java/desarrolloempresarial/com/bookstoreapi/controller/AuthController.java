package desarrolloempresarial.com.bookstoreapi.controller;

import desarrolloempresarial.com.bookstoreapi.dto.request.LoginRequest;
import desarrolloempresarial.com.bookstoreapi.dto.request.RegisterRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.ApiResponse;
import desarrolloempresarial.com.bookstoreapi.dto.response.AuthResponse;
import desarrolloempresarial.com.bookstoreapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @RequestBody RegisterRequest request) {
        AuthResponse data = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(data, "Usuario registrado exitosamente", 201));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(request)));
    }
}