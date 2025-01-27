package com.jiubredeemer.charactersheet.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "character", schema = "charactersheet")
public class Character implements Persistable<UUID> {
    @Id
    private UUID id;
    private UUID userId;
    private UUID roomId;
    private String name;
    private String clazzCode;
    private String raceCode;
    private Integer proficiencyBonus;
    private Integer armoryClass;
    private Integer bonusArmoryClass;
    private Integer speed;
    private Integer bonusSpeed;
    private Integer inspiration;
    private Integer initiative;
    private Integer bonusInitiative;


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
