package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.CharacterNpcRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharacterNpcRelationRepository extends CrudRepository<CharacterNpcRelation, UUID> {

    List<CharacterNpcRelation> findByCharacterId(UUID characterId);

    List<CharacterNpcRelation> findByCharacterIdAndRelationType(UUID characterId, String relationType);
}

