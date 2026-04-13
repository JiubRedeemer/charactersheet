CREATE TABLE charactersheet.pet (
    id                 uuid NOT NULL,
    character_id       uuid NOT NULL,
    name               text NOT NULL,
    age                int8,
    description        text,
    avatar             text,
    max_hp             int8 NOT NULL DEFAULT 0,
    current_hp         int8 NOT NULL DEFAULT 0,
    armor_class        int4,
    speed              int4,
    size               text,
    creature_type      text,
    proficiency_bonus  int4,
    senses             text,
    languages          text,
    is_summoned        boolean NOT NULL DEFAULT false,
    is_active          boolean NOT NULL DEFAULT true,
    created_at         timestamp NOT NULL DEFAULT now(),
    updated_at         timestamp NOT NULL DEFAULT now(),
    deleted_at         timestamp NULL,
    PRIMARY KEY (id)
);

CREATE TABLE charactersheet.pet_ability (
    id           uuid NOT NULL,
    pet_id       uuid NOT NULL,
    ability_code text NOT NULL,
    bonus_value  int8 NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE charactersheet.pet_skill (
    id           uuid NOT NULL,
    pet_id       uuid NOT NULL,
    name         text NOT NULL,
    description  text,
    created_at   timestamp NOT NULL DEFAULT now(),
    updated_at   timestamp NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
);

ALTER TABLE charactersheet.pet
    ADD CONSTRAINT FKpet_324981 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);

ALTER TABLE charactersheet.pet_ability
    ADD CONSTRAINT FKpet_ability_421741 FOREIGN KEY (pet_id) REFERENCES charactersheet.pet (id);

ALTER TABLE charactersheet.pet_skill
    ADD CONSTRAINT FKpet_skill_514812 FOREIGN KEY (pet_id) REFERENCES charactersheet.pet (id);

CREATE INDEX idx_pet_character_id ON charactersheet.pet (character_id);
CREATE INDEX idx_pet_deleted_at ON charactersheet.pet (deleted_at);
CREATE INDEX idx_pet_ability_pet_id ON charactersheet.pet_ability (pet_id);
CREATE UNIQUE INDEX uidx_pet_ability_pet_code ON charactersheet.pet_ability (pet_id, ability_code);
CREATE INDEX idx_pet_skill_pet_id ON charactersheet.pet_skill (pet_id);
