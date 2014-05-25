drop table if exists AUTOSERVICE_CUSTOMER;
create table AUTOSERVICE_CUSTOMER (
    ID             bigserial,
    FIRST_NAME     varchar(50),
    MIDDLE_NAME    varchar(50),
    LAST_NAME      varchar(50) not null,
    --
    primary key (ID)
);

drop table if exists AUTOSERVICE_VEHICLE;
create table AUTOSERVICE_VEHICLE (
    ID             bigserial,
    REG_NUMBER     varchar(50) not null,
    CUSTOMER_ID    bigint not null,
    --
    primary key (ID)
);

drop table if exists AUTOSERVICE_AUTOSERVICE;
create table AUTOSERVICE_AUTOSERVICE (
    ID             bigserial,
    NAME           varchar(50) not null,
    ADDRESS        varchar(50),
    --
    primary key (ID)
);

drop table if exists AUTOSERVICE_ORDER;
create table AUTOSERVICE_ORDER (
    ID             bigserial,
    TIME           timestamp not null,
    AMOUNT         decimal(19, 2),
    VEHICLE_ID     bigint not null,
    AUTOSERVICE_ID bigint not null,
    --
    primary key (ID)
);

