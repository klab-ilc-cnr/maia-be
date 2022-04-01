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
    id      serial NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT workspaces_pk PRIMARY KEY (id)
);

--- workspace_items;

CREATE TABLE public.workspace_windows
(
    id           serial NOT NULL,
    workspace_id bigint NOT NULL,
    window_id    bigint NOT NULL,
    "rank"       bigint NOT NULL DEFAULT 0,
    CONSTRAINT workspace_windows_pk PRIMARY KEY (id),
    CONSTRAINT workspace_windows_un UNIQUE (workspace_id, window_id, "rank"),
    CONSTRAINT workspace_windows_fk FOREIGN KEY (workspace_id) REFERENCES public.workspaces (id)
);


--- WINDOW

CREATE TABLE public.window
(
    id         serial4 NOT NULL,
    "type"     varchar NOT NULL,
    x_position decimal NOT NULL DEFAULT 0,
    y_position decimal NOT NULL DEFAULT 0,
    x_size     decimal NOT NULL DEFAULT 10,
    y_size     decimal NOT NULL DEFAULT 10,
    CONSTRAINT window_pk PRIMARY KEY (id)
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
