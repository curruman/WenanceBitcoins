SET MODE MYSQL;

CREATE TABLE IF NOT EXISTS `CRIPTO_CURRENCIES` (
	`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `time_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `price` decimal(9,1) NOT NULL,
    `cripto` VARCHAR(3),
    `currency_code` VARCHAR(3)
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8 AUTO_INCREMENT=1;


