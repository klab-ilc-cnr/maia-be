--- USERS
CREATE TABLE public.users
(
    id         serial                not null,
    name       character varying(30) not null,
    surname    character varying(30) not null,
    email      character varying(50) not null,
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

--- WORKSPACES
CREATE TABLE public.workspaces
(
    id         serial       NOT NULL,
    name       varchar(100) NOT NULL,
    note       varchar(500),
    created    timestamp without time zone NOT NULL,
    created_by bigint       NOT NULL,
    updated    timestamp without time zone NOT NULL,
    updated_by bigint       NOT NULL,
    layout     text,
    CONSTRAINT workspaces_pk PRIMARY KEY (id)
);

--- TILE
CREATE TABLE public.tiles
(
    id           serial      NOT NULL,
    workspace_id bigint      NOT NULL,
    tile_config  TEXT,
    content_id   bigint      NOT NULL,
    type         varchar(50) NOT NULL,
    created      timestamp without time zone NOT NULL,
    created_by   bigint      NOT NULL,
    updated      timestamp without time zone NOT NULL,
    updated_by   bigint      NOT NULL,
    CONSTRAINT tile_pk PRIMARY KEY (id),
    CONSTRAINT workspace_tiles_fk FOREIGN KEY (workspace_id) REFERENCES public.workspaces (id)
);

--- LAYER
CREATE TABLE public.layers
(
    id          serial      NOT NULL,
    name        varchar(50) NOT NULL,
    color       varchar(50) NOT NULL,
    description varchar(500),
    CONSTRAINT layers_pk PRIMARY KEY (id),
    CONSTRAINT layers_un UNIQUE ("name")
);

--- FEATURE
CREATE TABLE public.features
(
    id          serial      NOT NULL,
    name        varchar(50) NOT NULL,
    description varchar(500),
    type        varchar(50) NOT NULL,
    tagset_id   bigint,
    layer_id    bigint      NOT NULL,
    CONSTRAINT features_pk PRIMARY KEY (id),
    CONSTRAINT features_un UNIQUE ("name"),
    CONSTRAINT features_tagset_fk FOREIGN KEY (tagset_id) REFERENCES public.tagsets (id),
    CONSTRAINT layer_tagset_fk FOREIGN KEY (layer_id) REFERENCES public.layers (id)
);

--- TAGSET
CREATE TABLE public.tagsets
(
    id          serial      NOT NULL,
    name        varchar(50) NOT NULL,
    description varchar(500),
    CONSTRAINT tagsets_pk PRIMARY KEY (id),
    CONSTRAINT tagsets_un UNIQUE ("name")
);

--- TAGSET VALUES
CREATE TABLE public.tagset_values
(
    id          serial      NOT NULL,
    name        varchar(50) NOT NULL,
    description varchar(500),
    tagset_id   bigint      NOT NULL,
    CONSTRAINT tagset_values_pk PRIMARY KEY (id),
    CONSTRAINT tagsets_tagsets_value_fk FOREIGN KEY (tagset_id) REFERENCES public.tagsets (id)
);

--- ANNOTATION FEATURE
CREATE TABLE public.annotation_feature
(
    annotation_id bigint NOT NULL,
    feature_id    bigint NOT NULL,
    CONSTRAINT FK_annotation_feature_feature_id FOREIGN KEY (feature_id) REFERENCES public.features (id),
    CONSTRAINT annotation_feature_un UNIQUE (annotation_id, feature_id)
);

--- RELATIONS
CREATE TABLE public.relations
(
    id               serial      NOT NULL,
    name             varchar(50) NOT NULL,
    description      varchar(500),
    src_layer_id    bigint      NOT NULL,
    target_layer_id bigint      NOT NULL,
    src_annotation_id       bigint      NOT NULL,
    target_annotation_id    bigint      NOT NULL,
    CONSTRAINT relations_pk PRIMARY KEY (id),
    CONSTRAINT realtions_src_layer_fk FOREIGN KEY (src_tagset_id) REFERENCES public.tagsets (id),
    CONSTRAINT realtions_target_layer_fk FOREIGN KEY (target_tagset_id) REFERENCES public.tagsets (id)
);

/*

--- TEXT
CREATE TABLE public.texts
(
    id               serial4 NOT NULL,
    readonly         boolean default false,
    created          timestamp without time zone,
    created_by       bigint,
    updated          timestamp without time zone,
    updated_by       bigint,
    external_node_id bigint  NOT NULL,
    CONSTRAINT texts_pk PRIMARY KEY (id)
);

--- ANNOTATION
CREATE TABLE public.annotations
(
    id               serial4 NOT NULL,
    layer_id         bigint  NOT NULL,
    external_node_id bigint  NOT NULL,
    CONSTRAINT annotations_pk PRIMARY KEY (id)
);*/
