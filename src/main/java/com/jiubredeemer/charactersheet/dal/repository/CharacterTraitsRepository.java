package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.CharacterTraits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharacterTraitsRepository extends CrudRepository<CharacterTraits, UUID> {
    List<CharacterTraits> findByCharacterId(UUID characterId);

    CharacterTraits findByCharacterIdAndId(UUID characterId, UUID id);
}
