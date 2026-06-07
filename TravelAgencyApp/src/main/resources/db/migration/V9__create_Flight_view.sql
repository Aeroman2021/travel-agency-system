CREATE VIEW vw_flights AS
SELECT f.id as id,
       f.airline_id,
       a.name as airline_name,
       f.flight_number,
       f.origin_airport_id,
       CONCAT(origin_city.name, ' - ', origin.name) as origin_airport_display,
       f.destination_airport_id,
       CONCAT(dest_city.name, ' - ', dest.name)     as dest_airport_display,
       f.departure_time,
       f.arrival_time,
       f.price,
       f.currency_code,
       f.available_seats,
       f.status
FROM flights f
         JOIN airlines a ON f.airline_id = a.id
         JOIN airports origin ON f.origin_airport_id = origin.id
         JOIN airports dest ON f.destination_airport_id = dest.id
         JOIN cities origin_city ON origin.city_id = origin_city.id
         JOIN cities dest_city ON dest.city_id = dest_city.id