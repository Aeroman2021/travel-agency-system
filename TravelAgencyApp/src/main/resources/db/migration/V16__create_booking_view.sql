DROP VIEW
    IF
    EXISTS vw_bookings;
CREATE VIEW vw_bookings AS
SELECT b.id            AS id,
       b.current_user_id,
       b.flight_id,
       b.flight_cabin_id,
       b.num_of_passengers,
       b.total_price,
       b.booking_status,
       b.currency_code,
       b.created_at,
       f.flight_number,
       fc.cabin_class
FROM bookings b
         JOIN flights f ON b.flight_id = f.id
         JOIN flight_cabins fc ON b.flight_cabin_id = fc.id