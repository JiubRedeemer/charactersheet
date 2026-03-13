package com.jiubredeemer.charactersheet.domain.characterTraits.mapper;

import com.jiubredeemer.charactersheet.dal.entity.CharacterTraits;
import com.jiubredeemer.charactersheet.domain.characterTraits.dto.CharacterTraitsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterTraitsDtoMapper {

    public CharacterTraits toEntity(CharacterTraitsDto characterTraitsDto, boolean isNew) {
        final CharacterTraits characterTraits = new CharacterTraits();
        characterTraits.setId(characterTraitsDto.getId());
        characterTraits.setCharacterId(characterTraitsDto.getCharacterId());
        characterTraits.setName(characterTraitsDto.getName());
        characterTraits.setDescription(characterTraitsDto.getDescription());
        characterTraits.setNew(isNew);
        return characterTraits;
    }

    public CharacterTraitsDto toDto(CharacterTraits entity) {
        final CharacterTraitsDto characterTraitsDto = new CharacterTraitsDto();
        characterTraitsDto.setId(entity.getId());
        characterTraitsDto.setCharacterId(entity.getCharacterId());
        characterTraitsDto.setName(entity.getName());
        characterTraitsDto.setDescription(entity.getDescription());
        return characterTraitsDto;
    }

    public List<CharacterTraitsDto> toDto(List<CharacterTraits> entities) {
        return entities.stream().map(this::toDto).toList();
    }
}
