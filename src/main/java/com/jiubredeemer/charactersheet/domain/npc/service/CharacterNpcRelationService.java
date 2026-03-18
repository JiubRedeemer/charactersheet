package com.jiubredeemer.charactersheet.domain.npc.service;

import com.jiubredeemer.charactersheet.dal.entity.CharacterNpcRelation;
import com.jiubredeemer.charactersheet.dal.entity.Npc;
import com.jiubredeemer.charactersheet.dal.repository.CharacterNpcRelationRepository;
import com.jiubredeemer.charactersheet.dal.repository.NpcRepository;
import com.jiubredeemer.charactersheet.domain.npc.dto.CharacterNpcRelationDto;
import com.jiubredeemer.charactersheet.domain.npc.dto.NpcDto;
import com.jiubredeemer.charactersheet.domain.npc.dto.RelationTypeEnum;
import com.jiubredeemer.charactersheet.domain.npc.dto.SaveCharacterNpcRelationRequest;
import com.jiubredeemer.charactersheet.domain.npc.mapper.NpcDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterNpcRelationService {

    private final CharacterNpcRelationRepository relationRepository;
    private final NpcRepository npcRepository;
    private final NpcDtoMapper npcDtoMapper;

    public CharacterNpcRelationDto save(SaveCharacterNpcRelationRequest request) {
        CharacterNpcRelation relation = new CharacterNpcRelation();
        relation.setId(request.getId() == null ? UUID.randomUUID() : request.getId());
        relation.setCharacterId(request.getCharacterId());
        relation.setNpcId(request.getNpcId());
        relation.setNote(request.getNote());
        relation.setRelationType(request.getRelationType() != null ? request.getRelationType().name() : null);
        relation.setNew(true);
        CharacterNpcRelation saved = relationRepository.save(relation);
        return toDto(saved);
    }

    public void deleteById(UUID id) {
        relationRepository.deleteById(id);
    }

    public List<NpcDto> findByCharacterId(UUID characterId) {
        List<CharacterNpcRelation> relations = relationRepository.findByCharacterId(characterId);
        List<Npc> npcs = relations.stream()
                .map(CharacterNpcRelation::getNpcId)
                .map(npcId -> npcRepository.findById(npcId)
                        .orElseThrow(() -> new NotFoundException("Npc not found by id " + npcId)))
                .toList();
        return npcDtoMapper.toDto(npcs);
    }

    public List<NpcDto> getByRelationType(UUID characterId, RelationTypeEnum relationType) {
        List<CharacterNpcRelation> relations = relationRepository
                .findByCharacterIdAndRelationType(characterId, relationType.name());
        List<Npc> npcs = relations.stream()
                .map(CharacterNpcRelation::getNpcId)
                .map(npcId -> npcRepository.findById(npcId)
                        .orElseThrow(() -> new NotFoundException("Npc not found by id " + npcId)))
                .toList();
        return npcDtoMapper.toDto(npcs);
    }

    private CharacterNpcRelationDto toDto(CharacterNpcRelation relation) {
        CharacterNpcRelationDto dto = new CharacterNpcRelationDto();
        dto.setId(relation.getId());
        dto.setCharacterId(relation.getCharacterId());
        dto.setNpcId(relation.getNpcId());
        dto.setNote(relation.getNote());
        dto.setRelationType(relation.getRelationType() != null ? RelationTypeEnum.valueOf(relation.getRelationType()) : null);
        return dto;
    }
}

