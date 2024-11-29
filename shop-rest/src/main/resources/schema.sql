CREATE SCHEMA IF NOT EXISTS electroShopDb;

DROP TABLE IF EXISTS counter cascade;
DROP TABLE IF EXISTS electro_item cascade;
DROP TABLE IF EXISTS electro_shop cascade;
DROP TABLE IF EXISTS electro_type cascade;
DROP TABLE IF EXISTS electro_type_employee cascade;
DROP TABLE IF EXISTS employee cascade;
DROP TABLE IF EXISTS position_type cascade;
DROP TABLE IF EXISTS purchase cascade;
DROP TABLE IF EXISTS purchase_type cascade;
DROP TABLE IF EXISTS shop cascade;

CREATE TABLE IF NOT EXISTS counter (
    "name" varchar(75) NOT NULL,
    currentid int8 NULL,
    CONSTRAINT counter_pkey PRIMARY KEY (name)
    );

CREATE TABLE IF NOT EXISTS shop (
    id_ int8 NOT NULL,
    "name" varchar(250) NOT NULL,
    address TEXT NOT NULL,
    CONSTRAINT shop_pkey PRIMARY KEY (id_)
    );

CREATE TABLE IF NOT EXISTS electro_type (
    id_ int8 NOT NULL,
    "name" varchar(150) NOT NULL,
    CONSTRAINT electro_type_pkey PRIMARY KEY (id_)
    );

CREATE TABLE IF NOT EXISTS position_type (
     id_ int8 NOT NULL,
     "name" varchar(150) NOT NULL,
    CONSTRAINT position_type_pkey PRIMARY KEY (id_)
    );

CREATE TABLE IF NOT EXISTS purchase_type (
     id_ int8 NOT NULL,
     "name" varchar(150) NOT NULL,
    CONSTRAINT purchase_type_pkey PRIMARY KEY (id_)
    );

CREATE TABLE IF NOT EXISTS employee (
    id_ int8 NOT NULL,
    lastname varchar(100) NOT NULL CHECK (length(trim(lastname)) > 0),
    firstname varchar(100) NOT NULL CHECK (length(trim(firstname)) > 0),
    patronymic varchar(100) NOT NULL CHECK (length(trim(patronymic)) > 0),
    birth_date date NOT NULL,
    position_id int8 NOT NULL REFERENCES position_type (id_),
    shop_id int8 NOT NULL REFERENCES shop (id_),
    gender bool NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id_)
    );

CREATE TABLE IF NOT EXISTS electro_item (
    id_ int8 NOT NULL,
    "name" varchar(150) NOT NULL,
    type_id int8 NOT NULL REFERENCES electro_type (id_),
    price int8 NOT NULL,
    "count" INTEGER NOT NULL,
    archive bool NOT NULL,
    text TEXT NOT NULL,
    CONSTRAINT electro_item_pkey PRIMARY KEY (id_)
    );

CREATE TABLE IF NOT EXISTS electro_shop (
    shop_id int8 NOT NULL REFERENCES shop (id_),
    electro_item_id int8 NOT NULL REFERENCES electro_item (id_),
    count_ INTEGER NOT NULL,
    CONSTRAINT electro_shop_pkey PRIMARY KEY (shop_id, electro_item_id)
    );

CREATE TABLE IF NOT EXISTS electro_type_employee (
    employee_id int8 NOT NULL REFERENCES employee (id_),
    electro_type_id int8 NOT NULL REFERENCES electro_type (id_),
    CONSTRAINT employee_electro_type_pkey PRIMARY KEY (employee_id, electro_type_id)
    );

CREATE TABLE IF NOT EXISTS purchase (
    id_ int8 NOT NULL,
    amount int8 NOT NULL,
    count_ int8 NOT NULL,
    electro_id int8 NOT NULL REFERENCES electro_item (id_),
    employee_id int8 NOT NULL REFERENCES employee (id_),
    purchase_date timestamp NOT NULL,
    type_id int8 NOT NULL REFERENCES purchase_type (id_),
    shop_id int8 NOT NULL REFERENCES shop (id_),
    CONSTRAINT purchase_pkey PRIMARY KEY (id_)
    );

