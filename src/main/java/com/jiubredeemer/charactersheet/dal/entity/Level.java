package com.jiubredeemer.charactersheet.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "level", schema = "charactersheet")
public class Level implements Persistable<UUID> {
    @Id
    private UUID characterId;
    private Long level;
    private Long xp;

    //-----META_FIELDS------
    @Transient
    @JsonIgnore
    private boolean newEntity;

    @Override
    public UUID getId() {
        return characterId;
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
