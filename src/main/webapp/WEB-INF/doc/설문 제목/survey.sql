/**********************************/
/* Table Name: 설문 제목 */
/**********************************/

DROP TABLE SURVEY;
DROP TABLE SURVEY CASCADE CONSTRAINTS;

CREATE TABLE SURVEY(
    SURVEYNO                          NUMBER(8)    NOT NULL    PRIMARY KEY,
    SURVEYITEMNO                      NUMBER(8)    NOT NULL,
    SURVEYTOPIC                       VARCHAR2(100)    NOT NULL,
    SURVEYITEM1                        VARCHAR2(200)     NULL,
    SURVEYITEM2                        VARCHAR2(200)     NULL,
    SURVEYITEM3                        VARCHAR2(200)     NULL,
    SURVEYITEM4                        VARCHAR2(200)     NULL,
    SURVEYITEM5                        VARCHAR2(200)     NULL,
    SURVEYITEM6                        VARCHAR2(200)     NULL,
    SURVEYITEM7                        VARCHAR2(200)     NULL,
    ETC                               VARCHAR2(200)     NULL,
    SURVEYANSWER1                      VARCHAR2(1)     NULL,
    SURVEYANSWER2                      VARCHAR2(1)     NULL,
    SURVEYANSWER3                      VARCHAR2(1)     NULL,
    SURVEYANSWER4                      VARCHAR2(1)     NULL,
    SURVEYANSWER5                      VARCHAR2(1)     NULL,
    SURVEYANSWER6                      VARCHAR2(1)     NULL,
    STARTDATE                         DATE     NOT NULL ,
    ENDDATE                           DATE     NOT NULL ,
    YN                                VARCHAR2(1)    DEFAULT 'N'     NOT NULL,
    RDATE                             DATE     NOT NULL,
    ADMINNO                           NUMBER(10)     NOT NULL,
    FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)    
);

COMMENT ON TABLE SURVEY is '설문 제목';
COMMENT ON COLUMN SURVEY.SURVEYNO is '설문 제목 번호';
COMMENT ON COLUMN SURVEY.SURVEYITEMNO is '설문 질문 번호';
COMMENT ON COLUMN SURVEY.SURVEYTOPIC is '설문 제목';
COMMENT ON COLUMN SURVEY.SURVEYITEM1 is '설문 질문 1번';
COMMENT ON COLUMN SURVEY.SURVEYITEM2 is '설문 질문 2번';
COMMENT ON COLUMN SURVEY.SURVEYITEM3 is '설문 질문 3번';
COMMENT ON COLUMN SURVEY.SURVEYITEM4 is '설문 질문 4번';
COMMENT ON COLUMN SURVEY.SURVEYITEM5 is '설문 질문 5번';
COMMENT ON COLUMN SURVEY.SURVEYITEM6 is '설문 질문 6번';
COMMENT ON COLUMN SURVEY.SURVEYITEM7 is '설문 질문 7번';
COMMENT ON COLUMN SURVEY.ETC is '설문 응답 기타';
COMMENT ON COLUMN SURVEY.SURVEYANSWER1 is '설문 1번 응답';
COMMENT ON COLUMN SURVEY.SURVEYANSWER2 is '설문 2번 응답';
COMMENT ON COLUMN SURVEY.SURVEYANSWER3 is '설문 3번 응답';
COMMENT ON COLUMN SURVEY.SURVEYANSWER4 is '설문 4번 응답';
COMMENT ON COLUMN SURVEY.SURVEYANSWER5 is '설문 5번 응답';
COMMENT ON COLUMN SURVEY.SURVEYANSWER6 is '설문 6번 응답';
COMMENT ON COLUMN SURVEY.STARTDATE is '시작 날짜';
COMMENT ON COLUMN SURVEY.ENDDATE is '종료 날짜';
COMMENT ON COLUMN SURVEY.YN is '진행여부';
COMMENT ON COLUMN SURVEY.RDATE is '등록일';
COMMENT ON COLUMN SURVEY.ADMINNO is '관리자 번호';

DROP SEQUENCE SURVEY_seq;

CREATE SEQUENCE SURVEY_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO survey(surveyno,SURVEYITEMNO, surveytopic, startdate, enddate, yn, rdate, adminno)
VALUES (survey_seq.nextval, 1,'설문조사', '2022-12-26', '2022-12-31', 'N', sysdate, 1);

INSERT INTO survey(surveyno,SURVEYITEMNO, surveytopic, startdate, enddate, yn, rdate, adminno)
VALUES (survey_seq.nextval, 2, '설문조사1', '2022-12-26', '2022-12-31', 'Y', sysdate, 1);

SELECT surveyno, surveyitemno, surveytopic,  surveyitem1, surveyitem2, surveyitem3, surveyitem4, surveyitem5,
                                surveyitem6, surveyitem7, etc, surveyanswer1, surveyanswer2, surveyanswer3, surveyanswer4,
                                surveyanswer5, surveyanswer6, startdate, enddate, yn, rdate, adminno
FROM survey
ORDER BY surveyno ASC;

commit;                          
