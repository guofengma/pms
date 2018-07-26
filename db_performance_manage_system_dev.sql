/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : db_performance_manage_system_dev

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-03-09 18:20:51
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='机构（部门）积分';

-- ----------------------------
-- Records of biz_dept_scores
-- ----------------------------
INSERT INTO `biz_dept_scores` VALUES ('1', '68', '1', '2018-02-01', '45', '10', '50', '40', '5', null);
INSERT INTO `biz_dept_scores` VALUES ('2', '69', '2', '2018-03-01', '30', '20', '60', '40', null, null);
INSERT INTO `biz_dept_scores` VALUES ('3', '68', '2', '2018-02-01', '10', '10', '10', null, null, null);
INSERT INTO `biz_dept_scores` VALUES ('4', '69', '1', '2018-02-01', '44', '20', '50', '30', null, null);

-- ----------------------------
-- Table structure for biz_position_project
-- ----------------------------
DROP TABLE IF EXISTS `biz_position_project`;
CREATE TABLE `biz_position_project` (
  `id` bigint(20) NOT NULL,
  `position_id` bigint(20) DEFAULT NULL COMMENT '岗位id',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `type` tinyint(4) DEFAULT NULL COMMENT '0 基础  1 联动',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位 基础项目 中间表';

-- ----------------------------
-- Records of biz_position_project
-- ----------------------------

-- ----------------------------
-- Table structure for biz_product
-- ----------------------------
DROP TABLE IF EXISTS `biz_product`;
CREATE TABLE `biz_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `product_name` varchar(50) DEFAULT NULL COMMENT '子项目（产品名）',
  `product_remark` varchar(200) DEFAULT NULL COMMENT '项目描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime DEFAULT NULL COMMENT '上次编辑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='项目下的产品';

-- ----------------------------
-- Records of biz_product
-- ----------------------------
INSERT INTO `biz_product` VALUES ('1', '1', '产品1', '这是备注', '2018-03-01 13:48:03', null);
INSERT INTO `biz_product` VALUES ('3', '1', '产品2', '啊哈哈或或', '2018-08-01 00:00:00', null);

-- ----------------------------
-- Table structure for biz_project
-- ----------------------------
DROP TABLE IF EXISTS `biz_project`;
CREATE TABLE `biz_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_type_id` bigint(20) DEFAULT NULL COMMENT '项目类型id',
  `project_num` varchar(50) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `product_name` varchar(50) DEFAULT NULL COMMENT '子项目（产品名）',
  `project_remark` varchar(200) DEFAULT NULL COMMENT '项目描述',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime DEFAULT NULL COMMENT '上次编辑时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_project_num` (`project_num`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='项目管理';

-- ----------------------------
-- Records of biz_project
-- ----------------------------
INSERT INTO `biz_project` VALUES ('1', '1', '2134123', '测试项目', null, '这是备注', '0', '2018-03-01 13:48:03', null);
INSERT INTO `biz_project` VALUES ('3', '1', null, '测试项目2', null, '啊哈哈或或', '0', '2018-08-01 00:00:00', null);

-- ----------------------------
-- Table structure for biz_project_type
-- ----------------------------
DROP TABLE IF EXISTS `biz_project_type`;
CREATE TABLE `biz_project_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='项目类型';

-- ----------------------------
-- Records of biz_project_type
-- ----------------------------
INSERT INTO `biz_project_type` VALUES ('1', '个金类');
INSERT INTO `biz_project_type` VALUES ('2', '公司类');
INSERT INTO `biz_project_type` VALUES ('3', '信贷类');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='项目类型每个月分的积分';

-- ----------------------------
-- Records of biz_project_type_scores
-- ----------------------------
INSERT INTO `biz_project_type_scores` VALUES ('1', '1', '2018-02-01', '50', '80', '100', '20', '50', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='总积分';

-- ----------------------------
-- Records of biz_total_scores
-- ----------------------------
INSERT INTO `biz_total_scores` VALUES ('1', '2018-02-01', '20', '500', '600', '100', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of biz_upload_info
-- ----------------------------
INSERT INTO `biz_upload_info` VALUES ('1', 'db.sql', '1d5b36fa-a98f-4a34-9445-4e473a745a0f.sql', '6246', 'sql', 'D:/pms/user/1d5b36fa-a98f-4a34-9445-4e473a745a0f.sql', '1', '2018-03-08 11:11:43', 'user');
INSERT INTO `biz_upload_info` VALUES ('2', '12.txt', '89800837-5b62-41cd-b7e3-d1f4e0e0f0a2.txt', '104', 'txt', 'D:/pms/user/89800837-5b62-41cd-b7e3-d1f4e0e0f0a2.txt', '1', '2018-03-08 14:55:07', 'user');
INSERT INTO `biz_upload_info` VALUES ('3', 'import_user.xlsx', '0f972738-8715-4715-ab2f-616ba06fbbf3.xlsx', '10462', 'xlsx', 'D:/pms/user/0f972738-8715-4715-ab2f-616ba06fbbf3.xlsx', '1', '2018-03-08 14:59:03', 'user');

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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', null, '机构节点', '0', null, null, null, null, null, null, '2018-02-11 10:54:52', null);
INSERT INTO `sys_dept` VALUES ('68', null, '网通区', '1', null, null, null, null, null, null, '2018-03-01 11:28:05', null);
INSERT INTO `sys_dept` VALUES ('69', null, '弗雷尔卓德', '68', null, null, null, null, null, null, '2018-03-01 11:28:28', null);
INSERT INTO `sys_dept` VALUES ('70', null, '扭曲丛林', '68', null, null, null, null, null, null, '2018-03-01 11:28:40', null);

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
  `perms` varchar(20) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `iview_icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=451 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('409', '101', '0', '测试目录', null, null, 'directory1', '0', 'ios-folder', null);
INSERT INTO `sys_menu` VALUES ('410', '0905c7b221e04782b19f3090c162cf71', '101', '角色管理', null, null, 'role', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('411', 'cfe4a166ed044c3ab554c48547c2d6b0', '101', '菜单管理', null, null, 'menu', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('412', '211c085165a74b5f9e578c7849bca3e0', '101', '用户管理', null, null, 'user', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('413', 'd7c2d30f6f4a49d2bde5da7ea9680e55', '0905c7b221e04782b19f3090c162cf71', '编辑', null, null, 'role_edit', '2', null, null);
INSERT INTO `sys_menu` VALUES ('415', '412ea70deba840a29ed6315ecda548a9', '0905c7b221e04782b19f3090c162cf71', '增加', null, null, 'role_add', '2', null, null);
INSERT INTO `sys_menu` VALUES ('416', '95ede4e1ae9d4a1ca910122adffea415', '0905c7b221e04782b19f3090c162cf71', '资源授权', null, null, 'role_resource_auth', '2', null, null);
INSERT INTO `sys_menu` VALUES ('417', '9d851ec320194622b81133353648bb8e', '0905c7b221e04782b19f3090c162cf71', '删除', null, null, 'role_del', '2', null, null);
INSERT INTO `sys_menu` VALUES ('446', 'ebee02555f394553835e183e80385d1c', '101', '部门管理', null, null, 'dept', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('447', '0c7b94b0a4314514b5c7331c27a806c5', '101', '规则管理', null, null, 'rule_manage', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('448', '49a99e76bbd84603a6720fb59446d603', '101', '积分管理', null, null, 'project_type', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('449', '64963a997b004af197526c6318bb868b', '101', '项目管理', null, null, 'project', '1', 'ios-list-outline', null);
INSERT INTO `sys_menu` VALUES ('450', 'f7708e3f21374bc18352040f87dd74a8', '101', '岗位管理', null, null, 'position', '1', 'ios-list-outline', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='岗位表';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
INSERT INTO `sys_position` VALUES ('1', '经理', null, '2018-02-27 10:02:04');
INSERT INTO `sys_position` VALUES ('2', '店小二', null, '2018-03-06 11:34:19');
INSERT INTO `sys_position` VALUES ('3', '老板娘', null, '2018-03-05 11:34:37');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('13', 'admin', 'admina', null);
INSERT INTO `sys_role` VALUES ('24', 'user', 'as', '2018-02-12 09:05:35');
INSERT INTO `sys_role` VALUES ('25', 'a', 'gas多个', '2018-02-12 09:05:43');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('45', '24', '409');
INSERT INTO `sys_role_menu` VALUES ('46', '24', '410');
INSERT INTO `sys_role_menu` VALUES ('47', '24', '417');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', '0', '2434387555@qq.com', '13647910242', null, '192.168.1.88', 'upload/adminAvatar/201707/1499675749475head.jpg', '1', '2018-01-16 14:47:05', null, null, null);
INSERT INTO `sys_user` VALUES ('7', 'sa', 'e10adc3949ba59abbe56e057f20f883e', '张三', '1', 'asd@qq.com', '13456465465', null, '192.168.1.88', 'upload/adminAvatar/201707/1499675749475head.jpg', '1', '2018-01-16 14:47:07', null, null, null);
INSERT INTO `sys_user` VALUES ('8', 'joey', 'e10adc3949ba59abbe56e057f20f883e', '德玛西亚', '1', '2434387555@qq.com', '13647910412', null, '192.168.1.88', 'upload/adminAvatar/201707/1499675749475head.jpg', '0', '2018-01-16 14:47:10', null, null, null);
INSERT INTO `sys_user` VALUES ('20', 'zhaoxin', 'e10adc3949ba59abbe56e057f20f883e', '赵信', '2', '12312@qq.com', '3124124124', null, null, null, '2', '2018-03-01 16:33:02', null, '53464567547', '2018-02-15');

-- ----------------------------
-- Table structure for sys_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_log`;
CREATE TABLE `sys_user_login_log` (
  `log_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '登录日志ID',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `operating_system` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Records of sys_user_login_log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('62', '1', '13');
INSERT INTO `sys_user_role` VALUES ('72', '8', '13');
INSERT INTO `sys_user_role` VALUES ('73', '7', '24');
INSERT INTO `sys_user_role` VALUES ('75', '10', '24');
INSERT INTO `sys_user_role` VALUES ('80', '11', '25');
