CREATE TABLE charactersheet.npc (
                                    id           uuid NOT NULL,
                                    room_id      uuid NOT NULL,
                                    name         text NOT NULL,
                                    description  text,
                                    type         text NOT NULL,
                                    clazz_code   text,
                                    race_code    text,
                                    armory_class text,
                                    speed        text,
                                    initiative   int4,
                                    img_url      text,
                                    created_by   uuid NOT NULL,
                                    created_at   timestamp NOT NULL DEFAULT now(),
                                    PRIMARY KEY (id));

CREATE TABLE charactersheet.character_npc_relations (
                                                        id            uuid NOT NULL,
                                                        character_id  uuid NOT NULL,
                                                        npc_id        uuid NOT NULL,
                                                        note          text,
                                                        relation_type text NOT NULL,
                                                        PRIMARY KEY (id));

ALTER TABLE charactersheet.character_npc_relations ADD CONSTRAINT FKcharacter_771938 FOREIGN KEY (npc_id) REFERENCES charactersheet.npc (id);
ALTER TABLE charactersheet.character_npc_relations ADD CONSTRAINT FKcharacter_32348 FOREIGN KEY (character_id) REFERENCES charactersheet.character (id);



