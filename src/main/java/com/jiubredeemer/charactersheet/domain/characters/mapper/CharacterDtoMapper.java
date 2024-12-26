package com.jiubredeemer.charactersheet.domain.characters.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.domain.characters.dto.CreateCharacterDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterDtoMapper {

    public Character toEntity(CreateCharacterDto createCharacterDto, boolean isNew) {
        final Character character = new Character();
        character.setRoomId(createCharacterDto.getRoomId());
        character.setName(createCharacterDto.getName());
        character.setClassCode(createCharacterDto.getClassCode());
        character.setRaceCode(createCharacterDto.getRaceCode());
        character.setNew(isNew);
        return character;
    }
}
