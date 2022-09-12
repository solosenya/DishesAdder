CREATE TABLE IF NOT EXISTS dishesDB
(
    id    BIGSERIAL PRIMARY KEY ,
    name  VARCHAR(50) NOT NULL ,
    proteins double precision NOT NULL ,
    carbs double precision  NOT NULL ,
    lipids double precision  NOT NULL ,
    kilo_calories double precision  NOT NULL
    );