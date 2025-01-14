package com.jiubredeemer.charactersheet.domain.level.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Level;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelDtoMapper {

    public Level toEntity(LevelDto model, boolean isNew) {
        final Level entity = new Level();
        entity.setCharacterId(model.getCharacterId());
        entity.setLevel(model.getLevel());
        entity.setXp(model.getXp());
        entity.setNew(isNew);
        return entity;
    }

    public LevelDto toDto(Level entity) {
        final LevelDto model = new LevelDto();
        model.setCharacterId(entity.getCharacterId());
        model.setLevel(entity.getLevel());
        model.setXp(entity.getXp());
        return model;
    }

    public List<LevelDto> toDto(List<Level> entity) {
        return entity.stream().map(this::toDto).toList();
    }
}
