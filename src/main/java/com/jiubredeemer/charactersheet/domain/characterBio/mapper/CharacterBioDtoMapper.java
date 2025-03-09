package com.jiubredeemer.charactersheet.domain.characterBio.mapper;

import com.jiubredeemer.charactersheet.dal.entity.CharacterBio;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterBioDtoMapper {
    public CharacterBioDto toDto(CharacterBio entity) {
        final CharacterBioDto model = new CharacterBioDto();
        model.setCharacterId(entity.getCharacterId());
        model.setAge(entity.getAge());
        model.setHeight(entity.getHeight());
        model.setWeight(entity.getWeight());
        model.setAttachments(entity.getAttachments());
        model.setHistory(entity.getHistory());
        model.setIdeals(entity.getIdeals());
        model.setPersonality(entity.getPersonality());
        model.setRelationships(entity.getRelationships());
        model.setWeaknesses(entity.getWeaknesses());
        model.setAvatar(entity.getAvatar());
        return model;
    }

    public List<CharacterBioDto> toDto(List<CharacterBio> entity) {
        return entity.stream().map(this::toDto).toList();
    }

    public CharacterBio toEntity(CharacterBioDto model, boolean isNew) {
        final CharacterBio entity = new CharacterBio();
        entity.setCharacterId(model.getCharacterId());
        entity.setAge(model.getAge());
        entity.setHeight(model.getHeight());
        entity.setWeight(model.getWeight());
        entity.setAttachments(model.getAttachments());
        entity.setHistory(model.getHistory());
        entity.setIdeals(model.getIdeals());
        entity.setPersonality(model.getPersonality());
        entity.setRelationships(model.getRelationships());
        entity.setWeaknesses(model.getWeaknesses());
        entity.setAvatar(model.getAvatar());
        entity.setNew(isNew);
        return entity;
    }
}
