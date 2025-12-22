package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.CharacterSkills;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacterSkillsRepository extends CrudRepository<CharacterSkills, UUID> {
    List<CharacterSkills> findByCharacterId(UUID characterId);

    Optional<CharacterSkills> findByIdAndCharacterId(UUID id, UUID characterId);
}
