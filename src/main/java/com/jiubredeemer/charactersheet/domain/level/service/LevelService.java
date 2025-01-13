package com.jiubredeemer.charactersheet.domain.level.service;

import com.jiubredeemer.charactersheet.dal.repository.LevelRepository;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.level.mapper.LevelDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelDtoMapper mapper;
    private final LevelRepository repository;


    public LevelDto saveLevel(LevelDto levelDto) {
        return mapper.toDto(repository.save(mapper.toEntity(levelDto, true)));
    }

}
