/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : db_performance_manage_system_dev

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-03-19 09:51:13
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=499 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('409', '101', '0', '业务配置管理', null, null, 'directory1', '0', 'ios-folder', null);
INSERT INTO `sys_menu` VALUES ('412', '211c085165a74b5f9e578c7849bca3e0', '103', '用户管理', null, null, 'user', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('481', '102', '0', '积分数据管理', null, null, 'directory2', '0', 'ios-folder', null);
INSERT INTO `sys_menu` VALUES ('482', '20d8e14488fb41258499b5ae9fb68942', '102', '业务数据管理', null, null, 'score_data', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('483', '4428446527c142e69574fa445b573429', '102', '基础业务数据管理', null, null, 'base_score_data', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('484', '68f419e626434477a0a49d6437645487', '102', '营销业务数据管理', null, null, 'market_score_data', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('485', '103', '0', '系统管理', null, null, 'directory3', '0', 'ios-folder', null);
INSERT INTO `sys_menu` VALUES ('486', '75d938d1ffcd4aa085b2c8dd8a6cc418', '103', '岗位管理', null, null, 'position', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('487', '4bea8873ba5f4a62bb85003071c92696', '103', '机构管理', null, null, 'dept', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('489', '9532e6bff4b342acb1fac5803646df5f', '103', '角色管理', null, null, 'role', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('490', '1e6c03336236410ebc5392d77a719843', '103', '菜单管理', null, null, 'menu', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('491', '31e8abdf49fa4ec4aaab76828e90a2e0', '103', '系统日志', null, null, 'sys_log', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('492', '94af4ada3f67438ca21c2d95e60a7873', '9532e6bff4b342acb1fac5803646df5f', '删除', null, null, 'role_del', '2', null, null);
INSERT INTO `sys_menu` VALUES ('493', 'c9d8215c32724e0eafc46b8fd564d750', '9532e6bff4b342acb1fac5803646df5f', '编辑', null, null, 'role_edit', '2', null, null);
INSERT INTO `sys_menu` VALUES ('494', '2deda4eca98248f2814346df46433aea', '9532e6bff4b342acb1fac5803646df5f', '增加', null, null, 'role_add', '2', null, null);
INSERT INTO `sys_menu` VALUES ('495', '899b374fa448463f8196b99c30889910', '9532e6bff4b342acb1fac5803646df5f', '资源授权', null, null, 'role_resource_auth', '2', null, null);
INSERT INTO `sys_menu` VALUES ('496', '9fa839c4592c449f95e687e41a8982f3', '101', '项目管理', null, null, 'project', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('497', 'a21d38498b294193a2d571aa897f7784', '101', '积分管理', null, null, 'project_type', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('498', 'e25bee99531a4701904c01db95c32535', '101', '规则管理', null, null, 'rule_manage', '1', 'ios-list-outline', null);
