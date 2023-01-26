/**********************************/
/* Table Name: 설문 참여 */
/**********************************/

DROP TABLE MSURVEY;
DROP TABLE MSURVEY CASCADE CONSTRAINTS;

CREATE TABLE MSURVEY(
    MSURVEYNO                         NUMBER(8)    NOT NULL    PRIMARY KEY,
    SURVEYNO                          NUMBER(8)    NOT NULL ,
    MEMBERNO                          NUMBER(10)     NOT NULL ,
<<<<<<< HEAD
    SURVEYANSWER1                      VARCHAR2(1)       NULL,
    SURVEYANSWER2                      VARCHAR2(1)       NULL,
    SURVEYANSWER3                      VARCHAR2(1)       NULL,
    SURVEYANSWER4                      VARCHAR2(1)       NULL,
    SURVEYANSWER5                      VARCHAR2(1)       NULL,
    SURVEYANSWER6                      VARCHAR2(1)       NULL,
    ETC                               VARCHAR2(200)      NULL,  
=======
    RDATE                             DATE     NOT NULL,
    ADMINNO                           NUMBER(10)     NOT NULL,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),  
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  FOREIGN KEY (SURVEYNO) REFERENCES SURVEY (SURVEYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msurvey is '설문 참여';
COMMENT ON COLUMN msurvey.MSURVEYNO is '설문 참여 번호';
COMMENT ON COLUMN msurvey.SURVEYNO is '설문 제목 번호';
COMMENT ON COLUMN msurvey.MEMBERNO is '회원 번호';
<<<<<<< HEAD
COMMENT ON COLUMN msurvey.SURVEYANSWER1 is '설문 1번 응답';
COMMENT ON COLUMN msurvey.SURVEYANSWER2 is '설문 2번 응답';
COMMENT ON COLUMN msurvey.SURVEYANSWER3 is '설문 3번 응답';
COMMENT ON COLUMN msurvey.SURVEYANSWER4 is '설문 4번 응답';
COMMENT ON COLUMN msurvey.SURVEYANSWER5 is '설문 5번 응답';
COMMENT ON COLUMN msurvey.SURVEYANSWER6 is '설문 6번 응답';
COMMENT ON COLUMN msurvey.ETC is '설문 응답 기타';
=======
COMMENT ON COLUMN msurvey.RDATE is '참여 날짜';
COMMENT ON COLUMN msurvey.ADMINNO is '관리자 번호';
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f

DROP SEQUENCE MSURVEY_seq;

CREATE SEQUENCE MSURVEY_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

<<<<<<< HEAD
commit;


SELECT msurveyno, surveyno, memberno,  SURVEYANSWER1, SURVEYANSWER2, SURVEYANSWER3, SURVEYANSWER4, SURVEYANSWER5, SURVEYANSWER6, ETC
FROM msurvey
ORDER BY msurveyno ASC;
=======
commit;
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
