package org.senla.service.Impl;

import org.senla.dto.AuthRequest;
import org.senla.dto.AuthResponse;
import org.senla.dto.RegisterRequest;

public interface AuthServiceImpl {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
}
