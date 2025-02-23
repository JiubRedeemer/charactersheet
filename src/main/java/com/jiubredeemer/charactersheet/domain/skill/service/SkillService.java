package com.jiubredeemer.charactersheet.domain.skill.service;

import com.jiubredeemer.charactersheet.dal.entity.Skill;
import com.jiubredeemer.charactersheet.dal.repository.SkillsRepository;
import com.jiubredeemer.charactersheet.domain.skill.dto.UpdateMasteryRequest;
import com.jiubredeemer.charactersheet.domain.skill.dto.SkillDto;
import com.jiubredeemer.charactersheet.domain.skill.mapper.SkillDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillDtoMapper mapper;
    private final SkillsRepository repository;

    public List<SkillDto> saveAllSkills(List<SkillDto> skills) {
        return mapper.toDto(repository.saveAll(mapper.toEntity(skills, true)));
    }

    public List<SkillDto> findAllByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findByCharacterId(characterId));
    }

    public List<SkillDto> findAllByCharacterIds(Set<UUID> characterIds) {
        return mapper.toDto(repository.findByCharacterIdIn(characterIds));
    }

    public void updateMastery(UUID characterId, String code, UpdateMasteryRequest request) {
        repository.findByCharacterIdAndCode(characterId, code).ifPresentOrElse(skill -> {
            if (!request.getIsMastery()) {
                repository.delete(skill);
            }
        }, () -> {
            if (request.getIsMastery()) {
                final Skill skill = new Skill();
                skill.setNew(true);
                skill.setId(UUID.randomUUID());
                skill.setCharacterId(characterId);
                skill.setCode(code);
                repository.save(skill);
            }
        });
    }
}
