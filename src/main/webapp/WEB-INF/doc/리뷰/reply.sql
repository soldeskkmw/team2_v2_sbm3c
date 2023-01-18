/**********************************/
/* Table Name: 댓글 */
/**********************************/
DROP TABLE REPLY;
DROP TABLE REPLY CASCADE CONSTRAINTS;



CREATE TABLE REPLY(
<<<<<<< HEAD
		REPLYNO                       		NUMBER(10)		 NOT NULL        PRIMARY KEY,
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
		REVIEWNO                      		NUMBER(10)		 NOT NULL,
		REPLYCONTENT                  		VARCHAR2(100)	 NOT NULL,
		RDATE                         		DATE		     NOT NULL,
		UDATE                         		DATE		     NULL,
     FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
     FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
=======
		REPLYNO                       		NUMBER(10)		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
		REVIEWNO                      		NUMBER(10)		 NOT NULL,
		REPLYCONTENT                  		VARCHAR2(100)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		UDATE                         		DATE		 NULL ,
		REVIEWGOOD                    		CHAR(1)		 DEFAULT 'N'		 NOT NULL
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글 번호';
COMMENT ON COLUMN REPLY.MEMBERNO is '회원 번호';
COMMENT ON COLUMN REPLY.REVIEWNO is '리뷰 번호';
COMMENT ON COLUMN REPLY.REPLYCONTENT is '댓글 내용';
COMMENT ON COLUMN REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY.UDATE is '수정일';
<<<<<<< HEAD


--ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_PK PRIMARY KEY (REPLYNO);
--ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK0 FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO);
--ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK1 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
=======
COMMENT ON COLUMN REPLY.REVIEWGOOD is '좋아요';


ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_PK PRIMARY KEY (REPLYNO);
ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK0 FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO);
ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK1 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3

DROP SEQUENCE reply_seq;

CREATE SEQUENCE reply_seq
  START WITH 1             -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
  -- 등록: 1건 이상
<<<<<<< HEAD
INSERT INTO REPLY( replyno, memberno, reviewno, replycontent,rdate)
    
VALUES (reply_seq.nextval, 1, 1, 'testcontent', sysdate);

-- 리뷰당 전체 목록
SELECT  replyno, memberno, reviewno, replycontent, rdate, udate
=======
INSERT INTO REPLY( replyno, memberno, reviewno, replycontent,rdate, udate, reviewgood)
    
VALUES (reply_seq.nextval, 1,2,'testcontent',sysdate,null,'N');

-- 리뷰당 전체 목록
SELECT  replyno, memberno, reviewno, replycontent,rdate, udate, reviewgood
>>>>>>> ccf1856aa8c91cb2454ed2ec9c008f842127afa3
    
FROM reply
WHERE reviewno = 2
ORDER BY replyno DESC;

-- 조회
SELECT  replyno, memberno, reviewno, replycontent,rdate, udate, reviewgood
    
FROM reply
WHERE replyno=1;

-- 수정
UPDATE review 

SET replycontent='updatecontent12',
RDATE='2022-11-15 12:38:01', UDATE='2022-11-16 12:38:01'

WHERE replyno=1;

commit;
  
SELECT c.reviewno,
		          t.reviewno, t.memberno, t.replyno, t.replycontent,
   t.rdate, t.udate,t.reviewgood 
		FROM review c, reply t
		WHERE c.reviewno = t.reviewno
		ORDER BY t.replyno DESC
        
        -- 삭제
DELETE FROM REPLY
WHERE replyno>=5;
commit
        
        
        