CREATE TABLE qr_code
(
  `id` BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `width` INT(5) NOT NULL,
  `height` INT(5) NOT NULL,
  `fore_ground_color` INT(10) NOT NULL,
  `back_ground_color` INT(10) NOT NULL,
  `data` BLOB,
  `status` TINYINT(1) NOT NULL,
  `type` TINYINT(1) NOT NULL,
  `url` VARCHAR(255),
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL
) ENGINE = InnoDB CHARACTER SET=utf8mb4;