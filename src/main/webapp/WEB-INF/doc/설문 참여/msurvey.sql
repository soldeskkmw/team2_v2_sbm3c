/**********************************/
/* Table Name: 설문 참여 */
/**********************************/

DROP TABLE MSURVEY;
DROP TABLE MSURVEY CASCADE CONSTRAINTS;

CREATE TABLE MSURVEY(
    MSURVEYNO                         NUMBER(8)    NOT NULL    PRIMARY KEY,
    SURVEYNO                          NUMBER(8)    NOT NULL ,
    MEMBERNO                          NUMBER(10)     NOT NULL ,
    RDATE                             DATE     NOT NULL,
    ADMINNO                           NUMBER(10)     NOT NULL,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),  
  FOREIGN KEY (SURVEYNO) REFERENCES SURVEY (SURVEYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msurvey is '설문 참여';
COMMENT ON COLUMN msurvey.MSURVEYNO is '설문 참여 번호';
COMMENT ON COLUMN msurvey.SURVEYNO is '설문 제목 번호';
COMMENT ON COLUMN msurvey.MEMBERNO is '회원 번호';
COMMENT ON COLUMN msurvey.RDATE is '참여 날짜';
COMMENT ON COLUMN msurvey.ADMINNO is '관리자 번호';

DROP SEQUENCE MSURVEY_seq;

CREATE SEQUENCE MSURVEY_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

commit;