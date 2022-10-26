-- orders.group_order definition
CREATE TABLE `group_order` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `created_time` datetime(6) DEFAULT NULL,
                               `modified_time` datetime(6) DEFAULT NULL,
                               `end_date` datetime(6) DEFAULT NULL,
                               `group_order_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `minimum_quantity` int DEFAULT NULL,
                               `start_date` datetime(6) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- orders.orders definition
CREATE TABLE `orders` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `created_time` datetime(6) DEFAULT NULL,
                          `modified_time` datetime(6) DEFAULT NULL,
                          `order_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `order_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `price` bigint DEFAULT NULL,
                          `quantity` bigint DEFAULT NULL,
                          `first_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `second_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `zip_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `recipient_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `recipient_phone_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `sender_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `sender_phone_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `shipping_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `group_order_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FK40qho2renb2dsrhof2ugt3ig4` (`group_order_id`),
                          CONSTRAINT `FK40qho2renb2dsrhof2ugt3ig4` FOREIGN KEY (`group_order_id`) REFERENCES `group_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- orders.shipping definition
CREATE TABLE `shipping` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `created_time` datetime(6) DEFAULT NULL,
                            `modified_time` datetime(6) DEFAULT NULL,
                            `first_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `second_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `zip_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `shipping_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `shipping_requests` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;