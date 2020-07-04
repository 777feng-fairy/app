/*
 Navicat Premium Data Transfer

 Source Server         : MySQl
 Source Server Type    : MySQL
 Source Server Version : 100410
 Source Host           : localhost:3306
 Source Schema         : shopmanage

 Target Server Type    : MySQL
 Target Server Version : 100410
 File Encoding         : 65001

 Date: 15/06/2020 23:05:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shopid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shopname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopnum` int(11) NULL DEFAULT NULL,
  `shopurl` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopprice` decimal(6, 2) NULL DEFAULT NULL,
  `shopinfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `stime` timestamp(0) NOT NULL DEFAULT date_format,
  `type` smallint(255) NULL DEFAULT NULL,
  `shopstatus` smallint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`shopid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart`  (
  `shopid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userid` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shopnum` int(11) NULL DEFAULT NULL,
  `shopprice` decimal(6, 2) NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total` decimal(6, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`shopid`, `userid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `shopcart_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shopcart_ibfk_2` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userage` date NULL DEFAULT NULL,
  `usertel` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userimg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('08040228704', '繁华落尽', '123456', '湖北省天门市', '2019-12-28', '13264673593', '');
INSERT INTO `user` VALUES ('1628345', '昵称', '13264673593', '天门', '2019-12-28', '13264673593', 'e:/图片/user/1628345.jpg');
INSERT INTO `user` VALUES ('201761648', '彭奇威', '123456', '湖北省荆州市荆州区学院路85号', '1998-07-07', '13264673593', 'e:/图片/user/201761648.png');
INSERT INTO `user` VALUES ('23557188503', '李高山', '123456', '湖北天门', '2019-12-28', '13264673593', '');
INSERT INTO `user` VALUES ('4170670622', '小话', '123456', '湖北', '2019-12-28', '13264673593', '');
INSERT INTO `user` VALUES ('4207455269', '你告诉', '123456', '湖北天门', '2019-12-28', '13264673593', '');

-- ----------------------------
-- Table structure for userorder
-- ----------------------------
DROP TABLE IF EXISTS `userorder`;
CREATE TABLE `userorder`  (
  `orderid` char(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userid` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordertime` timestamp(0) NOT NULL DEFAULT date_format,
  `total` decimal(6, 2) NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderstatus` smallint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `userorder_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
