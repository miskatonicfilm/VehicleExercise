CREATE TABLE owner (
  ID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  NAME VARCHAR(40) NOT NULL,
  EMAIL VARCHAR(40) NOT NULL
);

CREATE TABLE vehicle (
  ID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  YEAR YEAR NOT NULL,
  MAKE VARCHAR(40) NOT NULL,
  MODEL VARCHAR(40) NOT NULL,
  OWNER_ID BIGINT(20) UNSIGNED,
  PRIMARY KEY (ID),
  FOREIGN KEY (OWNER_ID) REFERENCES owner(ID)
);

CREATE TABLE review (
  ID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  VEHICLE_ID BIGINT(20) UNSIGNED,
  USERNAME VARCHAR(20) NOT NULL,
  REVIEW VARCHAR(256) NOT NULL,
  STAR INT,
  PRIMARY KEY (ID),
  FOREIGN KEY (VEHICLE_ID) REFERENCES vehicle(ID)
);