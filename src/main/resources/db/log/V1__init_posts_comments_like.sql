CREATE TABLE posts_comments_like (
    `id` varchar(32) NOT NULL,
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME,
    `user_id` varchar(32),
    `posts_comments_id` varchar(32),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (posts_comments_id) REFERENCES posts_comments(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;