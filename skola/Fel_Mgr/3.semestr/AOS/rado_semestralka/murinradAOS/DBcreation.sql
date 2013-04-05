SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
DROP SCHEMA IF EXISTS `bookingdb` ;
CREATE SCHEMA IF NOT EXISTS `bookingdb` DEFAULT CHARACTER SET utf8 ;
DROP SCHEMA IF EXISTS `paymentdb` ;
CREATE SCHEMA IF NOT EXISTS `paymentdb` DEFAULT CHARACTER SET latin1 ;
USE `mydb` ;
USE `bookingdb` ;

-- -----------------------------------------------------
-- Table `bookingdb`.`flights`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookingdb`.`flights` ;

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
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookingdb`.`passengers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookingdb`.`passengers` ;

CREATE  TABLE IF NOT EXISTS `bookingdb`.`passengers` (
  `idPassengers` INT(11) NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `Surname` VARCHAR(45) NOT NULL ,
  `IDdocNumber` VARCHAR(45) NOT NULL ,
  `Sex` VARCHAR(45) NOT NULL ,
  `Title` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idPassengers`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookingdb`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookingdb`.`ticket` ;

CREATE  TABLE IF NOT EXISTS `bookingdb`.`ticket` (
  `TicketNumber` INT(11) NOT NULL AUTO_INCREMENT ,
  `Flights_id` INT(11) NOT NULL ,
  `Status` TINYINT(1) NOT NULL ,
  `Seats_Seatnumber` INT(11) NOT NULL ,
  `TotalPrice` FLOAT NOT NULL ,
  PRIMARY KEY (`TicketNumber`) ,
  UNIQUE INDEX `TicketNumber_UNIQUE` (`TicketNumber` ASC) ,
  INDEX `fk_Ticket_Flights1_idx` (`Flights_id` ASC) ,
  INDEX `fk_Ticket_Seats1_idx` (`Seats_Seatnumber` ASC) ,
  CONSTRAINT `fk_Ticket_Flights1`
    FOREIGN KEY (`Flights_id` )
    REFERENCES `bookingdb`.`flights` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bookingdb`.`passengers_has_ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bookingdb`.`passengers_has_ticket` ;

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
DROP TABLE IF EXISTS `bookingdb`.`seats` ;

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

USE `paymentdb` ;

-- -----------------------------------------------------
-- Table `paymentdb`.`payments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentdb`.`payments` ;

CREATE  TABLE IF NOT EXISTS `paymentdb`.`payments` (
  `paymentID` INT(11) NOT NULL AUTO_INCREMENT ,
  `paymentAmount` INT(11) NOT NULL ,
  `TicketID` INT(11) NOT NULL ,
  `PaymentStatus` INT(11) NOT NULL ,
  PRIMARY KEY (`paymentID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
