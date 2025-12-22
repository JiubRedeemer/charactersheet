CREATE TABLE charactersheet.character_skills (
                                                id                uuid NOT NULL,
                                                character_id      uuid NOT NULL,
                                                name              text NOT NULL,
                                                cast_time         text,
                                                distance          text,
                                                description       text,
                                                short_description text NOT NULL,
                                                charges           int4,
                                                current_charges           int4,
                                                charges_refill    text,
                                                img_url           text,
                                                PRIMARY KEY (id));
ALTER TABLE charactersheet.character_skills ADD CONSTRAINT FKcharacter_736046 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);
