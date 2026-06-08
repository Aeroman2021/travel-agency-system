DROP VIEW IF EXISTS vw_flights;

CREATE VIEW vw_flights AS

SELECT
    f.id,

    f.airline_id,
    a.name AS airline_name,

    f.flight_number,

    f.origin_airport_id,
    CONCAT(origin_city.name, ' - ', origin.name)
           AS origin_airport_display,

    f.destination_airport_id,
    CONCAT(dest_city.name, ' - ', dest.name)
           AS dest_airport_display,

    f.departure_time,
    f.arrival_time,

    MIN(fb.price) AS starting_price,

    MIN(fb.currency_code) AS currency_code,

    CAST(SUM(fb.available_seats) AS SIGNED) AS total_available_seats,

    GROUP_CONCAT(fb.cabin_class) AS available_cabins,

    f.status

FROM flights f

         JOIN airlines a
              ON f.airline_id = a.id

         JOIN flight_cabins fb
              ON fb.flight_id = f.id

         JOIN airports origin
              ON f.origin_airport_id = origin.id

         JOIN airports dest
              ON f.destination_airport_id = dest.id

         JOIN cities origin_city
              ON origin.city_id = origin_city.id

         JOIN cities dest_city
              ON dest.city_id = dest_city.id

GROUP BY
    f.id,
    f.airline_id,
    a.name,
    f.flight_number,
    f.origin_airport_id,
    f.destination_airport_id,
    f.departure_time,
    f.arrival_time,
    f.status,
    origin_city.name,
    origin.name,
    dest_city.name,
    dest.name;