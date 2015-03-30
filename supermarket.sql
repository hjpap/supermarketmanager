/*
SQLyog Ultimate v8.71 
MySQL - 5.5.19 : Database - supermarket
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`supermarket` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `supermarket`;

/*Table structure for table `cashier` */

DROP TABLE IF EXISTS `cashier`;

CREATE TABLE `cashier` (
  `code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `retail` double(10,2) DEFAULT '0.00',
  `zhekou` double DEFAULT '1',
  `count` int(11) DEFAULT NULL,
  `total` double(10,2) DEFAULT '0.00',
  `sjcount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cashier` */

/*Table structure for table `cashier2` */

DROP TABLE IF EXISTS `cashier2`;

CREATE TABLE `cashier2` (
  `code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `retail` double(10,2) DEFAULT '0.00',
  `zhekou` double DEFAULT '1',
  `count` int(11) DEFAULT NULL,
  `total` double(10,2) DEFAULT '0.00',
  `sjcount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cashier2` */

/*Table structure for table `gonghuoshang` */

DROP TABLE IF EXISTS `gonghuoshang`;

CREATE TABLE `gonghuoshang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `people` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `gonghuoshang` */

insert  into `gonghuoshang`(`id`,`name`,`tel`,`address`,`people`) values (1,'哇哈哈有限公司','84637895','长春净月','李涛'),(2,'百事食品有限公司','13576897896','长春净月','曹勇军'),(3,'普通供应商','-','-','-');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `sp_code` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sp_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sp_unit` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '单位',
  `sp_kind` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '种类',
  `sp_cost` double(10,2) DEFAULT '0.00' COMMENT '成本价',
  `sp_retail` double(10,2) DEFAULT '0.00' COMMENT '零售价',
  `sp_kucun` int(20) DEFAULT '0' COMMENT '库存',
  `sp_dangerous` int(20) DEFAULT '0' COMMENT '报警数',
  `sp_gonghuoshang` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sp_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `goods` */

insert  into `goods`(`sp_code`,`sp_name`,`sp_unit`,`sp_kind`,`sp_cost`,`sp_retail`,`sp_kucun`,`sp_dangerous`,`sp_gonghuoshang`) values ('001','哇哈哈','瓶','饮料',0.70,1.50,51,30,'哇哈哈有限公司'),('002','乐事','袋','食品',2.00,3.00,100,20,'百事食品有限公司'),('003','黑人牙膏','支','保洁用品',4.00,12.00,25,20,'普通供应商'),('004','印象卫生纸','袋','日用品',2.00,4.00,50,20,'普通供应商'),('005','上好佳薯片','袋','食品',1.50,2.00,20,20,'普通供应商'),('006','八宝粥','瓶','食品',2.00,3.50,20,20,'普通供应商');

/*Table structure for table `goods_kind` */

DROP TABLE IF EXISTS `goods_kind`;

CREATE TABLE `goods_kind` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `kind` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`kind`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `goods_kind` */

insert  into `goods_kind`(`id`,`kind`) values (5,'日用品'),(6,'保洁用品'),(7,'食品'),(8,'饮料');

/*Table structure for table `goods_unit` */

DROP TABLE IF EXISTS `goods_unit`;

CREATE TABLE `goods_unit` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `unit` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`unit`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `goods_unit` */

insert  into `goods_unit`(`id`,`unit`) values (1,'瓶'),(2,'袋'),(3,'盒'),(5,'个'),(7,'箱'),(10,'只'),(11,'支');

/*Table structure for table `grounding` */

DROP TABLE IF EXISTS `grounding`;

CREATE TABLE `grounding` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `unit` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '单位',
  `kind` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '种类',
  `cost` double(10,2) DEFAULT NULL COMMENT '成本价',
  `retail` double(10,2) DEFAULT NULL COMMENT '零售价',
  `sjcount` int(100) DEFAULT NULL COMMENT '上架数量',
  `dangers` int(10) DEFAULT NULL COMMENT '报警数',
  `date` date DEFAULT NULL COMMENT '上架时间',
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `grounding` */

insert  into `grounding`(`id`,`code`,`name`,`unit`,`kind`,`cost`,`retail`,`sjcount`,`dangers`,`date`) values (7,'001','哇哈哈','瓶','饮料',0.70,1.50,50,20,'2012-03-02'),(8,'002','乐事','袋','食品',2.00,3.00,97,20,'2012-03-02'),(9,'003','黑人牙膏','支','保洁用品',4.00,12.00,49,10,'2012-03-02'),(10,'004','印象卫生纸','袋','日用品',2.00,4.00,49,20,'2012-03-02'),(11,'005','上好佳薯片','袋','食品',1.50,2.00,10,10,'2012-03-02');

/*Table structure for table `kucun` */

DROP TABLE IF EXISTS `kucun`;

CREATE TABLE `kucun` (
  `billcode` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `unit` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '单位',
  `kind` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gonghuoshang` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cost` double(10,2) DEFAULT '0.00' COMMENT '成本价',
  `retail` double(10,2) DEFAULT '0.00' COMMENT '零售价',
  `prodate` date DEFAULT NULL,
  `baddate` date DEFAULT NULL,
  `date` date DEFAULT NULL,
  `dangerous` int(100) DEFAULT NULL,
  `jhcount` int(100) DEFAULT NULL,
  `kcount` int(100) DEFAULT NULL,
  `state` varchar(200) COLLATE utf8_unicode_ci DEFAULT '正常' COMMENT '状态',
  `people` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `kucun` */

insert  into `kucun`(`billcode`,`code`,`name`,`unit`,`kind`,`gonghuoshang`,`cost`,`retail`,`prodate`,`baddate`,`date`,`dangerous`,`jhcount`,`kcount`,`state`,`people`) values ('9148412-03-02-43','001','哇哈哈','瓶','饮料','哇哈哈有限公司',0.70,1.50,'2012-02-01','2012-04-28','2012-03-02',30,100,50,'正常','2'),('9148412-03-02-43','002','乐事','袋','食品','百事食品有限公司',2.00,3.00,'2012-02-01','2012-12-28','2012-03-02',20,200,100,'正常','2'),('9148412-03-02-43','003','黑人牙膏','支','保洁用品','普通供应商',4.00,12.00,'2012-02-01','2012-12-28','2012-03-02',20,50,0,'正常','2'),('9148412-03-02-43','004','印象卫生纸','袋','日用品','普通供应商',2.00,4.00,'2012-02-01','2012-12-28','2012-03-02',20,100,50,'正常','2'),('9148412-03-02-43','005','上好佳薯片','袋','食品','普通供应商',1.50,2.00,'2012-02-01','2012-03-10','2012-03-02',20,40,20,'正常','2'),('9148412-03-02-43','006','八宝粥','瓶','食品','普通供应商',2.00,3.50,'2012-02-01','2012-04-05','2012-03-02',20,20,20,'正常','2'),('747612-03-03-44','001','哇哈哈','瓶','饮料','哇哈哈有限公司',0.70,1.50,'2012-03-01','2012-12-03','2012-03-03',30,1,1,'正常','2'),('1088412-03-03-17','005','上好佳薯片','袋','食品','普通供应商',1.50,2.00,'2012-03-03','2012-12-12','2012-03-03',20,1,0,'正常','2'),('1088412-03-03-17','003','黑人牙膏','支','保洁用品','普通供应商',4.00,12.00,'2012-03-03','2012-03-03','2012-03-03',20,25,25,'正常','2');

/*Table structure for table `returns` */

DROP TABLE IF EXISTS `returns`;

CREATE TABLE `returns` (
  `billcode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `retail` double(10,2) DEFAULT NULL,
  `zhekou` double(5,2) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `returns` */

/*Table structure for table `returns_bill` */

DROP TABLE IF EXISTS `returns_bill`;

CREATE TABLE `returns_bill` (
  `billcode` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `cost` double(10,2) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `agent` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`billcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `returns_bill` */

insert  into `returns_bill`(`billcode`,`cost`,`total`,`agent`,`date`) values ('5346812-02-20-53',3.50,3.50,'1','2012-02-20');

/*Table structure for table `returns_mingxi` */

DROP TABLE IF EXISTS `returns_mingxi`;

CREATE TABLE `returns_mingxi` (
  `billcode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `retail` double(10,2) DEFAULT NULL,
  `zhekou` double(5,2) DEFAULT NULL,
  `count` int(10) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  KEY `FK_returns_mingxi` (`billcode`),
  CONSTRAINT `FK_returns_mingxi` FOREIGN KEY (`billcode`) REFERENCES `returns_bill` (`billcode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `returns_mingxi` */

insert  into `returns_mingxi`(`billcode`,`code`,`name`,`retail`,`zhekou`,`count`,`total`,`date`) values ('5346812-02-20-53','002','乐事',3.50,1.00,1,3.50,'2012-02-20');

/*Table structure for table `sale_bill` */

DROP TABLE IF EXISTS `sale_bill`;

CREATE TABLE `sale_bill` (
  `billcode` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `isvip` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cost` double DEFAULT NULL COMMENT '成本',
  `total` double DEFAULT NULL COMMENT '总价',
  `date` date DEFAULT NULL,
  PRIMARY KEY (`billcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sale_bill` */

insert  into `sale_bill`(`billcode`,`isvip`,`cost`,`total`,`date`) values ('1593812-02-22-25','003',7,6.3,'2012-02-22'),('1890412-02-22-51','003',7.5,6.75,'2012-02-22'),('2361412-02-23-48','普通顾客',31.5,31.5,'2012-02-23'),('3530412-02-22-28','003',1.5,1.35,'2012-02-22'),('3549812-03-03-21','普通顾客',21,21,'2012-03-03'),('3551312-02-25-54','普通顾客',3.5,3.5,'2012-02-25'),('3666612-03-01-04','普通顾客',8.5,8.5,'2012-03-01'),('481712-02-22-17','001',5,3.5,'2012-02-22'),('5142812-02-22-42','003',17.5,15.75,'2012-02-22'),('5346812-02-20-53','普通顾客',3.5,3.5,'2012-02-20'),('5431312-02-22-12','002',1.5,1.35,'2012-02-22'),('5698712-02-22-58','001',5,3.5,'2012-02-22'),('5869012-02-21-18','普通顾客',5,5,'2012-02-21'),('6484112-02-22-09','003',140,112,'2012-02-22'),('6576912-03-01-19','普通顾客',11,11,'2012-03-01'),('7019012-02-21-39','普通顾客',1.5,1.5,'2012-02-21'),('7231012-02-20-21','普通顾客',8.5,8.5,'2012-02-20'),('7246112-02-21-00','002',5,4.5,'2012-02-21'),('7256912-02-22-08','003',4.5,4.05,'2012-02-22'),('7387812-02-22-03','003',8.5,7.65,'2012-02-22'),('8592312-02-20-08','001',1.5,1.05,'2012-02-20'),('859912-02-21-38','001',5,3.5,'2012-02-21'),('8867012-02-21-17','001',1.5,1.05,'2012-02-21'),('9506512-03-03-38','普通顾客',4,4,'2012-03-03'),('9571612-02-22-44','002',1.5,1.35,'2012-02-22'),('9620612-02-25-44','002',13.5,12.15,'2012-02-25'),('9740212-02-22-44','003',10.5,9.45,'2012-02-22'),('9821112-02-22-14','001',1.5,1.05,'2012-02-22');

/*Table structure for table `sale_mingxi` */

DROP TABLE IF EXISTS `sale_mingxi`;

CREATE TABLE `sale_mingxi` (
  `code` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `retail` double DEFAULT NULL,
  `zhekou` double DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `billcode` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  KEY `FK_sale_mingxi` (`billcode`),
  CONSTRAINT `FK_sale_mingxi` FOREIGN KEY (`billcode`) REFERENCES `sale_bill` (`billcode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sale_mingxi` */

insert  into `sale_mingxi`(`code`,`name`,`retail`,`zhekou`,`count`,`total`,`date`,`billcode`) values ('001','wahaha',1.5,1,1,1.5,'2012-02-20','7231012-02-20-21'),('002','乐事',3.5,1,2,7,'2012-02-20','7231012-02-20-21'),('002','乐事',3.5,1,1,3.5,'2012-02-20','5346812-02-20-53'),('001','wahaha',1.5,0.7,1,1.05,'2012-02-20','8592312-02-20-08'),('001','wahaha',1.5,0.7,1,1.05,'2012-02-21','8867012-02-21-17'),('001','wahaha',1.5,0.7,1,1.05,'2012-02-21','859912-02-21-38'),('002','乐事',3.5,0.7,1,2.45,'2012-02-21','859912-02-21-38'),('001','wahaha',1.5,1,1,1.5,'2012-02-21','7019012-02-21-39'),('001','wahaha',1.5,1,1,1.5,'2012-02-21','5869012-02-21-18'),('002','乐事',3.5,1,1,3.5,'2012-02-21','5869012-02-21-18'),('001','wahaha',1.5,0.9,1,1.35,'2012-02-21','7246112-02-21-00'),('002','乐事',3.5,0.9,1,3.15,'2012-02-21','7246112-02-21-00'),('001','wahaha',1.5,0.7,1,1.05,'2012-02-22','481712-02-22-17'),('002','乐事',3.5,0.7,1,2.45,'2012-02-22','481712-02-22-17'),('001','wahaha',1.5,0.7,1,1.05,'2012-02-22','5698712-02-22-58'),('002','乐事',3.5,0.7,1,2.45,'2012-02-22','5698712-02-22-58'),('001','wahaha',1.5,0.9,1,1.35,'2012-02-22','5431312-02-22-12'),('001','wahaha',1.5,0.9,1,1.35,'2012-02-22','9571612-02-22-44'),('001','wahaha',1.5,0.7,1,1.05,'2012-02-22','9821112-02-22-14'),('002','乐事',3.5,0.9,2,6.3,'2012-02-22','1593812-02-22-25'),('001','wahaha',1.5,0.9,3,4.05,'2012-02-22','7256912-02-22-08'),('001','wahaha',1.5,0.9,1,1.35,'2012-02-22','7387812-02-22-03'),('002','乐事',3.5,0.9,2,6.3,'2012-02-22','7387812-02-22-03'),('002','乐事',3.5,0.9,5,15.75,'2012-02-22','5142812-02-22-42'),('001','wahaha',1.5,0.9,5,6.75,'2012-02-22','1890412-02-22-51'),('002','乐事',3.5,0.9,3,9.45,'2012-02-22','9740212-02-22-44'),('001','wahaha',1.5,0.9,1,1.35,'2012-02-22','3530412-02-22-28'),('002','乐事',3.5,0.8,40,112,'2012-02-22','6484112-02-22-09'),('001','wahaha',1.5,1,21,31.5,'2012-02-23','2361412-02-23-48'),('001','wahaha',1.5,0.9,2,2.7,'2012-02-25','9620612-02-25-44'),('002','乐事薯片',3.5,0.9,3,9.45,'2012-02-25','9620612-02-25-44'),('002','乐事薯片',3.5,1,1,3.5,'2012-02-25','3551312-02-25-54'),('002','乐事薯片',3.5,1,1,3.5,'2012-02-25','3551312-02-25-54'),('001','wahaha',1.5,1,1,1.5,'2012-03-01','3666612-03-01-04'),('002','乐事薯片',3.5,1,2,7,'2012-03-01','3666612-03-01-04'),('002','乐事薯片',3.5,1,1,3.5,'2012-03-01','6576912-03-01-19'),('001','wahaha',1.5,1,5,7.5,'2012-03-01','6576912-03-01-19'),('003','黑人牙膏',12,1,1,12,'2012-03-03','3549812-03-03-21'),('002','乐事',3,1,3,9,'2012-03-03','3549812-03-03-21'),('004','印象卫生纸',4,1,1,4,'2012-03-03','9506512-03-03-38');

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `limits` char(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT ' ',
  `sex` varchar(20) COLLATE utf8_unicode_ci DEFAULT ' ',
  `age` varchar(20) COLLATE utf8_unicode_ci DEFAULT ' ',
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT ' ',
  `idcard` varchar(20) COLLATE utf8_unicode_ci DEFAULT ' ',
  `income` double(10,2) DEFAULT '0.00',
  PRIMARY KEY (`user_name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `staff` */

insert  into `staff`(`id`,`user_name`,`limits`,`name`,`sex`,`age`,`tel`,`idcard`,`income`) values (6,'1','收银员','王威','男','23','1111111111','330624199003110012',25.00),(2,'2','管理员','老刘','男','40',' ',' ',0.00),(3,'admin','经理','','男','','','',0.00);

/*Table structure for table `sunhao` */

DROP TABLE IF EXISTS `sunhao`;

CREATE TABLE `sunhao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cost` double(10,2) DEFAULT NULL,
  `retail` double(10,2) DEFAULT NULL,
  `xcount` int(11) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  `yuanyin` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sunhao` */

insert  into `sunhao`(`id`,`code`,`name`,`cost`,`retail`,`xcount`,`total`,`yuanyin`,`date`) values (6,'002','乐事薯片',2.00,3.50,10,20.00,'过期','2012-02-26'),(7,'002','乐事薯片',2.00,3.50,5,10.00,'潮湿','2012-02-26'),(8,'001','哇哈哈',0.70,1.50,50,35.00,'过期','2012-02-28'),(9,'001','哇哈哈',0.70,1.50,50,35.00,'过期','2012-02-28'),(10,'001','wahaha',0.70,1.50,80,56.00,'过期','2012-02-28'),(11,'005','上好佳薯片',1.50,2.00,10,15.00,'潮湿','2012-03-02'),(12,'005','上好佳薯片',1.50,2.00,1,1.50,'过期','2012-03-03');

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `limits` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user`),
  CONSTRAINT `FK_user_info` FOREIGN KEY (`user`) REFERENCES `staff` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_info` */

insert  into `user_info`(`user`,`password`,`limits`) values ('1','123','收银员'),('2','123','管理员'),('admin','admin','经理');

/*Table structure for table `vip` */

DROP TABLE IF EXISTS `vip`;

CREATE TABLE `vip` (
  `hy_id` int(11) NOT NULL AUTO_INCREMENT,
  `hy_code` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `hy_kind` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hy_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hy_tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hy_expense` double(10,2) DEFAULT '0.00' COMMENT '消费金额',
  `hy_zhekou` double DEFAULT '0' COMMENT '折扣率',
  `hy_birthday` date DEFAULT NULL COMMENT '生日',
  `hy_count` int(11) DEFAULT '0' COMMENT '消费次数',
  `hy_bz` varchar(200) COLLATE utf8_unicode_ci DEFAULT '-' COMMENT '备注(活动)',
  PRIMARY KEY (`hy_id`,`hy_code`),
  UNIQUE KEY `hy_id` (`hy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `vip` */

insert  into `vip`(`hy_id`,`hy_code`,`hy_kind`,`hy_name`,`hy_tel`,`hy_expense`,`hy_zhekou`,`hy_birthday`,`hy_count`,`hy_bz`) values (3,'001','金卡会员','王威','1359641915',26.25,0.7,'2012-03-03',11,'-'),(5,'003','金卡会员','李四','1234567',122.80,0.7,'1990-02-21',3,'-'),(6,'002','普通会员','王五','1234567',19.35,0.9,'1989-02-21',4,'-');

/*Table structure for table `vip_kind` */

DROP TABLE IF EXISTS `vip_kind`;

CREATE TABLE `vip_kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kind` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zhekou` double(10,2) DEFAULT '1.00',
  `Hcondition` double(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `vip_kind` */

insert  into `vip_kind`(`id`,`kind`,`zhekou`,`Hcondition`) values (1,'普通会员',0.90,0.00),(5,'银卡会员',0.80,600.00),(6,'金卡会员',0.70,1000.00);

/*Table structure for table `xiajia_kind` */

DROP TABLE IF EXISTS `xiajia_kind`;

CREATE TABLE `xiajia_kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kind` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `where` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `xiajia_kind` */

insert  into `xiajia_kind`(`id`,`kind`,`where`) values (1,'正常','回库'),(2,'虫蛀','损耗'),(3,'潮湿','损耗'),(4,'过期',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
