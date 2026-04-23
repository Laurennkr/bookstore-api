package desarrolloempresarial.com.bookstoreapi.service;
//Contiene la lógica de autenticación y registro de usuarios
import desarrolloempresarial.com.bookstoreapi.dto.request.LoginRequest;
import desarrolloempresarial.com.bookstoreapi.dto.request.RegisterRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.AuthResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Role;
import desarrolloempresarial.com.bookstoreapi.entity.User;
import desarrolloempresarial.com.bookstoreapi.exception.custom.DuplicateResourceException;
import desarrolloempresarial.com.bookstoreapi.repository.UserRepository;
import desarrolloempresarial.com.bookstoreapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "Ya existe un usuario con el email: " + request.getEmail()
            );
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .expiresIn(86400000L)
                .role(user.getRole().name())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .expiresIn(86400000L)
                .role(user.getRole().name())
                .build();
    }
}
