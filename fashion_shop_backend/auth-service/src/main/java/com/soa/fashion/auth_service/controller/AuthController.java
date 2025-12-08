package com.soa.fashion.auth_service.controller;

import com.soa.fashion.auth_service.dto.ApiResponseDTO;
import com.soa.fashion.auth_service.dto.LoginRequestDTO;
import com.soa.fashion.auth_service.dto.RegisterRequestDTO;
import com.soa.fashion.auth_service.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Object>> register(@RequestBody RegisterRequestDTO request){
        ApiResponseDTO<Object> result = authService.register(request);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<String>> login(@RequestBody LoginRequestDTO request){
        ApiResponseDTO<String> result = authService.login(request);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDTO<String>> logout(@RequestParam String email){
        ApiResponseDTO<String> result = authService.logout(email);
        return ResponseEntity.status(result.getStatus()).body(result);
    }


}
