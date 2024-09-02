-- LOCAL SCRIPT FOR INITIALISING DB

USE
mrc_resources;

INSERT INTO boats
(name, avg_crew_weight, boat_type, minimum_rower_level, best_blades_id)
VALUES ('Elan', 70, 'COXLESS_QUAD', 'NOVICE', 1),
       ('Maple', 70, 'DOUBLE_SCULL', 'INTERMEDIATE', 2),
       ('Viper', 70, 'DOUBLE_SCULL', 'INTERMEDIATE', 2),
       ('W&G', 70, 'COXLESS_PAIR', 'SENIOR', 3);

INSERT INTO blades
(name, amount)
VALUES ('Orange', 8),
       ('Purple', 8),
       ('Sweep W', 8);

INSERT INTO resources_in_use
(resource_id, type, quantity, upcoming_session_id, date, start_time, end_time)
VALUES (1, 'BOAT', 1, 1, '2024-01-01', "18:00:00", "20:00:00"),
       (2, 'BOAT', 1, 1, '2024-01-01', "18:00:00", "20:00:00"),
       (1, 'BLADE', 4, 1, '2024-01-01', "18:00:00", "20:00:00"),
       (2, 'BLADE', 2, 1, '2024-01-01', "18:00:00", "20:00:00");