--- USERS
CREATE TABLE public.users
(
    id      serial                not null,
    name    character varying(30) not null,
    surname character varying(30) not null,
    email   character varying(50) not null,
--     username character varying(50) not null,
    created timestamp without time zone,
    created_by              bigint,
    updated timestamp without time zone,
    updated_by              bigint,
    role    character varying(30) not null,
    active  bit default 1::bit,
    CONSTRAINT users_pkey PRIMARY KEY (id)
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
