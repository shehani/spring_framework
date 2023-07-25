CREATE TABLE IF NOT EXISTS `idea` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `firstName` varchar(30) NOT NULL,
    `lastName` varchar(30) NOT NULL,
    `address` varchar(50) NOT NULL,
    `address2` varchar(50) NOT NULL,
    `opinion` varchar(500) NOT NULL,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(30) NOT NULL,
    `modified_by` varchar(50),
    `modified_date` TIMESTAMP,
    `status` varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS `schedule` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `type` varchar(20),
    `days` varchar(20),
    `time_duration` varchar(20),
    `created_date` TIMESTAMP NOT NULL,
    `modified_by` varchar(50),
    `modified_date` TIMESTAMP,
    `created_by` varchar(30) NOT NULL,
    `status` varchar(10)
);

CREATE TABLE IF NOT EXISTS `address`(
   `address_id` int NOT NULL AUTO_INCREMENT,
   `address1` varchar(100) NOT NULL,
   `address2` varchar(100) DEFAULT NULL,
   `city` varchar(50) NOT NULL,
   `state` varchar(50) NOT NULL,
   `zipCode` int(5) NOT NULL,
   `created_date` TIMESTAMP NOT NULL,
   `created_by` varchar(50) NOT NULL,
   `modified_by` varchar(50),
   `modified_date` TIMESTAMP,
   PRIMARY KEY (`address_id`)
);


CREATE TABLE IF NOT EXISTS `role` (
   `role_id` int AUTO_INCREMENT PRIMARY KEY,
   `role_name` varchar(20),
   `created_date` TIMESTAMP NOT NULL,
   `created_by` varchar(50) NOT NULL,
   `modified_by` varchar(50),
   `modified_date` TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `person` (
   `person_id` int AUTO_INCREMENT,
   `name` varchar(20) NOT NULL,
   `mobile_number` varchar(20) NOT NULL,
   `email` varchar(50) NOT NULL,
   `password` varchar(200) NOT NULL,
   `address_id` int NULL,
   `role_id` int NOT NULL,
   `created_date` TIMESTAMP NOT NULL,
   `created_by` varchar(50) NOT NULL,
   `modified_by` varchar(50) NULL,
   `modified_date` TIMESTAMP NULL,
    PRIMARY KEY (person_id),
    FOREIGN KEY (role_id ) REFERENCES role(role_id),
    FOREIGN KEY (address_id) REFERENCES address(address_id)
);

CREATE TABLE IF NOT EXISTS 'church' (
    `church_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(30) NOT NULL,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `modified_by` varchar(50) NULL,
    `modified_date` TIMESTAMP NULL,
    PRIMARY KEY (church_id)
);

alter table person add column church_id int null;
alter table person add constraint fk_church_id foreign key (church_id) references church (church_id);

CREATE TABLE IF NOT EXISTS `grade` (
    `grade_id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(30) NOT NULL,
    `created_date` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `modified_by` varchar(50) NULL,
    `modified_date` TIMESTAMP NULL,
    PRIMARY KEY (grade_id)
);

CREATE TABLE IF NOT EXISTS `students_grade` (
    `grade_id` int NOT NULL,
    `person_id` int NOT NULL,
    PRIMARY KEY (grade_id,person_id)

);
alter table students_grade add constraint  fk_person_id foreign key (person_id) references person (person_id);
alter table students_grade add constraint  fk_grade_id foreign key (grade_id) references grade (grade_id);



