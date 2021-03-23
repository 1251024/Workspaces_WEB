DROP SEQUENCE BOARDNOSEQ;
DROP SEQUENCE GROUPNOSEQ;
DROP TABLE ANSWERBOARD;

CREATE SEQUENCE BOARDNOSEQ;
CREATE SEQUENCE GROUPNOSEQ;

--글번호, 그룹번호, 그룹순서, 제목공백, 삭제여부('Y','N')
--제목, 내용, 작성자, 작성일
CREATE TABLE ANSWERBOARD(
	BOARDNO NUMBER NOT NULL,
	GROUPNO NUMBER NOT NULL,
	GROUPSEQ NUMBER NOT NULL,
	TITLETAB NUMBER NOT NULL,
	DELFLAG VARCHAR2(2) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WRITER VARCHAR2(500) NOT NULL,
	REGDATE DATE NOT NULL,
	--제약조건 1: BOARDNO에 ANSWER_BOARDNO_PK 라는 이름으로 PK 걸자
	CONSTRAINT ANSWER_BOARDNO_PK PRIMARY KEY (BOARDNO),
	--제약조건 2: DELFLAG에 ANSWER_DELFLAG_CK 라는 이름으로 CHECK 걸자('Y','N')
	CONSTRAINT ANSWER_DELFLAG_CK CHECK(DELFLAG IN ('Y', 'N'))
);

SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE
FROM ANSWERBOARD
ORDER BY GROUPNO DESC, GROUPSEQ;


--1번 글작성
INSERT INTO ANSWERBOARD
VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, 'N', '답변형 재밌다.','정말 재밌다', '반장', SYSDATE);
--글번호: 글의 생성될때 생기는 번호, 몇번째 생기는 글인지
--그룹번호: 그룹마다 부여되는 번호, 몇번째 생성되는 그룹인지, 원글과 답변뭉텅이
--그룹순서: 그룹안에서의 순서, 그룹내에서 몇번째 위치인지
--제목공백: 제목앞에 한칸 띌건지 RE:, 두칸띌껀지 RE:RE: / 부모의 공백 +1
--답변RE:달때 부모글의 순서보다 더 큰애들을 찾아서 그룹순서를 바꿔줘야한단.(UPDATE 후 INSERT)
--삭제여부: 

--1번 글에 답글
--UPDATE
--부모의 그룹번호와 같은 그룹이면서
--부모의 그룹순서보다 더 큰 글들의 
--그룹순서 +1
UPDATE ANSWERBOARD
SET GROUPSEQ = GROUPSEQ+1
WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = 1)
AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO = 1);
--INSERT
--그룹번호는 부모의 그룹번호와 같은 거
--그룹순서는 부모의 그룹순서 +1
--제목공백은 부모의 그룹공백 +1
INSERT INTO ANSWERBOARD
VALUES(
		BOARDNOSEQ.NEXTVAL,
		(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = 1),
		(SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO = 1)+1,
		(SELECT TITLETAB +1 FROM ANSWERBOARD WHERE BOARDNO = 1),
		'N',
		'RE:답변형 재밌다.',
		'진짜 재밌는거 맞아?',
		'강사',
		SYSDATE
		
);


--1번글 삭제
UPDATE ANSWERBOARD
SET DELFLAG = 'Y'
WHERE BOARDNO = 1;