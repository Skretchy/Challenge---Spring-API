package com.zzemilio.forohub.service;


import com.zzemilio.forohub.dto.AuthRequest;
import com.zzemilio.forohub.dto.AuthResponse;
import com.zzemilio.forohub.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest req) {
        var authToken = new UsernamePasswordAuthenticationToken(req.email(), req.password());
        authenticationManager.authenticate(authToken);

        String token = jwtService.generateToken(req.email());
        return new AuthResponse(token, "Bearer");
    }
}