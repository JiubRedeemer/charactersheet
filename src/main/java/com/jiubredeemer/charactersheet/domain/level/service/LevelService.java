package com.jiubredeemer.charactersheet.domain.level.service;

import com.jiubredeemer.charactersheet.constants.LevelInfoEnum;
import com.jiubredeemer.charactersheet.dal.entity.Character;
import com.jiubredeemer.charactersheet.dal.repository.CharacterRepository;
import com.jiubredeemer.charactersheet.dal.repository.LevelRepository;
import com.jiubredeemer.charactersheet.domain.level.dto.LevelDto;
import com.jiubredeemer.charactersheet.domain.level.dto.UpdateCurrentXpRequest;
import com.jiubredeemer.charactersheet.domain.level.mapper.LevelDtoMapper;
import com.jiubredeemer.charactersheet.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelService {
    private static final Long MAX_XP_VALUE = 1000000L;
    private final LevelDtoMapper mapper;
    private final LevelRepository repository;
    private final CharacterRepository characterRepository;


    public LevelDto saveLevel(LevelDto levelDto) {
        return mapper.toDto(repository.save(mapper.toEntity(levelDto, true)));
    }

    public LevelDto saveLevel(LevelDto levelDto, Boolean isNew) {
        return mapper.toDto(repository.save(mapper.toEntity(levelDto, isNew)));
    }

    public LevelDto findByCharacterId(UUID characterId) {
        return mapper.toDto(repository.findById(characterId).orElseThrow(() ->
                new NotFoundException("LevelDto wasn'n found by characterId: " + characterId)));
    }

    public List<LevelDto> findByCharacterIds(Set<UUID> characterIds) {
        return mapper.toDto(repository.findByCharacterIdIn(characterIds));
    }

    public void updateCurrentXp(UUID characterId, UpdateCurrentXpRequest request) {
        if (request.getValue() == null || request.getValue() == 0) return;
        if (request.getValue() > MAX_XP_VALUE) throw new IllegalArgumentException("Xp count is too BIG");

        final LevelDto byCharacterId = findByCharacterId(characterId);
        byCharacterId.setXp(byCharacterId.getXp() + request.getValue());
        if(byCharacterId.getXp() < 0) byCharacterId.setXp(0L);
        saveLevel(byCharacterId, false);
    }

    public void levelUp(UUID characterId, Boolean force) {
        final LevelDto byCharacterId = findByCharacterId(characterId);
        if (byCharacterId.getLevel().equals(LevelInfoEnum.LVL_20.getLevel()))
            return; // FIXME: Сейчас максимальный хардкод
        if (byCharacterId.getXp() >= Objects.requireNonNull(LevelInfoEnum.getByLevel(byCharacterId.getLevel() + 1)).getXp()) {
            byCharacterId.setLevel(byCharacterId.getLevel() + 1);
            byCharacterId.setNextLevelXp(Objects.requireNonNull(LevelInfoEnum.getByLevel(byCharacterId.getLevel() + 1)).getXp());
            saveLevel(byCharacterId, false);
        } else if (byCharacterId.getXp() <= Objects.requireNonNull(LevelInfoEnum.getByLevel(byCharacterId.getLevel() + 1)).getXp() && force) {
            byCharacterId.setLevel(byCharacterId.getLevel() + 1);
            byCharacterId.setNextLevelXp(Objects.requireNonNull(LevelInfoEnum.getByLevel(byCharacterId.getLevel() + 1)).getXp());
            saveLevel(byCharacterId, false);
        } else {
            throw new IllegalArgumentException("Not enough XP for level up");
        }
        final Character character = characterRepository.findById(characterId).orElseThrow();
        character.setProficiencyBonus(Objects.requireNonNull(LevelInfoEnum.getByLevel(byCharacterId.getLevel())).getProficiencyBonus());
        character.setNew(false);
        characterRepository.save(character);
    }

    public void levelDown(UUID characterId) {
        final LevelDto byCharacterId = findByCharacterId(characterId);
        if (byCharacterId.getLevel().equals(LevelInfoEnum.LVL_1.getLevel())) return;
        byCharacterId.setNextLevelXp(Objects.requireNonNull(LevelInfoEnum.getByLevel(byCharacterId.getLevel())).getXp());
        byCharacterId.setLevel(byCharacterId.getLevel() - 1);
        saveLevel(byCharacterId, false);
        final Character character = characterRepository.findById(characterId).orElseThrow();
        character.setProficiencyBonus(Objects.requireNonNull(LevelInfoEnum.getByLevel(byCharacterId.getLevel())).getProficiencyBonus());
        character.setNew(false);
        characterRepository.save(character);
    }
}
