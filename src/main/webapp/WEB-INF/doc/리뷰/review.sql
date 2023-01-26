/**********************************/
/* Table Name: 리뷰 */
/**********************************/

DROP TABLE REVIEW;
DROP TABLE REVIEW CASCADE CONSTRAINTS;

CREATE TABLE REVIEW(
<<<<<<< HEAD
      REVIEWNO                            NUMBER(10)          NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)          NOT NULL ,
      CATENO                              NUMBER(10)          NOT NULL ,
      REVIEWTITLE                         VARCHAR2(400)       NOT NULL,
      REVIEWCONTENT                       CLOB                NOT NULL,
      REPLYCNT                            NUMBER(10)          NULL,
      CNT                                 NUMBER(30)          NULL,
=======
      REVIEWNO                            NUMBER(10)       NOT NULL       PRIMARY KEY,
      MEMBERNO                            NUMBER(10)       NOT NULL ,
      CATENO                              NUMBER(10)       NOT NULL ,
      REVIEWTITLE                         VARCHAR2(400)       NOT NULL,
      REVIEWCONTENT                       CLOB       NOT NULL,
      REVIEWCNT                           NUMBER(30)    DEFAULT 0   NOT NULL,
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
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
<<<<<<< HEAD
COMMENT ON COLUMN REVIEW.CATENO is '카테고리 번호';
COMMENT ON COLUMN REVIEW.REVIEWTITLE is '리뷰 제목';
COMMENT ON COLUMN REVIEW.REVIEWCONTENT is '리뷰 내용';
COMMENT ON COLUMN REVIEW.REPLYCNT is '댓글수';
COMMENT ON COLUMN REVIEW.CNT is '리뷰 조회수';
=======
COMMENT ON COLUMN REVIEW.CATENO is '관광지 카테고리 번호';
COMMENT ON COLUMN REVIEW.REVIEWTITLE is '리뷰 제목';
COMMENT ON COLUMN REVIEW.REVIEWCONTENT is '리뷰 내용';
COMMENT ON COLUMN REVIEW.REVIEWCNT is '리뷰 조회수';
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
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
  
<<<<<<< HEAD
commit;

=======
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
  
-- 등록: 1건 이상  
INSERT INTO review(reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, 
                    rdate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1)    
VALUES (review_seq.nextval, 1, 5, '카테고리5 리뷰 테스트', '리뷰 테스트 내용', 0, '리뷰 태그, 태그 테스트, 워드 테스트',
                    sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

<<<<<<< HEAD
INSERT INTO REVIEW( reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, cnt, reviewword,
 rdate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1)
    
VALUES (review_seq.nextval, 1, 6, 'testtitle', 'testcontent', 1, 1, 'testreword', sysdate, null, null, null, null);

-- 포스트당 전체 목록
SELECT  reviewno, memberno, cateno, reviewtitle, reviewcontent, replycnt, cnt, reviewword,
 rdate, udate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1
    
=======
INSERT INTO review(reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, 
                    rdate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1)    
VALUES (review_seq.nextval, 2, 5, '카테고리5 리뷰 테스트', '메리크리스마스', 0, '리뷰 태그, 태그 테스트, 크리스마스',
                    sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);
                    
INSERT INTO review(reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, 
                    rdate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1)    
VALUES (review_seq.nextval, 1, 6, '카테고리6 리뷰 테스트', '리뷰 테스트 내용', 0, '리뷰 태그, 태그 테스트, 워드 테스트',
                    sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

INSERT INTO review(reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, 
                    rdate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1)    
VALUES (review_seq.nextval, 2, 6, '카테고리6 리뷰 테스트', '메리크리스마스', 0, '리뷰 태그, 태그 테스트, 크리스마스',
                    sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);

SELECT reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, 
        rdate, udate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
FROM review
ORDER BY reviewno;

--카테고리별 리뷰 전체 목록
SELECT reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, 
        rdate, udate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1
FROM review
WHERE cateno = 6
ORDER BY reviewno;

-- 검색 목록
SELECT reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, rdate, udate
FROM review
WHERE reviewtitle LIKE '%테스트%';

-- 검색 + 페이징 목록(구현 권장)
-- 검색, 정렬 -> rownum -> 분할
SELECT reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, rdate, udate r
FROM(
     SELECT  reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, rdate, udate, rownum as r
     FROM (
          SELECT reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, rdate, udate
          FROM review
          WHERE reviewtitle LIKE '%테스트%'
          ORDER BY reviewno ASC
     )  
)
WHERE r >= 1 AND r <= 3;

-- 리뷰 조회(안쓸거임 reply에서 댓글+리뷰 한번에 조회, 리뷰에는 read파일 X)
SELECT reviewno, memberno, cateno, reviewtitle, reviewcontent, reviewcnt, reviewword, 
        rdate, udate, reviewfile1, reviewfile1saved, reviewthumb1,reviewsize1
FROM review
WHERE reviewno=1;

-- 리뷰 수정
UPDATE review
SET reviewtitle='크리스마스 리뷰테스트!', UDATE=sysdate
WHERE reviewno=3;

    POSTNO    ADMINNO     CATENO POSTTITLE                                                                                                                                                                                                                                                                                                                                                                                                        POSTCONTENT                                                                      RDATE               UDATE                 POSTSTAR    POSTCNT
---------- ---------- ---------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------------------------------------------------------------- ------------------- ------------------- ---------- ----------
         2          3          6 요즘 대세는 스페셜티 커피! 서울 스페셜티 커피 전문점 4                                                                                                                                                                                                                                                                                                                                                           좋은 원두가 주는 다채로운 풍미의 향연, 일반 커피와 얼마나 다른지 궁금하다면?     2022-11-16 11:43:19 2022-11-16 11:57:22          5          0

-- 리뷰 파일 수정
UPDATE review
SET reviewfile1='cafe.jpg', reviewfile1saved='cafe_1.jpg', reviewthumb1='cafe_1_t.jpg', reviewsize1=2000
WHERE reviewno=3;

-- 리뷰 조회수 증가
UPDATE review
SET reviewcnt = reviewcnt + 1
WHERE reviewno=1;

commit;
    
-- 리뷰 삭제
DELETE FROM review
WHERE reviewno=3;

commit;

-- 레코드 갯수
SELECT COUNT(*) as cnt FROM review;

