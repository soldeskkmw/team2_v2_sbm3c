/**********************************/
/* Table Name: 이메일 */
/**********************************/

DROP TABLE email CASCADE CONSTRAINTS;

CREATE TABLE email(
    EMAILNO                           NUMBER(10)     NOT NULL    PRIMARY KEY,
    MEMBERNO                          NUMBER(10)       NULL,
    ADMINNO                           NUMBER(10)       NULL,
    IP                                VARCHAR2(20)     NOT NULL,
    AUTHNO                            VARCHAR2(10)     NOT NULL,
    MDATE                         	  DATE		       NOT NULL,
    SEARCH                            VARCHAR2(20)     NOT NULL,
    RECEIVER                          VARCHAR2(30)     NOT NULL,
     FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
     FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE EMAIL is '이메일';
COMMENT ON COLUMN EMAIL.EMAILNO is '이메일 번호';
COMMENT ON COLUMN EMAIL.MEMBERNO is '회원 번호';
COMMENT ON COLUMN SMS.ADMINNO is '관리자 번호';
COMMENT ON COLUMN EMAIL.IP is '아이피';
COMMENT ON COLUMN EMAIL.AUTHNO is '인증 번호';
COMMENT ON COLUMN EMAIL.MDATE is '생성 날짜';
COMMENT ON COLUMN EMAIL.SEARCH is '찾기';
COMMENT ON COLUMN EMAIL.RECEIVER is '받은이메일';


DROP SEQUENCE email_seq;

CREATE SEQUENCE email_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;
  
commit;
  
-- ip, authno 저장

INSERT INTO email(emailno, memberno, adminno, ip, authno, mdate, search, receiver)
VALUES(email_seq.nextval, 1, 1, '1.225.90.237', '12345678', sysdate, '아이디찾기', 'fset663517@naver.com');
    
INSERT INTO email(emailno, ip, authno, mdate, search, receiver)
VALUES(email_seq.nextval, '2.225.90.237', '11111111', sysdate, '아이디찾기', 'fset663517@naver.com');

INSERT INTO email(emailno, ip, authno, mdate, search, receiver)
VALUES(email_seq.nextval, '3.225.90.238', '22222222', sysdate, '아이디찾기', 'fset663517@naver.com');

commit;

-- 전체 목록 출력

SELECT emailno, memberno, ip, authno, mdate, search, receiver
FROM email
ORDER BY emailno ASC;

-- ip로 email 조회

SELECT emailno, ip, authno, mdate, memberno, search, receiver
FROM email
WHERE ip = '1.225.90.237';

-- 회원 번호로 email 조회

SELECT emailno, ip, authno, mdate, memberno, search, receiver
FROM email
WHERE emailno = 2;

-- authno 수정

UPDATE email
SET authno = '55554444'
WHERE ip = '1.225.90.237';

-- 삭제

DELETE FROM email
WHERE emailno = 1;

-- ip로 기록 삭제

DELETE FROM email
WHERE ip = '1.225.90.237';

-- emailno 로 기록 삭제

DELETE FROM email
WHERE emailno = 1;

commit;