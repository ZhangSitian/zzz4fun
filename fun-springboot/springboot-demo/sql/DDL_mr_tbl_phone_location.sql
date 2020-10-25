
DROP TABLE IF EXISTS `mr_tbl_phone_location`;
CREATE TABLE `mr_tbl_phone_location` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
	`mobile_prefix` VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号前缀',
	`province` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '省份',
	`city` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '城市',
	`operator` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '运营商类型',
	`area_code` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '区域码',
	`post_code` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '邮编',
	`gmt_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `uniq_mobile_prefix` (`mobile_prefix`) USING BTREE
)
COMMENT='手机号归属地表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `mr_tbl_phone_location`
(`mobile_prefix`, `province`, `city`, `operator`, `area_code`, `post_code`)
VALUES
('1300000', '山东', '济南', '联通', '0531', '250000'),
('1300001', '江苏', '常州', '联通', '0519', '213000'),
('1300002', '安徽', '巢湖', '联通', '0551', '238000'),
('1300003', '四川', '宜宾', '联通', '0831', '644000');