show tables;

CREATE TABLE User (
	idx INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	age INT DEFAULT 20,
	gender CHAR(2) DEFAULT '여자',
	address VARCHAR(30)
);

DESC User;
DROP TABLE User;

INSERT INTO User VALUES (DEFAULT, '홍길동', DEFAULT, '남자', '서울');
INSERT INTO User VALUES (DEFAULT, '김말숙', 29, '여자', '청주');
INSERT INTO User VALUES (DEFAULT, '이기자', 35, '남자', '부산');
INSERT INTO User VALUES (DEFAULT, '소나무', 40, '남자', '하남');
INSERT INTO User VALUES (DEFAULT, '오하늘', DEFAULT, '남자', '서울');

SELECT * FROM User;

