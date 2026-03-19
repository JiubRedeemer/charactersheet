package com.jiubredeemer.charactersheet.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(name = "npc", schema = "charactersheet")
public class Npc implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID roomId;
    private String name;
    private String description;
    private String type;
    private Boolean visible;
    private Boolean unique;
    private String clazzCode;
    private String raceCode;
    private String armoryClass;
    private String speed;
    private Integer initiative;
    private String imgUrl;
    private UUID createdBy;
    private LocalDateTime createdAt;

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

