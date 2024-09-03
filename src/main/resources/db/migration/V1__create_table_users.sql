CREATE TABLE users (
    id bigint not null auto_increment,
    name VARCHAR(255),
    email varchar(255),
    password VARCHAR(255) ,
    bio VARCHAR(255) ,
    created_at datetime,
    updated_at datetime,
    primary key (id)
);