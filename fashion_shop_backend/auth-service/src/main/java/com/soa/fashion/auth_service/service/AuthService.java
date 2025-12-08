package com.soa.fashion.auth_service.service;

import com.soa.fashion.auth_service.dto.ApiResponseDTO;
import com.soa.fashion.auth_service.dto.LoginRequestDTO;
import com.soa.fashion.auth_service.dto.RegisterRequestDTO;



public interface AuthService {
    ApiResponseDTO<Object> register(RegisterRequestDTO request);
    ApiResponseDTO<String> login(LoginRequestDTO request);
    ApiResponseDTO<String> logout(String email);
}
