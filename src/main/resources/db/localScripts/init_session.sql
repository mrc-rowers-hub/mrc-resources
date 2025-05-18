-- LOCAL SCRIPT FOR INITIALISING DB

USE
mrc_resources;

INSERT INTO boats
(name, avg_crew_weight, boat_type, minimum_rower_level, best_blades_id)
VALUES ('Elan', 70, 'COXLESS_QUAD', 'NOVICE', 1),
     ('Penny', 70, 'COXLESS_QUAD', 'INTERMEDIATE', 1),
     ('Olga', 70, 'COXED_FOUR', 'INTERMEDIATE', 1),
     ('Tub1', 70, 'COXLESS_QUAD', 'INTERMEDIATE', 1),
     ('Tub2', 70, 'COXLESS_QUAD', 'INTERMEDIATE', 1),
     ('Octuple', 70, 'OCTUPLE', 'NOVICE', 1),
     ('Fillippi', 70, 'COXLESS_QUAD', 'INTERMEDIATE', 1),
     ('Ian Marr', 70, 'COXED_QUAD', 'INTERMEDIATE', 1),
       ('Maple', 70, 'DOUBLE_SCULL', 'SENIOR', 2),
       ('Viper', 70, 'DOUBLE_SCULL', 'INTERMEDIATE', 2),
       ('F&T', 70, 'DOUBLE_SCULL', 'SENIOR', 2),
       ('LJMU8+', 70, 'COXED_EIGHT', 'INTERMEDIATE', 2),
       ('Yellow8', 70, 'COXED_EIGHT', 'INTERMEDIATE', 2),
       ('Bart', 70, 'SINGLE_SCULL', 'INTERMEDIATE', 2),
       ('W&G', 70, 'COXLESS_PAIR', 'SENIOR', 3);

INSERT INTO blades
(name, amount)
VALUES ('Orange', 8),
       ('Purple', 8),
       ('Yellow', 8),
       ('Bantam', 16),
       ('Sweep M', 8),
       ('Sweep W', 8);

INSERT INTO resources_in_use
(resource_id, type, quantity, upcoming_session_id, date, start_time, end_time)
VALUES (1, 'BOAT', 1, 1, '2025-06-01', "18:00:00", "20:00:00"),
       (2, 'BOAT', 1, 1, '2025-06-01', "18:00:00", "20:00:00"),
       (1, 'BLADE', 4, 1, '2025-06-01', "18:00:00", "20:00:00"),
       (2, 'BLADE', 2, 1, '2025-06-01', "18:00:00", "20:00:00"),
       (1, 'BOAT', 1, 1, '2025-06-01', "17:00:00", "17:59:00");