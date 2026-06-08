SET
FOREIGN_KEY_CHECKS=0;
DROP TABLE flights;
CREATE TABLE flights
(
    id                     BIGINT      NOT NULL auto_increment PRIMARY KEY,
    airline_id             BIGINT ( 100 ) not NULL,
    flight_number          VARCHAR(20) not NULL,
    origin_airport_id      BIGINT      NOT NULL,
    destination_airport_id BIGINT      not NULL,
    departure_time         TIMESTAMP   not NULL,
    arrival_time           TIMESTAMP   not NULL,
    version BIGINT default 1,
    INDEX                  flight_airline_idx(airline_id),
    INDEX                  flight_origin_airport_idx(origin_airport_id),
    INDEX                  flight_dest_airport_idx(destination_airport_id),
    status                 ENUM ( 'SCHEDULED','BOARDING','DELAYED','CANCELLED','COMPLETED') DEFAULT 'SCHEDULED',
    CONSTRAINT flight_dest_airport_fk FOREIGN KEY (destination_airport_id) REFERENCES airports (id),
    CONSTRAINT flight_origin_airport_fk FOREIGN KEY (origin_airport_id) REFERENCES airports (id),
    CONSTRAINT flight_airline_fk FOREIGN KEY (airline_id) REFERENCES airlines (id)
)