package com.spring_cloud.eureka.client.auth;

import com.spring_cloud.eureka.client.auth.core.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        User newUser = authService.signUp(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser.getUserId());
    }

}
