SET
FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `bookings`;
CREATE TABLE `bookings`
(
    `id`                bigint    NOT NULL AUTO_INCREMENT,
    `user_id`           bigint NULL DEFAULT NULL,
    `flight_id`         bigint NULL DEFAULT NULL,
    `flight_cabin_id`   bigint NULL DEFAULT NULL,
    `num_of_passengers` int NULL DEFAULT NULL,
    `total_price`       decimal(38, 2) NULL DEFAULT NULL,
    `booking_status`    enum('INITIATED','PENDING','RESERVED','PAYMENT_PENDING','CONFIRMED','TICKET_ISSUED','FAILED','CANCELLED','EXPIRED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'INITIATED',
    `currency_code`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `created_at`        timestamp NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX               `booking_user_idx`(`user_id` ASC) USING BTREE,
    INDEX               `booking_flight_idx`(`flight_id` ASC) USING BTREE,
    INDEX               `booking_flight_cabin_idx`(`flight_cabin_id` ASC) USING BTREE,
    CONSTRAINT `booking_flight_fk` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`),
    CONSTRAINT `booking_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `booking_flight_cabin_fk` FOREIGN KEY (flight_cabin_id) REFERENCES flight_cabins (id)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;
