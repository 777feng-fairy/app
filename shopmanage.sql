/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 100410
 Source Host           : localhost:3306
 Source Schema         : shopmanage

 Target Server Type    : MySQL
 Target Server Version : 100410
 File Encoding         : 65001

 Date: 04/07/2020 12:54:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `shopid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userid` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shopnum` int(11) NULL DEFAULT NULL,
  `shopprice` decimal(6, 2) NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`shopid`, `userid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('420219', '713116628', 1, 450.00, '');

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shopid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shopname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopnum` int(11) NULL DEFAULT NULL,
  `shopurl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopprice` decimal(6, 2) NULL DEFAULT NULL,
  `shopinfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `stime` timestamp(0) NOT NULL DEFAULT date_format,
  `type` smallint(255) NULL DEFAULT NULL,
  `shopstatus` smallint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`shopid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('2342057', '苹果', 496, 'E:/图片/shop/2342057.jpg', 3.88, '产自xxxxx', '2020-06-28 00:00:00', 8, 1);
INSERT INTO `shop` VALUES ('420219', '小米', 118, 'E:/图片/shop/420219.jpg', 450.00, '达瓦达瓦', '2020-06-28 00:00:00', 1, 1);
INSERT INTO `shop` VALUES ('516542979316', '潮流上衣', 4500, 'E:/图片/shop/516542979316.jpg', 78.00, '啊啊啊啊啊啊啊啊啊啊啊啊啊', '2020-06-29 00:00:00', 5, 1);
INSERT INTO `shop` VALUES ('55843956', '青枣', 19999, 'E:/图片/shop/55843956.jpg', 7.20, '啊啊啊啊啊啊啊啊啊啊啊', '2020-06-29 00:00:00', 8, 1);
INSERT INTO `shop` VALUES ('6032958', 'mac电脑i7', 11111, 'E:/图片/shop/6032958.jpg', 7800.00, 'aaaaaaaaaaaaaaaaaaaaaaaaa', '2020-06-29 00:00:00', 0, 1);
INSERT INTO `shop` VALUES ('62533929544', '华为P30', 88, 'E:/图片/shop/62533929544.jpg', 4500.00, '华为科技嘻嘻嘻嘻嘻嘻嘻', '2020-06-28 00:00:00', 1, 1);
INSERT INTO `shop` VALUES ('748521806', '金牛插座', 1200000, 'E:/图片/shop/748521806.jpg', 20.00, '不过电', '2020-06-29 00:00:00', 13, 1);
INSERT INTO `shop` VALUES ('780856482', '乐视Tv', 2000, 'E:/图片/shop/780856482.jpg', 4500.00, 'dddddddddddddddddddddd', '2020-06-28 00:00:00', 10, 1);
INSERT INTO `shop` VALUES ('791922', 'Huawei数据线', 25000, 'E:/图片/shop/791922.jpg', 30.00, 'emmmm', '2020-06-29 00:00:00', 4, 1);
INSERT INTO `shop` VALUES ('889908', '草莓', 4500, 'E:/图片/shop/889908.jpg', 4500.00, 'xxxx', '2020-07-01 00:00:00', 8, 1);

-- ----------------------------
-- Table structure for uorder
-- ----------------------------
DROP TABLE IF EXISTS `uorder`;
CREATE TABLE `uorder`  (
  `orderid` char(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userid` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shopid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordertime` timestamp(0) NOT NULL DEFAULT date_format,
  `total` decimal(10, 2) NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usertel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderstatus` smallint(255) NULL DEFAULT NULL,
  `shopnum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `shopid`(`shopid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `uorder_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `uorder_ibfk_2` FOREIGN KEY (`shopid`) REFERENCES `shop` (`shopid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uorder
-- ----------------------------
INSERT INTO `uorder` VALUES ('0218768', '201761648', '55843956', '2020-06-30 00:00:00', 7.20, '湖北省荆州市荆州区学院路85号', '彭奇威', '13264673593', 2, 1);
INSERT INTO `uorder` VALUES ('120445523', '201761648', '2342057', '2020-06-28 00:00:00', 3.88, '湖北省荆州市荆州区学院路85号', '彭奇威', '13264673593', 3, 1);
INSERT INTO `uorder` VALUES ('142449868526', '201761648', '2342057', '2020-06-28 00:00:00', 3.88, '湖北省荆州市荆州区学院路85号', '彭奇威', '13264673593', 4, 1);
INSERT INTO `uorder` VALUES ('17386008946', '713476993313', '420219', '2020-07-01 00:00:00', 450.00, '湖北天门', '话', '13264673593', 4, 1);
INSERT INTO `uorder` VALUES ('5453256960', '713476993313', '516542979316', '2020-07-01 00:00:00', 78.00, '湖北天门', '话', '13264673593', 3, 1);
INSERT INTO `uorder` VALUES ('5878694', '713476993313', '420219', '2020-07-01 00:00:00', 450.00, '湖北天门', '话', '13264673593', 1, 1);

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
  `type` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('201761648', '风柒兮', '123456', '湖北省荆州市荆州区学院路85号', '1998-07-07', '13593917637', 'e:/图片/user/201761648.jpg', 1);
INSERT INTO `user` VALUES ('713116628', '凤梧桐不止', '654123', '湖北省荆州市荆州区学院路86号', '2000-07-11', '13593917637', 'e:/图片/user/713116628.jpg', 0);
INSERT INTO `user` VALUES ('713476993313', '话', '123456', '湖北武汉', '1998-01-01', '13593917637', 'e:/图片/user/713476993313.png', 0);

SET FOREIGN_KEY_CHECKS = 1;
