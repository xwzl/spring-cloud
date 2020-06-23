/*
 Navicat Premium Data Transfer

 Source Server         : 172.16.142.128
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 172.16.142.128:3306
 Source Schema         : prepare

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 28/05/2020 18:35:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class_schedule
-- ----------------------------
DROP TABLE IF EXISTS `class_schedule`;
CREATE TABLE `class_schedule`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程表',
  `schedule_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `teacher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师',
  `arrangement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程安排',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class_schedule
-- ----------------------------
INSERT INTO `class_schedule` VALUES ('1265950271566094337', '语文', '汤唯', '星期二', '2020-05-28 18:17:20', '2015-05-12 12:12:12', 0);
INSERT INTO `class_schedule` VALUES ('1265950321725775873', '数学', '汤唯', '星期一', '2020-05-28 18:17:32', '2015-05-12 12:12:12', 0);
INSERT INTO `class_schedule` VALUES ('1265952520270880770', '英语', '汤唯', '星期一', '2020-05-28 18:26:16', '2015-05-12 12:12:12', 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1265952782280675330, NULL, 'Like', 17, NULL, '南京市', '2020-05-28 18:27:18', '2020-05-28 18:27:18', 0);
INSERT INTO `student` VALUES (1265952782280675331, NULL, 'MrsLi', 30, NULL, '西南市', '2020-05-28 18:27:18', '2020-05-28 18:27:18', 0);
INSERT INTO `student` VALUES (1265952782289063937, NULL, 'Lucy', 8, NULL, '西南市', '2020-05-28 18:27:18', '2020-05-28 18:27:18', 0);
INSERT INTO `student` VALUES (1265952782289063938, NULL, 'Lucy1', 94, NULL, '西南市', '2020-05-28 18:27:18', '2020-05-28 18:27:18', 0);
INSERT INTO `student` VALUES (1265952782289063939, NULL, 'Tom', 90, NULL, '西南市', '2020-05-28 18:27:18', '2020-05-28 18:27:18', 0);

-- ----------------------------
-- Table structure for user_class_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_class_relation`;
CREATE TABLE `user_class_relation`  (
  `id` bigint(20) NOT NULL COMMENT '用户课程关系表',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `class_id` bigint(20) NOT NULL COMMENT '课程表主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_class_relation
-- ----------------------------
INSERT INTO `user_class_relation` VALUES (1265952886790148097, 1265952782289063937, 1265950321725775873);
INSERT INTO `user_class_relation` VALUES (1265953533774123009, 1265952782289063937, 1265952520270880770);

SET FOREIGN_KEY_CHECKS = 1;
