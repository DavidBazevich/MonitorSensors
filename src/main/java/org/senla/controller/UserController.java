package org.senla.controller;

import lombok.RequiredArgsConstructor;
import org.senla.dto.LoginDto;
import org.senla.dto.LoginResponse;
import org.senla.dto.RegisterDto;
import org.senla.filter.JWTUtil;
import org.senla.handler.JwtResponseHandler;
import org.senla.service.Impl.AuthServiceImpl;
import org.senla.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class UserController {

    private final JWTUtil jwtUtil;

    private final AuthServiceImpl authService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDTO) {
        return JwtResponseHandler.generatedResponse("User registered successfully", HttpStatus.OK, authService.register(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDTO) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getName(), loginDTO.getPassword()));
            UserDetails userDetails = userService.loadUserByUsername(loginDTO.getName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            LoginResponse loginResponse = LoginResponse
                    .builder()
                    .accessToken(jwt)
                    .build();
            return JwtResponseHandler.generatedResponse("User logged in successfully", HttpStatus.OK, loginResponse);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }



}
