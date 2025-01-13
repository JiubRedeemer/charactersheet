package com.jiubredeemer.charactersheet.domain.ability.service;

import com.jiubredeemer.charactersheet.dal.entity.Ability;
import com.jiubredeemer.charactersheet.dal.repository.AbilityRepository;
import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.ability.mapper.AbilityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
