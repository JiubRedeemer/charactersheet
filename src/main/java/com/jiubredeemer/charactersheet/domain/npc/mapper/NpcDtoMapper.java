package com.jiubredeemer.charactersheet.domain.npc.mapper;

import com.jiubredeemer.charactersheet.dal.entity.Npc;
import com.jiubredeemer.charactersheet.domain.npc.dto.NpcDto;
import com.jiubredeemer.charactersheet.domain.npc.dto.NpcTypeEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NpcDtoMapper {

    public NpcDto toDto(Npc entity) {
        final NpcDto dto = new NpcDto();
        dto.setId(entity.getId());
        dto.setRoomId(entity.getRoomId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType() != null ? NpcTypeEnum.valueOf(entity.getType()) : null);
        dto.setClazzCode(entity.getClazzCode());
        dto.setRaceCode(entity.getRaceCode());
        dto.setArmoryClass(entity.getArmoryClass());
        dto.setSpeed(entity.getSpeed());
        dto.setInitiative(entity.getInitiative());
        dto.setImgUrl(entity.getImgUrl());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public List<NpcDto> toDto(List<Npc> entities) {
        return entities.stream().map(this::toDto).toList();
    }
}

