create sequence users_id_seq start 1 increment 1;

    create table users (
       id int8 not null,
        email varchar(255),
        name varchar(255),
        surname varchar(255),
        primary key (id)
    );
