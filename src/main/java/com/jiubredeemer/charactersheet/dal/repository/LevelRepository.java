package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.Level;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LevelRepository extends CrudRepository<Level, UUID> {
}
