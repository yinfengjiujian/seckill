/*
Navicat MariaDB Data Transfer

Source Server         : localhost_MariaDB
Source Server Version : 100306
Source Host           : 192.168.56.101:3306
Source Database       : demo_ds_1

Target Server Type    : MariaDB
Target Server Version : 100306
File Encoding         : 65001

Date: 2018-06-11 22:55:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order_0
-- ----------------------------
DROP TABLE IF EXISTS `t_order_0`;
CREATE TABLE `t_order_0` (
`order_id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`user_id`  int(11) NOT NULL ,
`status`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' ,
PRIMARY KEY (`order_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3239

;

-- ----------------------------
-- Records of t_order_0
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_order_1
-- ----------------------------
DROP TABLE IF EXISTS `t_order_1`;
CREATE TABLE `t_order_1` (
`order_id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`user_id`  int(11) NOT NULL ,
`status`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' ,
PRIMARY KEY (`order_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3240

;

-- ----------------------------
-- Records of t_order_1
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_order_item_0
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item_0`;
CREATE TABLE `t_order_item_0` (
`order_item_id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`order_id`  bigint(20) NOT NULL ,
`user_id`  int(11) NOT NULL ,
PRIMARY KEY (`order_item_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_order_item_0
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_order_item_1
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item_1`;
CREATE TABLE `t_order_item_1` (
`order_item_id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`order_id`  bigint(20) NOT NULL ,
`user_id`  int(11) NOT NULL ,
PRIMARY KEY (`order_item_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of t_order_item_1
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for t_order_0
-- ----------------------------
ALTER TABLE `t_order_0` AUTO_INCREMENT=3239;

-- ----------------------------
-- Auto increment value for t_order_1
-- ----------------------------
ALTER TABLE `t_order_1` AUTO_INCREMENT=3240;

-- ----------------------------
-- Auto increment value for t_order_item_0
-- ----------------------------
ALTER TABLE `t_order_item_0` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for t_order_item_1
-- ----------------------------
ALTER TABLE `t_order_item_1` AUTO_INCREMENT=1;
