/*
Navicat MySQL Data Transfer

Source Server         : wh
Source Server Version : 50627
Source Host           : 101.200.91.143:3306
Source Database       : zk

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-08-09 18:15:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` varchar(32) NOT NULL,
  `user_code` varchar(32) DEFAULT '' COMMENT '用户编号',
  `user_account` varchar(32) DEFAULT NULL COMMENT '用户账号',
  `phone` varchar(16) DEFAULT '' COMMENT '登陆名',
  `user_name` varchar(32) DEFAULT '' COMMENT '昵称',
  `true_name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT '' COMMENT '密码',
  `pay_password` varchar(32) DEFAULT '' COMMENT '支付密码',
  `email` varchar(32) DEFAULT '' COMMENT '邮箱地址',
  `address` varchar(128) DEFAULT '' COMMENT '家庭住址',
  `sex` varchar(16) DEFAULT '' COMMENT '性别(449700160001男449700160002女)',
  `channel` varchar(16) DEFAULT '1' COMMENT '渠道：1：平台注册，2：第三方平台，3：虚拟',
  `type` varchar(8) DEFAULT '' COMMENT '用户类型',
  `status` varchar(16) DEFAULT '' COMMENT '状态',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `head_pic` varchar(256) DEFAULT '' COMMENT '用户头像',
  `delete_flag` varchar(8) DEFAULT '0' COMMENT '删除',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS=1;
