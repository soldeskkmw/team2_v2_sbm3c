/**********************************/
/* Table Name: 문자 */
/**********************************/

DROP TABLE SMS CASCADE CONSTRAINTS;

CREATE TABLE SMS(
    SMSNO                             NUMBER(10)       NOT NULL    PRIMARY KEY,
    MEMBERNO                          NUMBER(10)       NOT NULL,
    IP                                VARCHAR2(20)     NOT NULL,
    AUTHNO                            VARCHAR2(10)     NOT NULL,
    MDATE                         	  DATE		       NOT NULL,
     FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE SMS is '문자';
COMMENT ON COLUMN SMS.SMSNO is '문자 번호';
COMMENT ON COLUMN SMS.MEMBERNO is '회원 번호';
COMMENT ON COLUMN SMS.IP is '아이피';
COMMENT ON COLUMN SMS.AUTHNO is '인증 번호';
COMMENT ON COLUMN SMS.MDATE is '생성 날짜';


DROP SEQUENCE sms_seq;

CREATE SEQUENCE sms_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;
  
commit;
  
-- ip, authno 저장

INSERT INTO sms(smsno, memberno, ip, authno, mdate)
VALUES(sms_seq.nextval, 1, '1.225.90.237', '12345678', sysdate);
    
INSERT INTO sms(smsno, ip, authno, mdate)
VALUES(sms_seq.nextval, '2.225.90.237', '11111111', sysdate);

INSERT INTO sms(smsno, ip, authno, mdate)
VALUES(sms_seq.nextval, '3.225.90.238', '22222222', sysdate);

commit;

-- 전체 목록 출력

SELECT smsno, memberno, ip, authno, mdate
FROM sms
ORDER BY smsno ASC;

-- ip 조회

SELECT smsno, ip, authno, mdate
FROM sms
WHERE ip = '1.225.90.237';

-- 회원 번호 조회

SELECT smsno, ip, authno, mdate, memberno
FROM sms
WHERE smsno = 2;

-- authno 수정

UPDATE sms 
SET authno = '55554444'
WHERE ip = '1.225.90.237';

-- 삭제

DELETE FROM sms
WHERE smsno = 1;

-- ip로 기록 삭제

DELETE FROM sms
WHERE ip = '1.225.90.237';

-- smsno 로 기록 삭제

DELETE FROM sms
WHERE smsno = 1;

commit;