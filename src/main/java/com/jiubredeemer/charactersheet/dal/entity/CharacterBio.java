package com.jiubredeemer.charactersheet.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table(name = "character_bio", schema = "charactersheet")
public class CharacterBio implements Persistable<UUID> {

    @Id
    private UUID characterId;
    private Long age;
    private Long height;
    private Long weight;
    private String attachments;
    private String history;
    private String ideals;
    private String personality;
    private String relationships;
    private String weaknesses;

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
