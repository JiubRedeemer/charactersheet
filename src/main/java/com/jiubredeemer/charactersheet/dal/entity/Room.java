package com.jiubredeemer.charactersheet.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jiubredeemer.charactersheet.domain.rooms.dto.RuleTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "rooms", schema = "charactersheet")
public class Room implements Persistable<UUID> {
    @Id
    private UUID roomId;
    private UUID ownerId;
    private RuleTypeEnum ruleType;


    //-----META_FIELDS------
    @Transient
    @JsonIgnore
    private boolean newEntity;

    @Override
    public UUID getId() {
        return roomId;
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

