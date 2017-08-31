/*
Navicat MySQL Data Transfer

Source Server         : 本地_3311
Source Server Version : 50091
Source Host           : 127.0.0.1:3311
Source Database       : app_benefit

Target Server Type    : MYSQL
Target Server Version : 50091
File Encoding         : 65001

Date: 2017-08-31 08:01:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_advice
-- ----------------------------
DROP TABLE IF EXISTS `app_advice`;
CREATE TABLE `app_advice` (
  `id` char(32) NOT NULL,
  `userid` char(32) NOT NULL,
  `content` text NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_advice
-- ----------------------------
INSERT INTO `app_advice` VALUES ('2b0f9501eb2d786447010000', '', '明天,你好', '1503991345964');

-- ----------------------------
-- Table structure for app_login_log
-- ----------------------------
DROP TABLE IF EXISTS `app_login_log`;
CREATE TABLE `app_login_log` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `ip` varchar(16) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_login_log
-- ----------------------------
INSERT INTO `app_login_log` VALUES ('4ee98001eb2d786447000000', '123', '192.168.1.16', '1503990025551');

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` char(24) NOT NULL,
  `mobile` char(11) NOT NULL COMMENT '手机号',
  `mail` char(48) NOT NULL default '' COMMENT '邮件',
  `pass` char(48) NOT NULL default '' COMMENT '密码',
  `wx_id` char(32) NOT NULL,
  `wb_id` char(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `head` varchar(255) NOT NULL COMMENT '头像图片url',
  `sex` int(11) NOT NULL default '0' COMMENT '性别 1是男 2是女',
  `createtime` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  KEY `mobile` USING BTREE (`mobile`),
  KEY `wx_id` (`wx_id`),
  KEY `wb_id` (`wb_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('123', '123', '123', '123', '', '', '', '', '0', '0');
INSERT INTO `app_user` VALUES ('124', '123', '123', '123', '', '', '', '', '0', '0');
INSERT INTO `app_user` VALUES ('77237803aeddbf4c1f000000', '18789061261', '', '111111', '', '', '', '', '0', '2016');
INSERT INTO `app_user` VALUES ('89ddfa01eb2d78b455000000', '13242423411', '', '111111', '', '', '', '', '0', '2016');
INSERT INTO `app_user` VALUES ('ac524003aeddbf588b000000', '18789081811', '', '111111', '', '', '', '', '0', '2016');
INSERT INTO `app_user` VALUES ('c7fba403aeddbff443000000', '18789061263', '', '111111', '', '', '', '', '0', '2016');
INSERT INTO `app_user` VALUES ('cf486c03aeddbf3012000000', '12345678', '', '111111', '', '', '', '', '0', '2016');
INSERT INTO `app_user` VALUES ('f912a103aeddbfc832000000', '18789061262', '', '111', '', '', '', '', '0', '2016');
INSERT INTO `app_user` VALUES ('35281604aeddbf8856000000', '', '', '', 'oIBoIs0LYi6j9pfrqvnc2-teo0AI', '', '飒飒西风', 'http://wx.qlogo.cn/mmopen/PdibpV1sFDHd7jGjouGwGr5yfNGzrfu47C63VVicRgxY19Cr5vddE1Sq41X0cgicLu1xJcxibGXqxORGGgR7a0ogYtyYgASZ7RTf/0', '1', '1503946960949');

-- ----------------------------
-- Table structure for bored_head
-- ----------------------------
DROP TABLE IF EXISTS `bored_head`;
CREATE TABLE `bored_head` (
  `id` char(32) NOT NULL,
  `useid` char(32) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `user_face` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `create_time` bigint(255) NOT NULL default '0' COMMENT '//创建时间全部使用 long类型  毫秒级',
  `rownum` int(11) NOT NULL,
  `invalid_time` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `invalid_time` (`invalid_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bored_head
-- ----------------------------
INSERT INTO `bored_head` VALUES ('d5fa6703aeddbf443b000000', '0', '', '', '测试一下', '1503935546071', '6', '0');
INSERT INTO `bored_head` VALUES ('7672a301eb2d786447030000', '', '', '', '好无聊呀好无聊', '1503992293174', '1', '1503936000000');
INSERT INTO `bored_head` VALUES ('71f4a501eb2d786447040000', '', '', '', '还是好无聊', '1503992457913', '0', '1503936000000');

-- ----------------------------
-- Table structure for bored_voice
-- ----------------------------
DROP TABLE IF EXISTS `bored_voice`;
CREATE TABLE `bored_voice` (
  `id` char(32) NOT NULL,
  `bored_id` char(32) NOT NULL,
  `useid` char(32) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `user_face` varchar(255) NOT NULL,
  `record_len` int(11) NOT NULL,
  `record_url` varchar(512) NOT NULL,
  `create_time` bigint(255) NOT NULL default '0' COMMENT '//创建时间全部使用 long类型  毫秒级',
  PRIMARY KEY  (`id`),
  KEY `bored_id` USING BTREE (`bored_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bored_voice
-- ----------------------------
INSERT INTO `bored_voice` VALUES ('3de5ad01eb2d78e8ae000000', '7672a301eb2d786447030000', '', '', '', '10', '12321', '1503992978889');
INSERT INTO `bored_voice` VALUES ('05eeaf01eb2d78e403000000', '7672a301eb2d786447030000', '', '', '', '10', '12321', '1503993110790');
INSERT INTO `bored_voice` VALUES ('608bb001eb2d78e403010000', '7672a301eb2d786447030000', '', '', '', '10', '12321', '1503993152456');
INSERT INTO `bored_voice` VALUES ('ee83b201eb2d786023000000', '7672a301eb2d786447030000', '', '', '', '10', '12321', '1503993280113');
INSERT INTO `bored_voice` VALUES ('b1c7b301eb2d786023010000', '7672a301eb2d786447030000', '', '', '', '10', '12321', '1503993363850');
INSERT INTO `bored_voice` VALUES ('1680b401eb2d78847a000000', '7672a301eb2d786447030000', '', '', '', '10', '12321', '1503993410531');

-- ----------------------------
-- Table structure for game_2048_rank
-- ----------------------------
DROP TABLE IF EXISTS `game_2048_rank`;
CREATE TABLE `game_2048_rank` (
  `id` char(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `four_max_score` int(11) NOT NULL,
  `six_max_score` int(11) NOT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_2048_rank
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
