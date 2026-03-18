package com.jiubredeemer.charactersheet.domain.npc.service;

import com.jiubredeemer.charactersheet.dal.entity.Npc;
import com.jiubredeemer.charactersheet.dal.repository.NpcRepository;
import com.jiubredeemer.charactersheet.domain.character.dto.ClassInfoDto;
import com.jiubredeemer.charactersheet.domain.character.dto.RaceInfoDto;
import com.jiubredeemer.charactersheet.domain.clazz.service.ClazzIntegrationService;
import com.jiubredeemer.charactersheet.domain.npc.dto.NpcDto;
import com.jiubredeemer.charactersheet.domain.npc.dto.SaveNpcRequest;
import com.jiubredeemer.charactersheet.domain.npc.mapper.NpcDtoMapper;
import com.jiubredeemer.charactersheet.domain.race.service.RaceIntegrationService;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import com.jiubredeemer.charactersheet.integration.dto.clazz.ClazzDto;
import com.jiubredeemer.charactersheet.integration.dto.race.RaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NpcService {

    private final NpcRepository npcRepository;
    private final NpcDtoMapper npcDtoMapper;
    private final RaceIntegrationService raceIntegrationService;
    private final ClazzIntegrationService clazzIntegrationService;

    public NpcDto save(SaveNpcRequest request) {
        final Npc npc = new Npc();
        if (request.getId() == null) {
            npc.setId(UUID.randomUUID());
            npc.setNew(true);
        } else {
            npc.setId(request.getId());
            npc.setNew(false);
        }
        npc.setRoomId(request.getRoomId());
        npc.setName(request.getName());
        npc.setDescription(request.getDescription());
        npc.setType(request.getType() != null ? request.getType().name() : null);
        npc.setClazzCode(request.getClazzCode());
        npc.setRaceCode(request.getRaceCode());
        npc.setArmoryClass(request.getArmoryClass());
        npc.setSpeed(request.getSpeed());
        npc.setInitiative(request.getInitiative());
        npc.setImgUrl(request.getImgUrl());
        npc.setCreatedBy(request.getCreatedBy());
        npc.setCreatedAt(LocalDateTime.now());
        Npc saved = npcRepository.save(npc);
        NpcDto dto = npcDtoMapper.toDto(saved);
        enrichRaceAndClass(dto);
        return dto;
    }

    public List<NpcDto> findByRoomId(UUID roomId) {
        List<NpcDto> dtos = npcDtoMapper.toDto(npcRepository.findByRoomId(roomId));
        dtos.forEach(this::enrichRaceAndClass);
        return dtos;
    }

    public NpcDto findById(UUID id) {
        Npc npc = npcRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Npc not found by id " + id));
        NpcDto dto = npcDtoMapper.toDto(npc);
        enrichRaceAndClass(dto);
        return dto;
    }

    public void deleteById(UUID id) {
        npcRepository.deleteById(id);
    }

    private void enrichRaceAndClass(NpcDto dto) {
        if (dto.getRaceCode() != null && dto.getRoomId() != null) {
            RaceDto race = raceIntegrationService.getRaceByCode(dto.getRaceCode(), dto.getRoomId());
            RaceInfoDto raceInfo = new RaceInfoDto();
            raceInfo.setCode(race.getCode());
            raceInfo.setName(race.getName());
            raceInfo.setSpeciesCode(race.getSpeciesCode());
            raceInfo.setImgUrl(race.getImgUrl());
            raceInfo.setTraits(race.getStats().getTraits());
            dto.setRaceInfo(raceInfo);
        }
        if (dto.getClazzCode() != null && dto.getRoomId() != null) {
            ClazzDto clazz = clazzIntegrationService.getClassByCode(dto.getClazzCode(), dto.getRoomId());
            ClassInfoDto classInfo = new ClassInfoDto();
            classInfo.setCode(clazz.getCode());
            classInfo.setName(clazz.getName());
            dto.setClazzInfo(classInfo);
        }
    }
}

