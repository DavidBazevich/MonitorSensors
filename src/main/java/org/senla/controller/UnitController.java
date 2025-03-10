package org.senla.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.senla.dto.UnitDto;
import org.senla.dto.creators.UnitCreateDto;
import org.senla.service.Impl.UnitServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unit")
@RequiredArgsConstructor
public class UnitController {

    private final UnitServiceImp unitService;

    @GetMapping("/{id}")
    @Operation(summary = "Find by id")
    public ResponseEntity<UnitDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(unitService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UnitDto>> findAllUnits(){
        return ResponseEntity.ok(unitService.findAllUnits());
    }

    @PostMapping("save")
    public ResponseEntity<UnitDto> addUnit(@RequestBody UnitCreateDto unit){
        return new ResponseEntity<>(unitService.saveUnit(unit), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Integer id){
        unitService.deleteUnitById(id);
        return ResponseEntity.noContent().build();
    }

}
