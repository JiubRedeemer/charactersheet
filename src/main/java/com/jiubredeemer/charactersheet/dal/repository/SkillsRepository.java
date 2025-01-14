package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface SkillsRepository extends CrudRepository<Skill, UUID> {
    List<Skill> findByCharacterId(UUID characterId);

    List<Skill> findByCharacterIdIn(Collection<UUID> characterIds);
}
