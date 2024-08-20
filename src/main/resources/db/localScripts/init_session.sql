-- LOCAL SCRIPT FOR INITIALISING DB

USE mrc_resources;

INSERT INTO boats
(name, avg_crew_weight, boat_type, minimum_rower_level, best_blades_id)
VALUES
('Elan', 70, 'COXLESS_QUAD', 'NOVICE', 1 ),
('Maple', 70, 'DOUBLE_SCULL', 'INTERMEDIATE', 2),
('Viper', 70, 'DOUBLE_SCULL', 'INTERMEDIATE', 2),
('W&G', 70, 'COXLESS_PAIR', 'SENIOR', 3);

INSERT INTO blades
(name,amount)
VALUES
('Orange',8),
('Purple',8),
('Sweep W',8);