/*
 Navicat Premium Data Transfer

 Source Server         : spring
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : travel_agency

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 24/05/2026 15:03:26
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

    -- ----------------------------
-- Table structure for flight_reservations
-- ----------------------------
DROP TABLE IF EXISTS `flight_reservations`;
CREATE TABLE `flight_reservations`
(
    `id`             bigint    NOT NULL AUTO_INCREMENT,
    `booking_id`     bigint    NOT NULL,
    `flight_id`      bigint    NOT NULL,
    `airline_id`     bigint    NOT NULL,
    `flight_number`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `departure_time` timestamp NOT NULL,
    `arrival_time`   timestamp NOT NULL,
    `price`          decimal(38, 2) NULL DEFAULT NULL,
    `STATUS`         enum('PENDING','RESERVED','PAYMENT_PENDING','CONFIRMED','TICKET_ISSUED','CANCELLED','FAILED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING',
    `pnr`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `flight_res_booking_idx`(`booking_id` ASC) USING BTREE,
    INDEX            `flight_res_flight_idx`(`flight_id` ASC) USING BTREE,
    INDEX            `flight_res_airliner_idx`(`airline_id` ASC) USING BTREE,
    CONSTRAINT `flight_res_airliner_fk` FOREIGN KEY (`airline_id`) REFERENCES `airlines` (`id`) ,
    CONSTRAINT `flight_res_booking_fk` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`) ,
    CONSTRAINT `flight_res_flight_fk` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for bookings
-- ----------------------------
DROP TABLE IF EXISTS `bookings`;
CREATE TABLE `bookings`
(
    `id`                bigint    NOT NULL AUTO_INCREMENT,
    `user_id`           bigint NULL DEFAULT NULL,
    `flight_id`         bigint NULL DEFAULT NULL,
    `num_of_passangers` int NULL DEFAULT NULL,
    `total_price`       decimal(38, 2) NULL DEFAULT NULL,
    `booking_status`    enum('INITIATED','PENDING','RESERVED','PAYMENT_PENDING','CONFIRMED','TICKET_ISSUED','FAILED','CANCELLED','EXPIRED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'INITIATED',
    `currency_code`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `created_at`        timestamp NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX               `booking_user_idx`(`user_id` ASC) USING BTREE,
    INDEX               `booking_flight_idx`(`flight_id` ASC) USING BTREE,
    CONSTRAINT `booking_flight_fk` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`) ,
    CONSTRAINT `booking_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flight_reservation_passengers
-- ----------------------------
DROP TABLE IF EXISTS `flight_reservation_passangers`;
CREATE TABLE `flight_reservation_passengers`
(
    `id`                    bigint NOT NULL AUTO_INCREMENT,
    `passenger_id`          bigint NULL DEFAULT NULL,
    `seat_number`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `ticket_number`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `flight_reservation_id` bigint NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                   `flight_res_psg_psg_idx`(`passenger_id` ASC) USING BTREE,
    INDEX                   `flight_res_psg_flight_reservation_idx` (`flight_reservation_id`),
    CONSTRAINT `flight_res_psg_psg_fk` FOREIGN KEY (`passenger_id`) REFERENCES `passengers` (`id`) ,
    CONSTRAINT `flight_res_psg_flight_reservation_fk` FOREIGN KEY (`flight_reservation_id`) REFERENCES `flight_reservations` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


SET
FOREIGN_KEY_CHECKS = 1;