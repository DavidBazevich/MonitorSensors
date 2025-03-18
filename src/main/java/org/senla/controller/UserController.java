package org.senla.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.senla.dto.AuthRequest;
import org.senla.dto.RegisterRequest;
import org.senla.handler.JwtResponseHandler;
import org.senla.service.Impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return JwtResponseHandler.generatedResponse(
                "User registered successfully",
                HttpStatus.OK,
                authService.register(registerRequest)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthRequest authRequest) {
        return JwtResponseHandler.generatedResponse(
                "User is exist",
                HttpStatus.OK,
                authService.authenticate(authRequest)
        );
    }

}