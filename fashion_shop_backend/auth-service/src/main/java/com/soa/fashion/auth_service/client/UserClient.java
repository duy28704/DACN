package com.soa.fashion.auth_service.client;

import com.soa.fashion.auth_service.dto.ApiResponseDTO;
import com.soa.fashion.auth_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "user-service" , url = "http://localhost:8087")
public interface UserClient {

    @PostMapping("/api/users")
    ApiResponseDTO<UserDTO> create(@RequestBody UserDTO dto);

    @GetMapping("/api/users/find-by-email/{email}")
    ApiResponseDTO<UserDTO> getUserByEmail(@PathVariable("email") String email);

}
