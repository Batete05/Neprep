package com.eucl.controllers;

import com.eucl.dto.request.LoginRequestDTO;
import com.eucl.dto.response.LoginResponseDTO;
import com.eucl.dto.request.RegisterRequestDTO;
import com.eucl.model.User;
import com.eucl.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService service;


    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequestDTO dto) throws BadRequestException {
        return ResponseEntity.ok(service.createUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) throws BadRequestException {
        return ResponseEntity.ok(service.loginUser(dto));
    }



}
