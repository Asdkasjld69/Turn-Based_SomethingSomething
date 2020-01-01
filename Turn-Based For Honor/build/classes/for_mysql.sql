DROP DATABASE IF EXISTS `tbfh`;

CREATE DATABASE `tbfh`;

USE `tbfh`;

/*Table structure for table `users` */
DROP TABLE IF EXISTS `USERS`;

CREATE TABLE `USERS` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL,
  `PASSWORD` varchar(24) NOT NULL,
  `gender` varchar(2) DEFAULT 'S',
  `email` varchar(50) DEFAULT NULL,
  `activeCode` varchar(50) DEFAULT NULL,
  `state` int(2) DEFAULT 0,
  `role` varchar(10) DEFAULT 'NORMAL',
  `avatar` varchar(128),
  `gamesfinished` int(8) NOT NULL DEFAULT 0,
  `turnsmoved` int(8) NOT NULL DEFAULT 0,
  `gamesabandoned` int(8) NOT NULL DEFAULT 0,
  `damagedealt` int(8) NOT NULL DEFAULT 0,
  `damagereceived` int(8) NOT NULL DEFAULT 0,
  `kills` int(8) NOT NULL DEFAULT 0,
  `deaths` int(8) NOT NULL DEFAULT 0,
  `assists` int(8) NOT NULL DEFAULT 0,
  `win` int(8) NOT NULL DEFAULT 0,
  `lose` int(8) NOT NULL DEFAULT 0,
  `draw` int(8) NOT NULL DEFAULT 0,
  `likes` int(8) NOT NULL DEFAULT 0,
  `dislikes` int(8) NOT NULL DEFAULT 0,
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `USERS`(`id`,`username`,`PASSWORD`,`gender`,`email`,`activeCode`,`state`,`role`,`registTime`) values 
(1,'admin','111111','M','edwurend1999@163.com','49338fdc-d8c9-46fa-8391-0fac7bf09707',1,'SUPER','2018-12-24 08:34:40'),
(2,'user1','111111','M','cnm@163.com','49338fdc-d8c9-46fa-8391-0fac7bf09708',1,'NORMAL','2019-12-20 05:30:45'),
(3,'user2','111111','M','cnmd@163.com','49338fdc-d8c9-46fa-8391-0fac7bf09709',1,'NORMAL','2019-12-21 09:34:10'),
(4,'Brother667','111111','M','cnmb@163.com','49338fdc-d8c9-46fa-8391-0fac7bf09710',1,'NORMAL','2019-12-23 11:00:30'),
(5,'Totallygudguy1234','111111','M','wcnm@163.com','49338fdc-d8c9-46fa-8391-0fac7bf09711',1,'NORMAL','2019-12-22 13:36:56');

DROP TABLE IF EXISTS `MAPS`;

CREATE TABLE `MAPS`(
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(24),
  `width` int,
  `height` int,
  `obstacle` varchar(768),
  `fire` varchar(768),
  `trap` varchar(768),
  `pit` varchar(768),
  PRIMARY KEY (`id`)
);

INSERT INTO `MAPS` VALUES
(1,
'CRASH',
32,32,
'00-1Fx1F,00x00-1E,1Fx00-1E,01-1Ex00,0Ex13-1E,01-05x0B,1ex07,1d-1ex06,1c-1ex05,1b-1ex04,1x01-a,2x01-09,3x01-08,4x01-05,5x01-03,6x01-02,7x01-02,8x01-02,9x01-03,ax01-03,bx01-04,cx01-05,dx01-06,ex01-07,fx01-08,10-12x01-06,17-1ex1e,18-1ex1d,1d-1ex1c,1x14-1e,2x19-1e,3x1b-1e,4x1c-1e,5x1d-1e,6x1e-1e,7x1e-1e,8x1e-1e,9x1d-1e,ax1a-1e,bx19-1e,cx14-1e,dx13-1e',
'0f-12x1e,0fx1b-1d,10x1d,05-07x11-12','1bx05,1cx06,1dx07,1xc-f',
'1b-1ex11-18');


DROP TABLE IF EXISTS `CHARACTERS`;

CREATE TABLE `CHARACTERS`(
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(24),
  `health` int(3) NOT NULL DEFAULT 125,
  `speed` int(2) NOT NULL DEFAULT 6,
  `avatar` varchar(128),
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`)
);

INSERT INTO `CHARACTERS`(`id`,`name`,`health`,`speed`) VALUES
(10,'WARDEN',125,6),
(11,'RAIDER',140,6),
(12,'KENSEI',125,4),
(13,'LAW BRINGER',150,5),
(14,'VALKIRIE',125,8),
(15,'NOBUSHI',125,8),
(16,'PEACE KEEPER',125,7),
(17,'BERSERKER',125,7),
(18,'SHINOBI',110,9);

DROP TABLE IF EXISTS `SESSIONS`;

CREATE TABLE `SESSIONS`(
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `map_id` int(12) NOT NULL,
  `cap` int(2) DEFAULT 2,
  `state` int(1) DEFAULT 0,
  `password` varchar(16),
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_time` TIMESTAMP,
  `end_time` TIMESTAMP,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`map_id`) REFERENCES `MAPS`(`id`)
);

INSERT INTO `SESSIONS`(`id`,`name`,`map_id`,`cap`,`state`) VALUES
(1,'LOBBY FOR TEST!',1,2,1),
(2,'ANOTHER ONE!',1,2,5);

DROP TABLE IF EXISTS `PLAYERS`;

CREATE TABLE `PLAYERS`(
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `session_id` int(12) NOT NULL,
  `user_id` int(12) NOT NULL,
  `character_id` int(12),
  `team` int(1) DEFAULT 0,
  `health` int(3),
  `health_max` int(3),
  `revenge` int(3) DEFAULT 0,
  `state` int(4) DEFAULT -1,
  `posx` int(3) DEFAULT -1,
  `posy` int(3) DEFAULT -1,
  `join_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `move_time` TIMESTAMP,
  `end_time` TIMESTAMP,
  PRIMARY KEY(`id`,`user_id`),
  FOREIGN KEY(`session_id`) REFERENCES `SESSIONS`(`id`),
  FOREIGN KEY(`user_id`) REFERENCES `USERS`(`id`)
);

INSERT INTO `PLAYERS`(`session_id`,`user_id`,`character_id`,`team`,`health`,`health_max`,`revenge`,`state`,`posx`,`posy`) VALUES
(1,2,10,0,125,125,0,-1,25,26),
(1,3,12,1,125,125,0,-1,16,15),
(2,4,13,0,150,140,0,-1,15,14),
(2,5,14,1,125,125,0,0,15,26);

DROP TABLE IF EXISTS `CHATS`;

CREATE TABLE `CHATS`(
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `session_id` int(12) NOT NULL,
  `player_id` int(12) NOT NULL,
  `content` varchar(256) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`session_id`) REFERENCES `SESSIONS`(`id`),
  FOREIGN KEY(`player_id`) REFERENCES `USERS`(`id`)
);

INSERT INTO `CHATS`(`session_id`,`player_id`,`content`) VALUES
(1,2,'hi'),
(1,3,'sup'),
(2,4,'eish...');

DROP TABLE IF EXISTS `CHATS_PUBLIC`;

CREATE TABLE `CHATS_PUBLIC`(
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `user_id` int(12) NOT NULL,
  `content` varchar(256) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`)
);

INSERT INTO `CHATS_PUBLIC`(`user_id`,`content`) VALUES
(2,'heya'),
(3,'hello'),
(4,'hows it going?'),
(3,'not very well :(');

DROP TABLE IF EXISTS `BATTLELOG`;

CREATE TABLE `BATTLELOGS`(
 `id` int(12) NOT NULL AUTO_INCREMENT,
 `player_id` int(12) NOT NULL,
 `turn` int(12) NOT NULL DEFAULT 0,
 `stateno` int(12) NOT NULL DEFAULT 0,
 `movex` int(12) DEFAULT 0,
 `movey` int(12) DEFAULT 0,
 `priority` int(12) NOT NULL DEFAULT 0,
 `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY(`id`),
 FOREIGN KEY(`player_id`) REFERENCES `PLAYERS`(`id`)
);
