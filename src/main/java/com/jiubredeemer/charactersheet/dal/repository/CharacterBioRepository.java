package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.CharacterBio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterBioRepository extends CrudRepository<CharacterBio, UUID> {
}
