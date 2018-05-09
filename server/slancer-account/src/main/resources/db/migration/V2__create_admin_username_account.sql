
CREATE TABLE `admin_user` (
`id` bigint NOT NULL,
`nick_name` varchar(256) NOT NULL,
`create_time` timestamp NOT NULL,
`update_time` timestamp NULL,
`delete_flag` tinyint(1) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;

CREATE TABLE `admin_username_account` (
`user_name` varchar(256) NOT NULL,
`password` varchar(256) NOT NULL,
`user_id` bigint NOT NULL,
`create_time` timestamp NOT NULL,
PRIMARY KEY (`user_id`) ,
UNIQUE INDEX `index_username` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;


