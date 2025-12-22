package com.jiubredeemer.charactersheet.domain.characterSkill.mapper;

import com.jiubredeemer.charactersheet.dal.entity.CharacterBio;
import com.jiubredeemer.charactersheet.dal.entity.CharacterSkills;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterSkill.dto.CharacterSkillsDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterSkillsDtoMapper {
    public CharacterSkillsDto toDto(CharacterSkills entity) {
        final CharacterSkillsDto model = new CharacterSkillsDto();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

    public List<CharacterSkillsDto> toDto(List<CharacterSkills> entity) {
        return entity.stream().map(this::toDto).toList();
    }

    public CharacterSkills toEntity(CharacterSkillsDto model, boolean isNew) {
        final CharacterSkills entity = new CharacterSkills();
        BeanUtils.copyProperties(model, entity);
        entity.setId(model.getId());
        entity.setNew(isNew);
        return entity;
    }
}
