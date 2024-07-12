create table if not exists credmarg.admins
(
    id         int auto_increment
        primary key,
    created_at datetime default CURRENT_TIMESTAMP not null,
    updated_at datetime default CURRENT_TIMESTAMP not null,
    created_by varchar(255)                       null,
    email      varchar(255)                       not null,
    name       varchar(255)                       not null,
    password   varchar(255)                       not null,
    updated_by varchar(255)                       null,
    constraint UK_47bvqemyk6vlm0w7crc3opdd4
        unique (email)
);

create table if not exists credmarg.employee
(
    id          int auto_increment
        primary key,
    created_at  datetime default CURRENT_TIMESTAMP not null,
    ctc         bigint                             null,
    updated_at  datetime default CURRENT_TIMESTAMP not null,
    created_by  varchar(255)                       null,
    designation varchar(255)                       not null,
    email       varchar(255)                       not null,
    name        varchar(255)                       not null,
    updated_by  varchar(255)                       null,
    constraint UK_fopic1oh5oln2khj8eat6ino0
        unique (email)
);

create table if not exists credmarg.vendor
(
    id         int auto_increment
        primary key,
    created_at datetime default CURRENT_TIMESTAMP not null,
    updated_at datetime default CURRENT_TIMESTAMP not null,
    created_by varchar(255)                       null,
    email      varchar(255)                       not null,
    name       varchar(255)                       not null,
    updated_by varchar(255)                       null,
    upi        varchar(255)                       null,
    constraint UK_jyjmopegfp4iape655lu75sml
        unique (email)
);

/*
    PWD: Rush#123
*/
insert ignore into admins(id,
                          created_at,
                          updated_at,
                          created_by,
                          email,
                          name,
                          password,
                          updated_by)
values (1,
        "2024-07-10 18:39:48",
        "2024-07-10 18:39:48",
        "SYSTEM_INSERT",
        "rushabh.makani@ril.com",
        "Rushabh Makani",
        "570e483578bc8122ca44b2cbb9b395f85c9b44fdb7c87e0cd1ba36eddcf0d990",
        "SYSTEM_INSERT");
