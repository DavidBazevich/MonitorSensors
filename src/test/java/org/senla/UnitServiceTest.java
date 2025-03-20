package org.senla;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.senla.dto.UnitDto;
import org.senla.dto.creators.UnitCreateDto;
import org.senla.dto.mapper.UnitMapper;
import org.senla.entity.Units;
import org.senla.repository.UnitRepository;
import org.senla.service.UnitService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {

    @Mock
    private UnitRepository unitRepository;
    @Mock
    private UnitMapper unitMapper;
    @InjectMocks
    private UnitService unitService;


    @Test
    void saveUnitTest() {
        when(unitMapper.toUnit(getUnitCreateDto())).thenReturn(getUnit());
        when(unitRepository.save(getUnit())).thenReturn(getUnit());
        when(unitMapper.toUnitDto(getUnit())).thenReturn(getUnitDto());

        UnitDto result = unitService.saveUnit(getUnitCreateDto());
        assertEquals(getUnitDto(), result);
    }

    @Test
    void saveUnitTest_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            unitService.saveUnit(getUnitCreateDto());
        });
    }

    @Test
    void updateUnitTest() {  // FAIL
        int id = 1;
        UnitCreateDto newUnit = new UnitCreateDto("voltage");
        when(unitRepository.findById(id)).thenReturn(Optional.ofNullable(getUnit()));
        when(unitRepository.save(getUnit())).thenReturn(getUnit());
        when(unitMapper.toUnitDto(getUnit())).thenReturn(getUnitDto());

        UnitDto result = unitService.updateUnit(id, newUnit);
        assertEquals(UnitDto.builder().id(1).name("voltage").build(), result);
    }

    @Test
    void findUnitByIdTest() {
        int id = 1;
        when(unitMapper.toUnitDto(getUnit())).thenReturn(getUnitDto());
        when(unitRepository.findById(id)).thenReturn(Optional.ofNullable(getUnit()));

        UnitDto result = unitService.findById(id);
        assertEquals(result, getUnitDto());
    }

    @Test
    void findAllUnitsTest() {  //FAIL
        when(unitMapper.toUnitDto(getUnit())).thenReturn(getUnitDto());
        when(unitRepository.findAll()).thenReturn(getUnitList());

        var result = unitService.findAllUnits();
        assertEquals(getUnitDtoList(), result);
    }

    @Test
    void deleteUnitByIdTest(){ //Работает но при смене id на другую цифру тоже самое срабатывает
        int id = 1;
        when(unitRepository.findById(id)).thenReturn(Optional.ofNullable(getUnit()));

        unitService.deleteUnitById(id);

        verify(unitRepository, times(1)).findById(id);
        verify(unitRepository, times(1)).delete(getUnit());
    }

    private UnitCreateDto getUnitCreateDto() {
        return new UnitCreateDto("bar");
    }

    private Units getUnit() {
        return Units.builder()
                .id(1)
                .name("bar")
                .build();
    }

    private UnitDto getUnitDto() {
        return new UnitDto(1, "bar");
    }

    private List<Units> getUnitList() {
        return List.of(
                Units.builder().id(1).name("bar").build(),
                Units.builder().id(2).name("voltage").build(),
                Units.builder().id(3).name("degree Celsius").build()
        );
    }

    private List<UnitDto> getUnitDtoList() {
        return List.of(
                new UnitDto(1, "bar"),
                new UnitDto(2, "voltage"),
                new UnitDto(3, "degree Celsius")
        );
    }

}
