CREATE DATABASE IF NOT EXISTS `test`;
USE `test`;

/*Table structure for table `items` */

CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '商品名称',
  `price` float(10,1) NOT NULL COMMENT '商品定价',
  `detail` text COMMENT '商品描述',
  `pic` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `create_time` datetime NOT NULL COMMENT '生产日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `orderdetail` */

CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_id` int(11) NOT NULL COMMENT '订单id',
  `items_id` int(11) NOT NULL COMMENT '商品id',
  `items_num` int(11) DEFAULT NULL COMMENT '商品购买数量',
  PRIMARY KEY (`id`),
  INDEX `idx_order_detail_1` (`orders_id`),
  INDEX `idx_order_detail_2` (`items_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `orders` */

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '下单用户id',
  `number` varchar(32) NOT NULL COMMENT '订单号',
  `create_time` datetime NOT NULL COMMENT '创建订单时间',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  INDEX `idx_orders_1` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/*Data for the table `items` */

insert  into `items`(`id`,`name`,`price`,`detail`,`pic`,`create_time`) values (1,'台式机',3000.0,'该电脑质量非常好！！！！',NULL,'2015-02-03 13:22:53'),(2,'笔记本',6000.0,'笔记本性能好，质量好！！！！！',NULL,'2015-02-09 13:22:57'),(3,'背包',200.0,'名牌背包，容量大质量好！！！！',NULL,'2015-02-06 13:23:02');

/*Data for the table `orderdetail` */

insert  into `order_detail`(`id`,`orders_id`,`items_id`,`items_num`) values (1,3,1,1),(2,3,2,3),(3,4,3,4),(4,4,2,3);

/*Data for the table `orders` */

insert  into `orders`(`id`,`user_id`,`number`,`create_time`,`note`) values (3,1,'1000010','2015-02-04 13:22:35',NULL),(4,1,'1000011','2015-02-03 13:22:41',NULL),(5,10,'1000012','2015-02-12 16:13:23',NULL);

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`birthday`,`sex`,`address`) values (1,'王五',NULL,'2',NULL),(10,'张三','2014-07-10','1','北京市'),(16,'张小明',NULL,'1','河南郑州'),(22,'陈小明',NULL,'1','河南郑州'),(24,'张三丰',NULL,'1','河南郑州'),(25,'陈小明',NULL,'1','河南郑州'),(26,'王五',NULL,NULL,NULL);
