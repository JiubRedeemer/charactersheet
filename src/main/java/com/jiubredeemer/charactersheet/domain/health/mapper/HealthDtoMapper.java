package com.jiubredeemer.charactersheet.domain.health.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Health;
import com.jiubredeemer.charactersheet.domain.health.dto.HealthDto;
import org.springframework.stereotype.Component;

@Component
public class HealthDtoMapper {
    public HealthDto toDto(Health entity) {
        final HealthDto model = new HealthDto();
        model.setCharacterId(entity.getCharacterId());
        model.setCurrentHp(entity.getCurrentHp());
        model.setMaxHp(entity.getMaxHp());
        model.setTempHp(entity.getTempHp());
        return model;
    }

    public Health toEntity(HealthDto model, boolean isNew) {
        final Health entity = new Health();
        entity.setCharacterId(model.getCharacterId());
        entity.setCurrentHp(model.getCurrentHp());
        entity.setMaxHp(model.getMaxHp());
        entity.setTempHp(model.getTempHp());
        entity.setNew(isNew);
        return entity;
    }
}
