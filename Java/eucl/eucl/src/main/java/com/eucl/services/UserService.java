package com.eucl.services;

import com.eucl.dto.request.LoginRequestDTO;
import com.eucl.dto.response.LoginResponseDTO;
import com.eucl.dto.request.RegisterRequestDTO;
import com.eucl.model.Role;
import com.eucl.model.User;
import com.eucl.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JWTServiceImpl jwtService;


    public User createUser(RegisterRequestDTO dto) throws BadRequestException {
        if (userRepository.existsByEmailOrPhoneOrNationalId(dto.getEmail(), dto.getPhone(), dto.getNationalID())) {
            throw new BadRequestException("user exists");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setNames(dto.getName());
        user.setRole(Role.ROLE_CUSTOMER);
        user.setNationalId(dto.getNationalID());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return user;
    }

    public LoginResponseDTO loginUser(LoginRequestDTO dto) throws BadRequestException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new BadCredentialsException(("user not found")));

        String token = jwtService.generateToken(user);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);
        return loginResponseDTO;


    }

}
