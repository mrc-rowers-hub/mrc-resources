CREATE TABLE boats
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    avg_crew_weight FLOAT(2),
    description ENUM('1x', '2x', '2-', '4x', '4+', '8+'),
    capacity INT NOT NULL,
    minimum_rower_level ENUM('DEVELOPMENT', 'NOVICE', 'INTERMEDIATE', 'SENIOR') DEFAULT 'INTERMEDIATE',
    status ENUM('WORKING', 'WORKING WITH FAULTS', 'DO NOT USE - FAULTY', 'DO NOT USE - OTHER') DEFAULT 'WORKING',
    best_blades INT
);

CREATE TABLE blades
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    amount INT,
    status ENUM('WORKING', 'WORKING WITH FAULTS','DO NOT USE - FAULTY', 'DO NOT USE - OTHER') DEFAULT 'WORKING'
)




