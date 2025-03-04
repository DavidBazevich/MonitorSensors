package org.senla.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.senla.dto.SensorCreateDto;
import org.senla.dto.SensorDto;

import org.senla.service.SensorServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SensorController {

    private final SensorServiceImp sensorService;

    @GetMapping("/{id}")
    @Operation(summary = "Find by id")
    public ResponseEntity<SensorDto> findById(@PathVariable Integer id){
       return new ResponseEntity<>(sensorService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SensorDto>> findAllSensor(){
        return new ResponseEntity<>(sensorService.findAllSensor(), HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<SensorDto> addSensor(@RequestBody SensorCreateDto sensor){
        return new ResponseEntity<>(sensorService.saveSensor(sensor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Integer id){
        sensorService.deleteSensorById(id);
        return ResponseEntity.noContent().build(); //TODO не работает noContent
    }

}
