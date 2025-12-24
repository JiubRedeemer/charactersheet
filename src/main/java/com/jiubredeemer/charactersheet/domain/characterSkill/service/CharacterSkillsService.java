package com.jiubredeemer.charactersheet.domain.characterSkill.service;

import com.jiubredeemer.charactersheet.dal.entity.CharacterSkills;
import com.jiubredeemer.charactersheet.dal.repository.CharacterSkillsRepository;
import com.jiubredeemer.charactersheet.domain.characterSkill.dto.CharacterSkillsDto;
import com.jiubredeemer.charactersheet.domain.characterSkill.mapper.CharacterSkillsDtoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterSkillsService {
    private static final Logger log = LoggerFactory.getLogger(CharacterSkillsService.class);
    private final CharacterSkillsDtoMapper mapper;
    private final CharacterSkillsRepository repository;

    public CharacterSkillsDto saveCharacterSkill(CharacterSkillsDto characterSkillsDto) {
        characterSkillsDto.setId(UUID.randomUUID());
        if (characterSkillsDto.getCurrentCharges() == null) {
            characterSkillsDto.setCurrentCharges(characterSkillsDto.getCharges());
        }
        return mapper.toDto(repository.save(mapper.toEntity(characterSkillsDto, true)));
    }

    public CharacterSkillsDto updateCharacterSkill(CharacterSkillsDto characterSkillsDto) {
        CharacterSkills entity = mapper.toEntity(characterSkillsDto, false);
        return mapper.toDto(repository.save(entity));
    }

    public List<CharacterSkillsDto> findByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findByCharacterId(characterId));
    }

    public void deleteCharacterSkill(UUID characterId, UUID id) {
        repository.findByIdAndCharacterId(id, characterId).orElseThrow();
        repository.deleteById(id);
    }

    public CharacterSkillsDto updateCharacterSkill(UUID id, CharacterSkillsDto characterSkillsDto) {
        characterSkillsDto.setId(id);
        return mapper.toDto(repository.save(mapper.toEntity(characterSkillsDto, false)));
    }

    public CharacterSkillsDto useCharacterSkill(UUID characterId, UUID id) {
        final CharacterSkills characterSkills = repository.findByIdAndCharacterId(id, characterId).orElseThrow();
        characterSkills.setCurrentCharges(characterSkills.getCurrentCharges() > 0 ? characterSkills.getCurrentCharges() - 1 : characterSkills.getCurrentCharges());
        return mapper.toDto(repository.save(characterSkills));
    }
}
