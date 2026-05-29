alter table booking_saga add column failure_reason varchar(500);

alter table booking_saga add column completed_at Timestamp;

alter table booking_saga add column compensation_started_at Timestamp;

alter table booking_saga add column compensation_completed_at Timestamp;