/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : db_performance_manage_system_dev

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-03-28 16:52:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for biz_dept_scores
-- ----------------------------
DROP TABLE IF EXISTS `biz_dept_scores`;
CREATE TABLE `biz_dept_scores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `project_type_id` bigint(20) DEFAULT NULL,
  `curr_month` date DEFAULT NULL COMMENT '月份',
  `finish_score` double DEFAULT NULL COMMENT '该月份已完成',
  `curr_score` double DEFAULT NULL COMMENT '编辑当月积分',
  `total_score` double DEFAULT NULL COMMENT '该月份总的(等于 curr_socre+score_from_last）',
  `score_from_last` double DEFAULT NULL COMMENT '从上月结余的积分',
  `score_to_next` double DEFAULT NULL COMMENT '结余到下月的积分',
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='机构（部门）积分';

-- ----------------------------
-- Records of biz_dept_scores
-- ----------------------------
INSERT INTO `biz_dept_scores` VALUES ('29', '71', '3', '2018-03-01', null, '30', '30', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('30', '72', '3', '2018-03-01', null, '30', '30', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('31', '71', '1', '2018-03-01', null, '40', '40', null, null, '2018-03-19 13:46:06');
INSERT INTO `biz_dept_scores` VALUES ('32', '72', '1', '2018-03-01', null, '40', '40', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('33', '73', '1', '2018-03-01', null, '30', '30', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('34', '74', '1', '2018-03-01', null, '30', '30', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('35', '75', '1', '2018-03-01', null, '100', '100', null, null, '2018-03-19 11:50:53');
INSERT INTO `biz_dept_scores` VALUES ('36', '76', '1', '2018-03-01', null, '60', '60', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('37', '78', '1', '2018-03-01', null, '40', '40', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('38', '79', '1', '2018-03-01', null, '30', '30', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('39', '80', '1', '2018-03-01', null, '30', '30', null, null, null);

-- ----------------------------
-- Table structure for biz_position_project
-- ----------------------------
DROP TABLE IF EXISTS `biz_position_project`;
CREATE TABLE `biz_position_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `position_id` bigint(20) DEFAULT NULL COMMENT '岗位id',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `type` tinyint(4) DEFAULT NULL COMMENT '0 基础  1 联动',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位 基础项目 中间表';

-- ----------------------------
-- Records of biz_position_project
-- ----------------------------

-- ----------------------------
-- Table structure for biz_project
-- ----------------------------
DROP TABLE IF EXISTS `biz_project`;
CREATE TABLE `biz_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_type_id` bigint(20) DEFAULT NULL COMMENT '项目类型id',
  `project_num` varchar(50) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(50) DEFAULT NULL COMMENT '项目名称（或产品名称）',
  `project_remark` varchar(200) DEFAULT NULL COMMENT '项目描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime DEFAULT NULL COMMENT '上次编辑时间',
  `is_product` bit(1) NOT NULL,
  `has_product` bit(1) DEFAULT NULL COMMENT '项目是否有产品',
  `p_id` bigint(20) DEFAULT NULL COMMENT '产品的项目id（当该记录是产品时，该字段才有用）',
  `upper_limit` double DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_project_num` (`project_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1487 DEFAULT CHARSET=utf8 COMMENT='项目管理';

-- ----------------------------
-- Records of biz_project
-- ----------------------------
INSERT INTO `biz_project` VALUES ('1395', '2', null, '省行审批的综合授信', null, '0', '2018-03-03 00:00:00', null, '\0', '', null, null, null);
INSERT INTO `biz_project` VALUES ('1396', '2', null, '项目融资放款', null, '0', '2018-03-04 00:00:00', null, '\0', '', null, null, null);
INSERT INTO `biz_project` VALUES ('1397', '2', null, '项目融资', null, '0', '2018-03-05 00:00:00', null, '\0', '', null, null, null);
INSERT INTO `biz_project` VALUES ('1398', '2', null, '分行审批的授信/低风险业务（主调、辅调）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1399', '2', null, '贸融、供应链放款（主调、辅调）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1400', '2', null, '非流贷类额度首次支用（主调、辅调）（额度有效期内）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1401', '2', null, '授信客户重检（主调、副调）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1402', '2', null, '管户（含公贷、贸融、供应链）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1403', '2', null, '贷后（含公贷、贸融、供应链）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1404', '2', null, '国际贸易融资（技术审查）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1405', '2', null, '公司授信收入（存量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1406', '2', null, '公司授信收入（增量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1407', '2', null, '对公理财收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1408', '2', null, '托管业务收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1409', '2', null, '同业收入（存量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1410', '2', null, '同业收入（增量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1411', '2', null, '中间业务收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1412', '2', null, '再贴现和小票收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1413', '2', null, '中间业务收入（低风险业务-承兑手续费）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1414', '2', null, '票据大管家(户数)', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1415', '2', null, '票据大管家(收入)', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1416', '2', null, '中间业务收入（票据大管家-承兑手续费）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1417', '2', null, '票据托收', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1418', '2', null, '供应链融资收入（存量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1419', '2', null, '供应链融资收入（增量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1420', '2', null, '开发核心企业', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1421', '2', null, '公司外汇开户', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1422', '2', null, '对公外币储蓄收入（存量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1423', '2', null, '对公外币储蓄收入（增量）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1424', '2', null, '结售汇收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1425', '2', null, '贸易融资业务收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1426', '2', null, '包买福费廷收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1427', '2', null, '一事一议项', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1428', '2', null, '公司定期存量', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1429', '2', null, '公司定期增量（定期新客户）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1430', '2', null, '公司活期存量', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1431', '2', null, '公司活期增量（活期新客户）', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1432', '2', null, '公司负债（特）1', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1433', '2', null, '现金管理', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1434', '2', null, '烟草零售商', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1435', '2', null, '商承和非信贷整合银承收入', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1436', '2', null, '100%承兑保证金', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1437', '2', null, '公司余额以贷引存超出部分', null, '0', '2018-03-03 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1438', '1', null, '个人储蓄资产定期', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1439', '1', null, '个人储蓄资产活期', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1440', '1', null, '以贷引存', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1441', '1', null, '手机银行净增（完成率〈80%）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1442', '1', null, '手机银行净增（完成率>=80%〈100%）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1443', '1', null, '手机银行净增（完成率>=100%）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1444', '1', null, '手机银行净增激活客户（完成率〈80%）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1445', '1', null, '手机银行净增激活客户（完成率>=80%〈100%）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1446', '1', null, '手机银行净增激活客户（完成率>=100%）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1447', '1', null, '理财签约', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1448', '1', null, '网银', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1449', '1', null, '存量客户全套开办(含理财签约、手机银行激活、网银)新增', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1450', '1', null, '存量客户全套开办(含理财签约、手机银行激活、网银)存量', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1451', '1', null, 'VIP卡发卡', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1452', '1', null, '银信通\n（包月）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1453', '1', null, '银信通\n（包年）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1454', '1', null, '代收代付', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1455', '1', null, '代发5万元以上户数', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1456', '1', null, '邮惠付加办', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1457', '1', null, '邮惠付交易（1万以上5万以下）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1458', '1', null, '邮惠付交易（5万以上）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1459', '1', null, '保险销售（期缴）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1460', '1', null, '保险销售（趸交）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1461', '1', null, '理财销量（销量0.8环比峰值增量4）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1462', '1', null, '基金销售', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1463', '1', null, '国债销售', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1464', '1', null, '基金定投', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1465', '1', null, '黄金定投', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1466', '1', null, '贵金属交易（收入）', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1467', '1', null, '第三方存管', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1468', '1', null, '国际汇兑', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1469', '1', null, '结售汇', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1470', '1', null, '电子支付商户发展奖', null, '0', '2018-03-19 00:00:00', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1471', '2', null, '总行审批的综合授信（主调、辅调）', null, '0', '2018-03-03 00:00:00', null, '', '\0', '1397', null, null);
INSERT INTO `biz_project` VALUES ('1472', '2', null, '低风险业务（主调、辅调）', null, '0', '2018-03-03 00:00:00', null, '', '\0', '1395', null, null);
INSERT INTO `biz_project` VALUES ('1473', '2', null, '综合授信首次支用（额度有效期内）', null, '0', '2018-03-03 00:00:00', null, '', '\0', '1396', null, null);
INSERT INTO `biz_project` VALUES ('1474', '1', '', '小额贷款', '14124', '0', '2018-03-28 10:04:21', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1476', '2', null, '小企业贷款', '', '0', '2018-03-28 11:44:17', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1477', '3', null, '信用卡进件', '', '0', '2018-03-28 11:44:38', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1478', '3', null, '信用卡发卡2018第一季度', '', '0', '2018-03-28 11:44:47', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1479', '3', null, '信用卡首刷2018第一季度', '', '0', '2018-03-28 11:44:55', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1480', '3', null, '信用卡发卡', '', '0', '2018-03-28 11:45:03', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1481', '2', null, '信用卡首刷', '', '0', '2018-03-28 11:45:09', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1482', '3', null, '特惠商户（信用卡）', '', '0', '2018-03-28 11:45:40', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1483', '1', null, 'POS机', '', '0', '2018-03-28 11:45:47', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1484', '8', null, '个人负债（定期）', '', '0', '2018-03-28 11:45:56', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1485', '8', null, '个人负债（活期）', '', '0', '2018-03-28 11:46:03', null, '\0', '\0', null, null, null);
INSERT INTO `biz_project` VALUES ('1486', '1', null, '个人负债以贷引存率', '', '0', '2018-03-28 11:46:13', null, '\0', '\0', null, null, null);

-- ----------------------------
-- Table structure for biz_project_type
-- ----------------------------
DROP TABLE IF EXISTS `biz_project_type`;
CREATE TABLE `biz_project_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='项目类型';

-- ----------------------------
-- Records of biz_project_type
-- ----------------------------
INSERT INTO `biz_project_type` VALUES ('1', '个金类');
INSERT INTO `biz_project_type` VALUES ('2', '公司类');
INSERT INTO `biz_project_type` VALUES ('3', '信用卡类');
INSERT INTO `biz_project_type` VALUES ('7', '小企业类');
INSERT INTO `biz_project_type` VALUES ('8', '综合类');
INSERT INTO `biz_project_type` VALUES ('9', '零售类');

-- ----------------------------
-- Table structure for biz_project_type_scores
-- ----------------------------
DROP TABLE IF EXISTS `biz_project_type_scores`;
CREATE TABLE `biz_project_type_scores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_type_id` bigint(20) DEFAULT NULL COMMENT '项目类型id',
  `curr_month` date DEFAULT NULL COMMENT '当前月份',
  `finish_score` double DEFAULT NULL COMMENT '该类型当月完成的积分',
  `curr_score` double DEFAULT NULL COMMENT '该类型当月分配积分',
  `total_score` double DEFAULT NULL COMMENT '该月份总的(等于 curr_socre+score_from_last）',
  `score_from_last` double DEFAULT NULL COMMENT '从上月结余的积分',
  `score_to_next` double DEFAULT NULL COMMENT '结余到下月的积分',
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='项目类型每个月分的积分';

-- ----------------------------
-- Records of biz_project_type_scores
-- ----------------------------
INSERT INTO `biz_project_type_scores` VALUES ('1', '1', '2018-02-01', '50', '80', '100', '20', '50', null);
INSERT INTO `biz_project_type_scores` VALUES ('2', '1', '2018-03-01', null, '200', '250', '50', null, null);
INSERT INTO `biz_project_type_scores` VALUES ('3', '2', '2018-03-01', null, '150', null, null, null, null);
INSERT INTO `biz_project_type_scores` VALUES ('4', '3', '2018-03-01', null, '150', null, null, null, null);

-- ----------------------------
-- Table structure for biz_total_scores
-- ----------------------------
DROP TABLE IF EXISTS `biz_total_scores`;
CREATE TABLE `biz_total_scores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `curr_month` date DEFAULT NULL COMMENT '当前月份',
  `finish_score` double DEFAULT NULL COMMENT '该月份的完成的积分',
  `curr_score` double DEFAULT NULL COMMENT '该月份的总积分',
  `total_score` double DEFAULT NULL COMMENT '该月份总的(等于 curr_socre+score_from_last）',
  `score_from_last` double DEFAULT NULL COMMENT '从上月结余的积分',
  `score_to_next` double DEFAULT NULL COMMENT '结余到下月的积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='总积分';

-- ----------------------------
-- Records of biz_total_scores
-- ----------------------------
INSERT INTO `biz_total_scores` VALUES ('1', '2018-02-01', '20', '500', '600', '100', '480');
INSERT INTO `biz_total_scores` VALUES ('2', '2018-03-01', null, '500', '980', '480', null);

-- ----------------------------
-- Table structure for biz_upload_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_upload_info`;
CREATE TABLE `biz_upload_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `original_name` varchar(50) DEFAULT NULL COMMENT '文件原名',
  `curr_name` varchar(100) DEFAULT NULL COMMENT '保存在服务器的文件名字',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小（字节）',
  `file_suffix` varchar(10) DEFAULT NULL COMMENT '文件后缀',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件在服务器硬盘的路径',
  `upload_user_id` bigint(20) DEFAULT NULL COMMENT '文件上传者的id',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `from_module` varchar(50) DEFAULT NULL COMMENT '从哪个模块菜单里上传的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_upload_info
-- ----------------------------
INSERT INTO `biz_upload_info` VALUES ('187', '项目导入(公司类).xls', 'c0cc7e0d-eb0e-4f50-bf9d-ed9269e895df.xls', '39936', 'xls', 'D:/pms/project/c0cc7e0d-eb0e-4f50-bf9d-ed9269e895df.xls', '1', '2018-03-22 16:28:50', 'project');
INSERT INTO `biz_upload_info` VALUES ('188', '项目导入(公司类).xls', 'ea38a51c-ea7a-4cd1-a558-3ea909d7d9b7.xls', '35328', 'xls', 'D:/pms/project/ea38a51c-ea7a-4cd1-a558-3ea909d7d9b7.xls', '1', '2018-03-22 16:31:43', 'project');
INSERT INTO `biz_upload_info` VALUES ('189', '项目导入(公司类).xls', 'e2e5152c-c826-4c75-af88-e4887d985520.xls', '29184', 'xls', 'D:/pms/project/e2e5152c-c826-4c75-af88-e4887d985520.xls', '1', '2018-03-22 16:32:15', 'project');
INSERT INTO `biz_upload_info` VALUES ('190', '项目导入(公司类).xls', '334a8d37-683c-4865-8e14-118705a02ba4.xls', '41472', 'xls', 'D:/pms/project/334a8d37-683c-4865-8e14-118705a02ba4.xls', '1', '2018-03-22 16:35:46', 'project');
INSERT INTO `biz_upload_info` VALUES ('191', '项目导入(公司类).xls', 'd29b5232-7f57-40b3-a07f-861e92efab4d.xls', '41472', 'xls', 'D:/pms/project/d29b5232-7f57-40b3-a07f-861e92efab4d.xls', '1', '2018-03-22 16:39:00', 'project');
INSERT INTO `biz_upload_info` VALUES ('192', '项目导入(公司类).xls', 'c4812bc0-509f-461f-ac59-7ece4a3ecee8.xls', '41472', 'xls', 'D:/pms/project/c4812bc0-509f-461f-ac59-7ece4a3ecee8.xls', '1', '2018-03-22 16:48:50', 'project');
INSERT INTO `biz_upload_info` VALUES ('193', '项目导入(公司类).xls', '879b66aa-1300-4589-9495-ebab7317156d.xls', '39936', 'xls', 'D:/pms/project/879b66aa-1300-4589-9495-ebab7317156d.xls', '1', '2018-03-22 16:59:29', 'project');
INSERT INTO `biz_upload_info` VALUES ('194', 'score_data_import.xls', '97289a70-4546-4363-b0e5-75f472c3cd93.xls', '46080', 'xls', 'D:/pms/score_data/97289a70-4546-4363-b0e5-75f472c3cd93.xls', '1', '2018-03-26 16:48:41', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('195', 'score_data_import.xls', '9fa98967-ccc9-40e9-92ea-6298a782228f.xls', '46080', 'xls', 'D:/pms/score_data/9fa98967-ccc9-40e9-92ea-6298a782228f.xls', '1', '2018-03-26 16:49:03', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('196', 'score_data_import.xls', '4a434bdb-f85b-4974-9a84-cd5127f35cd6.xls', '46080', 'xls', 'D:/pms/score_data/4a434bdb-f85b-4974-9a84-cd5127f35cd6.xls', '1', '2018-03-26 16:49:40', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('197', 'score_data_import.xls', '426d62a7-7df3-491b-adeb-2027ee3a676d.xls', '46080', 'xls', 'D:/pms/score_data/426d62a7-7df3-491b-adeb-2027ee3a676d.xls', '1', '2018-03-26 16:50:01', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('198', 'score_data_import.xls', 'fe3be9f7-dbde-4146-a9a9-3f805f25b5b0.xls', '46592', 'xls', 'D:/pms/score_data/fe3be9f7-dbde-4146-a9a9-3f805f25b5b0.xls', '1', '2018-03-26 16:50:56', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('199', 'score_data_import.xls', '5a8cf034-1acc-4a8c-b0c3-75d04de3b434.xls', '46592', 'xls', 'D:/pms/score_data/5a8cf034-1acc-4a8c-b0c3-75d04de3b434.xls', '1', '2018-03-26 16:55:47', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('200', 'score_data_import.xls', 'ca4e802b-f367-4b9a-9a44-f1a42d044128.xls', '47104', 'xls', 'D:/pms/score_data/ca4e802b-f367-4b9a-9a44-f1a42d044128.xls', '1', '2018-03-27 17:05:54', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('201', 'score_data_import.xls', '9411cc4c-4be1-4e70-adbe-abc4667d6c34.xls', '47104', 'xls', 'D:/pms/score_data/9411cc4c-4be1-4e70-adbe-abc4667d6c34.xls', '1', '2018-03-27 17:08:12', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('202', '业务数据导入.xlsx', 'e03731ea-5cdc-4325-aa7e-1f688e6ddd20.xlsx', '27679', 'xlsx', 'D:/pms/score_data/e03731ea-5cdc-4325-aa7e-1f688e6ddd20.xlsx', '1', '2018-03-28 10:02:55', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('203', '业务数据导入.xlsx', '38d04942-6e11-4254-895f-4cc8146e5fad.xlsx', '27690', 'xlsx', 'D:/pms/score_data/38d04942-6e11-4254-895f-4cc8146e5fad.xlsx', '1', '2018-03-28 11:38:37', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('204', '业务数据导入.xlsx', '1a78d245-3832-454a-8e5f-6b7116f3ca60.xlsx', '27690', 'xlsx', 'D:/pms/score_data/1a78d245-3832-454a-8e5f-6b7116f3ca60.xlsx', '1', '2018-03-28 11:43:54', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('205', '业务数据导入.xlsx', '36e224ee-d8be-428a-9ce6-876c55156d1e.xlsx', '27690', 'xlsx', 'D:/pms/score_data/36e224ee-d8be-428a-9ce6-876c55156d1e.xlsx', '1', '2018-03-28 11:47:54', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('206', '业务数据导入.xlsx', 'd6a12c66-a999-4a74-8eb5-0db644a3d380.xlsx', '27687', 'xlsx', 'D:/pms/score_data/d6a12c66-a999-4a74-8eb5-0db644a3d380.xlsx', '1', '2018-03-28 11:51:16', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('207', '业务数据导入.xlsx', 'aed81b42-8016-444d-8949-4ea04fa1e834.xlsx', '27682', 'xlsx', 'D:/pms/score_data/aed81b42-8016-444d-8949-4ea04fa1e834.xlsx', '1', '2018-03-28 11:55:56', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('208', '业务数据导入.xlsx', '53a5d676-d61a-432a-ae9b-dd41a34bda1c.xlsx', '27682', 'xlsx', 'D:/pms/score_data/53a5d676-d61a-432a-ae9b-dd41a34bda1c.xlsx', '1', '2018-03-28 11:59:33', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('209', '业务数据导入.xlsx', '61306ff7-fea3-4b7c-b648-0a5d7e7241e2.xlsx', '29744', 'xlsx', 'D:/pms/score_data/61306ff7-fea3-4b7c-b648-0a5d7e7241e2.xlsx', '1', '2018-03-28 12:01:12', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('210', '2018.3积分基本项目及录入表样326 (1).xls', 'ff9cb453-5a23-40f1-b968-9ace48699bd3.xls', '140288', 'xls', 'D:/pms/score_data/ff9cb453-5a23-40f1-b968-9ace48699bd3.xls', '1', '2018-03-28 12:01:32', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('211', '业务数据导入.xlsx', 'fabe35b3-1e91-488d-a694-d0fe48d823ae.xlsx', '29744', 'xlsx', 'D:/pms/score_data/fabe35b3-1e91-488d-a694-d0fe48d823ae.xlsx', '1', '2018-03-28 12:02:30', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('212', '业务数据导入.xlsx', '3552521d-d378-4d15-ab02-edf7f65f1662.xlsx', '29744', 'xlsx', 'D:/pms/score_data/3552521d-d378-4d15-ab02-edf7f65f1662.xlsx', '1', '2018-03-28 12:03:44', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('213', '业务数据导入.xlsx', 'fe76bc16-b45a-498d-b2a4-5d41c72a2bda.xlsx', '29744', 'xlsx', 'D:/pms/score_data/fe76bc16-b45a-498d-b2a4-5d41c72a2bda.xlsx', '1', '2018-03-28 12:05:44', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('214', '业务数据导入.xlsx', '9b66b3e6-6b38-4d98-b7dc-b26ed3a2c48f.xlsx', '29744', 'xlsx', 'D:/pms/score_data/9b66b3e6-6b38-4d98-b7dc-b26ed3a2c48f.xlsx', '1', '2018-03-28 12:59:59', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('215', '业务数据导入.xlsx', '293fe362-7dfd-49cb-b03a-c03f39347df2.xlsx', '29744', 'xlsx', 'D:/pms/score_data/293fe362-7dfd-49cb-b03a-c03f39347df2.xlsx', '1', '2018-03-28 13:00:42', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('216', '业务数据导入.xlsx', '79f012f3-1753-4c62-8eb7-7afd629af4a8.xlsx', '29744', 'xlsx', 'D:/pms/score_data/79f012f3-1753-4c62-8eb7-7afd629af4a8.xlsx', '1', '2018-03-28 13:03:03', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('217', '业务数据导入.xlsx', 'ef00b988-7258-40f5-8774-b934786579f1.xlsx', '29744', 'xlsx', 'D:/pms/score_data/ef00b988-7258-40f5-8774-b934786579f1.xlsx', '1', '2018-03-28 13:03:47', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('218', '业务数据导入.xlsx', 'e5d14762-187a-4d3e-b04a-6fdd824cb34c.xlsx', '29744', 'xlsx', 'D:/pms/score_data/e5d14762-187a-4d3e-b04a-6fdd824cb34c.xlsx', '1', '2018-03-28 13:04:47', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('219', '业务数据导入.xlsx', 'a87205e2-499b-4123-b852-6fd5adde2c14.xlsx', '29744', 'xlsx', 'D:/pms/score_data/a87205e2-499b-4123-b852-6fd5adde2c14.xlsx', '1', '2018-03-28 13:07:19', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('220', '业务数据导入.xlsx', '520cc697-1a2f-40b5-8a48-e3b3e541a263.xlsx', '29744', 'xlsx', 'D:/pms/score_data/520cc697-1a2f-40b5-8a48-e3b3e541a263.xlsx', '1', '2018-03-28 13:07:50', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('221', '业务数据导入.xlsx', 'f523391e-656b-4bb5-8bfe-d25d198638e1.xlsx', '29744', 'xlsx', 'D:/pms/score_data/f523391e-656b-4bb5-8bfe-d25d198638e1.xlsx', '1', '2018-03-28 13:26:31', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('222', '业务数据导入.xlsx', 'ee0595bd-8779-44ce-967c-62a961bfbb48.xlsx', '29744', 'xlsx', 'D:/pms/score_data/ee0595bd-8779-44ce-967c-62a961bfbb48.xlsx', '1', '2018-03-28 13:31:42', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('223', '业务数据导入.xlsx', '7b0b88d8-6c37-4148-8789-8be113ad8065.xlsx', '29744', 'xlsx', 'D:/pms/score_data/7b0b88d8-6c37-4148-8789-8be113ad8065.xlsx', '1', '2018-03-28 13:33:21', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('224', '业务数据导入.xlsx', 'e3a78b9f-10ac-4df0-abf4-cd02d9abc42f.xlsx', '29859', 'xlsx', 'D:/pms/score_data/e3a78b9f-10ac-4df0-abf4-cd02d9abc42f.xlsx', '1', '2018-03-28 13:37:29', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('225', '业务数据导入.xlsx', 'c01c413b-1a9e-413f-84ff-301e957dd963.xlsx', '29859', 'xlsx', 'D:/pms/score_data/c01c413b-1a9e-413f-84ff-301e957dd963.xlsx', '1', '2018-03-28 13:39:34', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('226', '业务数据导入.xlsx', '14ffa8ee-9775-46fe-b9ef-67ba15a7363a.xlsx', '29830', 'xlsx', 'D:/pms/score_data/14ffa8ee-9775-46fe-b9ef-67ba15a7363a.xlsx', '1', '2018-03-28 13:43:40', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('227', '业务数据导入.xlsx', '9bb4344b-4fb6-4b92-9657-fbf48add5fe3.xlsx', '29830', 'xlsx', 'D:/pms/score_data/9bb4344b-4fb6-4b92-9657-fbf48add5fe3.xlsx', '1', '2018-03-28 13:45:27', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('228', '业务数据导入.xlsx', 'd72a64c1-e117-45bc-aef4-e4175296434a.xlsx', '29830', 'xlsx', 'D:/pms/score_data/d72a64c1-e117-45bc-aef4-e4175296434a.xlsx', '1', '2018-03-28 13:49:20', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('229', '业务数据导入.xlsx', 'b5b14e26-9678-4c59-96e8-d6292e4cbbf1.xlsx', '29830', 'xlsx', 'D:/pms/score_data/b5b14e26-9678-4c59-96e8-d6292e4cbbf1.xlsx', '1', '2018-03-28 13:58:12', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('230', '业务数据导入.xlsx', 'eaac8d7c-694d-4e15-9cff-97bf75962a2e.xlsx', '29830', 'xlsx', 'D:/pms/score_data/eaac8d7c-694d-4e15-9cff-97bf75962a2e.xlsx', '1', '2018-03-28 14:03:21', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('231', '业务数据导入.xlsx', '4de0af12-f804-440c-b656-7a9a6e74a908.xlsx', '29830', 'xlsx', 'D:/pms/score_data/4de0af12-f804-440c-b656-7a9a6e74a908.xlsx', '1', '2018-03-28 14:07:20', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('232', '业务数据导入.xlsx', '58db5a47-1908-45e2-bd0e-b2e27ec81ce5.xlsx', '29830', 'xlsx', 'D:/pms/score_data/58db5a47-1908-45e2-bd0e-b2e27ec81ce5.xlsx', '1', '2018-03-28 14:23:55', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('233', '业务数据导入.xlsx', 'b62891d2-2f16-4d59-b544-cef1b188926c.xlsx', '29830', 'xlsx', 'D:/pms/score_data/b62891d2-2f16-4d59-b544-cef1b188926c.xlsx', '1', '2018-03-28 14:25:46', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('234', 'project_import.xls', 'bb77d66f-22bf-4f6a-aa7a-633d3418f5d4.xls', '25088', 'xls', 'D:/pms/project/bb77d66f-22bf-4f6a-aa7a-633d3418f5d4.xls', '1', '2018-03-28 15:32:13', 'project');
INSERT INTO `biz_upload_info` VALUES ('235', 'project_import.xls', '16d31d7d-c33c-4eaa-bc70-8707e1f79886.xls', '25088', 'xls', 'D:/pms/project/16d31d7d-c33c-4eaa-bc70-8707e1f79886.xls', '1', '2018-03-28 15:33:19', 'project');
INSERT INTO `biz_upload_info` VALUES ('236', 'project_import.xls', '81c20ca1-86c2-45e4-aa89-6c5ec0eaad1a.xls', '25088', 'xls', 'D:/pms/project/81c20ca1-86c2-45e4-aa89-6c5ec0eaad1a.xls', '1', '2018-03-28 15:34:47', 'project');
INSERT INTO `biz_upload_info` VALUES ('237', 'project_import.xls', 'e38da656-5a2e-465e-8357-cd9ddb0929d3.xls', '25088', 'xls', 'D:/pms/project/e38da656-5a2e-465e-8357-cd9ddb0929d3.xls', '1', '2018-03-28 15:36:32', 'project');
INSERT INTO `biz_upload_info` VALUES ('238', 'project_import.xls', 'dba05822-11d9-45eb-ba6e-503fe382f569.xls', '25088', 'xls', 'D:/pms/project/dba05822-11d9-45eb-ba6e-503fe382f569.xls', '1', '2018-03-28 15:36:53', 'project');
INSERT INTO `biz_upload_info` VALUES ('239', 'project_import.xls', '87e3e3f3-3e15-4ffb-bc17-dc6ce7338984.xls', '25600', 'xls', 'D:/pms/project/87e3e3f3-3e15-4ffb-bc17-dc6ce7338984.xls', '1', '2018-03-28 15:38:00', 'project');
INSERT INTO `biz_upload_info` VALUES ('240', 'score_data_import.xls', 'ccb7346e-b026-46de-bb08-8ba7ea4abd03.xls', '58368', 'xls', 'D:/pms/score_data/ccb7346e-b026-46de-bb08-8ba7ea4abd03.xls', '1', '2018-03-28 15:49:36', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('241', 'project_import.xls', '7eeef701-70ca-481d-82cd-b70c2ebb535b.xls', '25600', 'xls', 'D:/pms/project/7eeef701-70ca-481d-82cd-b70c2ebb535b.xls', '1', '2018-03-28 15:55:41', 'project');
INSERT INTO `biz_upload_info` VALUES ('242', 'score_data_import.xls', '2c48238b-2928-4ee5-901f-3a1d25d519bd.xls', '58368', 'xls', 'D:/pms/score_data/2c48238b-2928-4ee5-901f-3a1d25d519bd.xls', '1', '2018-03-28 15:57:59', 'score_data');
INSERT INTO `biz_upload_info` VALUES ('243', 'user_import.xls', '41f0b418-70cb-4295-aa81-bbb60046e093.xls', '24576', 'xls', 'D:/pms/user/41f0b418-70cb-4295-aa81-bbb60046e093.xls', '1', '2018-03-28 16:21:08', 'user');
INSERT INTO `biz_upload_info` VALUES ('244', 'user_import.xls', 'de721c74-ef1d-4453-a19c-c756fc5fb732.xls', '24576', 'xls', 'D:/pms/user/de721c74-ef1d-4453-a19c-c756fc5fb732.xls', '1', '2018-03-28 16:22:11', 'user');
INSERT INTO `biz_upload_info` VALUES ('245', 'user_import.xls', '18fe0ea0-d43c-48f0-bf7b-f6583f5e4806.xls', '24576', 'xls', 'D:/pms/user/18fe0ea0-d43c-48f0-bf7b-f6583f5e4806.xls', '1', '2018-03-28 16:22:48', 'user');
INSERT INTO `biz_upload_info` VALUES ('246', 'user_import.xls', '8f18dd7c-ca4d-468b-9fa1-fa330ae0d8da.xls', '24576', 'xls', 'D:/pms/user/8f18dd7c-ca4d-468b-9fa1-fa330ae0d8da.xls', '1', '2018-03-28 16:27:11', 'user');
INSERT INTO `biz_upload_info` VALUES ('247', 'user_import.xls', '29e5f4d4-297b-4d90-9a76-89898e883001.xls', '24576', 'xls', 'D:/pms/user/29e5f4d4-297b-4d90-9a76-89898e883001.xls', '1', '2018-03-28 16:28:46', 'user');

-- ----------------------------
-- Table structure for score_base_project
-- ----------------------------
DROP TABLE IF EXISTS `score_base_project`;
CREATE TABLE `score_base_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `score_data_id` bigint(20) DEFAULT NULL COMMENT '业务数据id',
  `loan_no` varchar(255) DEFAULT NULL COMMENT '借据号',
  `loan_date` date DEFAULT NULL COMMENT '放款时间',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位(元、万元等)',
  `biz_amount` double DEFAULT NULL COMMENT '业务数量',
  `save_or_add` varchar(20) DEFAULT NULL COMMENT '存量、增量',
  `standard_rate` varchar(20) DEFAULT NULL COMMENT '对标利率',
  `loan_rate` varchar(20) DEFAULT NULL COMMENT '放款利率',
  `rate_score` double DEFAULT NULL COMMENT '利率得分',
  `rate_shareman_no` varchar(50) DEFAULT NULL COMMENT '利率得分人工号',
  `customer` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `id_card` varchar(255) DEFAULT NULL COMMENT '客户身份证/营业执照号码',
  `fall_dept` bigint(20) DEFAULT NULL COMMENT '落地部门',
  `market_man_no` varchar(100) DEFAULT NULL COMMENT '营销人员工号',
  `team_emp_names` varchar(255) DEFAULT NULL COMMENT '团队分配名单',
  `team_emp_nos` varchar(255) DEFAULT NULL COMMENT '团队分配名单工号(以“+”分割)',
  `team_emp_rates` varchar(255) DEFAULT NULL COMMENT '团队分配比例(以“+”分割)',
  `master_no` varchar(50) DEFAULT NULL COMMENT '主调工号',
  `assistant_no` varchar(50) DEFAULT NULL COMMENT '副调工号',
  `is_retrospect_link` bit(1) DEFAULT NULL COMMENT '是否追溯联动',
  `retrospect_link_no` varchar(100) DEFAULT NULL COMMENT '联动贷款客户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8 COMMENT='积分 数据导入 （基础项目）';

-- ----------------------------
-- Records of score_base_project
-- ----------------------------
INSERT INTO `score_base_project` VALUES ('176', '26', '4499981Q118012345678901', '2018-01-02', '1474', '万元', '30', '增量', '0.01%', '0.01%', null, null, '王二小', '440604198502271234', '72', '234', null, null, null, '24234', '3534', '', '44060419850227123402');
INSERT INTO `score_base_project` VALUES ('177', '26', '4499981Q118012345678902', '2018-01-02', '1474', '万元', '30', '增量', '0.01%', '0.01%', null, null, '王二小', '440604198502271234', '72', '4325', null, null, null, '2314', '53425', '', '44060419850227123402');
INSERT INTO `score_base_project` VALUES ('178', '26', '4499981Q118012345678903', '2018-01-02', '1474', '万元', '20', '增量', '0.01%', '0.01%', null, null, '王二小', '440604198502271234', '72', '6456', null, null, null, '4654', '23423', '\0', null);
INSERT INTO `score_base_project` VALUES ('179', '26', '4499981Q118012345678904', '2018-01-02', '1474', '万元', '20', '增量', '0.01%', '0.01%', null, null, '王二小', '440604198502271234', '72', '4242', null, null, null, '6456', '457547', '\0', null);
INSERT INTO `score_base_project` VALUES ('180', '26', '02440014321801310061000', '2018-01-02', '1476', '万元', '100', '存量', '0.01%', '0.11%', '0.005', 'hhhhh456', '佛山市*建材有限公司', '10000004943366', '72', '123', '张钰+陈华+李宝+豆丁', 'nosdf+aga4234+2342+gaga', '50%+20%+10%+20%', '567', '535', '\0', null);
INSERT INTO `score_base_project` VALUES ('181', '26', '0244001432180131006100A', '2018-01-02', '1476', '万元', '500', '增量', '0.01%', '0.11%', '0.025', 'sss546', '佛山市*建材有限公司', '10000004943366', '72', '123', null, null, null, '7567', 'wert', '', '10000004943366000011');
INSERT INTO `score_base_project` VALUES ('182', '26', '02440014321801240061000', '2018-01-02', '1476', '万元', '400', '存量', '0.01%', '5.21%', '1.04', 'dddd242', '佛山市*建材有限公司', '10000004943366', '72', '313', null, null, null, '8678', '234234', '\0', null);

-- ----------------------------
-- Table structure for score_data
-- ----------------------------
DROP TABLE IF EXISTS `score_data`;
CREATE TABLE `score_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rule_template_id` bigint(20) DEFAULT NULL COMMENT '模板id',
  `import_no` varchar(50) DEFAULT NULL COMMENT '导入编号',
  `import_status` tinyint(4) DEFAULT NULL COMMENT '导入进程',
  `import_time` datetime DEFAULT NULL,
  `base_count` int(11) DEFAULT NULL COMMENT '导入的基础业务数据条数',
  `market_count` int(11) DEFAULT NULL COMMENT '导入的营销业务数据条数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='业务数据管理表';

-- ----------------------------
-- Records of score_data
-- ----------------------------
INSERT INTO `score_data` VALUES ('26', '2', '20180328155802', '3', '2018-03-28 15:58:03', '7', '12');

-- ----------------------------
-- Table structure for score_market_project
-- ----------------------------
DROP TABLE IF EXISTS `score_market_project`;
CREATE TABLE `score_market_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `score_data_id` bigint(20) DEFAULT NULL COMMENT '业务数据id',
  `loan_date` date DEFAULT NULL COMMENT '放款时间',
  `fall_dept` bigint(20) DEFAULT NULL COMMENT '落地部门',
  `product_id` bigint(20) DEFAULT NULL COMMENT '产品id',
  `customer_name` varchar(20) DEFAULT NULL,
  `id_card` varchar(100) DEFAULT NULL COMMENT '客户身份证/营业执照号码',
  `market_man_no` varchar(100) DEFAULT NULL COMMENT '营销人员工号',
  `loan_no` varchar(255) DEFAULT NULL COMMENT '借据号（属于联动填写,非联动项目不填）',
  `score_amount` varchar(50) DEFAULT NULL COMMENT '积分量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位(个等)',
  `biz_amount` varchar(50) DEFAULT NULL COMMENT '业务数量',
  `biz_unit` varchar(20) DEFAULT NULL COMMENT '业务量单位',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='积分 数据导入 （营销项目）';

-- ----------------------------
-- Records of score_market_project
-- ----------------------------
INSERT INTO `score_market_project` VALUES ('82', '26', '2018-01-15', '72', '1477', '王二小', '440604198502271234', '20081229310', '02440014321801310061000', '1', '个', null, null, null);
INSERT INTO `score_market_project` VALUES ('83', '26', '2018-01-16', '72', '1478', '王玉', '440604198605125461', '20081229310', '0244001432180131006100A', '1', '个', null, null, null);
INSERT INTO `score_market_project` VALUES ('84', '26', '2018-01-17', '72', '1479', '李木木', '440605198705045461', '20081229311', '02440014321801240061000', '1', '个', null, null, null);
INSERT INTO `score_market_project` VALUES ('85', '26', '2018-01-16', '72', '1480', '王玉', '440604198605125461', '20081229311', null, null, null, null, null, null);
INSERT INTO `score_market_project` VALUES ('86', '26', '2018-01-17', '72', '1481', '李木木', '440605198705045461', '20081229311', null, null, null, null, null, null);
INSERT INTO `score_market_project` VALUES ('87', '26', '2018-01-18', '72', '1482', '王玉', '914403002792371234', '20081229311', null, null, null, null, null, '时间填写：签约时间；业务识别码填写：营业执照号码');
INSERT INTO `score_market_project` VALUES ('88', '26', '2018-01-19', '72', '1483', '李木木', '914403002792371234', '20081229311', null, null, null, null, null, '时间填写：签约时间；业务识别码填写：营业执照号码');
INSERT INTO `score_market_project` VALUES ('89', '26', '2018-01-17', '72', '1484', '张三', '605881001200001292', '20081229310', null, '200', '分', '300', '万元', '业务识别码填写：账号');
INSERT INTO `score_market_project` VALUES ('90', '26', '2018-03-20', '72', '1485', '张三', '605881001200001292', '20081229310', null, '1500', '分', '150', '元', '业务识别码填写：账号；业务量：按季度结息金额录入');
INSERT INTO `score_market_project` VALUES ('91', '26', '2018-03-21', '72', '1486', '张三', '605881001200001292', '20081229311', '4499981Q11801234567890', '10%', null, '1-3', '万元', '积分量：季均余额的以贷引存率；业务量：季均余额以贷引存金额区间');
INSERT INTO `score_market_project` VALUES ('92', '26', '2018-01-31', '72', '1441', null, null, '20081229310', null, '12', '户', null, null, null);
INSERT INTO `score_market_project` VALUES ('93', '26', '2018-01-31', '72', '1442', null, null, '20081229310', null, '10', '户', null, null, null);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(20) DEFAULT NULL COMMENT '部门码',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父部门id',
  `charge_man_id` bigint(20) DEFAULT NULL COMMENT '部门负责人id',
  `link_phone` varchar(20) DEFAULT NULL COMMENT '部门电话',
  `email` varchar(50) DEFAULT NULL COMMENT '部门email',
  `ancestor` varchar(255) DEFAULT NULL COMMENT '世代部门码，以，分开',
  `lev` tinyint(4) DEFAULT NULL COMMENT '当前部门所在级别',
  `remark` varchar(255) DEFAULT NULL COMMENT '部门信息',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', null, '机构节点', '0', null, null, null, null, null, null, '2018-02-11 10:54:52', null);
INSERT INTO `sys_dept` VALUES ('71', null, '禅城区', '1', null, null, null, null, null, null, '2018-03-19 10:29:59', '2018-03-19 17:36:29');
INSERT INTO `sys_dept` VALUES ('72', null, '同华区支行', '71', null, null, null, null, null, null, '2018-03-19 10:34:27', null);
INSERT INTO `sys_dept` VALUES ('73', null, '南海区', '1', null, null, null, null, null, null, '2018-03-19 10:35:33', null);
INSERT INTO `sys_dept` VALUES ('74', null, '南海区支行', '73', null, null, null, null, null, null, '2018-03-19 10:35:51', null);
INSERT INTO `sys_dept` VALUES ('75', null, '顺德区', '1', null, null, null, null, null, null, '2018-03-19 10:36:25', null);
INSERT INTO `sys_dept` VALUES ('76', null, '顺德区支行', '75', null, null, null, null, null, null, '2018-03-19 10:36:37', null);
INSERT INTO `sys_dept` VALUES ('78', null, '容桂区支行', '75', null, null, null, null, null, null, '2018-03-19 10:38:52', null);
INSERT INTO `sys_dept` VALUES ('79', null, '三水区', '1', null, null, null, null, null, null, '2018-03-19 10:39:22', null);
INSERT INTO `sys_dept` VALUES ('80', null, '宝南区支行', '79', null, null, null, null, null, null, '2018-03-19 10:39:33', null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(50) DEFAULT NULL COMMENT '用户真实姓名',
  `username` varchar(50) DEFAULT NULL COMMENT '登录名',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `oper_desc` varchar(50) DEFAULT NULL COMMENT '操作行为',
  `oper_url` varchar(200) DEFAULT NULL COMMENT '操作url',
  PRIMARY KEY (`id`),
  KEY `sys_log_oper_time_index` (`oper_time`)
) ENGINE=InnoDB AUTO_INCREMENT=548 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('374', '1', '管理员', 'admin', '2018-03-22 08:58:35', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('375', '1', '管理员', 'admin', '2018-03-22 11:07:20', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('376', '1', '管理员', 'admin', '2018-03-22 11:11:11', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('377', '1', '管理员', 'admin', '2018-03-22 11:11:58', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('378', '1', '管理员', 'admin', '2018-03-22 11:15:05', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('379', '1', '管理员', 'admin', '2018-03-22 11:15:40', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('380', '1', '管理员', 'admin', '2018-03-22 11:16:46', '登出', '/admin/sys/logout');
INSERT INTO `sys_log` VALUES ('381', '7', '大勺哥', 'sa', '2018-03-22 11:16:48', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('382', '1', '管理员', 'admin', '2018-03-22 11:21:55', '登出', '/admin/sys/logout');
INSERT INTO `sys_log` VALUES ('383', '1', '管理员', 'admin', '2018-03-22 11:29:28', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('384', '7', '大勺哥', 'sa', '2018-03-22 11:30:37', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('385', '1', '管理员', 'admin', '2018-03-22 16:24:52', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('386', '1', '管理员', 'admin', '2018-03-22 16:26:56', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('387', '1', '管理员', 'admin', '2018-03-22 16:28:52', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('388', '1', '管理员', 'admin', '2018-03-22 16:31:45', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('389', '1', '管理员', 'admin', '2018-03-22 16:31:47', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('390', '1', '管理员', 'admin', '2018-03-22 16:32:17', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('391', '1', '管理员', 'admin', '2018-03-22 16:35:51', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('392', '1', '管理员', 'admin', '2018-03-21 16:39:05', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('393', '1', '管理员', 'admin', '2018-03-22 16:48:40', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('394', '1', '管理员', 'admin', '2018-03-22 16:48:52', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('395', '1', '管理员', 'admin', '2018-03-22 16:59:12', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('396', '1', '管理员', 'admin', '2018-03-22 16:59:32', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('397', '1', '管理员', 'admin', '2018-03-22 17:09:51', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('398', '1', '管理员', 'admin', '2018-03-22 17:11:30', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('399', '1', '管理员', 'admin', '2018-03-22 17:17:19', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('400', '1', '管理员', 'admin', '2018-03-22 17:17:52', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('401', '1', '管理员', 'admin', '2018-03-22 17:18:48', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('402', '1', '管理员', 'admin', '2018-03-22 17:20:51', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('403', '1', '管理员', 'admin', '2018-03-22 17:21:54', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('404', '1', '管理员', 'admin', '2018-03-22 17:27:04', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('405', '1', '管理员', 'admin', '2018-03-22 17:30:37', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('406', '1', '管理员', 'admin', '2018-03-23 16:12:00', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('407', '1', '管理员', 'admin', '2018-03-26 13:07:16', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('408', '1', '管理员', 'admin', '2018-03-26 13:48:58', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('409', '1', '管理员', 'admin', '2018-03-26 13:49:00', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('410', '1', '管理员', 'admin', '2018-03-26 16:12:48', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('411', '1', '管理员', 'admin', '2018-03-26 16:13:11', '登出', '/admin/sys/logout');
INSERT INTO `sys_log` VALUES ('412', '1', '管理员', 'admin', '2018-03-26 16:14:41', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('413', '1', '管理员', 'admin', '2018-03-26 16:47:02', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('414', '1', '管理员', 'admin', '2018-03-26 16:48:31', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('415', '1', '管理员', 'admin', '2018-03-26 16:48:43', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('416', '1', '管理员', 'admin', '2018-03-26 16:48:54', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('417', '1', '管理员', 'admin', '2018-03-26 16:49:05', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('418', '1', '管理员', 'admin', '2018-03-26 16:49:42', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('419', '1', '管理员', 'admin', '2018-03-26 16:49:52', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('420', '1', '管理员', 'admin', '2018-03-26 16:50:03', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('421', '1', '管理员', 'admin', '2018-03-26 16:50:48', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('422', '1', '管理员', 'admin', '2018-03-26 16:50:58', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('423', '1', '管理员', 'admin', '2018-03-26 16:55:38', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('424', '1', '管理员', 'admin', '2018-03-26 16:55:43', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('425', '1', '管理员', 'admin', '2018-03-26 16:55:49', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('426', '1', '管理员', 'admin', '2018-03-26 16:56:57', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('427', '1', '管理员', 'admin', '2018-03-26 17:23:11', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('428', '1', '管理员', 'admin', '2018-03-26 17:42:00', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('429', '1', '管理员', 'admin', '2018-03-27 13:44:34', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('430', '1', '管理员', 'admin', '2018-03-27 13:44:38', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('431', '1', '管理员', 'admin', '2018-03-27 17:04:52', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('432', '1', '管理员', 'admin', '2018-03-27 17:04:57', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('433', '1', '管理员', 'admin', '2018-03-27 17:05:58', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('434', '1', '管理员', 'admin', '2018-03-27 17:08:02', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('435', '1', '管理员', 'admin', '2018-03-27 17:08:08', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('436', '1', '管理员', 'admin', '2018-03-27 17:08:15', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('437', '1', '管理员', 'admin', '2018-03-28 10:02:29', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('438', '1', '管理员', 'admin', '2018-03-28 10:02:34', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('439', '1', '管理员', 'admin', '2018-03-28 10:02:57', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('440', '1', '管理员', 'admin', '2018-03-28 11:38:27', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('441', '1', '管理员', 'admin', '2018-03-28 11:38:39', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('442', '1', '管理员', 'admin', '2018-03-28 11:43:43', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('443', '1', '管理员', 'admin', '2018-03-28 11:43:56', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('444', '1', '管理员', 'admin', '2018-03-28 11:47:58', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('445', '1', '管理员', 'admin', '2018-03-28 11:51:10', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('446', '1', '管理员', 'admin', '2018-03-28 11:51:18', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('447', '1', '管理员', 'admin', '2018-03-28 11:55:47', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('448', '1', '管理员', 'admin', '2018-03-28 11:55:52', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('449', '1', '管理员', 'admin', '2018-03-28 11:55:58', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('450', '1', '管理员', 'admin', '2018-03-28 11:59:25', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('451', '1', '管理员', 'admin', '2018-03-28 11:59:29', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('452', '1', '管理员', 'admin', '2018-03-28 11:59:36', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('453', '1', '管理员', 'admin', '2018-03-28 12:01:06', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('454', '1', '管理员', 'admin', '2018-03-28 12:01:14', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('455', '1', '管理员', 'admin', '2018-03-28 12:01:18', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('456', '1', '管理员', 'admin', '2018-03-28 12:01:24', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('457', '1', '管理员', 'admin', '2018-03-28 12:02:35', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('458', '1', '管理员', 'admin', '2018-03-28 12:03:37', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('459', '1', '管理员', 'admin', '2018-03-28 12:03:41', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('460', '1', '管理员', 'admin', '2018-03-28 12:03:46', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('461', '1', '管理员', 'admin', '2018-03-28 12:05:37', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('462', '1', '管理员', 'admin', '2018-03-28 12:05:42', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('463', '1', '管理员', 'admin', '2018-03-28 12:05:47', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('464', '1', '管理员', 'admin', '2018-03-28 12:59:55', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('465', '1', '管理员', 'admin', '2018-03-28 13:00:03', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('466', '1', '管理员', 'admin', '2018-03-28 13:00:36', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('467', '1', '管理员', 'admin', '2018-03-28 13:00:44', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('468', '1', '管理员', 'admin', '2018-03-28 13:02:56', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('469', '1', '管理员', 'admin', '2018-03-28 13:03:01', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('470', '1', '管理员', 'admin', '2018-03-28 13:03:06', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('471', '1', '管理员', 'admin', '2018-03-28 13:03:44', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('472', '1', '管理员', 'admin', '2018-03-28 13:03:49', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('473', '1', '管理员', 'admin', '2018-03-28 13:04:45', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('474', '1', '管理员', 'admin', '2018-03-28 13:04:49', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('475', '1', '管理员', 'admin', '2018-03-28 13:07:12', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('476', '1', '管理员', 'admin', '2018-03-28 13:07:21', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('477', '1', '管理员', 'admin', '2018-03-28 13:07:36', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('478', '1', '管理员', 'admin', '2018-03-28 13:07:52', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('479', '1', '管理员', 'admin', '2018-03-28 13:09:55', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('480', '1', '管理员', 'admin', '2018-03-28 13:26:22', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('481', '1', '管理员', 'admin', '2018-03-28 13:26:27', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('482', '1', '管理员', 'admin', '2018-03-28 13:26:33', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('483', '1', '管理员', 'admin', '2018-03-28 13:31:35', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('484', '1', '管理员', 'admin', '2018-03-28 13:31:39', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('485', '1', '管理员', 'admin', '2018-03-28 13:31:44', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('486', '1', '管理员', 'admin', '2018-03-28 13:33:15', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('487', '1', '管理员', 'admin', '2018-03-28 13:33:19', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('488', '1', '管理员', 'admin', '2018-03-28 13:33:24', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('489', '1', '管理员', 'admin', '2018-03-28 13:37:22', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('490', '1', '管理员', 'admin', '2018-03-28 13:37:31', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('491', '1', '管理员', 'admin', '2018-03-28 13:39:27', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('492', '1', '管理员', 'admin', '2018-03-28 13:39:36', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('493', '1', '管理员', 'admin', '2018-03-28 13:43:28', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('494', '1', '管理员', 'admin', '2018-03-28 13:43:42', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('495', '1', '管理员', 'admin', '2018-03-28 13:45:20', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('496', '1', '管理员', 'admin', '2018-03-28 13:45:24', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('497', '1', '管理员', 'admin', '2018-03-28 13:45:29', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('498', '1', '管理员', 'admin', '2018-03-28 13:49:23', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('499', '1', '管理员', 'admin', '2018-03-28 13:58:06', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('500', '1', '管理员', 'admin', '2018-03-28 13:58:09', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('501', '1', '管理员', 'admin', '2018-03-28 13:58:14', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('502', '1', '管理员', 'admin', '2018-03-28 14:03:14', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('503', '1', '管理员', 'admin', '2018-03-28 14:03:23', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('504', '1', '管理员', 'admin', '2018-03-28 14:07:13', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('505', '1', '管理员', 'admin', '2018-03-28 14:07:17', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('506', '1', '管理员', 'admin', '2018-03-28 14:07:22', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('507', '1', '管理员', 'admin', '2018-03-28 14:23:45', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('508', '1', '管理员', 'admin', '2018-03-28 14:23:52', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('509', '1', '管理员', 'admin', '2018-03-28 14:23:57', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('510', '1', '管理员', 'admin', '2018-03-28 14:25:38', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('511', '1', '管理员', 'admin', '2018-03-28 14:25:43', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('512', '1', '管理员', 'admin', '2018-03-28 14:25:48', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('513', '1', '管理员', 'admin', '2018-03-28 14:42:32', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('514', '1', '管理员', 'admin', '2018-03-28 15:30:31', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('515', '1', '管理员', 'admin', '2018-03-28 15:32:15', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('516', '1', '管理员', 'admin', '2018-03-28 15:33:30', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('517', '1', '管理员', 'admin', '2018-03-28 15:34:49', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('518', '1', '管理员', 'admin', '2018-03-28 15:35:20', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('519', '1', '管理员', 'admin', '2018-03-28 15:36:28', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('520', '1', '管理员', 'admin', '2018-03-28 15:36:34', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('521', '1', '管理员', 'admin', '2018-03-28 15:36:55', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('522', '1', '管理员', 'admin', '2018-03-28 15:38:02', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('523', '1', '管理员', 'admin', '2018-03-28 15:46:51', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('524', '1', '管理员', 'admin', '2018-03-28 15:46:56', '业务数据删除', '/admin/score/delData');
INSERT INTO `sys_log` VALUES ('525', '1', '管理员', 'admin', '2018-03-28 15:49:40', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('526', '1', '管理员', 'admin', '2018-03-28 15:52:05', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('527', '1', '管理员', 'admin', '2018-03-28 15:55:13', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('528', '1', '管理员', 'admin', '2018-03-28 15:55:43', '项目导入', '/admin/biz/project/projectImport');
INSERT INTO `sys_log` VALUES ('529', '1', '管理员', 'admin', '2018-03-28 15:58:03', '导入业务数据', '/admin/score/import');
INSERT INTO `sys_log` VALUES ('530', '1', '管理员', 'admin', '2018-03-28 16:21:02', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('531', '1', '管理员', 'admin', '2018-03-28 16:21:10', '用户导入', '/admin/sys/user/userImport');
INSERT INTO `sys_log` VALUES ('532', '1', '管理员', 'admin', '2018-03-28 16:22:06', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('533', '1', '管理员', 'admin', '2018-03-28 16:22:13', '用户导入', '/admin/sys/user/userImport');
INSERT INTO `sys_log` VALUES ('534', '1', '管理员', 'admin', '2018-03-28 16:22:43', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('535', '1', '管理员', 'admin', '2018-03-28 16:22:50', '用户导入', '/admin/sys/user/userImport');
INSERT INTO `sys_log` VALUES ('536', '1', '管理员', 'admin', '2018-03-28 16:26:37', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('537', '1', '管理员', 'admin', '2018-03-28 16:27:13', '用户导入', '/admin/sys/user/userImport');
INSERT INTO `sys_log` VALUES ('538', '1', '管理员', 'admin', '2018-03-28 16:28:40', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('539', '1', '管理员', 'admin', '2018-03-28 16:28:48', '用户导入', '/admin/sys/user/userImport');
INSERT INTO `sys_log` VALUES ('540', '1', '管理员', 'admin', '2018-03-28 16:30:57', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('541', '1', '管理员', 'admin', '2018-03-28 16:33:57', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('542', '1', '管理员', 'admin', '2018-03-28 16:34:38', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('543', '1', '管理员', 'admin', '2018-03-28 16:34:59', '登出', '/admin/sys/logout');
INSERT INTO `sys_log` VALUES ('544', '7', '大勺哥', 'sa', '2018-03-28 16:35:03', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('545', '1', '管理员', 'admin', '2018-03-28 16:35:23', '登出', '/admin/sys/logout');
INSERT INTO `sys_log` VALUES ('546', '1', '管理员', 'admin', '2018-03-28 16:35:25', '登录', '/admin/sys/login');
INSERT INTO `sys_log` VALUES ('547', '1', '管理员', 'admin', '2018-03-28 16:36:10', '登录', '/admin/sys/login');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `id` varchar(50) DEFAULT NULL COMMENT 'ztree 树的id',
  `p_id` varchar(50) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `perms` varchar(50) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `iview_icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('412', '211c085165a74b5f9e578c7849bca3e0', '103', '用户管理', null, null, 'user', '1', 'ios-list-outline', '10');
INSERT INTO `sys_menu` VALUES ('485', '103', '0', '系统管理', null, null, 'directory3', '0', 'ios-folder', '9');
INSERT INTO `sys_menu` VALUES ('486', '75d938d1ffcd4aa085b2c8dd8a6cc418', '103', '岗位管理', null, null, 'position', '1', 'ios-list-outline', '11');
INSERT INTO `sys_menu` VALUES ('487', '4bea8873ba5f4a62bb85003071c92696', '103', '机构管理', null, null, 'dept', '1', 'ios-list-outline', '13');
INSERT INTO `sys_menu` VALUES ('489', '9532e6bff4b342acb1fac5803646df5f', '103', '角色管理', null, null, 'role', '1', 'ios-list-outline', '12');
INSERT INTO `sys_menu` VALUES ('490', '1e6c03336236410ebc5392d77a719843', '103', '菜单管理', null, null, 'menu', '1', 'ios-list-outline', '15');
INSERT INTO `sys_menu` VALUES ('491', '31e8abdf49fa4ec4aaab76828e90a2e0', '103', '系统日志', null, null, 'sys_log', '1', 'ios-list-outline', '16');
INSERT INTO `sys_menu` VALUES ('492', '0c3a753fb62944198659376600ebc6f5', '9532e6bff4b342acb1fac5803646df5f', '删除', null, null, 'role_del', '2', null, null);
INSERT INTO `sys_menu` VALUES ('493', '778b62fd78884f0f9c101d434ee92493', '9532e6bff4b342acb1fac5803646df5f', '编辑', null, null, 'role_edit', '2', null, null);
INSERT INTO `sys_menu` VALUES ('494', 'b9ef2072fe654d19a758a1856e496c9e', '9532e6bff4b342acb1fac5803646df5f', '增加', null, null, 'role_add', '2', null, null);
INSERT INTO `sys_menu` VALUES ('495', 'b4cfa334f3144c3bb20120f869567867', '9532e6bff4b342acb1fac5803646df5f', '资源授权', null, null, 'role_resource_auth', '2', null, null);
INSERT INTO `sys_menu` VALUES ('499', 'e0e61a04f5194472a46d9d72e417f48c', '103', '导入模板下载', null, null, 'excel_download', '1', 'ios-list-outline', '14');
INSERT INTO `sys_menu` VALUES ('500', '104', '0', '业务数据管理', null, null, 'directory4', '0', 'ios-folder', '5');
INSERT INTO `sys_menu` VALUES ('501', '63f9b9c3e41b4e9caeb523bfa0bbddf8', '104', '业务数据管理', null, null, 'score_data', '1', 'ios-list-outline', '6');
INSERT INTO `sys_menu` VALUES ('502', '7bdc06e430e3406697c912df49a5eaa9', '104', '营销业务数据管理', null, null, 'market_score_data', '1', 'ios-list-outline', '8');
INSERT INTO `sys_menu` VALUES ('503', 'efc37e83eefe408cb5a043db85dc932f', '104', '基础业务数据管理', null, null, 'base_score_data', '1', 'ios-list-outline', '7');
INSERT INTO `sys_menu` VALUES ('504', '105', '0', '业务管理', null, null, 'directory5', '0', 'ios-folder', '1');
INSERT INTO `sys_menu` VALUES ('505', 'bcaa53277e6744b3be449c2484b7883c', '105', '项目管理', null, null, 'project', '1', 'ios-list-outline', '2');
INSERT INTO `sys_menu` VALUES ('506', '61ef4266303540e9940deca08ca9c8b7', '105', '规则管理', null, null, 'rule_manage', '1', 'ios-list-outline', '4');
INSERT INTO `sys_menu` VALUES ('507', '2291f7c8009844d78ba9419a2c9838b0', '105', '积分管理', null, null, 'project_type', '1', 'ios-list-outline', '3');

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL COMMENT '岗位名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='岗位表';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
INSERT INTO `sys_position` VALUES ('4', '理财经理', '', '2018-03-19 10:23:37');
INSERT INTO `sys_position` VALUES ('5', ' 客户经理', '', '2018-03-19 10:25:08');
INSERT INTO `sys_position` VALUES ('6', '信贷员', '', '2018-03-19 10:25:24');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('13', 'admin', '超级管理员', null);
INSERT INTO `sys_role` VALUES ('24', 'user', '用户', '2018-02-12 09:05:35');
INSERT INTO `sys_role` VALUES ('26', 'zhangwei', '张伟', '2018-03-19 10:41:40');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('71', '13', '485');
INSERT INTO `sys_role_menu` VALUES ('72', '13', '412');
INSERT INTO `sys_role_menu` VALUES ('73', '13', '486');
INSERT INTO `sys_role_menu` VALUES ('74', '13', '487');
INSERT INTO `sys_role_menu` VALUES ('75', '13', '489');
INSERT INTO `sys_role_menu` VALUES ('76', '13', '490');
INSERT INTO `sys_role_menu` VALUES ('77', '13', '491');
INSERT INTO `sys_role_menu` VALUES ('78', '13', '499');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_no` varchar(100) DEFAULT NULL COMMENT '工号',
  `username` varchar(50) NOT NULL COMMENT '用户登录名(用于登录的）',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '用户真实姓名',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别 0=男/1=女/2=保密 /3=未知',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像缩略图地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `work_phone` varchar(20) DEFAULT NULL COMMENT '工作电话、座机',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `dept_id_index` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '321312', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '0', '623454173@qq.com', '18102509477', null, null, null, '1', '2018-01-16 14:47:05', '72', null, '2018-03-13');
INSERT INTO `sys_user` VALUES ('7', 'twetwe', 'sa', 'e10adc3949ba59abbe56e057f20f883e', '大勺哥', '1', 'asd@qq.com', '13456465465', null, null, null, '1', '2018-01-16 14:47:07', '74', null, '2018-03-19');
INSERT INTO `sys_user` VALUES ('8', '31235f', 'joey', 'e10adc3949ba59abbe56e057f20f883e', '德玛西亚', '1', '2434387555@qq.com', '13647910412', null, '192.168.1.88', 'upload/adminAvatar/201707/1499675749475head.jpg', '1', '2018-01-16 14:47:10', null, null, null);
INSERT INTO `sys_user` VALUES ('40', '827356565', 'zhaoli', 'e10adc3949ba59abbe56e057f20f883e', '赵丽', '1', '12312@qq.com', null, null, null, null, '1', '2018-03-19 16:14:38', null, null, '1990-02-15');
INSERT INTO `sys_user` VALUES ('41', '123456789', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '1', '12313@qq.com', null, null, null, null, '1', '2018-03-19 16:14:38', null, null, '1990-02-16');
INSERT INTO `sys_user` VALUES ('42', '987654321', 'zhangguo', 'e10adc3949ba59abbe56e057f20f883e', '张钰', '0', '12314@qq.com', null, null, null, null, '1', '2018-03-19 16:14:38', null, null, '1990-02-17');
INSERT INTO `sys_user` VALUES ('43', '666666661', 'chenhua', 'e10adc3949ba59abbe56e057f20f883e', '陈华', '2', '12315@qq.com', null, null, null, null, '1', '2018-03-19 16:14:38', null, null, '1990-02-18');
INSERT INTO `sys_user` VALUES ('49', '8888888888', 'liyi', 'e10adc3949ba59abbe56e057f20f883e', '李sss', '0', '222222222@qq.com', null, null, null, null, '1', '2018-03-20 17:25:40', null, null, '1990-02-15');
INSERT INTO `sys_user` VALUES ('50', '11111111111', 'lier', 'e10adc3949ba59abbe56e057f20f883e', '李二', '0', '222222222@qq.com', '15916812000', null, null, null, '1', '2018-03-20 17:30:30', null, null, '1990-02-15');

-- ----------------------------
-- Table structure for sys_user_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_position`;
CREATE TABLE `sys_user_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户岗位 中间表';

-- ----------------------------
-- Records of sys_user_position
-- ----------------------------
INSERT INTO `sys_user_position` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('72', '8', '13');
INSERT INTO `sys_user_role` VALUES ('75', '10', '24');
INSERT INTO `sys_user_role` VALUES ('80', '11', '25');
INSERT INTO `sys_user_role` VALUES ('81', '2', '13');
INSERT INTO `sys_user_role` VALUES ('82', '39', '24');
INSERT INTO `sys_user_role` VALUES ('83', '47', '26');
INSERT INTO `sys_user_role` VALUES ('86', '7', '13');
INSERT INTO `sys_user_role` VALUES ('87', '1', '13');
