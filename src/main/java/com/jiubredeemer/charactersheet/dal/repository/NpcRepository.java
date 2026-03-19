package com.jiubredeemer.charactersheet.dal.repository;

import com.jiubredeemer.charactersheet.dal.entity.Npc;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NpcRepository extends CrudRepository<Npc, UUID> {

    @Query("select n.* from charactersheet.npc n " +
            "where n.room_id = :roomId order by n.name desc")
    List<Npc> findByRoomId(UUID roomId);

    @Query("select distinct(n.*) from charactersheet.npc n " +
            "left join charactersheet.character_npc_relations cnr  on n.id = cnr.npc_id " +
            "where n.room_id = :roomId " +
            "and (" +
            "n.visible = true or " +
            "n.created_by = :createdBy or " +
            "cnr.character_id = :characterId " +
            ") order by n.name desc")
    List<Npc> findByRoomIdVisibleForUser(@Param("roomId") UUID roomId,
                                         @Param("createdBy") UUID createdBy,
                                         @Param("characterId") UUID characterId);
}

