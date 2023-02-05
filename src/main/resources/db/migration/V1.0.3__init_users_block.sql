CREATE TABLE users_block (
    `id` varchar(32) NOT NULL,
    `user_id` varchar(32) NOT NULL,
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME,
    `other_user_id` varchar(32) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;