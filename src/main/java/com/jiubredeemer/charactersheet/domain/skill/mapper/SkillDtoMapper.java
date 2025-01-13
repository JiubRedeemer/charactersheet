package com.jiubredeemer.charactersheet.domain.skill.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Skill;
import com.jiubredeemer.charactersheet.domain.character.dto.SkillShort;
import com.jiubredeemer.charactersheet.domain.skill.dto.SkillDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class SkillDtoMapper {
    public SkillDto toDto(Skill entity) {
        final SkillDto model = new SkillDto();
        model.setId(entity.getId());
        model.setCharacterId(entity.getCharacterId());
        model.setCode(entity.getCode());
        return model;
    }

    public List<SkillDto> toDto(List<Skill> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    public List<SkillDto> toDto(Iterable<Skill> entities) {
        return StreamSupport.stream(entities.spliterator(), false).map(this::toDto).toList();
    }

    public Skill toEntity(SkillDto model, boolean isNew) {
        final Skill entity = new Skill();
        entity.setId(model.getId());
        entity.setCharacterId(model.getCharacterId());
        entity.setCode(model.getCode());
        entity.setNew(isNew);
        return entity;
    }

    public List<Skill> toEntity(List<SkillDto> models, boolean isNew) {
        return models.stream().map(model -> toEntity(model, isNew)).toList();
    }

    public SkillDto mapSkillShortToDto(SkillShort model) {
        final SkillDto dto = new SkillDto();
        dto.setCode(model.getCode());
        return dto;
    }
}
