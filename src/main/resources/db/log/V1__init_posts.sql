CREATE TABLE posts (
    `id` varchar(32) NOT NULL,
    `user_id` varchar(32),
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME,
    `posts_content` longtext,
    `location` varchar(100),
    `is_hide` boolean DEFAULT true,
    `is_comment` boolean DEFAULT true,
    `posts_status` varchar(32),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;