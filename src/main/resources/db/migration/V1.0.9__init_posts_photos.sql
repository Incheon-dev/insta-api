CREATE TABLE posts_photos (
    `id` varchar(32) NOT NULL,
    `posts_id` varchar(32) NOT NULL,
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME,
    `photo` varchar(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (posts_id) REFERENCES posts(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;