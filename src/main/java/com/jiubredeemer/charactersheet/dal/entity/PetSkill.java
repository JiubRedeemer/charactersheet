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
@Table(name = "pet_skill", schema = "charactersheet")
public class PetSkill implements Persistable<UUID> {
    @Id
    private UUID id;
    private UUID petId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
