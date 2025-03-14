package org.senla.service;

import lombok.RequiredArgsConstructor;
import org.senla.dto.RegisterDto;
import org.senla.dto.RegisterResponse;
import org.senla.dto.mapper.UserMapper;
import org.senla.entity.Role;
import org.senla.entity.User;
import org.senla.repository.UserRepository;
import org.senla.service.Impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService implements AuthServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public RegisterResponse register(RegisterDto registerDto) {
        User user = userMapper.toUser(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setCreateAt(LocalDateTime.now());
        user.setRole(Role.valueOf("User"));
        User save = userRepository.save(user);
        return userMapper.toRegisterResponse(user);
    }
}
