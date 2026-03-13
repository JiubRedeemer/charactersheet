package com.jiubredeemer.charactersheet.domain.characterTraits.service;

import com.jiubredeemer.charactersheet.dal.entity.CharacterTraits;
import com.jiubredeemer.charactersheet.dal.repository.CharacterTraitsRepository;
import com.jiubredeemer.charactersheet.domain.characterTraits.dto.CharacterTraitsDto;
import com.jiubredeemer.charactersheet.domain.characterTraits.mapper.CharacterTraitsDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterTraitsService {
    private final CharacterTraitsRepository characterTraitsRepository;
    private final CharacterTraitsDtoMapper characterTraitsDtoMapper;

    public List<CharacterTraitsDto> findByCharacterId(UUID characterId) {
        List<CharacterTraits> byCharacterId = characterTraitsRepository.findByCharacterId(characterId);
        if (byCharacterId.isEmpty()) return null;
        return characterTraitsDtoMapper.toDto(byCharacterId);
    }

    public CharacterTraitsDto create(CharacterTraitsDto characterTraitsDto) {
        characterTraitsDto.setId(UUID.randomUUID());
        return characterTraitsDtoMapper.toDto(characterTraitsRepository.save(characterTraitsDtoMapper.toEntity(characterTraitsDto, true)));
    }

    public void deleteById(UUID characterId, UUID traitId) {
        final CharacterTraits byCharacterId = characterTraitsRepository.findByCharacterIdAndId(characterId, traitId);
        characterTraitsRepository.deleteById(Objects.requireNonNull(byCharacterId.getId()));
    }
}
