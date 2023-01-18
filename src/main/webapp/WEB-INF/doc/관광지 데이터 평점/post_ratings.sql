DROP TABLE post_ratings CASCADE CONSTRAINTS;
DROP TABLE post_ratings;

/**********************************/
/* Table Name: 관광지 데이터 평점 */
/**********************************/
CREATE TABLE POST_RATINGS(
		RATINGNO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		MEMBERNO                      		NUMBER(10)		 NOT NULL,
		POSTNO                        		NUMBER(10)		 NOT NULL,
		RATINGS                       		NUMBER(3,2)		 DEFAULT 1		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
  FOREIGN KEY (POSTNO) REFERENCES POST (POSTNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE POST_RATINGS is '관광지 데이터 평점';
COMMENT ON COLUMN POST_RATINGS.RATINGNO is '평점 번호';
COMMENT ON COLUMN POST_RATINGS.MEMBERNO is '회원 번호';
COMMENT ON COLUMN POST_RATINGS.POSTNO is '관광지 데이터 번호';
COMMENT ON COLUMN POST_RATINGS.RATINGS is '평점';
COMMENT ON COLUMN POST_RATINGS.RDATE is '등록일';

DROP SEQUENCE post_ratings_seq;

CREATE SEQUENCE post_ratings_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;
  
-- 1. 등록
INSERT INTO post_ratings(ratingno, memberno, postno, ratings, rdate)
VALUES (post_ratings_seq.nextval, 1, 1, 2, sysdate);
 
INSERT INTO post_ratings(ratingno, memberno, postno, ratings, rdate)
VALUES (post_ratings_seq.nextval, 1, 3, 4, sysdate);

INSERT INTO post_ratings(ratingno, memberno, postno, ratings, rdate)
VALUES (post_ratings_seq.nextval, 2, 1, 5, sysdate);
 
INSERT INTO post_ratings(ratingno, memberno, postno, ratings, rdate)
VALUES (post_ratings_seq.nextval, 2, 3, 3, sysdate);

COMMIT;

-- 2. 목록
SELECT ratingno, memberno, postno, ratings, rdate
FROM post_ratings
ORDER BY postno ASC;
     
-- 3. 조회
SELECT ratingno, memberno, postno, ratings, rdate
FROM post_ratings
WHERE postno = 1;

-- 4. 평점 변경
--  1) 회원 검사
SELECT COUNT(memberno) as cnt
FROM post_ratings
WHERE memberno=1;
 
--  2) 평점 수정
UPDATE post_ratings
SET ratings='1.5'
WHERE memberno=1 AND postno=1;

-- 5. 특정 post의 평점 평균 산출
SELECT AVG(ratings)
FROM post_ratings
WHERE postno=1;

COMMIT;

-- 5. 삭제
--  1) 모두 삭제
DELETE FROM post_ratings;
 
--  2) 특정 회원 삭제
DELETE FROM post_ratings
WHERE memberno=1 AND postno=2;

COMMIT;

  -- *************** FK 자식 테이블일 경우 구현 시작 ***************
-- FK 컬럼 기준 카운트, 특정 그룹에 속한 레코드 갯수 산출
-- 1번 회원이 등록한 평점 갯수는?
SELECT COUNT(*) as cnt FROM post_ratings WHERE memberno=1;

-- 1번 관광지 데이터의 평점 갯수는?
SELECT COUNT(*) as cnt FROM post_ratings WHERE postno=1;

-- FK 컬럼 기준 삭제, 특정 그룹에 속한 레코드 모두 삭제
-- 1번 회원이 등록한 평점 삭제
DELETE FROM post_ratings WHERE memberno=1;

-- 1번 관광지 데이터의 평점 삭제
DELETE FROM post_ratings WHERE postno=1;

-- 부모 테이블과의 JOIN (관광지 데이터)
SELECT p.postno, p.adminno, p.cateno, p.posttitle, p.postcontent, p.postword, p.rdate, p.poststar, p.postcnt, p.postfile1, p.postfile1saved, p.postthumb1, p.postsize1,
           r.ratingno, r.memberno, r.postno, r.ratings, r.rdate
FROM post p, post_ratings r
WHERE p.postno = r.postno;

-- 부모 테이블과의 JOIN (회원)
SELECT m.memberno, m.memberid, m.memberpasswd, m.membername, m.tel, m.mdate,
           r.ratingno, r.memberno, r.postno, r.ratings, r.rdate
FROM member m, post_ratings r
WHERE m.memberno = r.memberno;

-- *************** FK 자식 테이블일 경우 구현 종료 ***************

SELECT memberno, memberid
FROM member
WHERE memberno=1;
