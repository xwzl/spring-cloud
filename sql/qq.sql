/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 47.105.218.58:3306
 Source Schema         : qq

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 07/08/2019 13:44:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for UserConnection
-- ----------------------------
DROP TABLE IF EXISTS `UserConnection`;
CREATE TABLE `UserConnection`  (
  `userId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `providerId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `providerUserId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `profileUrl` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `imageUrl` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `accessToken` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `refreshToken` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `expireTime` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`, `providerId`, `providerUserId`) USING BTREE,
  UNIQUE INDEX `UserConnectionRank`(`userId`, `providerId`, `rank`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hyy_emp
-- ----------------------------
DROP TABLE IF EXISTS `hyy_emp`;
CREATE TABLE `hyy_emp`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emp_work_id` int(11) NULL DEFAULT NULL COMMENT '工号',
  `emp_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `emp_dep_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工所属部门名字',
  `emp_post_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工岗位名称',
  `emp_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工职级',
  `emp_workarea` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工工作区域',
  `emp_skill` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工技术等级',
  `emp_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工电话号码',
  `emp_idcardnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工联系方式',
  `emp_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工性别',
  `emp_age` int(255) NULL DEFAULT NULL COMMENT '员工年龄',
  `emp_nation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工民族',
  `emp_in` date NULL DEFAULT NULL COMMENT '员工入项时间',
  `emp_out` date NULL DEFAULT NULL COMMENT '员工离项时间',
  `emp_allworkday` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '员工预计投入工作日',
  `emp_allmoney` decimal(15, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '员工预计报价金额',
  `emp_remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工备注信息',
  `emp_status` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '员工入项状态',
  `emp_entry_time` datetime(0) NULL DEFAULT NULL COMMENT '员工入职时间',
  `emp_separation_time` datetime(0) NULL DEFAULT NULL COMMENT '员工离职时间',
  `emp_work_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '员工就职状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 170 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hyy_office_computer
-- ----------------------------
DROP TABLE IF EXISTS `hyy_office_computer`;
CREATE TABLE `hyy_office_computer`  (
  `office_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '办公电脑主键',
  `owner` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `asset_number` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL COMMENT '资产编号',
  `asset_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资产类型',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资产品牌',
  `computer_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电脑来源',
  `attribution_company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '归属公司',
  `use_company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用公司',
  `use_department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用部门',
  `use_of_state` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用状态',
  `machine_state` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机器状态',
  `machine_configuration` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机器配置',
  `machine_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机器位置',
  `quantity` int(255) NULL DEFAULT NULL COMMENT '资产数量',
  `unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资产单位',
  `trade_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '厂商名称',
  `iphone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话号',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`office_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hyy_office_computer
-- ----------------------------
INSERT INTO `hyy_office_computer` VALUES (1, '闲置', '主机编号：12001281               ', '台式机', '逾辉', '食材转入', '天呈慧眼云', '天呈慧眼云', NULL, NULL, '17楼研发旁小办公室', '正常', 'i5-6500CPU 3.2GHZ  (内存：8GB）', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (2, '马建轲', '主机编号：12000028              显示器编号：12007327', '台式机', '百盛', '食材转入', '天呈慧眼云', '榴莲', '研发部', '外借', '榴莲办公区', '正常', 'i5-4460CPU 3.2GHZ  (内存：8GB）  显示器：戴尔', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (3, '张鹏', '编号：12001378', '显示器', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '执行董事', '自用', '执行董事办公室', '正常', '显示器：戴尔', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (4, '杨林', '主机编号：12001263                       显示器编号：12001299', '台式机', '逾辉', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'AMD A4-6300 3.7GHZ 内存：8G 显示器：戴尔', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (5, '唐伟', '主机编号：12001281                         显示器编号：12001084', '台式机', '多彩', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'Intel(R)_Core(TM)_i5-6500  内存：8G 显示器：envision', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (6, '黄洋洋', '编号：12001370', '显示器', '多彩', '食材转入', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '18楼办公区', '正常', '显示器：戴尔', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (7, '王雪兰', '编号：1200013', '显示器', '多彩', '食材转入', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '18楼办公区', '正常', '显示器：戴尔', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (8, '赵娟', '编号 :12000046', '台式机', '逾辉', '传媒转入', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '18楼办公区', '正常', '显示器：Sabretooth', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (9, '殷康建', '编号 :yqq02016', '台式机', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', '显示器：DELL', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (10, '李章琼', '主机编号：12001087            显示器编号：12001373                ', '台式机', '多彩', '传媒转入', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '17楼办公区', '正常', 'I5-3470 3.2G(内存：4GC+ 安全 DDR3 1600MHZ)  ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (11, '宋荣杰', '主机编号：12001364                        显示器编号：1200003', '台式机', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'I7-4790CPU 3.60GHZ (内存：8GB）显示器：戴尔 23寸', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (12, '徐伟智', '主机编号：12001079                显示器编号：12000007', '台式机', '逾辉', '食材转入', '天呈慧眼云', '天呈快快', '产品部', '外借', '17楼研发旁小办公室', '正常', 'i5-6500CPU 3.2GHZ (内存：8GB） 显示器：飞利浦', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (13, '黄丹桂', '主机编号：12001077                     显示器编号：12001059', '台式机', '逾辉', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'I5-6500CPU 3.2GHZ (内存：8GB）显示器：飞利浦', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (14, '邓禹', '主机编号：12000022                      显示器编号：12000250', '台式机', '逾辉', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'I5-4460 3.2GHZ(内存：16GB)  显示器：飞利浦', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (15, '胡翼', '主机编号：12001088                       显示器编号：807480286', '台式机', '逾辉', '传媒转入', '天呈慧眼云', '天呈慧眼云', '行政人事', '自用', '行政人事办公室', '正常', 'I5-6500CPU 3.2GHZ (内存：8GB）显示器：戴尔', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (16, '陈禹廷', '主机编号：12001084                 显示器编号：12000022', '台式机', '逾辉', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'i5-4590CPU 3.2GHZ  (内存：8GB）显示器：戴尔', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (17, '邹吉', '主机编号：12001363    显示器编号：12001371/12001083', '台式机', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'Intel(R)_Core(TM)_i5-4590_CPU_@_3.30GHz  8G  硬盘500G（两个显示器 DELL AOC）', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (18, '闲置', '主机编号：12001379                 显示器编号：', '台式机', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '榴莲办公区', '正常', 'Intel(R)_Core(TM)_i5-4590_CPU_@_3.30GHz 8G 硬盘1T  显示器： DELL', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (19, '邹吉', '主机编号：12001270', '笔记本', '联想（笔记本）', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'Intel(R)_Core(TM)_i7-7500U_CPU_@_2.70GHz   8G 硬盘300G  ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (20, '汪滔', '主机编号：1200008                   显示器编号：1200008', '台式机', '百盛', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'I5-8G 硬盘1T 显示器：philips', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (21, '胡元垚', '主机编号：12000033                 显示器编号：12000033', '台式机', '百盛', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'I5-8G 硬盘1T 显示器：philips', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (22, '弋明', '主机编号：12001381                 显示器编号：12001297', '台式机', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'intel i5  12G 硬盘500G 显示器：envision', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (23, '程玮', '主机编号：12001390', '一体机', '苹果', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '18楼办公区', '正常', '3.2 GHz Intel Core i5 8G 硬盘1T  苹果一体机', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (24, '欧敏', '主机编号：12001393', '一体机', '苹果', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼服务器仓库', '正常', '3.2 GHz Intel Core i5 8G 硬盘1T  苹果一体机', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (25, '陈云平', '主机编号：12000032                 显示器编号：12001389/1200138', '台式机', '百盛', '传媒转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'i5-4460-8GB * 2  硬盘1T DELL * 2 DELL', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (26, '施丽雯', '主机编号：12001362                 显示器编号：12000047', '台式机', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '行政人事', '自用', '行政人事办公室', '正常', 'i7-4790-8GB    显示器：飞利浦', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (27, '张贝佳', '00004', '笔记本', '戴尔笔记本', '快快转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', '戴尔I5480-1525S 14英寸(i5-8265U 4G 1T+128G固 MX130-2G 无光驱 Win10 银', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (28, '许东', '00006', '显示器', '联想', '自购', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '17楼办公区', '正常', '显示器：联想', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (29, '毛雅婷', '00007', '一体机', '苹果一体机', '自购', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '18楼办公区', '正常', 'Apple iMac 21.5英寸一体机（2017款四核 Core i5/8GB内存/1TB Fusion Drive/RP560显卡/4K屏 MNE02CH/A）', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (30, '许东', 'hyy00001', '笔记本', '华为笔记本', '自购', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '17楼办公区', '正常', 'AMD锐龙5  8G512G ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (31, '张贝佳', 'hyy00002', '显示器', '戴尔显示器', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'U2417H23.8寸', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (32, '黄宏发', 'hyy00004', '显示器', '戴尔显示器', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'U2417H23.8寸', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (33, '张亚林', 'hyy00005', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (34, '燕虹宇', 'hyy00006', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (35, '郑勇', 'hyy00007', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (36, '杨凌霄', 'hyy00008', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (37, '杨开鑫', 'hyy00009', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (38, '汪骥驰', 'hyy00010', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (39, '计国栋', 'hyy00011', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (40, '向川', 'hyy00012', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (41, '胡强', 'hyy00013', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (42, '陈晓', 'hyy00014', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (43, '曹小锋', 'hyy00015', '台式机', '戴尔台式机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'V3670-R14N8R  I5-8400/16G/1T+128G/无光驱/集成/WIN10 U2417H 23.8寸 ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (44, '李天祺', 'hyy00016', '一体机', '苹果一体机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '18楼办公区', '正常', 'Apple MacBook Pro （13-inch,2017,two thunderbolt 3 ports) 处理器 2.3GHz Intel Core i5 内存8G 2133 MHz LPDDR3', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (45, '张猛', 'hyy00017', '笔记本', '苹果笔记本', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '产品副总监办公室', '正常', 'Apple MacBook Pro 13.3英寸笔记本电脑 深空灰色（2017款Core i5处理器/8GB内存/256GB硬盘 MPXT2CH/A）', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (46, '杨银', 'hyy00018', '笔记本', '苹果笔记本', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', 'Apple MacBook Pro 13.3英寸笔记本电脑 深空灰色（2017款Core i5处理器/8GB内存/256GB硬盘 MPXT2CH/A）', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (47, '殷康建', 'hyy00020', '手机', '苹果手机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', '苹果6S 银 16G 全网通 SKU6124924', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (48, '张亚林', 'hyy00021', '手机', '苹果手机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', '苹果6 PLUS 深空灰色 全网通 16G', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (49, '张亚林', 'hyy00022', '手机', '华为手机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', '华为MATE 10 4GB+64GB香槟金（全网通）', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (50, '张亚林', 'hyy00023', '手机', '小米手机', '自购', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼办公区', '正常', '小米6 6GB64GB 亮黑色 全网通', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (51, '黄洋洋', 'TS201810HLW001', '台式机', '台式电脑', '自购', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '17楼办公区', '正常', '戴尔/i5 8400/8G/1T/联想显示器', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (52, '张文骁', 'TS201810HLW002', '台式机', '台式电脑', '食材 ', '天呈食材', '天呈慧眼云', '产品部', '留下', '18楼食材办公区', '正常', '苹果Imac27寸一体机', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (53, '徐荣泽', 'TS201804HLW003', '台式机', '台式电脑', '食材 ', '天呈食材', '天呈慧眼云', '产品部', '留下', '18楼食材办公区', '正常', 'iMac一体机/i5/16G/1TB', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (54, '雷冬学', 'BJB201802HLW004', '笔记本', '笔记本电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '18楼办公区', '正常', '联想 S3 银色 i5-6200U/8G', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (55, '夏梦娜', 'TS201811HLW011', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '18楼办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (56, '李昌宣', 'BJB201804YF001', '笔记本', '笔记本电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', NULL, '正常', '联想ThinkPad S3 i5-6200u 8G内存 128G SSD + 1TWQHD IPS Win10', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (57, '杨柏绫', 'BJB201711YF003', '笔记本', '笔记本电脑', '食材 ', '天呈食材', '天呈慧眼云', '研发部', '留下', '18楼食材办公区', '正常', 'MacBook Pro2017银色/2.3GHz i5/8g', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (58, '张驰', 'BJB201804YF006', '笔记本', '笔记本电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '18楼办公区', '正常', '联想E570 黑 I5/8G/1T/2G独立显卡', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (59, '陈戬', 'BJB201804YF007', '笔记本', '笔记本电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '联想E570 黑 I5/8G/1T/2G独立显卡', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (60, '蒲嘉林', 'TS201804YF002   显示器编号：Q2490PXQ258/众信-058 ', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '联想微型计算机 启天M415-N000 I5-7500 16G内存 500G硬盘 24寸AOC显示器', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (61, '何力', 'TS201804YF003', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '戴尔  黑 I5-7400/8G/1T  ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (62, '杨柏绫', 'TS201804YF004', '台式机', '台式电脑', '食材 ', '天呈食材', '天呈慧眼云', '研发部', '留下', '17楼办公区', '正常', 'iMac (21.5-inch, 2017) 2.3 GHz Intel Core i5 8 GB 2133 MHz DDR4', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (63, '文萍', '显示器编号：AOCQ2490PXQ238/众信-060', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'AOC Inspiron 3668  i5-7400 8G\n23.8寸显示器', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (64, '彭恋邱', 'TS201804YF006', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '戴尔 3668/Core(TM) i5-7400/戴尔 07KY25\n8.00 GB (2400 MHz)/130 GB (MS7BN55981150BM43)', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (65, '贾超', 'TS201804YF007    众信046', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '戴尔Inspiron 灵越 3668 I5-7400 8G内存 120GSSD 2G独显  23.8寸AOC显示器', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (66, '宁少晋', 'TS201811YF009', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (67, '周春桥', 'TS201811YF010', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (68, '陈远明', 'TS201811YF013', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (69, '仲福超', 'TS201811YF015', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (70, '李明阳', 'TS201811YF016', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (71, '张小龙', 'TS201901YF017', '主机', '主机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'Mac Mini', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (72, '张海青', NULL, '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I7-4790/内存金士顿16G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (73, '宋贤坤', 'TS201804YF001', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '联想thinkcentreM4500K', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (74, '陈勇兵', 'TS201811YF018', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '醋瑞7400/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (75, '凌征亮', NULL, '显示器', '显示器', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'AOC显示器', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (76, '王雪兰', 'BJB201805IT004', '笔记本', '笔记本电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '产品部', '自用', '18楼办公区', '正常', '笔记本：联想thinkpad  i5-6200U 128GSSD+1T WQHD IPS Win10银色', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (77, '李昌宣', 'XSQ201809IT001', '显示器', '显示器', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '显示器：AOC Q2490PXQ；', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (78, '张先敏', 'TS201807IT002', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '戴尔 Inspiron 3668  I5-7400/8g/128GG固态+1T/2G独显/DVD  24寸，AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (79, '陈奉超', '8TDDLN2  TS201804TY001', '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '戴尔 黑 i5-7004 8G内存 1T硬盘 AOC23.8英寸显示器', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (80, '吴津京', NULL, '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (81, '毛雪美', NULL, '台式机', '台式电脑', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '组装机I5-8500/内存金士顿8G/硬盘S1-1T/固态硬盘金士顿120G/显卡影驰2G/显示器AOC', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (82, '李寅斌', 'SJ201812YF001', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'iphone8', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (83, '李寅斌', 'SJ201812YF002', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '小米8', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (84, '叶茂', 'SJ201812YF003', '手机', '手机', '食材转入', '天呈食材', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '小米 MIX3', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (85, '张亚林', 'SJ201812YF004', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '华为P20', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (86, '李寅斌', 'SJ201812YF005', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '华为mate20', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (87, '李寅斌', 'SJ201812YF006', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'OPPO r17', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (88, '李寅斌', 'SJ201812YF007', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'oppo findX', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (89, '张亚林', 'SJ201812YF008', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'vivo X23', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (90, '李寅斌', 'SJ201812YF009', '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'vivo NEX', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (91, '李寅斌', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '华为P9', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (92, '李寅斌', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'OPPOR11', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (93, '李寅斌', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'VIVOX9S', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (94, '李寅斌', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'OPPOR11S', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (95, '张亚林', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', 'IPHONE X', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (96, '李寅斌', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '移动通信手持机（手机）', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (97, '李寅斌', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '移动通信设备-手机', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (98, '李寅斌', NULL, '手机', '手机', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '17楼传媒办公区', '正常', '（二手95新）Apple iPhoneX64G深空灰色手机', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (99, '张先敏', 'EL201811IT001', '服务器', '服务器', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '18楼办公区', '正常', 'DELL服务器-R730', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (100, '张先敏', 'EL201811IT002', '服务器', '服务器', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '18楼办公区', '正常', 'DELL服务器-R731', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (101, '张先敏', 'EL201811IT003', '服务器', '服务器', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '财务使用管理', '正常', 'DELL服务器-T30', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (102, '张先敏', 'EL201811IT004', '服务器', '服务器', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '财务使用管理', '正常', 'DELL服务器-T30  ', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (103, '张先敏', NULL, '服务器', '服务器', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '18楼办公区', '正常', 'DELLR730机架式服务器', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (104, '文档储存', '主机编号：12001360                 显示器编号：12001377', '文档储存', '文档储存', '食材转入', '天呈慧眼云', '天呈慧眼云', '研发部', '自用', '18楼办公区', '正常', 'Intel(R)_Core(TM)_i5-4590_CPU_@_3.30GHz-8G-硬盘1T  显示器：dell', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (105, '闲置', NULL, '一体机', '苹果', '传媒转入', '天呈慧眼云', '天呈慧眼云', '闲置', '闲置', '18楼食材产品办公区', '正常', 'apple 一体机', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (106, '闲置', '编号：12001297', '主机', '主机', '传媒转入', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '17楼研发旁小办公室', '正常', 'I5-3470 3.2G(内存：4G', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (107, '闲置', '无编号', '台式机', '台式电脑', '自购', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '17楼研发旁小办公室', '正常', '扬天M2200R G3930 4G 500集成 无光驱  21.5', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (108, '闲置', 'BJB201711YF005', '笔记本', '笔记本电脑', '食材', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '行政人事部', '正常', '联想20H5A01UCD  Intel Core i5-7200U 黑 内存:8192MB RAM  2G独显  15.6寸屏幕', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (109, '闲置', 'TS201811YF014', '主机', '主机', '食材转入', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '蒲嘉林处', '正常', 'Mac Mini', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (110, '闲置', '00001', '笔记本', '笔记本电脑', '自购', '天呈慧眼云', '天呈慧眼云', '闲置', '外借', '行政人事部', '正常', '扬天笔记本V330-14 i5-8250 4G 500G 2G 无光驱 WIN10 14.0 铁灰色 无指纹 无背光', 1, '套', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (111, '闲置', '显示器编号：120001379', '显示器', '戴尔', '食材转入', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '17楼研发旁小办公室', '正常', '显示器：DELL', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (112, '闲置', '显示器编号：12000465', '显示器', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '行政人事办公室', '正常', '显示器：DELL', 1, '台', NULL, NULL, NULL);
INSERT INTO `hyy_office_computer` VALUES (113, '闲置', '显示器编号：12000164', '显示器', '戴尔', '传媒转入', '天呈慧眼云', '天呈慧眼云', '闲置', '自用', '17楼研发旁小办公室', '正常', '显示器：DELL', 1, '台', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for mybatis_expansion
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_expansion`;
CREATE TABLE `mybatis_expansion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `apple` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '苹果',
  `delete` int(255) NULL DEFAULT NULL COMMENT '逻辑删除',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'mybatis plus 扩展测试\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_used` timestamp(0) NULL,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nameZh` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (6, 'ROLE_admin', '系统管理员');
INSERT INTO `role` VALUES (16, 'ROLE_adm', '行政');
INSERT INTO `role` VALUES (17, 'ROLE_pm', '项目经理');
INSERT INTO `role` VALUES (21, 'ROLE_rs', '人事');

SET FOREIGN_KEY_CHECKS = 1;
