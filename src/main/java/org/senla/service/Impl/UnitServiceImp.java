package org.senla.service.Impl;

import org.senla.dto.creators.UnitCreateDto;
import org.senla.dto.UnitDto;

import java.util.List;

public interface UnitServiceImp {
    List<UnitDto> findAllUnits();
    UnitDto saveUnit(UnitCreateDto type);
    UnitDto updateUnit(String name, UnitCreateDto newUnit);
    UnitDto findById(Integer id);
    UnitDto deleteUnitByName(String name);
}
