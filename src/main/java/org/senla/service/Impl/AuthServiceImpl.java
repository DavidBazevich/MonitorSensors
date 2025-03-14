package org.senla.service.Impl;

import org.senla.dto.RegisterDto;
import org.senla.dto.RegisterResponse;

public interface AuthServiceImpl {
    RegisterResponse register(RegisterDto registerDto);
}
