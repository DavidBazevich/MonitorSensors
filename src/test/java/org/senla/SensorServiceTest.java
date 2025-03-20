package org.senla;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.senla.dto.mapper.SensorMapper;
import org.senla.repository.SensorsRepository;
import org.senla.repository.TypeRepository;
import org.senla.repository.UnitRepository;
import org.senla.service.SensorService;

@ExtendWith(MockitoExtension.class)
public class SensorServiceTest {

    @Mock
    private SensorsRepository sensorsRepository;
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private SensorMapper sensorMapper;

    @InjectMocks
    private SensorService sensorService;


}
