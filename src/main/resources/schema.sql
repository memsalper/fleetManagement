create table if not exists vehicles
(
    id    uuid        not null
        constraint vehicles_pkey
            primary key,
    plate varchar(11) not null
        constraint uk_lle7kf4cbmwh6twthj1tik9us
            unique
);
COMMIT;

alter table vehicles
    owner to postgres;
COMMIT;

create table if not exists delivery_points
(
    id         uuid         not null
        constraint delivery_points_pkey
            primary key,
    point_id   serial
        constraint uk_5b5o3lant0hl0ohx0th7l50f3
            unique,
    point_name varchar(100) not null
);
COMMIT;

alter table delivery_points
    owner to postgres;
COMMIT;


create table if not exists packages
(
    id                uuid           not null
        constraint packages_pkey
            primary key,
    package_barcode   varchar(20)    not null
        constraint idx_package_barcode
            unique,
    package_status    integer,
    volumetric_weight numeric(15, 2) not null,
    delivery_point_id serial
        constraint fkkddkchfnbyi8ddupd2aqkhaqx
            references delivery_points (point_id)
);
COMMIT;

alter table packages
    owner to postgres;
COMMIT;

create table if not exists bags
(
    id                uuid        not null
        constraint bags_pkey
            primary key,
    bag_barcode       varchar(20) not null
        constraint idx_bag_barcode
            unique,
    state             smallint    not null,
    delivery_point_id serial
        constraint fk2d52l2hnqqlg18uyhkg3b67xv
            references delivery_points (point_id)
);
COMMIT;

alter table bags
    owner to postgres;
COMMIT;

create table if not exists log_shipments
(
    id                uuid        not null
        constraint log_shipments_pkey
            primary key,
    barcode           varchar(20) not null,
    delivery_point_id serial
        constraint fk20yuwyybt8aoiriarwu4ndur7
            references delivery_points (point_id)
);
COMMIT;

alter table log_shipments
    owner to postgres;
COMMIT;



create table if not exists packages_to_bag
(
    bag_barcode     varchar(20) not null,
    package_barcode varchar(20) not null,
    id              uuid        not null,
    constraint packages_to_bag_pkey
        primary key (bag_barcode, package_barcode)
);
COMMIT;

alter table packages_to_bag
    owner to postgres;
COMMIT;



