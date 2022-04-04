--- USERS
CREATE TABLE public.users
(
    id         serial                not null,
    name       character varying(30) not null,
    surname    character varying(30) not null,
    email      character varying(50) not null,
--     username character varying(50) not null,
    created    timestamp without time zone,
    created_by bigint,
    updated    timestamp without time zone,
    updated_by bigint,
    active     boolean default true,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

--- USER ROLES
CREATE TABLE public.user_roles
(
    user_id bigint NOT NULL,
    role    character varying(30) COLLATE pg_catalog."default"
);

ALTER TABLE user_roles
    add CONSTRAINT FK_user_roles_user_id FOREIGN KEY (user_id) REFERENCES public.users (id);

--- WORKSPACES
CREATE TABLE public.workspaces
(
    id         serial       NOT NULL,
    name       varchar(100) NOT NULL,
    created    timestamp without time zone,
    created_by bigint,
    updated    timestamp without time zone,
    updated_by bigint,
    CONSTRAINT workspaces_pk PRIMARY KEY (id)
);

--- workspace_items;

CREATE TABLE public.workspaces_tiles
(
    id           serial NOT NULL,
    workspace_id bigint NOT NULL,
    tile_id      bigint NOT NULL,
    "rank"       bigint NOT NULL DEFAULT 0,
    CONSTRAINT workspace_tiles_pk PRIMARY KEY (id),
    CONSTRAINT workspace_tiles_un UNIQUE (workspace_id, tile_id, "rank"),
    CONSTRAINT workspace_tiles_fk FOREIGN KEY (workspace_id) REFERENCES public.workspaces (id)
);


--- TILE

CREATE TABLE public.tiles
(
    id           serial4      NOT NULL,
    content_type varchar(100) NOT NULL,
    content_id   bigint       NOT NULL,
    x_position   decimal      NOT NULL DEFAULT 0,
    y_position   decimal      NOT NULL DEFAULT 0,
    x_size       decimal      NOT NULL DEFAULT 10,
    y_size       decimal      NOT NULL DEFAULT 10,
    CONSTRAINT tile_pk PRIMARY KEY (id)
);


--- TEXT
CREATE TABLE public.texts
(
    id       serial4 NOT NULL,
    readonly boolean default false,
    created    timestamp without time zone,
    created_by bigint,
    updated    timestamp without time zone,
    updated_by bigint,
    CONSTRAINT texts_pk PRIMARY KEY (id)
);

--- LAYER
CREATE TABLE public.layers
(
    id      serial4 NOT NULL,
    text_id bigint  NOT NULL,
    CONSTRAINT layers_pk PRIMARY KEY (id)
);

--- ANNOTATION
CREATE TABLE public.annotations
(
    id       serial4 NOT NULL,
    layer_id bigint  NOT NULL,
    CONSTRAINT annotations_pk PRIMARY KEY (id)
);


-- -- LANGUAGES
-- CREATE TABLE public.languages
-- (
--     id         serial                not null,
--     name       character varying(30) not null,
--     created    timestamp,
--     created_by bigint,
--     updated    timestamp,
--     updated_by bigint,
--     CONSTRAINT languages_pkey PRIMARY KEY (id)
-- );
--
--
-- -- USER_LANGUAGES
-- CREATE TABLE public.user_languages
-- (
--     user_id      bigint not null,
--     languages_id bigint not null,
--     created      timestamp,
--     created_by   bigint,
--     updated      timestamp,
--     updated_by   bigint,
--     CONSTRAINT pkey_user_languages PRIMARY KEY (user_id, languages_id)
-- );
