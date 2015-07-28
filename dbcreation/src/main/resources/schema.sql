CREATE DATABASE IF NOT EXIST`nmsdb` ;

CREATE TABLE `nmsdb`.`tbl_managers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `mobile` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  INDEX `index1` (`name` ASC, `mobile` ASC, `email` ASC),
  PRIMARY KEY (`id`));
