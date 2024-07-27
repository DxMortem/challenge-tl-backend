CREATE SCHEMA IF NOT EXISTS api AUTHORIZATION api;

DROP TABLE IF EXISTS api.product;

CREATE TABLE api.product(
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    price NUMERIC(20,2) NOT NULL,
    category VARCHAR(60) NOT NULL,
    imageurl VARCHAR(400) NOT NULL,
    description VARCHAR(2000)
);

DROP TABLE IF EXISTS api.user_info;

CREATE TABLE api.user_info(
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    lastname VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    username VARCHAR(60) NOT NULL,
    password VARCHAR(60) NOT NULL
);