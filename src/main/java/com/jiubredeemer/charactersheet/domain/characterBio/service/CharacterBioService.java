package com.jiubredeemer.charactersheet.domain.characterBio.service;

import com.jiubredeemer.charactersheet.dal.repository.CharacterBioRepository;
import com.jiubredeemer.charactersheet.domain.characterBio.dto.CharacterBioDto;
import com.jiubredeemer.charactersheet.domain.characterBio.mapper.CharacterBioDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterBioService {
    private final CharacterBioDtoMapper mapper;
    private final CharacterBioRepository repository;

    public CharacterBioDto saveCharacterBio(CharacterBioDto characterBioDto) {
        return mapper.toDto(repository.save(mapper.toEntity(characterBioDto, true)));
    }
}
