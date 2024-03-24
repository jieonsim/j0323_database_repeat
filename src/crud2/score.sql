show tables;

CREATE TABLE score(
	idx			INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name		VARCHAR(20) NOT NULL,
	korean		INT DEFAULT 0,
	english		INT DEFAULT 0,
	math			INT DEFAULT 0
);

DESC score;
DROP TABLE score;
SELECT * FROM score;

INSERT INTO score VALUES (DEFAULT, '홍길동', 100, 90, 80);
