package com.jiubredeemer.charactersheet.domain.health.service;

import com.jiubredeemer.charactersheet.dal.repository.HealthRepository;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.health.mapper.HealthDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final HealthDtoMapper mapper;
    private final HealthRepository repository;

    public HealthDto saveHealth(HealthDto healthDto) {
        return mapper.toDto(repository.save(mapper.toEntity(healthDto, true)));
    }
}
