DROP TABLE IF EXISTS `sys_dept` ;

CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `dept_name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示排序',
  `leader` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门负责人',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门练习电话',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '部门状态 0正常 1停用',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除状态 0正常 1删除',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';

DROP TABLE IF EXISTS `sys_menu` ;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '是否刷新（0刷新 1不刷新）',
  `perms` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单权限表';

DROP TABLE IF EXISTS `sys_role` ;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(63) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标记 0未删除 1删除',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色信息表';

DROP TABLE IF EXISTS `sys_role_menu` ;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色和菜单关联表';

DROP TABLE IF EXISTS `sys_user` ;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆账号',
  `user_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户昵称',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `phonenumber` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '用户手机',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户邮箱',
  `sex` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '用户性别 0男 1女 2未知',
  `avatar` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '头像路径',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `isAdmin` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '超级管理员 1是超级管理员 ',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '账号状态 0启动 1停用',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除状态 0未删除 1已删除',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';

DROP TABLE IF EXISTS `sys_user_role` ;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

TRUNCATE TABLE `sys_dept` ;

INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('200','0','总部','0','老板','13700000000','0','0','admin','2023-01-04 11:22:45','admin','2023-01-04 11:22:45');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('201','200','人事部','1','老板','13700000001','0','0','admin','2023-01-04 11:24:22','admin','2023-01-04 11:24:22');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('202','200','IT部','2','老板','13700000001','0','0','admin','2023-01-04 11:24:29','admin','2023-01-04 11:24:29');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('203','200','销售部','3','老板','13700000001','0','0','admin','2023-01-04 11:24:37','admin','2023-01-04 11:24:37');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('204','200','采购部','4','老板','13700000001','0','0','admin','2023-01-04 11:25:02','admin','2023-01-04 11:25:02');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `leader`, `phone`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES ('205','200','售后部','5','老板','13700000001','0','0','admin','2023-01-04 11:25:09','admin','2023-01-04 11:25:09');

TRUNCATE TABLE `sys_menu` ;


TRUNCATE TABLE `sys_role` ;

INSERT INTO `sys_role` (`role_id`, `role_name`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('100','管理员','0','admin','2023-01-04 12:02:39','admin','2023-01-04 12:02:39','管理员账号');
INSERT INTO `sys_role` (`role_id`, `role_name`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('101','普通员','0','admin','2023-01-04 12:02:41','admin','2023-01-04 12:02:41','普通用户');

TRUNCATE TABLE `sys_role_menu` ;


TRUNCATE TABLE `sys_user` ;

INSERT INTO `sys_user` (`user_id`, `login_name`, `user_name`, `password`, `phonenumber`, `email`, `sex`, `avatar`, `dept_id`, `isAdmin`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('100','admin','管理员','123456','13700000000','mail@163.com','0','','100','1','0','0','admin','2023-01-04 11:41:04','admin','2023-01-04 11:41:04','管理员账号');
INSERT INTO `sys_user` (`user_id`, `login_name`, `user_name`, `password`, `phonenumber`, `email`, `sex`, `avatar`, `dept_id`, `isAdmin`, `status`, `del_flag`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('101','guest','用户','123456','13700000000','mail@163.com','0','','100','','0','0','admin','2023-01-04 11:41:08','admin','2023-01-04 11:41:08','用户账号');

TRUNCATE TABLE `sys_user_role` ;

INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES ('100','100');
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES ('101','101');

