DROP TABLE IF EXISTS `bn_categories`;

CREATE TABLE `voter` (
  `id` int(20) unsigned NOT NULL,
  `post` bigint(20) unsigned NOT NULL,
  `vote` int (20) unsigned DEFAULT NULL,
  PRIMARY KEY (`post`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;