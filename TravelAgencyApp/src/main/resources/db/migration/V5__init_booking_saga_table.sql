-- ----------------------------
-- Table structure for booking_saga
-- ----------------------------
DROP TABLE IF EXISTS `booking_saga`;
CREATE TABLE `booking_saga`  (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `booking_id` bigint NULL DEFAULT NULL,
                                 `currentStep` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `sagaStatus` enum('STARTED','FLIGHT_RESERVED','PASSENGERS_REGISTERED','PAYMENT_COMPLETED','TICKET_ISSUED','FAILED','COMPENSATED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'STARTED',
                                 `createdAt` timestamp NOT NULL,
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `booking_saga_booking_idx`(`booking_id` ASC) USING BTREE,
                                 CONSTRAINT `booking_saga_booking_fk` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;