package com.spring_cloud.eureka.client.auth;

import com.spring_cloud.eureka.client.auth.core.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest request) {
        String token = authService.signIn(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class AuthResponse {
        private String accessToken;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SignInRequest {
        private String userId;
        private String password;
    }

}
