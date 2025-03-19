package org.senla.service.Impl;

import org.senla.dto.creators.TypeCreateDto;
import org.senla.dto.TypeDto;

import java.util.List;

public interface TypeServiceImp {
    List<TypeDto> findAllTypes();
    TypeDto saveType(TypeCreateDto type);
    TypeDto updateType(String name, TypeCreateDto type);
    TypeDto findById(Integer id);
    TypeDto deleteTypeByName(String name);
}
