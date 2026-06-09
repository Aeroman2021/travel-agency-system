ALTER table bookings
add column current_user_id varchar(100);

ALTER TABLE bookings DROP FOREIGN KEY booking_user_fk;
ALTER TABLE bookings DROP INDEX booking_user_idx;
Alter table bookings drop column user_id;

DROP TABLE IF EXISTS `users`;
