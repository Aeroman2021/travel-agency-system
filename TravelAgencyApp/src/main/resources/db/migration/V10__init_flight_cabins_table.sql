SET
    FOREIGN_KEY_CHECKS=0;
DROP TABLE flight_cabins;
create TABLE flight_cabins(
                              id BIGINT not NULL auto_increment PRIMARY KEY,
                              flight_id BIGINT not NULL,
                              cabin_class ENUM('ECONOMY','BUSINESS','FIRST') DEFAULT 'ECONOMY',
                              price DECIMAL ( 10, 2 ) not NULL,
                              currency_code VARCHAR(3) not NULL,
                              available_seats INT not null,
                              INDEX flight_cabin_flight_idx(flight_id),
                              CONSTRAINT flight_cabin_flight_fk FOREIGN KEY(flight_id) REFERENCES flights(id)
);