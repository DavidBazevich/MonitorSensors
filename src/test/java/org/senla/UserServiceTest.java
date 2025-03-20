package org.senla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.senla.entity.User;
import org.senla.repository.UserRepository;
import org.senla.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("David");
        user.setPassword("qwerty");
    }

    @Test
    void loadUserByUsername_IfUserExists() {
        when(userRepository.findByName("David")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("David");

        assertNotNull(userDetails);
        assertEquals("David", userDetails.getUsername());
        assertEquals("qwerty", userDetails.getPassword());
        verify(userRepository, times(1)).findByName("David");
    }

    @Test
    void loadUserByUsername_IfUserDoesNotExist() {
        when(userRepository.findByName("Ivan")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("Ivan");
        });

        assertEquals("User not found with username: Ivan", exception.getMessage());
        verify(userRepository, times(1)).findByName("Ivan");
    }

}
