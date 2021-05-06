DROP SEQUENCE MYMEMBERSEQ;
DROP TABLE MYMEMBER;

CREATE SEQUENCE MYMEMBERSEQ;

--번호, 아이디, 비밀번호, 이름, 주소, 전화번호, 이메일, 가입여부(Y/N), 등급(ADMIN, USER, ...)
CREATE TABLE MYMEMBER(
	MYNO NUMBER NOT NULL,
	MYID VARCHAR2(500) NOT NULL,
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(500) NOT NULL,
	MYADDR VARCHAR2(2000) NOT NULL,
	MYPHONE VARCHAR2(18) NOT NULL,
	MYEMAIL VARCHAR2(100) NOT NULL,
	MYENABLED VARCHAR2(2) NOT NULL,
	MYROLE VARCHAR2(100) NOT NULL,
	CONSTRAINT MYMEMBER_MYNO_PK PRIMARY KEY (MYNO),
	CONSTRAINT MYMEMBER_MYID_UQ UNIQUE (MYID), 
	CONSTRAINT MYMEMBER_MYPHONE_UQ UNIQUE (MYPHONE), 
	CONSTRAINT MYMEMBER_MYEMAIL_UQ UNIQUE (MYEMAIL), 
	CONSTRAINT MYMEMBER_MYENABLED_CHK CHECK (MYENABLED IN ('Y', 'N'))
);
	--1.MYNO에 PK
	--2.MYID UNIQUE
	--3.MYPHONE UNIQUE
	--4.MYEMAIL UNIQUE
	--5. MYENABLED Y나 N만 들어갈 수 있도록

INSERT INTO MYMEMBER
VALUES(MYMEMBERSEQ.NEXTVAL, 'admin', 'admin1234', '장보옥', '서울 강동', 
		'010-33367-0721','admin@admin.com','Y','ADMIN');
		
INSERT INTO MYMEMBER
VALUES(MYMEMBERSEQ.NEXTVAL, 'admin1', 'admin1111', '사용자', '시스템', 
		'010-1111-1111','admin1@admin.com','Y','ADMIN');
		
INSERT INTO MYMEMBER
VALUES(MYMEMBERSEQ.NEXTVAL, 'apple', 'apple1234', '애플', '사과나무', 
		'010-0000-0000','apple@admin.com','Y','ADMIN');
		
INSERT INTO MYMEMBER
VALUES(MYMEMBERSEQ.NEXTVAL, 'pine', 'pine1234', '파인', '애플', 
		'010-2222-2222','pine@admin.com','Y','ADMIN');
		
INSERT INTO MYMEMBER
VALUES(MYMEMBERSEQ.NEXTVAL, 'test', 'test1234', 'test', 'test', 
		'010-1111-2222','test@admin.com','Y','ADMIN');
		
SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE
FROM MYMEMBER
ORDER BY MYNO DESC;

SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE
FROM MYMEMBER
WHERE MYID = 'apple'
AND MYPW = 'apple1234';
