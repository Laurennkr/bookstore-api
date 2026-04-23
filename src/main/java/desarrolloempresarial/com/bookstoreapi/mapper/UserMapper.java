package desarrolloempresarial.com.bookstoreapi.mapper;

import desarrolloempresarial.com.bookstoreapi.dto.request.RegisterRequest;
import desarrolloempresarial.com.bookstoreapi.dto.response.UserResponse;
import desarrolloempresarial.com.bookstoreapi.entity.Role;
import desarrolloempresarial.com.bookstoreapi.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }
}