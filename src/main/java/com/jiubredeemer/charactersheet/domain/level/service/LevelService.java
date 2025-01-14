package com.jiubredeemer.charactersheet.domain.level.service;

import com.jiubredeemer.charactersheet.dal.repository.LevelRepository;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.level.mapper.LevelDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelDtoMapper mapper;
    private final LevelRepository repository;


    public LevelDto saveLevel(LevelDto levelDto) {
        return mapper.toDto(repository.save(mapper.toEntity(levelDto, true)));
    }

    public LevelDto findByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findById(characterId).orElseThrow(() ->
                new NotFoundException("LevelDto wasn'n found by characterId: " + characterId)));
    }

    public List<LevelDto> findByCharacterIds(Set<UUID> characterIds) {
        return mapper.toDto(repository.findByCharacterIdIn(characterIds));
    }
}
