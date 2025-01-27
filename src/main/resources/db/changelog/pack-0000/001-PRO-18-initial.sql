CREATE TABLE charactersheet.room
(
    room_id   uuid NOT NULL,
    owner_id  uuid NOT NULL,
    rule_type text NOT NULL,
    PRIMARY KEY (room_id)
);
CREATE TABLE charactersheet.character
(
    id                uuid NOT NULL,
    room_id           uuid NOT NULL,
    user_id           uuid NOT NULL,
    name              text NOT NULL,
    clazz_code        text NOT NULL,
    race_code         text NOT NULL,
    proficiency_bonus int4 NOT NULL,
    armory_class      int4 NOT NULL,
    speed             int4 NOT NULL,
    inspiration       int4 NOT NULL,
    initiative        int4 NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE charactersheet.character_bio
(
    character_id  uuid NOT NULL,
    age           int8,
    height        int8,
    weight        int8,
    attachments   text,
    history       text,
    ideals        text,
    personality   text,
    relationships text,
    weaknesses    text,
    PRIMARY KEY (character_id)
);
CREATE TABLE charactersheet.ability
(
    id           uuid NOT NULL,
    character_id uuid NOT NULL,
    code         text NOT NULL,
    value        int8 NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE charactersheet.level
(
    character_id uuid NOT NULL,
    level        int8 NOT NULL,
    xp           int8 NOT NULL,
    PRIMARY KEY (character_id)
);
CREATE TABLE charactersheet.health
(
    character_id uuid NOT NULL,
    current_hp   int8 NOT NULL,
    max_hp       int8 NOT NULL,
    temp_hp      int8 NOT NULL,
    PRIMARY KEY (character_id)
);
CREATE TABLE charactersheet.skill
(
    id           uuid NOT NULL,
    character_id uuid NOT NULL,
    code         text NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE charactersheet.character
    ADD CONSTRAINT FKcharacter712808 FOREIGN KEY (room_id) REFERENCES charactersheet.room (room_id);
ALTER TABLE charactersheet.character_bio
    ADD CONSTRAINT FKcharacter_618589 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);
ALTER TABLE charactersheet.ability
    ADD CONSTRAINT FKabilities379312 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);
ALTER TABLE charactersheet.level
    ADD CONSTRAINT FKlevel862424 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);
ALTER TABLE charactersheet.health
    ADD CONSTRAINT FKhealth267451 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);
ALTER TABLE charactersheet.skill
    ADD CONSTRAINT FKskills567252 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);
