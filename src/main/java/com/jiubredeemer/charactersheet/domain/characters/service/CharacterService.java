package com.jiubredeemer.charactersheet.domain.characters.service;

import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.dal.repository.CharacterRepository;
import com.jiubredeemer.charactersheet.domain.characters.dto.CreateCharacterDto;
import com.jiubredeemer.charactersheet.domain.characters.mapper.CharacterDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterDtoMapper characterDtoMapper;

    public UUID saveCharacter(CreateCharacterDto createCharacterDto) {
        final Character characterEntity = characterDtoMapper.toEntity(createCharacterDto, true);
        characterEntity.setId(UUID.randomUUID());
        //FIXME доделать интеграцию тут
        Character savedEntity = characterRepository.save(characterEntity);
        return savedEntity.getId();
    }
}
