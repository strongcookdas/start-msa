package com.spring_cloud.eureka.client.auth;

import com.spring_cloud.eureka.client.auth.core.User;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private final SecretKey secretKey;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(@Value("${service.jwt.secret-key}") String key, UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
