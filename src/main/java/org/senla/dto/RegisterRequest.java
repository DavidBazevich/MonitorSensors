package org.senla.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.senla.entity.Role;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @UniqueElements
    private String name;
    @Size(min = 8)
    @EqualsAndHashCode.Exclude
    private String password;
    private List<Role> role;
}
