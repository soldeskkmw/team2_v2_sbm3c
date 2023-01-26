/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE ADMIN(
    ADMINNO                           NUMBER(10)     NOT NULL    PRIMARY KEY,
    ADMINID                           VARCHAR2(20)     NOT NULL
);

COMMENT ON TABLE ADMIN is '관리자';
COMMENT ON COLUMN ADMIN.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN.ADMINID is '관리자 ID';

DROP SEQUENCE ADMIN_seq;
CREATE SEQUENCE ADMIN_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지DROP SEQUENCE ADMIN_seq;

/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE MEMBER(
    MEMBERNO                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    MEMBERID                          VARCHAR2(20)     NOT NULL,
    PASSWD                            VARCHAR2(60)     NOT NULL,
    MNAME                             VARCHAR2(30)     NOT NULL,
    TEL                               VARCHAR2(14)     NOT NULL,
    MDATE                             DATE     NOT NULL
);

COMMENT ON TABLE MEMBER is '회원';
COMMENT ON COLUMN MEMBER.MEMBERNO is '회원 번호';
COMMENT ON COLUMN MEMBER.MEMBERID is '회원 ID';
COMMENT ON COLUMN MEMBER.PASSWD is '패스워드';
COMMENT ON COLUMN MEMBER.MNAME is '성명';
COMMENT ON COLUMN MEMBER.TEL is '전화번호';
COMMENT ON COLUMN MEMBER.MDATE is '가입일';

DROP SEQUENCE MEMBER_seq;
CREATE SEQUENCE MEMBER_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지DROP SEQUENCE ADMIN_seq;

/**********************************/
/* Table Name: 고객센터 글 */
/**********************************/
DROP TABLE ADMIN_REPLY;
DROP TABLE CUSTOMER_POST;
CREATE TABLE CUSTOMER_POST(
		SERVICENO NUMBER(10) NOT NULL PRIMARY KEY,
		SERVICECATENO NUMERIC(10),
		MEMBERNO NUMBER(10) NOT NULL,
		SERVICETITLE VARCHAR2(50) NOT NULL,
		SERVICECONTENTS CLOB   NOT NULL,
		SERVICEVISIBLE CHAR(1) NOT NULL,
		RDATE DATE NOT NULL,
		UDATE DATE NULL,
        file1                                   VARCHAR(100)          NULL,
        file1saved                            VARCHAR(100)          NULL,
        thumb1                              VARCHAR(100)          NULL,
        size1                                 NUMBER(10)      DEFAULT 0 NULL,
        FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
        FOREIGN KEY (SERVICECATENO) REFERENCES SERVICECATE (SERVICECATENO)
);

COMMENT ON TABLE CUSTOMER_POST is '고객센터 글';
COMMENT ON COLUMN CUSTOMER_POST.SERVICENO is '고객센터 글 번호';
COMMENT ON COLUMN CUSTOMER_POST.MEMBERNO is '회원 번호';
COMMENT ON COLUMN CUSTOMER_POST.SERVICETITLE is '고객센터 글 제목';
COMMENT ON COLUMN CUSTOMER_POST.SERVICECONTENTS is '고객센터 글 내용';
COMMENT ON COLUMN CUSTOMER_POST.SERVICECATENO is '문의 카테고리 번호';
COMMENT ON COLUMN CUSTOMER_POST.SERVICEVISIBLE is '공개여부';
COMMENT ON COLUMN CUSTOMER_POST.RDATE is '등록일';
COMMENT ON COLUMN CUSTOMER_POST.UDATE is '수정일';
COMMENT ON COLUMN CUSTOMER_POST.file1 is '메인 이미지';
COMMENT ON COLUMN CUSTOMER_POST.file1saved is '실제 저장된 메인 이미지';
COMMENT ON COLUMN CUSTOMER_POST.thumb1 is '메인 이미지 preview';
COMMENT ON COLUMN CUSTOMER_POST.size1 is '메인 이미지 크기';

DROP SEQUENCE CUSTOMER_POST_seq;
CREATE SEQUENCE CUSTOMER_POST_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지DROP SEQUENCE ADMIN_seq;



  
  
  
  
  
  
-- ========================
DELETE FROM CUSTOMER_POST;
-- 고객센터 글
-- 등록: 1건 이상
/*
INSERT INTO CUSTOMER_POST (SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEVISIBLE, RDATE, UDATE, file1, file1saved, thumb1, size1) 
VALUES (SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEVISIBLE, RDATE, UDATE, file1, file1saved, thumb1, size1);
*/
INSERT INTO CUSTOMER_POST (serviceno, servicecateno, memberno, servicetitle, servicecontents, servicevisible, RDATE, file1, file1saved, thumb1, size1) 
VALUES (CUSTOMER_POST_seq.nextval, 1, 1, '문의글 제목', '이러이러한 내용을 문의하고 싶습니다.', 'T', sysdate, '', '', '', '');

-- 전체 목록
SELECT SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEVISIBLE, RDATE, UDATE
FROM CUSTOMER_POST
ORDER BY SERVICENO DESC;

-- 조회
SELECT SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEPASSWD, SERVICEVISIBLE, RDATE, UDATE
FROM CUSTOMER_POST
WHERE SERVICENO = 1;
/*
UPDATE CUSTOMER_POST
SET servicetitle = #{servicetitle}, servicecontents=#{servicecontents}, servicecateno=#{servicecateno}, servicevisible=#{servicevisible}, udate=sysdate
WHERE serviceno = #{serviceno};
*/

-- 수정
UPDATE CUSTOMER_POST
SET SERVICETITLE = 'hello', SERVICECONTENTS='and again', SERVICECATENO=3, SERVICEVISIBLE='N', UDATE=sysdate
WHERE SERVICENO = 1;

-- 삭제
DELETE FROM CUSTOMER_POST
WHERE SERVICENO = 1;

-- 레코드 갯수
SELECT COUNT(*)
FROM CUSTOMER_POST;

-- FK 컬럼 기준 카운트, 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*)
FROM CUSTOMER_POST
WHERE MEMBERNO = 1;

-- FK 컬럼 기준 삭제, 특정 그룹에 속한 레코드 모두 삭제
DELETE FROM CUSTOMER_POST
WHERE MEMBERNO = 1;

-- 검색 목록
SELECT SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEPASSWD, SERVICEVISIBLE, RDATE, UDATE
FROM CUSTOMER_POST
WHERE SERVICETITLE LIKE '%%'
ORDER BY SERVICENO DESC;

-- 검색 + 페이징 목록
SELECT SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEPASSWD, SERVICEVISIBLE, RDATE, UDATE
FROM(
    SELECT SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEPASSWD, SERVICEVISIBLE, RDATE, UDATE, rownum as r
    FROM (
        SELECT SERVICENO, SERVICECATENO, MEMBERNO, SERVICETITLE, SERVICECONTENTS, SERVICEPASSWD, SERVICEVISIBLE, RDATE, UDATE
        FROM CUSTOMER_POST
        WHERE SERVICETITLE LIKE '%%'
        ORDER BY SERVICENO DESC
    )
)
WHERE r >= 1 AND r <= 3;