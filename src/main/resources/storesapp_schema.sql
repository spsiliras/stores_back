create database storesapp;

use storesapp;

CREATE TABLE IF NOT EXISTS `store` (
    `store_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `owner` varchar(100) NOT NULL,
    `location` varchar(100) NOT NULL,
    PRIMARY KEY (`store_id`)
);

CREATE TABLE IF NOT EXISTS `product` (
    `product_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `quantity` int NOT NULL,
    `price` double NOT NULL,
    `store_id` int NULL,
    PRIMARY KEY (`product_id`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`)
);

CREATE TABLE IF NOT EXISTS `employee` (
    `employee_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `address` varchar(100) NOT NULL,
    `salary` double NOT NULL,
    `store_id` int NULL,
    PRIMARY KEY (`employee_id`),
    FOREIGN KEY (`store_id`) REFERENCES `store`(`store_id`)
);