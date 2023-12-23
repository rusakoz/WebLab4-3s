CREATE TABLE users(
    id bigserial primary key,
    name text not null unique,
    password text not null,
    date_creation date not null
);

