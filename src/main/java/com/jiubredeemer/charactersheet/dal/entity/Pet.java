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
@Table(name = "pet", schema = "charactersheet")
public class Pet implements Persistable<UUID> {
    @Id
    private UUID id;
    private UUID characterId;
    private String name;
    private Long age;
    private String description;
    private String avatar;
    private Long maxHp;
    private Long currentHp;
    private Integer armorClass;
    private Integer speed;
    private String size;
    private String creatureType;
    private Integer proficiencyBonus;
    private String senses;
    private String languages;
    private Boolean isSummoned;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

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
