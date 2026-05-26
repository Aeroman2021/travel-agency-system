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

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`
VALUES (1, 'Mohsen', 'Malakouti', '3490024206', 'male');

-- ----------------------------
-- Records of countries
-- ----------------------------
INSERT INTO `countries`
VALUES (1, 'France');
INSERT INTO `countries`
VALUES (2, 'Iran');
INSERT INTO `countries`
VALUES (3, 'Germany');
INSERT INTO `countries`
VALUES (4, 'UAE');
INSERT INTO `countries`
VALUES (5, 'USA');

-- ----------------------------
-- Records of cities
-- ----------------------------
INSERT INTO `cities`
VALUES (1, 'Tehran', 2);
INSERT INTO `cities`
VALUES (2, 'Paris', 1);
INSERT INTO `cities`
VALUES (3, 'Frankfurt', 3);


-- ----------------------------
-- Records of airlines
-- ----------------------------
INSERT INTO `airlines`
VALUES (1, 'Pegasus', 'PC');

-- ----------------------------
-- Records of airports
-- ----------------------------
INSERT INTO `airports`
VALUES (1, 'MehrAbad', 'THR', 1);
INSERT INTO `airports`
VALUES (2, 'Frankfurt', 'FRA', 3);
INSERT INTO `airports`
VALUES (3, 'Charles de Gaulle', 'XCR', 2);


-- ----------------------------
-- Records of flights
-- ----------------------------
INSERT INTO `flights`
VALUES (1, 1, 'PC560', 1, 2, '2026-05-31 01:03:24', '2026-05-31 11:03:39', 50000000.00, 'RL', 100, 'SCHEDULED', 5);


