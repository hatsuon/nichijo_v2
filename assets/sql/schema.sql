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
    `id`             INTEGER PRIMARY KEY AUTO_INCREMENT,
    `cover`          VARCHAR(200) NOT NULL DEFAULT 'https://i.imgtg.com/2022/07/02/NEUcU.jpg',
    `title`          VARCHAR(200) NOT NULL UNIQUE,
    `content`        TEXT,
    `tag_ids`        VARCHAR(100) NOT NULL,
    `type_id`        INTEGER      NOT NULL,
    `user_id`        CHAR(32)     NOT NULL DEFAULT '63a9f0ea7bb98050796b649e85481845',
    `reading`        INTEGER      NOT NULL DEFAULT 0,
    `description`    VARCHAR(400) NULL,
    `is_draft`       BOOLEAN      NOT NULL DEFAULT TRUE COMMENT '是否为草稿',
    `is_public`      BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '是否为私有',
    `is_comment`     BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '是否允许评论',
    `is_transport`   BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '是否为转载',
    `is_recommend` BOOLEAN      NOT NULL DEFAULT FALSE COMMENT '是否为推荐',
    `gmt_created`    DATETIME     NOT NULL DEFAULT NOW(),
    `gmt_updated`    DATETIME     NOT NULL DEFAULT NOW(),
    FOREIGN KEY fk_user_article (user_id) REFERENCES t_user (uid),
    FOREIGN KEY fk_type_article (type_id) REFERENCES t_type (id)
) ENGINE = InnoDB
  CHARACTER SET utf8;

INSERT INTO `t_article`(`tag_ids`, `title`, `content`, `type_id`, `reading`)
    VALUE ('1,2,3,4', '网络服务提供者',
           '网络服务提供者是指通过信息网络向公众提供信息或者为获取网络信息等目的提供服务的机构，包括网络上的一切提供设施、信息和中介、接入等技术服务的个人用户、网络服务商以及非营利组织。根据其提供的“服务”不同，网络服务提供者具体可以分为网络接入服务提供者、网络平台服务提供者、网络内容及产品服务提供者。',
           1, 1000);
INSERT INTO `t_article`(`tag_ids`, `title`, `content`, `type_id`, `reading`)
    VALUE ('1,2,4', '独创性',
           '独创性是一个汉语词语，也称原创性或初创性，是指一部作品经独立创作产生而具有的原创性。一部作品只要不是对一部已有作品的完全的或实质的模仿，而是作者独立构思的产物，就可以视为具有独创性。著作权法理论中允许偶合，两位作者独立同时完成或单独完成相同或实质性相似的作品，均可获得著作权法的保护。',
           2, 1000);

INSERT INTO `t_article`(`tag_ids`, `title`, `content`, `type_id`, `reading`)
    VALUE ('2,4,5', '演绎作品',
           '演绎作品是根据另外一件前已存在的作品所创作的作品。它的创造性就在于对前已存在的作品进行改编，或在于将其译成其他语言的创新成份。对演绎作品的保护不得损害上述原著的版权。演绎作品作者的著作权不是独立的，而是有限制的。划分演绎作品与原作的界线在于新作中保留原作情节或结构的量的多少。演绎作品很易与合作作品相混淆。演绎作品中固然含有原作者的精神劳动，再创作人在行使自己的版权时也要注意勿损害原作者的利益，但演绎作品的作者却享有完整的版权。而合作作品的各个合作者，则是共享一部作品的版权，其中每个人自己享有的版权都不是完整的。',
           9, 1000);
INSERT INTO `t_article`(`tag_ids`, `title`, `content`, `type_id`, `reading`)
    VALUE ('2,4,5', '住所地',
           '住所地是指自然人的户籍所在地、法人或者其他组织的主要办事机构所在地。自然人的户籍所在地一般是以其户口簿或者居民身份证上登记的地址为准，法人或者其他组织的主要办事机构所在地一般是以营业执照上登记的地址为准。',
           10, 100);
INSERT INTO `t_article`(`tag_ids`, `title`, `content`, `type_id`, `reading`)
    VALUE ('2,4,5', '著作权人',
           '著作权人（CopyrightOwner）又称“著作权主体”，是指依法对文学、艺术和科学作品享有著作权的人。著作权人可分为原始著作权人和继受著作权人。原始著作权人指创作作品的自然人和依照法律规定视为作者的法人或者非法人组织；继受著作权指通过继承、受让、受赠等法律许可的形式取得著作权财产权的自然人、法人或者非法人组织。',
           11, 1100);
INSERT INTO `t_article`(`tag_ids`, `title`, `content`, `type_id`, `reading`)
    VALUE ('2,4,5', '违约责任',
           '违约责任，即违反合同的民事责任，也就是合同当事人因违反合同义务所承担的责任。',
           7, 1100);

-- 简单分页查询
SELECT ta.id             AS id,
       ta.title          AS title,
       ta.is_recommend AS is_recommended,
       ta.gmt_updated    AS gmt_updated,
       tt.name           AS type_name
FROM t_article ta
         LEFT JOIN t_type AS tt
                   ON ta.type_id = tt.id
WHERE title LIKE '%网%' AND tt.id = 1 AND is_recommend = TRUE
ORDER BY ta.gmt_updated