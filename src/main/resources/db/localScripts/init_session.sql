-- LOCAL SCRIPT FOR INITIALISING DB

USE mrc_resources;

INSERT INTO boats
(name, avg_crew_weight,description, capacity, minimum_rower_level)
VALUES
('ELAN', 70, '4x', 4, 'NOVICE'),
('Maple', 70, '2x', 2, 'INTERMEDIATE'),
('Viper', 70, '2x', 2, 'INTERMEDIATE'),
('W&G', 70, '2-', 2, 'SENIOR');