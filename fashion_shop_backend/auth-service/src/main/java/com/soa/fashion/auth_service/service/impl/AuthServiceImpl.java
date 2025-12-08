package com.soa.fashion.auth_service.service.impl;


import com.soa.fashion.auth_service.client.UserClient;
import com.soa.fashion.auth_service.dto.ApiResponseDTO;
import com.soa.fashion.auth_service.dto.LoginRequestDTO;
import com.soa.fashion.auth_service.dto.RegisterRequestDTO;
import com.soa.fashion.auth_service.dto.UserDTO;
import com.soa.fashion.auth_service.jwtService.JwtService;
import com.soa.fashion.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ApiResponseDTO<Object> register(RegisterRequestDTO request) {

        try {
            userClient.getUserByEmail(request.getEmail());
            return new ApiResponseDTO<>(400, "Email đã tồn tại", null);
        } catch (Exception e) {

        }

        UserDTO newUser = new UserDTO(null, request.getEmail(),
                passwordEncoder.encode(request.getPassword()), "USER");

        ApiResponseDTO<UserDTO> createdUserResp = userClient.create(newUser);
        UserDTO savedUser = createdUserResp.getData();



        return new ApiResponseDTO<>(201, "Đăng ký thành công" ,savedUser);
    }

    @Override
    public ApiResponseDTO<String> login(LoginRequestDTO request) {
        try {
            ApiResponseDTO<UserDTO> userResp = userClient.getUserByEmail(request.getEmail());
            UserDTO user = userResp.getData();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new ApiResponseDTO<>(400, "Sai mật khẩu", null);
            }

            String token = jwtService.generateToken(user);
            return new ApiResponseDTO<>(200, "Đăng nhập thành công", token);

        } catch (Exception e) {
            return new ApiResponseDTO<>(404, "User không tồn tại", null);
        }
    }


    @Override
    public ApiResponseDTO<String> logout(String email) {

        return new ApiResponseDTO<>(200, "Đăng xuất thành công", null);
    }
}
