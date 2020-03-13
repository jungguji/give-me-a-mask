CREATE TABLE `store_sale` (
    `code` VARCHAR(50) NOT NULL 
    , `name` VARCHAR(200)  
    , `addr` VARCHAR(200) 
    , `type` VARCHAR(2) 
    , `lat` FLOAT 
    , `lng` FLOAT
    , `stock_at` datetime 
    , `remain_stat` VARCHAR(10) NOT NULL 
    , `created_at` datetime 
    , PRIMARY KEY (`code`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
DEFAULT CHARSET=utf8
;