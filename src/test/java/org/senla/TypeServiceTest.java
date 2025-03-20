package org.senla;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.senla.dto.TypeDto;
import org.senla.dto.creators.TypeCreateDto;
import org.senla.dto.mapper.TypeMapper;
import org.senla.entity.Type;
import org.senla.repository.TypeRepository;
import org.senla.service.TypeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TypeServiceTest {  //TODO add integration and unit tests, Docker Compose

    @Mock
    private TypeRepository typeRepository;
    @Mock
    private TypeMapper typeMapper;

    @InjectMocks
    private TypeService typeService;

    @Test
    void saveTypeTest() {
        when(typeMapper.toType(getTypeCreateDto())).thenReturn(getType());
        when(typeRepository.save(getType())).thenReturn(getType());
        when(typeMapper.toTypeDto(getType())).thenReturn(getTypeDto());

        TypeDto result = typeService.saveType(getTypeCreateDto());
        assertEquals(getTypeDto(), result);
    }

    @Test
    void saveTypeTest_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            typeService.saveType(getTypeCreateDto());
        });
    }

    @Test
    void updateTypeTest() {  // FAIL
        int id = 1;
        Type type = Type.builder()
                .id(1)
                .name("Voltage")
                .build();
        TypeCreateDto newType = new TypeCreateDto("Voltage");
        when(typeRepository.findById(id)).thenReturn(Optional.ofNullable(getType()));
        when(typeRepository.save(type)).thenReturn(type);
        when(typeMapper.toTypeDto(getType())).thenReturn(getTypeDto());


        TypeDto result = typeService.updateType(id, newType);
        assertEquals(TypeDto.builder().id(1).name("Voltage").build(), result);
    }

    @Test
    void findTypeByIdTest() {
        int id = 1;
        when(typeMapper.toTypeDto(getType())).thenReturn(getTypeDto());
        when(typeRepository.findById(id)).thenReturn(Optional.ofNullable(getType()));

        TypeDto result = typeService.findById(id);
        assertEquals(result, getTypeDto());
    }

    @Test
    void findAllTypesTest() {  //FAIL
        when(typeMapper.toTypeDto(getType())).thenReturn(getTypeDto());
        when(typeRepository.findAll()).thenReturn(getTypeList());

        var result = typeService.findAllTypes();
        assertEquals(getTypeDtoList(), result);
    }

    @Test
    void deleteTypeByIdTest() { //Работает но при смене id на другую цифру тоже самое срабатывает
        int id = 1;
        when(typeRepository.findById(id)).thenReturn(Optional.ofNullable(getType()));

        typeService.deleteTypeById(id);

        verify(typeRepository, times(1)).findById(id);
        verify(typeRepository, times(1)).delete(getType());
    }

    private TypeCreateDto getTypeCreateDto() {
        return new TypeCreateDto("Pressure");
    }

    private Type getType() {
        return Type.builder()
                .id(1)
                .name("Pressure")
                .build();
    }

    private TypeDto getTypeDto() {
        return new TypeDto(1, "Pressure");
    }

    private List<Type> getTypeList() {
        return List.of(
                Type.builder().id(1).name("Pressure").build(),
                Type.builder().id(2).name("Voltage").build(),
                Type.builder().id(3).name("Temperature").build()
        );
    }

    private List<TypeDto> getTypeDtoList() {
        return List.of(
                new TypeDto(1, "Pressure"),
                new TypeDto(2, "Voltage"),
                new TypeDto(3, "Temperature")
        );
    }

}
