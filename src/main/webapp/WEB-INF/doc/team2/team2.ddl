/**********************************/
/* Table Drop */
/**********************************/
DROP TABLE order_pay CASCADE CONSTRAINTS;

/**********************************/
/* Table SEQUENCE Drop */
/**********************************/
DROP SEQUENCE cate_seq;

commit;

/**********************************/
/* Table Name: 관광지 카테고리 */
/**********************************/

DROP TABLE CATE CASCADE CONSTRAINTS;

CREATE TABLE CATE(
      CATENO                              NUMBER(10)       NOT NULL       PRIMARY KEY,
      CATENAME                            VARCHAR2(30)       NOT NULL
);

COMMENT ON TABLE CATE is '관광지 카테고리';
COMMENT ON COLUMN CATE.CATENO is '관광지 카테고리 번호';
COMMENT ON COLUMN CATE.CATENAME is '관광지 카테고리 이름';

INSERT INTO CATE(CATENO, CATENAME)
VALUES(admin_seq.nextval, 'festival');

DROP SEQUENCE cate_seq;

CREATE SEQUENCE cate_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지


/**********************************/
/* Table Name: 관리자 */
/**********************************/

DROP TABLE ADMIN CASCADE CONSTRAINTS;

CREATE TABLE ADMIN(
      ADMINNO                             NUMBER(10)         NOT NULL    PRIMARY KEY,
      ADMINID                             VARCHAR2(20)       NOT NULL,
      ADMINPASSWD                         VARCHAR2(20)       NOT NULL,
      ADMINNAME                           VARCHAR2(20)       NOT NULL,
      grade                               NUMBER(2)     NOT NULL -- 등급(1~10: 관리자, 11~20: 회원, 비회원: 30~39, 정지 회원: 40~49, 탈퇴 회원: 99) 
);

COMMENT ON TABLE ADMIN is '관리자';
COMMENT ON COLUMN ADMIN.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN.ADMINID is '관리자 ID';
COMMENT ON COLUMN ADMIN.ADMINPASSWD is '관리자 비밀번호';
COMMENT ON COLUMN ADMIN.ADMINNAME is '관리자 이름';
COMMENT ON COLUMN ADMIN.GRADE is '등급';

DROP SEQUENCE admin_seq;

CREATE SEQUENCE admin_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 관광지 데이터 */
/**********************************/

DROP TABLE POST CASCADE CONSTRAINTS;

CREATE TABLE POST(
      POSTNO                              NUMBER(10)       NOT NULL       PRIMARY KEY,
      ADMINNO                             NUMBER(10)       NOT NULL,
      CATENO                              NUMBER(10)       NOT NULL,
      POSTTITLE                           VARCHAR2(400)       NOT NULL,
      POSTCONTENT                         CLOB       NOT NULL,
      POSTWORD                            VARCHAR2(400)       NULL ,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL ,
      POSTSTAR                            NUMBER(2)       NOT NULL,
      POSTCNT                             NUMBER(30)       NOT NULL,
      POSTFILE1                           VARCHAR2(200)       NULL ,
      POSTFILE1SAVED                      VARCHAR2(400)       NULL ,
      POSTTHUMB1                          VARCHAR2(200)       NULL ,
      POSTSIZE1                           NUMBER(10)       NULL ,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),
  FOREIGN KEY (CATENO) REFERENCES CATE (CATENO)
);

COMMENT ON TABLE POST is '관광지 데이터';
COMMENT ON COLUMN POST.POSTNO is '관광지 데이터 번호';
COMMENT ON COLUMN POST.ADMINNO is '관리자 번호';
COMMENT ON COLUMN POST.CATENO is '관광지 카테고리 번호';
COMMENT ON COLUMN POST.POSTTITLE is '게시글 제목';
COMMENT ON COLUMN POST.POSTCONTENT is '게시글 내용';
COMMENT ON COLUMN POST.POSTWORD is '게시글 검색어';
COMMENT ON COLUMN POST.RDATE is '등록일';
COMMENT ON COLUMN POST.UDATE is '수정일';
COMMENT ON COLUMN POST.POSTSTAR is '별점';
COMMENT ON COLUMN POST.POSTCNT is '게시글 조회수';
COMMENT ON COLUMN POST.POSTFILE1 is '게시글 메인 이미지';
COMMENT ON COLUMN POST.POSTFILE1SAVED is '게시글 실제 저장된 메인 이미지';
COMMENT ON COLUMN POST.POSTTHUMB1 is '게시글 메인 이미지 Preview';
COMMENT ON COLUMN POST.POSTSIZE1 is '게시글 메인 이미지 크기';

DROP SEQUENCE post_seq;

CREATE SEQUENCE post_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 랭킹 */
/**********************************/

DROP TABLE RANK CASCADE CONSTRAINTS;

CREATE TABLE RANK(
      RANKNO                              NUMBER(10)       NOT NULL       PRIMARY KEY,
      POSTNO                              NUMBER(10)       NOT NULL,
  FOREIGN KEY (POSTNO) REFERENCES POST (POSTNO)
);

COMMENT ON TABLE RANK is '랭킹';
COMMENT ON COLUMN RANK.RANKNO is '랭킹 순위';
COMMENT ON COLUMN RANK.POSTNO is '관광지 데이터 번호';

DROP SEQUENCE rank_seq;

CREATE SEQUENCE rank_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 공지사항 */
/**********************************/

DROP TABLE NOTICE CASCADE CONSTRAINTS;

CREATE TABLE NOTICE(
      NOTICENO                            NUMBER(10)       NOT NULL       PRIMARY KEY,
      ADMINNO                             NUMBER(10)       NOT NULL,
      NOTICETITLE                         VARCHAR2(300)       NOT NULL,
      NOTICECONTENT                       CLOB       NOT NULL,
      NOTICECNT                           NUMBER(30)       NOT NULL,
      NOTICEWORD                          VARCHAR2(400)       NULL ,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL,
      FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE NOTICE is '공지사항';
COMMENT ON COLUMN NOTICE.NOTICENO is '공지사항 번호';
COMMENT ON COLUMN NOTICE.ADMINNO is '관리자 번호';
COMMENT ON COLUMN NOTICE.NOTICETITLE is '공지사항 제목';
COMMENT ON COLUMN NOTICE.NOTICECONTENT is '공지사항 내용';
COMMENT ON COLUMN NOTICE.NOTICECNT is '공지사항 조회수';
COMMENT ON COLUMN NOTICE.NOTICEWORD is '공지사항 검색어';
COMMENT ON COLUMN NOTICE.RDATE is '등록일';
COMMENT ON COLUMN NOTICE.UDATE is '수정일';

DROP SEQUENCE notice_seq;

CREATE SEQUENCE notice_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 회원 */
/**********************************/

DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER(
      MEMBERNO                            NUMBER(10)       NOT NULL       PRIMARY KEY,
      MEMBERID                            VARCHAR2(20)       NOT NULL,
      MEMBERPASSWD                              VARCHAR2(60)       NOT NULL,
      MEMBERNAME                               VARCHAR2(30)       NOT NULL,
      TEL                                 VARCHAR2(14)       NOT NULL,
      MDATE                               DATE       NOT NULL
);

COMMENT ON TABLE MEMBER is '관리자';
COMMENT ON COLUMN MEMBER.MEMBERNO is '회원 번호';
COMMENT ON COLUMN MEMBER.MEMBERID is '회원 ID';
COMMENT ON COLUMN MEMBER.MEMBERPASSWD is '패스워드';
COMMENT ON COLUMN MEMBER.MEMBERNAME is '성명';
COMMENT ON COLUMN MEMBER.TEL is '전화번호';
COMMENT ON COLUMN MEMBER.MDATE is '가입일';

DROP SEQUENCE member_seq;

CREATE SEQUENCE member_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;

/**********************************/
/* Table Name: 문자 */
/**********************************/

DROP TABLE SMS CASCADE CONSTRAINTS;

CREATE TABLE SMS(
    SMSNO                             NUMBER(10)       NOT NULL    PRIMARY KEY,
    IP                                     VARCHAR2(20)    NOT NULL,
    AUTHNO                           NUMBER(10)       NOT NULL,
    MDATE                              DATE                 NOT NULL,
    MEMBERNO                          NUMBER(10)     NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE SMS is '문자';
COMMENT ON COLUMN SMS.SMSNO is '문자 번호';
COMMENT ON COLUMN SMS.IP is '아이피';
COMMENT ON COLUMN SMS.AUTHNO is '인증 번호';
COMMENT ON COLUMN SMS.MDATE is '생성 날짜';
COMMENT ON COLUMN SMS.MEMBERNO is '회원 번호';

DROP SEQUENCE sms_seq;

CREATE SEQUENCE sms_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;

/**********************************/
/* Table Name: 리뷰 */
/**********************************/

DROP TABLE REVIEW CASCADE CONSTRAINTS;

CREATE TABLE REVIEW(
      REVIEWNO                            NUMBER(10)       NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)       NULL ,
      POSTNO                              NUMBER(10)       NULL ,
      REVIEWTITLE                         VARCHAR2(400)       NOT NULL,
      REVIEWCONTENT                       CLOB       NOT NULL,
      REVIEWGOOD                          CHAR(1)       DEFAULT 'N'       NOT NULL,
      REPLYCNT                            NUMBER(10)       NOT NULL,
      CNT                                 NUMBER(30)       NOT NULL,
      REVIEWWORD                          VARCHAR2(400)       NULL ,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL ,
      REVIEWFILE1                         VARCHAR2(200)       NULL ,
      REVIEWFILE1SAVED                    VARCHAR2(400)       NULL ,
      REVIEWTHUMB1                        VARCHAR2(200)       NULL ,
      REVIEWSIZE1                         NUMBER(10)       NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (POSTNO) REFERENCES POST (POSTNO)
);

COMMENT ON TABLE REVIEW is '리뷰';
COMMENT ON COLUMN REVIEW.REVIEWNO is '리뷰 번호';
COMMENT ON COLUMN REVIEW.MEMBERNO is '회원 번호';
COMMENT ON COLUMN REVIEW.POSTNO is '관광지 데이터 번호';
COMMENT ON COLUMN REVIEW.REVIEWTITLE is '리뷰 제목';
COMMENT ON COLUMN REVIEW.REVIEWCONTENT is '리뷰 내용';
COMMENT ON COLUMN REVIEW.REVIEWGOOD is '좋아요';
COMMENT ON COLUMN REVIEW.REPLYCNT is '댓글수';
COMMENT ON COLUMN REVIEW.CNT is '리뷰 조회수';
COMMENT ON COLUMN REVIEW.REVIEWWORD is '리뷰 검색어';
COMMENT ON COLUMN REVIEW.RDATE is '등록일';
COMMENT ON COLUMN REVIEW.UDATE is '수정일';
COMMENT ON COLUMN REVIEW.REVIEWFILE1 is '리뷰 메인 이미지';
COMMENT ON COLUMN REVIEW.REVIEWFILE1SAVED is '리뷰 실제 저장된 메인 이미지';
COMMENT ON COLUMN REVIEW.REVIEWTHUMB1 is '리뷰 메인 이미지 Preview';
COMMENT ON COLUMN REVIEW.REVIEWSIZE1 is '리뷰 메인 이미지 크기';

DROP SEQUENCE review_seq;

CREATE SEQUENCE review_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 댓글 */
/**********************************/

DROP TABLE REPLY CASCADE CONSTRAINTS;

CREATE TABLE REPLY(
      REPLYNO                             NUMBER(10)       NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)       NOT NULL,
      REVIEWNO                            NUMBER(10)       NOT NULL,
      REPLYCONTENT                        CLOB       NOT NULL,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL ,
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글 번호';
COMMENT ON COLUMN REPLY.MEMBERNO is '회원 번호';
COMMENT ON COLUMN REPLY.REVIEWNO is '리뷰 번호';
COMMENT ON COLUMN REPLY.REPLYCONTENT is '댓글 내용';
COMMENT ON COLUMN REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY.UDATE is '수정일';

DROP SEQUENCE reply_seq;

CREATE SEQUENCE reply_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 관리자 추천 게시글 */
/**********************************/

DROP TABLE ADMIN_RECOMMEND CASCADE CONSTRAINTS;

CREATE TABLE ADMIN_RECOMMEND(
      RECOMMENDID                         NUMBER(10)       NOT NULL       PRIMARY KEY,
      ADMINNO                             NUMBER(10)       NOT NULL,
      POSTNO                              NUMBER(10)       NOT NULL,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL ,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),
  FOREIGN KEY (POSTNO) REFERENCES POST (POSTNO)
);

COMMENT ON TABLE ADMIN_RECOMMEND is '관리자 추천 게시글';
COMMENT ON COLUMN ADMIN_RECOMMEND.RECOMMENDID is '추천 게시글 ID';
COMMENT ON COLUMN ADMIN_RECOMMEND.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN_RECOMMEND.POSTNO is '관광지 데이터 번호';
COMMENT ON COLUMN ADMIN_RECOMMEND.RDATE is '등록일';
COMMENT ON COLUMN ADMIN_RECOMMEND.UDATE is '수정일';

DROP SEQUENCE admin_recommend_seq;

CREATE SEQUENCE admin_recommend_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;
  

/**********************************/
/* Table Name: 문의 카테고리 */
/**********************************/

DROP TABLE SERVICE_CATE CASCADE CONSTRAINTS;

CREATE TABLE SERVICE_CATE(
		SERVICECATENO                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		SERVICETYPE_CONTENT           		VARCHAR2(50)		 NOT NULL,
		CNT                           		NUMBER(10)		 NOT NULL,
		ORDER_INDEX                   		NUMERIC(10)		 NULL ,
		VISIBLE                       		CHARACTER(1)		 NULL 
);

COMMENT ON TABLE SERVICE_CATE is '문의 카테고리';
COMMENT ON COLUMN SERVICE_CATE.SERVICECATENO is '문의 카테고리 번호';
COMMENT ON COLUMN SERVICE_CATE.SERVICETYPE_CONTENT is '고객센터 글 종류';
COMMENT ON COLUMN SERVICE_CATE.CNT is '관련 문의수';
COMMENT ON COLUMN SERVICE_CATE.ORDER_INDEX is '출력순서';
COMMENT ON COLUMN SERVICE_CATE.VISIBLE is '출력모드';

DROP SEQUENCE service_cate_seq;

CREATE SEQUENCE service_cate_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 고객센터 글 */
/**********************************/

DROP TABLE CUSTOMER_POST CASCADE CONSTRAINTS;

CREATE TABLE CUSTOMER_POST(
      SERVICENO                           NUMBER(10)       NOT NULL       PRIMARY KEY,
      SERVICECATENO                           NUMERIC(10)       NOT NULL,
      MEMBERNO                            NUMBER(10)       NOT NULL,
      SERVICETITLE                        VARCHAR2(50)       NOT NULL,
      SERVICECONTENTS                     CLOB       NOT NULL,
      SERVICEPASSWD                       NUMBER(10)       NOT NULL,
      SERVICEVISIBLE                      CHAR(1)       NOT NULL,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL ,
  FOREIGN KEY (SERVICECATENO) REFERENCES SERVICE_CATE (SERVICECATENO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE CUSTOMER_POST is '고객센터 글';
COMMENT ON COLUMN CUSTOMER_POST.SERVICENO is '고객센터 글 번호';
COMMENT ON COLUMN CUSTOMER_POST.SERVICECATENO is '문의 카테고리 번호';
COMMENT ON COLUMN CUSTOMER_POST.MEMBERNO is '회원 번호';
COMMENT ON COLUMN CUSTOMER_POST.SERVICETITLE is '고객센터 글 제목';
COMMENT ON COLUMN CUSTOMER_POST.SERVICECONTENTS is '고객센터 글 내용';
COMMENT ON COLUMN CUSTOMER_POST.SERVICEPASSWD is '고객센터 글 비밀번호';
COMMENT ON COLUMN CUSTOMER_POST.SERVICEVISIBLE is '공개여부';
COMMENT ON COLUMN CUSTOMER_POST.RDATE is '등록일';
COMMENT ON COLUMN CUSTOMER_POST.UDATE is '수정일';

DROP SEQUENCE customer_post_seq;

CREATE SEQUENCE customer_post_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 관리자 답글 */
/**********************************/

DROP TABLE ADMIN_REPLY CASCADE CONSTRAINTS;

CREATE TABLE ADMIN_REPLY(
      ADMINREPLYNO                        NUMBER(10)       NOT NULL       PRIMARY KEY,
      SERVICENO                           NUMBER(10)       NOT NULL,
      ADMINNO                             NUMBER(10)       NOT NULL,
      ADMINREPLYTITLE                     VARCHAR2(50)       NOT NULL,
      ADMINREPLYCONTENT                   CLOB       NOT NULL,
      ADMINREPLYVISIBLE                   CHAR(1)       NOT NULL,
      RDATE                               DATE       NOT NULL,
      UDATE                               DATE       NULL ,
  FOREIGN KEY (SERVICENO) REFERENCES CUSTOMER_POST (SERVICENO),
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE ADMIN_REPLY is '관리자 답글';
COMMENT ON COLUMN ADMIN_REPLY.ADMINREPLYNO is '관리자 답글 번호';
COMMENT ON COLUMN ADMIN_REPLY.SERVICENO is '고객센터 글 번호';
COMMENT ON COLUMN ADMIN_REPLY.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN_REPLY.ADMINREPLYTITLE is '관리자 답글 제목';
COMMENT ON COLUMN ADMIN_REPLY.ADMINREPLYCONTENT is '관리자 답글 내용';
COMMENT ON COLUMN ADMIN_REPLY.ADMINREPLYVISIBLE is '공개여부';
COMMENT ON COLUMN ADMIN_REPLY.RDATE is '등록일';
COMMENT ON COLUMN ADMIN_REPLY.UDATE is '수정일';

DROP SEQUENCE admin_reply_seq;

CREATE SEQUENCE admin_reply_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 설문 제목 */
/**********************************/

DROP TABLE SURVEY CASCADE CONSTRAINTS;

CREATE TABLE SURVEY(
      SURVEYNO                            NUMBER(8)       NOT NULL       PRIMARY KEY,
      SURVEYTOPIC                         VARCHAR2(100)       NOT NULL,
      STARTDATE                           DATE       NULL ,
      ENDDATE                             DATE       NULL ,
      YN                                  VARCHAR2(1)       DEFAULT 'N'       NOT NULL,
      RDATE                               DATE       NOT NULL
);

COMMENT ON TABLE SURVEY is '설문 제목';
COMMENT ON COLUMN SURVEY.SURVEYNO is '설문 제목 번호';
COMMENT ON COLUMN SURVEY.SURVEYTOPIC is '설문 제목';
COMMENT ON COLUMN SURVEY.STARTDATE is '시작 날짜';
COMMENT ON COLUMN SURVEY.ENDDATE is '종료 날짜';
COMMENT ON COLUMN SURVEY.YN is '진행여부';
COMMENT ON COLUMN SURVEY.RDATE is '등록일';

DROP SEQUENCE survey_seq;

CREATE SEQUENCE survey_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 설문 항목 */
/**********************************/

DROP TABLE SURVEYITEM CASCADE CONSTRAINTS;

CREATE TABLE SURVEYITEM(
      SURVEYITEMNO                        NUMBER(8)       NOT NULL       PRIMARY KEY,
      SURVEYNO                            NUMBER(8)       NULL ,
      SURVEYITEM                          VARCHAR2(200)       NOT NULL,
      SURVEYCNT                           NUMBER(8)       DEFAULT 0       NOT NULL,
      RDATE                               DATE       NOT NULL,
  FOREIGN KEY (SURVEYNO) REFERENCES SURVEY (SURVEYNO)
);

COMMENT ON TABLE SURVEYITEM is '설문 항목';
COMMENT ON COLUMN SURVEYITEM.SURVEYITEMNO is '설문 항목 번호';
COMMENT ON COLUMN SURVEYITEM.SURVEYNO is '설문 제목 번호';
COMMENT ON COLUMN SURVEYITEM.SURVEYITEM is '항목 내용';
COMMENT ON COLUMN SURVEYITEM.SURVEYCNT is '선택 카운트';
COMMENT ON COLUMN SURVEYITEM.RDATE is '등록날짜';

DROP SEQUENCE surveyitem_seq;

CREATE SEQUENCE surveyitem_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 설문 참여 */
/**********************************/

DROP TABLE MSURVEY CASCADE CONSTRAINTS;

CREATE TABLE MSURVEY(
      MSURVEYNO                           NUMBER(8)       NOT NULL       PRIMARY KEY,
      SURVEYNO                            NUMBER(8)       NULL ,
      MEMBERNO                            NUMBER(10)       NULL ,
      SURVEYITEMNO                        NUMBER(8)       NULL ,
      RDATE                               DATE       NOT NULL,
  FOREIGN KEY (SURVEYNO) REFERENCES SURVEY (SURVEYNO),
  FOREIGN KEY (SURVEYITEMNO) REFERENCES SURVEYITEM (SURVEYITEMNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE msurvey is '설문 참여';
COMMENT ON COLUMN msurvey.MSURVEYNO is '설문 참여 번호';
COMMENT ON COLUMN msurvey.SURVEYNO is '설문 제목 번호';
COMMENT ON COLUMN msurvey.MEMBERNO is '회원 번호';
COMMENT ON COLUMN msurvey.SURVEYITEMNO is '설문 항목 번호';
COMMENT ON COLUMN msurvey.RDATE is '참여 날짜';

DROP SEQUENCE msurvey_seq;

CREATE SEQUENCE msurvey_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 갤러리 댓글 */
/**********************************/

DROP TABLE GALLERYREPLY CASCADE CONSTRAINTS;

CREATE TABLE GALLERYREPLY(
      GALLERYREPLYNO                      NUMBER(10)       NOT NULL       PRIMARY KEY,
      GALLERYNO                           NUMBER(10)       NULL ,
      MEMBERNO                            NUMBER(10)       NULL ,
      CONTENT                             CLOB       NOT NULL,
      RECOM                               NUMBER(7)       NOT NULL,
      PASSWD                              VARCHAR2(15)       NOT NULL,
      RDATE                               DATE       NOT NULL
);

COMMENT ON TABLE GALLERYREPLY is '갤러리 댓글';
COMMENT ON COLUMN GALLERYREPLY.GALLERYREPLYNO is '갤러리 댓글 번호';
COMMENT ON COLUMN GALLERYREPLY.GALLERYNO is '갤러리 번호';
COMMENT ON COLUMN GALLERYREPLY.MEMBERNO is '회원 번호';
COMMENT ON COLUMN GALLERYREPLY.CONTENT is '내용';
COMMENT ON COLUMN GALLERYREPLY.RECOM is '추천수';
COMMENT ON COLUMN GALLERYREPLY.PASSWD is '패스워드';
COMMENT ON COLUMN GALLERYREPLY.RDATE is '등록일';

DROP SEQUENCE galleryreply_seq;

CREATE SEQUENCE galleryreply_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 갤러리 */
/**********************************/

DROP TABLE GALLERY CASCADE CONSTRAINTS;

CREATE TABLE GALLERY(
      GALLERYNO                           NUMBER(10)       NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)       NULL ,
      TITLE                               VARCHAR2(300)       NOT NULL,
      CONTENT                             CLOB       NOT NULL,
      RECOM                               NUMBER(7)       NOT NULL,
      CNT                                 NUMBER(7)       NOT NULL,
      REPLYCNT                            NUMBER(7)       NOT NULL,
      PASSWD                              VARCHAR2(15)       NOT NULL,
      WORD                                VARCHAR2(300)       NULL ,
      RDATE                               DATE       NOT NULL,
      FILE1                               VARCHAR2(100)       NULL ,
      FILE1SAVED                          VARCHAR2(100)       NULL ,
      THUMB1                              VARCHAR2(100)       NULL ,
      SIZE1                               NUMBER(10)       NULL 
);

COMMENT ON TABLE GALLERY is '갤러리';
COMMENT ON COLUMN GALLERY.GALLERYNO is '갤러리 번호';
COMMENT ON COLUMN GALLERY.MEMBERNO is '회원 번호';
COMMENT ON COLUMN GALLERY.TITLE is '제목';
COMMENT ON COLUMN GALLERY.CONTENT is '내용';
COMMENT ON COLUMN GALLERY.RECOM is '추천수';
COMMENT ON COLUMN GALLERY.CNT is '조회수';
COMMENT ON COLUMN GALLERY.REPLYCNT is '댓글수';
COMMENT ON COLUMN GALLERY.PASSWD is '패스워드';
COMMENT ON COLUMN GALLERY.WORD is '검색어';
COMMENT ON COLUMN GALLERY.RDATE is '등록일';
COMMENT ON COLUMN GALLERY.FILE1 is '메인 이미지';
COMMENT ON COLUMN GALLERY.FILE1SAVED is '실제 저장된 메인 이미지';
COMMENT ON COLUMN GALLERY.THUMB1 is '메인 이미지 Preview';
COMMENT ON COLUMN GALLERY.SIZE1 is '메인 이미지 크기';

DROP SEQUENCE gallery_seq;

CREATE SEQUENCE gallery_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;

/**********************************/
/* Table Name: 관광지 데이터 첨부파일 */
/**********************************/

DROP TABLE POST_ATTACHFILE CASCADE CONSTRAINTS;

CREATE TABLE POST_ATTACHFILE(
      POSTATTACHFILENO                    NUMBER(10)       NOT NULL       PRIMARY KEY,
      POSTNO                              NUMBER(10)       NOT NULL,
      POSTFNAME                           VARCHAR2(200)       NOT NULL,
      POSTFUPNAME                         VARCHAR2(200)       NOT NULL,
      POSTTHUMB                           VARCHAR2(200)       NULL ,
      POSTFSIZE                           NUMBER(10)       NOT NULL,
      RDATE                               DATE       NOT NULL,
  FOREIGN KEY (POSTNO) REFERENCES POST (POSTNO)
);

COMMENT ON TABLE POST_ATTACHFILE is '관광지 데이터 첨부파일';
COMMENT ON COLUMN POST_ATTACHFILE.POSTATTACHFILENO is '관광지 데이터 첨부 파일 번호';
COMMENT ON COLUMN POST_ATTACHFILE.POSTNO is '관광지 데이터 번호';
COMMENT ON COLUMN POST_ATTACHFILE.POSTFNAME is '관광지 데이터 원본 파일명';
COMMENT ON COLUMN POST_ATTACHFILE.POSTFUPNAME is '관광지 데이터 업로드 파일명';
COMMENT ON COLUMN POST_ATTACHFILE.POSTTHUMB is '관광지 데이터 Thumb 파일명';
COMMENT ON COLUMN POST_ATTACHFILE.POSTFSIZE is '관광지 데이터 파일 사이즈';
COMMENT ON COLUMN POST_ATTACHFILE.RDATE is '등록일';

DROP SEQUENCE post_attachfile_seq;

CREATE SEQUENCE post_attachfile_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;

/**********************************/
/* Table Name: 리뷰 첨부파일 */
/**********************************/

DROP TABLE REVIEW_ATTACHFILE CASCADE CONSTRAINTS;

CREATE TABLE REVIEW_ATTACHFILE(
      REVIEWATTACHFILENO                  NUMBER(10)       NOT NULL       PRIMARY KEY,
      REVIEWNO                            NUMBER(10)       NOT NULL,
      REVIEWFNAME                         VARCHAR2(200)       NOT NULL,
      REVIEWFUPNAME                       VARCHAR2(200)       NOT NULL,
      REVIEWTHUMB                         VARCHAR2(200)       NULL ,
      REVIEWFSIZE                         NUMBER(10)       NOT NULL,
      RDATE                               DATE       NOT NULL,
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
);

COMMENT ON TABLE REVIEW_ATTACHFILE is '리뷰 첨부파일';
COMMENT ON COLUMN REVIEW_ATTACHFILE.REVIEWATTACHFILENO is '리뷰 첨부 파일 번호';
COMMENT ON COLUMN REVIEW_ATTACHFILE.REVIEWNO is '리뷰 번호';
COMMENT ON COLUMN REVIEW_ATTACHFILE.REVIEWFNAME is '리뷰 원본 파일명';
COMMENT ON COLUMN REVIEW_ATTACHFILE.REVIEWFUPNAME is '리뷰 업로드 파일명';
COMMENT ON COLUMN REVIEW_ATTACHFILE.REVIEWTHUMB is '리뷰 Thumb 파일명';
COMMENT ON COLUMN REVIEW_ATTACHFILE.REVIEWFSIZE is '리뷰 파일 사이즈';
COMMENT ON COLUMN REVIEW_ATTACHFILE.RDATE is '등록일';

DROP SEQUENCE review_attachfile_seq;

CREATE SEQUENCE review_attachfile_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;

/**********************************/
/* Table Name: 유튜브 카테고리 */
/**********************************/

DROP TABLE YOUTUBE_CATE CASCADE CONSTRAINTS;

CREATE TABLE YOUTUBE_CATE(
		YOUTUBECATENO                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		YOUTUBECATENAME               		VARCHAR2(30)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		UDATE                         		DATE		 NULL 
);

COMMENT ON TABLE YOUTUBE_CATE is '유튜브 카테고리';
COMMENT ON COLUMN YOUTUBE_CATE.YOUTUBECATENO is '유튜브 카테고리 번호';
COMMENT ON COLUMN YOUTUBE_CATE.YOUTUBECATENAME is '유튜브 카테고리 이름';
COMMENT ON COLUMN YOUTUBE_CATE.RDATE is '등록일';
COMMENT ON COLUMN YOUTUBE_CATE.UDATE is '수정일';

DROP SEQUENCE youtube_cate_seq;

CREATE SEQUENCE youtube_cate_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;


/**********************************/
/* Table Name: 유튜브 데이터 */
/**********************************/

DROP TABLE YOUTUBE CASCADE CONSTRAINTS;

CREATE TABLE YOUTUBE(
		YOUTUBENO                     		NUMBER(20)		 NOT NULL		 PRIMARY KEY,
		ADMINNO                       		NUMBER(10)		 NOT NULL,
		YOUTUBECATENO                 		NUMBER(10)		 NOT NULL,
		YOUTUBETITLE                  		VARCHAR2(30)		 NOT NULL,
		YOUTUBEADDRESS                		CLOB		 NOT NULL,
		YOUTUBEWORD                   		CLOB		 NULL ,
		RDATE                         		DATE		 NOT NULL,
		UDATE                         		DATE		 NULL ,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO),
  FOREIGN KEY (YOUTUBECATENO) REFERENCES YOUTUBE_CATE (YOUTUBECATENO)
);

COMMENT ON TABLE YOUTUBE is '유튜브 데이터';
COMMENT ON COLUMN YOUTUBE.YOUTUBENO is '유튜브데이터번호';
COMMENT ON COLUMN YOUTUBE.ADMINNO is '관리자 번호';
COMMENT ON COLUMN YOUTUBE.YOUTUBECATENO is '유튜브 카테고리 번호';
COMMENT ON COLUMN YOUTUBE.YOUTUBETITLE is '유튜브 제목';
COMMENT ON COLUMN YOUTUBE.YOUTUBEADDRESS is '유튜브 주소';
COMMENT ON COLUMN YOUTUBE.YOUTUBEWORD is '유튜브 검색어';
COMMENT ON COLUMN YOUTUBE.RDATE is '등록일';
COMMENT ON COLUMN YOUTUBE.UDATE is '수정일';

DROP SEQUENCE youtube_seq;

CREATE SEQUENCE youtube_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;

commit;
