CREATE TABLE insta_users (
    `id` varchar(32) PRIMARY KEY NOT NULL,
    `created_date` DATETIME NOT NULL,
    `updated_date` DATETIME,
    `phone_number` varchar(32),
    `email` varchar(255),
    `name` varchar(32),
    `nickname` varchar(32),
    `password` varchar(255),
    `introduction` longtext,
    `sex` varchar(32)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;