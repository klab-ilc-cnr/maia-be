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
    attributes jsonb,
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


-- -- LANGUAGES
-- CREATE TABLE public.users_languages
-- (
--     id      serial                not null,
--     user_id bigint                not null,
--     code    character varying(50) not null,
--     name    character varying(50) not null,
--     CONSTRAINT languages_pkey PRIMARY KEY (id)
-- );


-- -- USER_LANGUAGES
-- CREATE TABLE public.users_languages
-- (
--     user_id      bigint not null,
--     language_id bigint not null,
--     CONSTRAINT pkey_user_languages PRIMARY KEY (user_id, languages_id)
-- );
