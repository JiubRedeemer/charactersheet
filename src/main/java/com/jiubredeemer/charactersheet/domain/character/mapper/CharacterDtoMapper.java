package com.jiubredeemer.charactersheet.domain.character.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.CreateCharacterDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterDtoMapper {

    public Character toEntity(CreateCharacterDto createCharacterDto, boolean isNew) {
        final Character character = new Character();
        character.setRoomId(createCharacterDto.getRoomId());
        character.setUserId(createCharacterDto.getUserId());
        character.setName(createCharacterDto.getName());
        character.setClazzCode(createCharacterDto.getClazzCode());
        character.setRaceCode(createCharacterDto.getRaceCode());
        character.setNew(isNew);
        return character;
    }

    public CharacterDto toDto(Character savedEntity) {
        final CharacterDto characterDto = new CharacterDto();
        characterDto.setId(savedEntity.getId());
        characterDto.setRoomId(savedEntity.getRoomId());
        characterDto.setUserId(savedEntity.getUserId());
        characterDto.setName(savedEntity.getName());
        characterDto.setClazzCode(savedEntity.getClazzCode());
        characterDto.setRaceCode(savedEntity.getRaceCode());
        characterDto.setProficiencyBonus(savedEntity.getProficiencyBonus());
        characterDto.setArmoryClass(savedEntity.getArmoryClass());
        characterDto.setSpeed(savedEntity.getSpeed());
        characterDto.setInspiration(savedEntity.getInspiration());
        return characterDto;
    }
}
