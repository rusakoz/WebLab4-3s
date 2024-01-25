CREATE TABLE users(
    id bigserial primary key,
    name text not null unique,
    password text not null,
    date_creation date not null
);

CREATE TABLE results(
    id bigserial primary key,
    x double precision not null,
    y double precision not null,
    r double precision not null,
    hit boolean not null,
    date timestamp not null,
    exec_time bigint not null,
    user_id bigint not null
);

