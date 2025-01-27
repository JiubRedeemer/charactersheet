package com.jiubredeemer.charactersheet.domain.health.service;

import com.jiubredeemer.charactersheet.dal.entity.Ability;
import com.jiubredeemer.charactersheet.dal.entity.Health;
import com.jiubredeemer.charactersheet.dal.repository.HealthRepository;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import com.jiubredeemer.charactersheet.domain.health.mapper.HealthDtoMapper;
import com.jiubredeemer.charactersheet.domain.util.dto.BonusValueUpdateRequest;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void updateBonusValue(UUID characterId, BonusValueUpdateRequest request) {
        final Optional<Health> healthFromDb = repository.findById(characterId);
        healthFromDb.ifPresentOrElse(health -> {
            health.setBonusValue(request.getBonusValue());
            repository.save(health);
        }, () -> {
            throw new NotFoundException("Health not found by character id");
        });
    }
}
