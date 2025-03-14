package org.senla.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse{
    private Integer id;
    private String name;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
