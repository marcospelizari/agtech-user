package com.agtech.auth;

import com.agtech.auth.dto.LoginRequest;
import com.agtech.auth.dto.LoginResponse;
import com.agtech.auth.dto.RegistroRequest;
import com.agtech.security.JwtUtil;
import com.agtech.user.model.User;
import com.agtech.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@Valid @RequestBody RegistroRequest req) {
        if (userRepository.findByEmail(req.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("E-mail já cadastrado.");
        }

        User user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(User.Role.USER);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuário registrado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(req.email());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário não encontrado: " + e.getMessage());
        }

        if (!passwordEncoder.matches(req.password(), userDetails.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Senha incorreta. Hash no banco: " + userDetails.getPassword().substring(0, 15));
        }

        String token = jwtUtil.gerarToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(token, req.email()));
    }
}