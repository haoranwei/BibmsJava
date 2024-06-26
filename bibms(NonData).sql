/*
 Navicat Premium Data Transfer

 Source Server         : HaoranWei
 Source Server Type    : MySQL
 Source Server Version : 50529 (5.5.29)
 Source Host           : localhost:3306
 Source Schema         : bibms

 Target Server Type    : MySQL
 Target Server Version : 50529 (5.5.29)
 File Encoding         : 65001

 Date: 18/06/2023 20:17:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bibms_account
-- ----------------------------
DROP TABLE IF EXISTS `bibms_account`;
CREATE TABLE `bibms_account`  (
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一识别码',
  `custName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户姓名',
  `idCard` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡号',
  `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户人公司',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户人通讯地址',
  `openTime` datetime NOT NULL COMMENT '开户时间',
  `accState` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户状态',
  `balance` double(10, 2) NOT NULL COMMENT '账户额度',
  PRIMARY KEY (`account`, `company`) USING BTREE,
  INDEX `company`(`company`) USING BTREE,
  INDEX `account`(`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bibms_role
-- ----------------------------
DROP TABLE IF EXISTS `bibms_role`;
CREATE TABLE `bibms_role`  (
  `roleNo` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '营业部员工号',
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业部员工姓名',
  PRIMARY KEY (`roleNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bibms_salary
-- ----------------------------
DROP TABLE IF EXISTS `bibms_salary`;
CREATE TABLE `bibms_salary`  (
  `id` int(11) NOT NULL,
  `compName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `empName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salary` double(20, 2) NOT NULL,
  `payMonth` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `compAcc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `empAcc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idCard` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `approve` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `compAcc`(`compName`) USING BTREE,
  CONSTRAINT `compAcc` FOREIGN KEY (`compName`) REFERENCES `bibms_account` (`company`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bibms_transfer
-- ----------------------------
DROP TABLE IF EXISTS `bibms_transfer`;
CREATE TABLE `bibms_transfer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单号',
  `transOutAcc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转出账户',
  `transAmount` double(20, 2) NOT NULL COMMENT '转账额度',
  `transTime` datetime NULL DEFAULT NULL COMMENT '转账时间',
  `transInAcc` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转入账户',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `转出关联（唯一识别码）`(`transOutAcc`) USING BTREE,
  INDEX `转入关联（唯一识别码）`(`transInAcc`) USING BTREE,
  CONSTRAINT `转入关联（唯一识别码）` FOREIGN KEY (`transInAcc`) REFERENCES `bibms_account` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `转出关联（唯一识别码）` FOREIGN KEY (`transOutAcc`) REFERENCES `bibms_account` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bibms_user
-- ----------------------------
DROP TABLE IF EXISTS `bibms_user`;
CREATE TABLE `bibms_user`  (
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `realName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `userRole` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属管理员号',
  PRIMARY KEY (`userName`) USING BTREE,
  INDEX `userRole`(`userRole`) USING BTREE,
  CONSTRAINT `userRole` FOREIGN KEY (`userRole`) REFERENCES `bibms_role` (`roleNo`) ON DELETE RESTRICT ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
