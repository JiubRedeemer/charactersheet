package com.jiubredeemer.charactersheet.domain.characterBio.service;

import com.jiubredeemer.charactersheet.dal.repository.CharacterBioRepository;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterBio.mapper.CharacterBioDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterBioService {
    private final CharacterBioDtoMapper mapper;
    private final CharacterBioRepository repository;

    public CharacterBioDto saveCharacterBio(CharacterBioDto characterBioDto) {
        return mapper.toDto(repository.save(mapper.toEntity(characterBioDto, true)));
    }

    public CharacterBioDto findByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findById(characterId)
                .orElseThrow(() -> new NotFoundException("CharacterBio not found by characterId: " + characterId)));
    }

    public List<CharacterBioDto> findByCharacterIds(Set<UUID> characterIds) {
        return mapper.toDto(repository.findByCharacterIdIn(characterIds));
    }
}
