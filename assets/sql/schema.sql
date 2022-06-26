DROP DATABASE IF EXISTS `db_nichijo_v2`;
CREATE DATABASE `db_nichijo_v2`;

USE `db_nichijo_v2`;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          INTEGER AUTO_INCREMENT PRIMARY KEY,
    `type`        BOOLEAN      NOT NULL DEFAULT FALSE,
    `username`    CHAR(10)     NOT NULL,
    `nickname`    CHAR(20)     NOT NULL,
    `password`    CHAR(32)     NOT NULL DEFAULT '4a7d1ed414474e4033ac29ccb8653d9b',
    `avatar`      VARCHAR(200) NOT NULL DEFAULT 'https://s1.328888.xyz/2022/05/04/hRyP3.jpg',
    `gmt_created` DATETIME     NOT NULL DEFAULT NOW(),
    `gmt_updated` DATETIME     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB
  CHARACTER SET utf8;

INSERT INTO `t_user`(`username`, `nickname`, `type`)
    VALUE ('root', 'root', TRUE);


DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`
(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name`        CHAR(10) NOT NULL,
    `user_id`     INTEGER  NOT NULL DEFAULT 1,
    `gmt_created` DATETIME NOT NULL DEFAULT NOW(),
    `gmt_updated` DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY fk_user_type (user_id) REFERENCES t_user (id)
) ENGINE = InnoDB
  CHARACTER SET utf8;

DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`
(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name`        CHAR(10) NOT NULL,
    `user_id`     INTEGER  NOT NULL DEFAULT 1,
    `gmt_created` DATETIME NOT NULL DEFAULT NOW(),
    `gmt_updated` DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY fk_user_tag (user_id) REFERENCES t_user (id)
) ENGINE = InnoDB
  CHARACTER SET utf8;

DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`
(
    `id`          INTEGER PRIMARY KEY AUTO_INCREMENT,
    `tags`        VARCHAR(100) NOT NULL,
    `content`     TEXT,
    `title`       VARCHAR(200) NOT NULL UNIQUE,
    `type_id`     INTEGER      NOT NULL,
    `user_id`     INTEGER      NOT NULL DEFAULT 1,
    `is_draft`    BOOLEAN      NOT NULL DEFAULT TRUE,
    `reading`     INTEGER      NOT NULL DEFAULT 0,
    `gmt_created` DATETIME     NOT NULL DEFAULT NOW(),
    `gmt_updated` DATETIME     NOT NULL DEFAULT NOW(),
    FOREIGN KEY fk_user_article (user_id) REFERENCES t_user (id),
    FOREIGN KEY fk_type_article (type_id) REFERENCES t_type (id)
) ENGINE = InnoDB
  CHARACTER SET utf8;