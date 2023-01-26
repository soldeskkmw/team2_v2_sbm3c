/**********************************/
/* Table Name: 리뷰 */
/**********************************/

DROP TABLE REVIEW;
DROP TABLE REVIEW CASCADE CONSTRAINTS;


CREATE TABLE REVIEW(
      REVIEWNO                            NUMBER(10)          NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)          NOT NULL ,
      CATENO                              NUMBER(10)          NOT NULL ,
      REVIEWTITLE                         VARCHAR2(400)       NOT NULL,
      REVIEWCONTENT                       CLOB                NOT NULL,
      REPLYCNT                            NUMBER(10)          NULL,
      CNT                                 NUMBER(30)          NULL,
      REVIEWWORD                          VARCHAR2(400)       NULL ,
      RDATE                               DATE                NOT NULL,
      UDATE                               DATE                NULL ,
      REVIEWFILE1                         VARCHAR2(200)       NULL ,
      REVIEWFILE1SAVED                    VARCHAR2(400)       NULL ,
      REVIEWTHUMB1                        VARCHAR2(200)       NULL ,
      REVIEWSIZE1                         NUMBER(10)          NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (CATENO) REFERENCES CATE (CATENO)
);

COMMENT ON TABLE REVIEW is '리뷰';
COMMENT ON COLUMN REVIEW.REVIEWNO is '리뷰 번호';
COMMENT ON COLUMN REVIEW.MEMBERNO is '회원 번호';
COMMENT ON COLUMN REVIEW.CATENO is '카테고리 번호';
COMMENT ON COLUMN REVIEW.REVIEWTITLE is '리뷰 제목';
COMMENT ON COLUMN REVIEW.REVIEWCONTENT is '리뷰 내용';
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
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
commit;

  
  
  -- 등록: 1건 이상

INSERT INTO REVIEW( reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, cnt, reviewword,
 rdate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1)
    
VALUES (review_seq.nextval, 1, 6, 'testtitle', 'testcontent', 1, 1, 'testreword', sysdate, null, null, null, null);

-- 포스트당 전체 목록
SELECT  reviewno, memberno, cateno, reviewtitle, reviewcontent, replycnt, cnt, reviewword,
 rdate, udate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1
    
FROM review
WHERE postno = 1
ORDER BY reviewno DESC;


-- 검색 목록
SELECT reviewno, reviewtitle,reviewstar,goodcnt, replycnt,cnt, rdate, udate
FROM review
WHERE reviewtitle LIKE 'updatesttitle1'
ORDER BY reviewno ASC;

-- 검색 + 페이징 목록

-- 조회
SELECT  reviewno, memberno, postno, reviewtitle, reviewcontent, reviewstar,goodcnt, replycnt, cnt, reviewword,
 rdate, udate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1
    
FROM review
WHERE reviewno=1;
-- 수정
UPDATE review 

SET reviewtitle='updatesttitle1' ,reviewcontent='updatecontent1',
RDATE='2022-11-15 12:38:01', UDATE='2022-11-16 12:38:01', reviewfile1='testfile.jpg',
reviewfile1saved='testfile.jpg',reviewthumb1='testfile.jpg',reviewsize1=1

WHERE reviewno=1;

commit;

    --제목수정
UPDATE review SET reviewtitle='updatesttitle' WHERE reviewno=1;

commit;
    --내용수정
    UPDATE review SET reviewcontent='updatecontent' WHERE reviewno=1;

    commit;
    --좋아요 수정
    UPDATE review SET reviewgood='Y' WHERE reviewno=1;

    commit;

    UPDATE review SET reviewgood='N' WHERE reviewno=1;

    commit;
    --댓글 갯수 수정
    UPDATE review SET replycnt= replycnt+1 WHERE reviewno=1;

    commit;
    
    UPDATE review SET replycnt= replycnt-1 WHERE reviewno=1;

    commit;
    --조회수 수정
    UPDATE review SET cnt= cnt+1 WHERE reviewno=1;

    commit;
    
    UPDATE review SET cnt= cnt-1 WHERE reviewno=1;

    commit;
    --해시태그 수정
    UPDATE review SET reviewword='updateword'  WHERE reviewno=1;

    commit;
    
-- 삭제
DELETE FROM REVIEW
WHERE reviewno=1;
commit
-- 레코드 갯수
SELECT COUNT(*) as cnt FROM review;

-- FK 컬럼 기준 카운트, 특정 그룹에 속한 레코드 갯수 산출

-- FK 컬럼 기준 삭제, 특정 그룹에 속한 레코드 모두 삭제

