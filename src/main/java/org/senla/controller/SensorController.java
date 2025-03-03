package org.senla.controller;

import lombok.RequiredArgsConstructor;
import org.senla.dto.SensorDto;

import org.senla.service.SensorServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SensorController {

    private final SensorServiceImp sensorService;

    @GetMapping("/{id}")
    public ResponseEntity<SensorDto> findById(@PathVariable Integer id){
        Optional<SensorDto> sensor = sensorService.findById(id);
        if(!sensor.isEmpty()) return ResponseEntity.ok(sensor.orElseThrow());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
