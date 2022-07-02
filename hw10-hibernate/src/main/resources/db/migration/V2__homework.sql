create table address
(
    id   bigint not null primary key,
    address varchar(250)
);


create table client_phone
(
    id   bigint not null primary key,
    phone varchar(32),
    client_id bigint
);

alter table client add address_id bigint;