package com.jiubredeemer.charactersheet.domain.character.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.domain.character.dto.CharacterDto;
import com.jiubredeemer.charactersheet.domain.character.dto.CreateCharacterRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterDtoMapper {

    public Character toEntity(CreateCharacterRequest createCharacterRequest, boolean isNew) {
        final Character character = new Character();
        character.setRoomId(createCharacterRequest.getRoomId());
        character.setUserId(createCharacterRequest.getUserId());
        character.setName(createCharacterRequest.getName());
        character.setClazzCode(createCharacterRequest.getClazzCode());
        character.setRaceCode(createCharacterRequest.getRaceCode());
        character.setCurrentHpDiceCount(createCharacterRequest.getCurrentHpDiceCount());
        character.setNew(isNew);
        return character;
    }

    public Character toEntity(CharacterDto characterDto, boolean isNew) {
        final Character character = new Character();
        BeanUtils.copyProperties(characterDto, character);
        character.setNew(isNew);
        return character;
    }

    public CharacterDto toDto(Character entity) {
        final CharacterDto characterDto = new CharacterDto();
        characterDto.setId(entity.getId());
        characterDto.setRoomId(entity.getRoomId());
        characterDto.setUserId(entity.getUserId());
        characterDto.setName(entity.getName());
        characterDto.setClazzCode(entity.getClazzCode());
        characterDto.setRaceCode(entity.getRaceCode());
        characterDto.setProficiencyBonus(entity.getProficiencyBonus());
        characterDto.setArmoryClass(entity.getArmoryClass());
        characterDto.setBonusArmoryClass(entity.getBonusArmoryClass());
        characterDto.setSpeed(entity.getSpeed());
        characterDto.setBonusSpeed(entity.getBonusSpeed());
        characterDto.setInspiration(entity.getInspiration());
        characterDto.setInitiative(entity.getInitiative());
        characterDto.setBonusInitiative(entity.getBonusInitiative());
        characterDto.setCurrentHpDiceCount(entity.getCurrentHpDiceCount());
        return characterDto;
    }

    public List<CharacterDto> toDto(List<Character> entities) {
        return entities.stream().map(this::toDto).toList();
    }
}
