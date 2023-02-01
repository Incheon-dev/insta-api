CREATE TABLE posts_like (
    `id` varchar(32) NOT NULL,
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME,
    `user_id` varchar(32),
    `posts_id` varchar(32),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (posts_id) REFERENCES posts(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;