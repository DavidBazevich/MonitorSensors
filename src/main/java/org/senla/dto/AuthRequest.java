package org.senla.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    @UniqueElements
    private String name;
    @Size(min = 8)
    @EqualsAndHashCode.Exclude
    private String password;
}
