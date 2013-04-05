SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
DROP SCHEMA IF EXISTS `bookingdb` ;
CREATE SCHEMA IF NOT EXISTS `bookingdb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;
USE `bookingdb` ;

-- -----------------------------------------------------
-- Table `bookingdb`.`flights`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bookingdb`.`flights` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `Departure` DATETIME NOT NULL ,
  `Arrival` DATETIME NOT NULL ,
  `DepartureAirport` VARCHAR(45) NOT NULL ,
  `ArrivalAirport` VARCHAR(45) NOT NULL ,
  `FlightNumber` VARCHAR(45) NOT NULL ,
  `BaseFare` INT(11) NOT NULL ,
  `PlaneType` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookingdb`.`passengers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bookingdb`.`passengers` (
  `idPassengers` INT(11) NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `Surname` VARCHAR(45) NOT NULL ,
  `IDdocNumber` VARCHAR(45) NOT NULL ,
  `Sex` VARCHAR(45) NOT NULL ,
  `Title` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idPassengers`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookingdb`.`ticket`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bookingdb`.`ticket` (
  `TicketNumber` INT(11) NOT NULL AUTO_INCREMENT ,
  `Flights_id` INT(11) NOT NULL ,
  `Status` TINYINT(1) NOT NULL ,
  `Seats_Flights_id` INT(11) NOT NULL ,
  `Seats_Seatnumber` INT(11) NOT NULL ,
  PRIMARY KEY (`TicketNumber`) ,
  UNIQUE INDEX `TicketNumber_UNIQUE` (`TicketNumber` ASC) ,
  INDEX `fk_Ticket_Flights1_idx` (`Flights_id` ASC) ,
  INDEX `fk_Ticket_Seats1_idx` (`Seats_Flights_id` ASC, `Seats_Seatnumber` ASC) ,
  CONSTRAINT `fk_Ticket_Flights1`
    FOREIGN KEY (`Flights_id` )
    REFERENCES `bookingdb`.`flights` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookingdb`.`passengers_has_ticket`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bookingdb`.`passengers_has_ticket` (
  `Passengers_idPassengers` INT(11) NOT NULL ,
  `Ticket_TicketNumber` INT(11) NOT NULL ,
  PRIMARY KEY (`Passengers_idPassengers`, `Ticket_TicketNumber`) ,
  INDEX `fk_Passengers_has_Ticket_Ticket1_idx` (`Ticket_TicketNumber` ASC) ,
  INDEX `fk_Passengers_has_Ticket_Passengers1_idx` (`Passengers_idPassengers` ASC) ,
  CONSTRAINT `fk_Passengers_has_Ticket_Passengers1`
    FOREIGN KEY (`Passengers_idPassengers` )
    REFERENCES `bookingdb`.`passengers` (`idPassengers` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Passengers_has_Ticket_Ticket1`
    FOREIGN KEY (`Ticket_TicketNumber` )
    REFERENCES `bookingdb`.`ticket` (`TicketNumber` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookingdb`.`seats`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `bookingdb`.`seats` (
  `Flights_id` INT(11) NOT NULL ,
  `Seatnumber` INT(11) NOT NULL ,
  PRIMARY KEY (`Flights_id`, `Seatnumber`) ,
  CONSTRAINT `fk_Seats_Flights`
    FOREIGN KEY (`Flights_id` )
    REFERENCES `bookingdb`.`flights` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
