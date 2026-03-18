package com.jiubredeemer.charactersheet.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "character_npc_relations", schema = "charactersheet")
public class CharacterNpcRelation implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID characterId;
    private UUID npcId;
    private String note;
    private String relationType;

    //-----META_FIELDS------
    @Transient
    @JsonIgnore
    private boolean newEntity;

    @Override
    public UUID getId() {
        return id;
    }

    public void setNew(boolean newInstance) {
        this.newEntity = newInstance;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return newEntity;
    }
}

