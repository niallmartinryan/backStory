DROP TABLE IF EXISTS USER;

CREATE TABLE IF NOT EXISTS USER (
                               id UUID NOT NULL,
                               first_name VARCHAR(250) NOT NULL,
                               last_name VARCHAR(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL,
                            PRIMARY KEY(id)
);