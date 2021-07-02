INSERT INTO owner (NAME, EMAIL) VALUES
('Thor', 'thor@gmail.com'),('Tony Stark', 'ironman@gmail.com'),('Bruce Banner', 'hulk@gmail.com'),('Steve Rogers', 'captainamerica@gmail.com');


INSERT INTO vehicle (YEAR, MAKE, MODEL, OWNER_ID) VALUES
(2018, 'Ford', 'Mustang', 1),
(2020, 'Ford', 'Bronco', 2),
(1969, 'Chevy', 'Camaro', 2),
(2018, 'Hyundai', 'Elantra', 3),
(2020, 'Toyota', 'Camry', 4);


INSERT INTO review (VEHICLE_ID, USERNAME, REVIEW, STAR)
VALUES
(4, 'Antman', 'An Elantra is too small for the Hulk', 3),
(4, 'Wasp', 'An Elantra is perfect size!', NULL),
(2, 'Hulk', 'Bronco too tiny for puny Antman!', 3),
(2, 'Wasp', 'A Bronco is perfect size too!', 4);