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
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `booking_id`  bigint NULL DEFAULT NULL,
    `price`       decimal(38, 2) NULL DEFAULT NULL,
    `STATUS`      enum('INITIATED','PROCESSING','SUCCESS','FAILED','REFUNDED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'INITIATED',
    `gateway_ref` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `payments_booking_idx`(`booking_id` ASC) USING BTREE,
    CONSTRAINT `payments_booking_fk` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for tickets
-- ----------------------------
DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets`
(
    `id`            bigint    NOT NULL AUTO_INCREMENT,
    `booking_id`    bigint NULL DEFAULT NULL,
    `passenger_id`  bigint NULL DEFAULT NULL,
    `ticket_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    `issued_at`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX           `ticket_booking_idx`(`booking_id` ASC) USING BTREE,
    INDEX           `ticket_passanger_idx`(`passenger_id` ASC) USING BTREE,
    CONSTRAINT `ticket_booking_fk` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`) ,
    CONSTRAINT `ticket_passenger_fk` FOREIGN KEY (`passenger_id`) REFERENCES `passengers` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

