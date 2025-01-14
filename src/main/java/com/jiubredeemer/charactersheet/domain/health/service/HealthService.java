package com.jiubredeemer.charactersheet.domain.health.service;

import com.jiubredeemer.charactersheet.dal.repository.HealthRepository;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.health.mapper.HealthDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final HealthDtoMapper mapper;
    private final HealthRepository repository;

    public HealthDto saveHealth(HealthDto healthDto) {
        return mapper.toDto(repository.save(mapper.toEntity(healthDto, true)));
    }

    public HealthDto findByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findById(characterId).orElseThrow(() ->
                new NotFoundException("HelthDto wasn't found by characterId: " + characterId)));
    }

    public List<HealthDto> findByCharacterIds(Set<UUID> characterIds) {
        return mapper.toDto(repository.findByCharacterIdIn(characterIds));
    }
}
