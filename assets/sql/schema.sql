DROP DATABASE IF EXISTS `db_nichijo_v2`;
CREATE DATABASE `db_nichijo_v2`;

USE `db_nichijo_v2`;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `uid`         CHAR(32) PRIMARY KEY  DEFAULT '63a9f0ea7bb98050796b649e85481845',
    `type`        BOOLEAN      NOT NULL DEFAULT FALSE,
    `cover`       VARCHAR(200) NOT NULL DEFAULT 'https://i.imgtg.com/2022/07/01/NDBBD.jpg',
    `username`    CHAR(10)     NOT NULL UNIQUE,
    `nickname`    CHAR(20)     NOT NULL UNIQUE,
    `password`    CHAR(32)     NOT NULL DEFAULT 'd41d8cd98f00b204e9800998ecf8427e',
    `avatar`      VARCHAR(200) NOT NULL DEFAULT 'https://i.imgtg.com/2022/07/01/N4EpK.jpg',
    `gmt_created` DATETIME     NOT NULL DEFAULT NOW(),
    `gmt_updated` DATETIME     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB
  CHARACTER SET utf8;

INSERT INTO `t_user`(`username`, `nickname`, `type`)
    VALUE ('root', 'Xiangkunxu', TRUE);


DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`
(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name`        CHAR(10) NOT NULL UNIQUE,
    `user_id`     CHAR(32) NOT NULL DEFAULT '63a9f0ea7bb98050796b649e85481845',
    `gmt_created` DATETIME NOT NULL DEFAULT NOW(),
    `gmt_updated` DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY fk_user_type (user_id) REFERENCES t_user (uid)
) ENGINE = InnoDB
  CHARACTER SET utf8;

INSERT INTO `t_type`(`name`) VALUE ('Java');
INSERT INTO `t_type`(`name`) VALUE ('Docker');
INSERT INTO `t_type`(`name`) VALUE ('HTML');
INSERT INTO `t_type`(`name`) VALUE ('CSS');
INSERT INTO `t_type`(`name`) VALUE ('Python');
INSERT INTO `t_type`(`name`) VALUE ('Linux');
INSERT INTO `t_type`(`name`) VALUE ('Redis');
INSERT INTO `t_type`(`name`) VALUE ('MySQL');
INSERT INTO `t_type`(`name`) VALUE ('Go');
INSERT INTO `t_type`(`name`) VALUE ('jQuery');
INSERT INTO `t_type`(`name`) VALUE ('JavaScript');
INSERT INTO `t_type`(`name`) VALUE ('MariaDB');
INSERT INTO `t_type`(`name`) VALUE ('Json');
INSERT INTO `t_type`(`name`) VALUE ('Lua');
INSERT INTO `t_type`(`name`) VALUE ('Maven');


DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`
(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name`        CHAR(10) NOT NULL UNIQUE,
    `user_id`     CHAR(32) NOT NULL DEFAULT '63a9f0ea7bb98050796b649e85481845',
    `gmt_created` DATETIME NOT NULL DEFAULT NOW(),
    `gmt_updated` DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY fk_user_tag (user_id) REFERENCES t_user (uid)
) ENGINE = InnoDB
  CHARACTER SET utf8;

INSERT INTO `t_tag`(`name`) VALUE ('alloy');
INSERT INTO `t_tag`(`name`) VALUE ('fair');
INSERT INTO `t_tag`(`name`) VALUE ('punish');
INSERT INTO `t_tag`(`name`) VALUE ('spectator');
INSERT INTO `t_tag`(`name`) VALUE ('frightened');
INSERT INTO `t_tag`(`name`) VALUE ('standards');
INSERT INTO `t_tag`(`name`) VALUE ('category');
INSERT INTO `t_tag`(`name`) VALUE ('cruel');
INSERT INTO `t_tag`(`name`) VALUE ('reward');
INSERT INTO `t_tag`(`name`) VALUE ('nerves');


DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`
(
    `id`           INTEGER PRIMARY KEY AUTO_INCREMENT,
    `cover`        VARCHAR(200) NOT NULL DEFAULT 'https://i.imgtg.com/2022/07/02/NEUcU.jpg',
    `tag_ids`      VARCHAR(100) NOT NULL,
    `content`      TEXT,
    `title`        VARCHAR(200) NOT NULL UNIQUE,
    `type_id`      INTEGER      NOT NULL,
    `user_id`      CHAR(32)     NOT NULL DEFAULT '63a9f0ea7bb98050796b649e85481845',
    `reading`      INTEGER      NOT NULL DEFAULT 0,
    `description`  VARCHAR(400) NULL,
    `is_draft`     BOOLEAN      NOT NULL DEFAULT TRUE,
    `is_public`    BOOLEAN      NOT NULL DEFAULT FALSE,
    `is_comment`   BOOLEAN      NOT NULL DEFAULT FALSE,
    `is_recommend` BOOLEAN      NOT NULL DEFAULT FALSE,
    `gmt_created`  DATETIME     NOT NULL DEFAULT NOW(),
    `gmt_updated`  DATETIME     NOT NULL DEFAULT NOW(),
    FOREIGN KEY fk_user_article (user_id) REFERENCES t_user (uid),
    FOREIGN KEY fk_type_article (type_id) REFERENCES t_type (id)
) ENGINE = InnoDB
  CHARACTER SET utf8;