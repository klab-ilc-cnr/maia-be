--- USERS
CREATE TABLE public.users
(
    id serial,
    name character varying(30),
    surname character varying(30),
    email character varying(50),
    username character varying(50),
    created timestamp without time zone,
    updated timestamp without time zone,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);