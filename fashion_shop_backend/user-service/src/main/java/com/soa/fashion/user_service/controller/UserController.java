package com.soa.fashion.user_service.controller;

import com.soa.fashion.user_service.dto.UserDTO;
import com.soa.fashion.user_service.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        try {
            UserDTO user = userService.getUserById(id);
            return ResponseEntity.ok(Map.of(
                    "status", 200,
                    "message", "Lấy thông tin user thành công",
                    "data", user
            ));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", 404,
                    "message", "Không tìm thấy user: " + e.getMessage()
            ));
        }
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        try {
            var users = userService.getAllUsers();
            return ResponseEntity.ok(Map.of(
                    "status", 200,
                    "message", "Lấy danh sách user thành công",
                    "data", users
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", 400,
                    "message", "Lấy danh sách thất bại: " + e.getMessage()
            ));
        }
    }
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody UserDTO dto) {
        try {
            UserDTO created = userService.createUser(dto);

            return ResponseEntity.status(201).body(Map.of(
                    "status", 201,
                    "message", "Thêm người dùng thành công",
                    "data", created
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", 400,
                    "message", "Thêm thất bại: " + e.getMessage()
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        try {
            UserDTO updated = userService.updateUser(id, dto);
            return ResponseEntity.ok(Map.of(
                    "status", 200,
                    "message", "Cập nhật user thành công",
                    "data", updated
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", 400,
                    "message", "Cập nhật thất bại: " + e.getMessage()
            ));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of(
                    "status", 200,
                    "message", "Xóa user thành công"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", 404,
                    "message", "Xóa thất bại: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/find-by-email/{email}")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable String email) {
        try {
            UserDTO user = userService.getUserByEmail(email);

            return ResponseEntity.ok(Map.of(
                    "status", 200,
                    "message", "Lấy user thành công",
                    "data", user
            ));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of(
                    "status", 404,
                    "message", "Không tìm thấy user: " + e.getMessage()
            ));
        }
    }
}
