/*
Navicat MariaDB Data Transfer

Source Server         : localhost_MariaDB
Source Server Version : 100306
Source Host           : 192.168.56.101:3306
Source Database       : demo_ds_0

Target Server Type    : MariaDB
Target Server Version : 100306
File Encoding         : 65001

Date: 2018-06-11 22:54:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seckill
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
`seckill_id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品库存id' ,
`name`  varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称' ,
`number`  int(11) NOT NULL COMMENT '库存数量' ,
`start_time`  timestamp NOT NULL DEFAULT current_timestamp() COMMENT '秒杀开始时间' ,
`end_time`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间' ,
`create_time`  timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间' ,
PRIMARY KEY (`seckill_id`),
INDEX `idx_start_time` (`start_time`) USING BTREE ,
INDEX `idx_end_time` (`end_time`) USING BTREE ,
INDEX `idx_create_time` (`create_time`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='秒杀库存表'
AUTO_INCREMENT=1004

;

-- ----------------------------
-- Records of seckill
-- ----------------------------
BEGIN;
INSERT INTO `seckill` VALUES ('1000', '1000元秒杀iphone6', '93', '2018-05-17 19:26:45', '2018-12-31 00:00:00', '2018-05-14 18:22:28'), ('1001', '5000元秒杀ipad2', '200', '2018-05-25 17:20:22', '2018-12-31 00:00:00', '2018-05-14 18:22:28'), ('1002', '300元秒杀小米4', '299', '2018-05-29 19:07:04', '2018-12-31 00:00:00', '2018-05-14 18:22:28'), ('1003', '200元秒杀红米note', '400', '2018-05-16 17:20:28', '2018-12-31 00:00:00', '2018-05-14 18:22:28');
COMMIT;

-- ----------------------------
-- Table structure for success_killed
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
`seckill_id`  bigint(20) NOT NULL COMMENT '秒杀商品id' ,
`user_phone`  bigint(20) NOT NULL COMMENT '用户手机号' ,
`state`  tinyint(4) NOT NULL DEFAULT '-1' COMMENT '状态标志：-1：无效 0：成功 1：已付款 2：已发货' ,
`create_time`  timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
PRIMARY KEY (`seckill_id`, `user_phone`),
INDEX `idx_create_time` (`create_time`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='秒杀成功明细表'

;

-- ----------------------------
-- Records of success_killed
-- ----------------------------
BEGIN;
INSERT INTO `success_killed` VALUES ('1000', '13421811262', '0', '2018-05-17 19:05:50'), ('1000', '13456457865', '0', '2018-05-17 19:02:57'), ('1000', '13587987896', '0', '2018-05-17 19:26:45'), ('1000', '13678456692', '0', '2018-05-17 19:37:41'), ('1000', '13876278435', '0', '2018-05-16 17:35:00'), ('1000', '13876278465', '0', '2018-05-16 17:50:58'), ('1000', '13876278735', '0', '2018-05-16 17:28:38'), ('1000', '15918673639', '-1', '2018-05-15 23:02:33'), ('1001', '15918673639', '0', '2018-05-15 23:11:56'), ('1002', '13421811262', '0', '2018-05-17 19:07:04');
COMMIT;

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
AUTO_INCREMENT=1

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
-- Auto increment value for seckill
-- ----------------------------
ALTER TABLE `seckill` AUTO_INCREMENT=1004;

-- ----------------------------
-- Auto increment value for t_order_0
-- ----------------------------
ALTER TABLE `t_order_0` AUTO_INCREMENT=1;

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
