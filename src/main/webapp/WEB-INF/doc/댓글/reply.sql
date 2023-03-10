/**********************************/
/* Table Name: 댓글 */
/**********************************/
DROP TABLE REPLY;
DROP TABLE REPLY CASCADE CONSTRAINTS;

<<<<<<< HEAD


CREATE TABLE REPLY(
		REPLYNO                       		NUMBER(10)		 NOT NULL        PRIMARY KEY,
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
		REVIEWNO                      		NUMBER(10)		 NOT NULL,
		REPLYCONTENT                  		VARCHAR2(100)	 NOT NULL,
		RDATE                         		DATE		     NOT NULL,
		UDATE                         		DATE		     NULL,
     FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
     FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
=======
CREATE TABLE REPLY(
		REPLYNO                       		NUMBER(10)		 NOT NULL       PRIMARY KEY,
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
		REVIEWNO                      		NUMBER(10)		 NOT NULL,
		REPLYCONTENT                  		VARCHAR2(400)		 NOT NULL,
		REPLYRDATE                             DATE     NOT NULL,
        REPLYUDATE                             DATE     NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO)
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글 번호';
COMMENT ON COLUMN REPLY.MEMBERNO is '회원 번호';
COMMENT ON COLUMN REPLY.REVIEWNO is '리뷰 번호';
COMMENT ON COLUMN REPLY.REPLYCONTENT is '댓글 내용';
<<<<<<< HEAD
COMMENT ON COLUMN REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY.UDATE is '수정일';


--ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_PK PRIMARY KEY (REPLYNO);
--ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK0 FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO);
--ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK1 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
=======
COMMENT ON COLUMN REPLY.REPLYRDATE is '댓글 등록일';
COMMENT ON COLUMN REPLY.REPLYUDATE is '댓글 수정일';

-- ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_PK PRIMARY KEY (REPLYNO);
-- ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK0 FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO);
-- ALTER TABLE REPLY ADD CONSTRAINT IDX_REPLY_FK1 FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO);
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f

DROP SEQUENCE reply_seq;

CREATE SEQUENCE reply_seq
  START WITH 1             -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
<<<<<<< HEAD
  -- 등록: 1건 이상
INSERT INTO REPLY( replyno, memberno, reviewno, replycontent,rdate)
    
VALUES (reply_seq.nextval, 1, 1, 'testcontent', sysdate);

-- 리뷰당 전체 목록
SELECT  replyno, memberno, reviewno, replycontent, rdate, udate
    
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
=======
-- 등록: 1건 이상
INSERT INTO REPLY(replyno, memberno, reviewno, replycontent, replyrdate)
VALUES (reply_seq.nextval, 1, 1,'리뷰1 댓글 테스트1',sysdate);

INSERT INTO REPLY(replyno, memberno, reviewno, replycontent, replyrdate)
VALUES (reply_seq.nextval, 2, 1,'리뷰1 댓글 테스트 2',sysdate);

INSERT INTO REPLY(replyno, memberno, reviewno, replycontent, replyrdate)
VALUES (reply_seq.nextval, 3, 1,'리뷰1 댓글 테스트 3',sysdate);

INSERT INTO REPLY(replyno, memberno, reviewno, replycontent, replyrdate)
VALUES (reply_seq.nextval, 1, 2,'리뷰2 댓글 테스트 1',sysdate);

INSERT INTO REPLY(replyno, memberno, reviewno, replycontent, replyrdate)
VALUES (reply_seq.nextval, 1, 3,'리뷰3 댓글 테스트 1',sysdate);

INSERT INTO REPLY(replyno, memberno, reviewno, replycontent, replyrdate)
VALUES (reply_seq.nextval, 1, 4,'리뷰4 댓글 테스트 1',sysdate);

commit;

-- 리뷰 조회 + 리뷰당 전체 댓글 목록
SELECT r.reviewno, r.memberno, r.cateno, r.reviewtitle, r.reviewcontent, r.reviewcnt, r.reviewword, 
        r.rdate, r.udate, r.reviewfile1, r.reviewfile1saved, r.reviewthumb1, r.reviewsize1,
        p.replyno, p.replycontent, p.replyrdate, p.replyudate,
        m.memberid
FROM review r, reply p, member m
WHERE r.reviewno = p.reviewno AND r.cateno=5 AND r.memberno = m.memberno;

-- 댓글 수정
UPDATE reply
SET replycontent='댓글을 수정할 수 있어요', replyudate=sysdate
WHERE replyno=1;
       
-- 댓글 삭제
DELETE FROM reply
WHERE replyno>=5;

commit;
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
        
        
        