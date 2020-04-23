CREATE DATABASE IF NOT EXISTS `test`;
USE `test`;

CREATE TABLE IF NOT EXISTS `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_type` varchar(255) NOT NULL DEFAULT '' COMMENT '产品类型',
  `left_num` varchar(255) NOT NULL DEFAULT '' COMMENT '剩余产品数',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';
