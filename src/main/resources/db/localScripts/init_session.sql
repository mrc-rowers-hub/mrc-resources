-- LOCAL SCRIPT FOR INITIALISING DB

USE mrc_resources;

INSERT INTO boats
(name, avg_crew_weight,description, capacity, minimum_rower_level, best_blades)
VALUES
('Elan', 70, '4x', 4, 'NOVICE', 1 ),
('Maple', 70, '2x', 2, 'INTERMEDIATE', 2),
('Viper', 70, '2x', 2, 'INTERMEDIATE', 2),
('W&G', 70, '2-', 2, 'SENIOR', 3);

INSERT INTO blades
(name,amount)
VALUES
('Orange',8),
('Purple',8),
('Sweep W',8);