package com.jiubredeemer.charactersheet.domain.ability.service;

import com.jiubredeemer.charactersheet.dal.entity.Ability;
import com.jiubredeemer.charactersheet.dal.repository.AbilityRepository;
import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.ability.mapper.AbilityDtoMapper;
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
public class AbilityService {
    private final AbilityDtoMapper mapper;
    private final AbilityRepository repository;

    public List<AbilityDto> saveAllAbilities(List<AbilityDto> abilities) {
        List<Ability> entity = mapper.toEntity(abilities, true);
        Iterable<Ability> entities = repository.saveAll(entity);
        return mapper.toDto(entities);
    }

    public Long getValueByCode(UUID characterId, String characteristicCode) {
        return repository.findByCharacterIdAndCode(characterId, characteristicCode).orElseThrow().getValue();
    }

    public List<AbilityDto> findAllByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findByCharacterIdOrderById(characterId));
    }

    public List<AbilityDto> findAllByCharacterIds(Set<UUID> characterIds) {
        return mapper.toDto(repository.findByCharacterIdIn(characterIds));
    }

    public void updateBonusValueByCode(UUID characterId,
                                       String abilityCode,
                                       BonusValueUpdateRequest request) {
        final Optional<Ability> abilityFromDb = repository.findByCharacterIdAndCode(characterId, abilityCode);
        abilityFromDb.ifPresentOrElse(ability -> {
            ability.setBonusValue(request.getBonusValue());
            repository.save(ability);
        }, () -> {
            throw new NotFoundException("Ability not found by code");
        });
    }
}
