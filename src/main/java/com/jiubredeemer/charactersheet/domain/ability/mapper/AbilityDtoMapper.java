package com.jiubredeemer.charactersheet.domain.ability.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Ability;
import com.jiubredeemer.charactersheet.domain.ability.dto.AbilityDto;
import com.jiubredeemer.charactersheet.domain.character.dto.AbilityShort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class AbilityDtoMapper {
    public AbilityDto toDto(Ability entity) {
        final AbilityDto model = new AbilityDto();
        model.setId(entity.getId());
        model.setCharacterId(entity.getCharacterId());
        model.setCode(entity.getCode());
        model.setValue(entity.getValue());
        return model;
    }

    public List<AbilityDto> toDto(List<Ability> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    public List<AbilityDto> toDto(Iterable<Ability> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::toDto).toList();
    }

    public Ability toEntity(AbilityDto model, boolean isNew) {
        final Ability entity = new Ability();
        entity.setId(model.getId());
        entity.setCharacterId(model.getCharacterId());
        entity.setCode(model.getCode());
        entity.setValue(model.getValue());
        entity.setNew(isNew);
        return entity;
    }

    public List<Ability> toEntity(List<AbilityDto> models, boolean isNew) {
        return models.stream().map(model -> toEntity(model, isNew)).toList();
    }

    public AbilityDto mapAbilityShortToDto(AbilityShort model) {
        final AbilityDto dto = new AbilityDto();
        dto.setCode(model.getCode());
        dto.setValue(model.getValue());
        return dto;
    }


}
