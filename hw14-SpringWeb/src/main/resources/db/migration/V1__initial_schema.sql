create table client
(
    id           bigserial   not null,
    name         varchar(50) not NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

create table address
(
    id          bigserial   not null,
    client_id   int,
    address     varchar(250) not NULL,
    CONSTRAINT pk_address PRIMARY KEY (id),
    CONSTRAINT fk_address$client FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE INDEX idx_address$client_id ON address(client_id);

create table client_phone
(
    id          bigserial   not null,
    client_id   int         not null,
    phone       varchar(50) not NULL,
    CONSTRAINT fk_client_phone$client FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE INDEX idx_client_phone$client_id ON client_phone(client_id);
