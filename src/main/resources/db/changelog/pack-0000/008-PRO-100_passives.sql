CREATE TABLE charactersheet.character_traits (
                                                 id uuid NULL,
                                                 name text NOT NULL,
                                                 character_id uuid NOT NULL,
                                                 description text NOT NULL,
                                                 CONSTRAINT character_traits_pk PRIMARY KEY (id)
);
