DROP TABLE post_rating CASCADE CONSTRAINTS;
DROP TABLE post_rating;

/**********************************/
/* Table Name: 관광지 데이터 평점 */
/**********************************/
CREATE TABLE POST_RATING(
    RATINGNO NUMERIC(10) NOT NULL PRIMARY KEY,
    MEMBERNO NUMERIC(10) NOT NULL,
    POSTNO NUMERIC(10) NOT NULL,
    RATING NUMERIC(10),
    RDATE DATE NOT NULL,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (POSTNO) REFERENCES POST (POSTNO)
);

COMMENT ON TABLE POST_RATING is '관광지 데이터 평점';
COMMENT ON COLUMN POST_RATING.RATINGNO is '평점 번호';
COMMENT ON COLUMN POST_RATING.MEMBERNO is '회원 번호';
COMMENT ON COLUMN POST_RATING.POSTNO is '관광지 데이터 번호';
COMMENT ON COLUMN POST_RATING.RATING is '평점';
COMMENT ON COLUMN POST_RATING.RDATE is '등록일';

DROP SEQUENCE post_rating_seq;

CREATE SEQUENCE post_rating_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;
  
-- 1. 등록
INSERT INTO post_rating(ratingno, memberno, postno, rating, rdate)
VALUES (post_rating_seq.nextval, 1, 1, 1, sysdate);
 
INSERT INTO post_rating(ratingno, memberno, postno, rating, rdate)
VALUES (post_rating_seq.nextval, 1, 2, 3, sysdate);

COMMIT;

-- 2. 목록
SELECT ratingno, memberno, postno, rating, rdate
FROM post_rating
ORDER BY postno ASC;
     
-- 3. 조회
SELECT ratingno, memberno, postno, rating, rdate
FROM post_rating
WHERE postno = 1;

-- 6. 평점 변경
--  1) 회원 검사
SELECT COUNT(memberno) as cnt
FROM post_rating
WHERE memberno=1;
 
--  2) 평점 수정
UPDATE post_rating
SET rating='4'
WHERE memberno=1 AND postno=2;

COMMIT;

-- 5. 삭제
--  1) 모두 삭제
DELETE FROM post_rating;
 
--  2) 특정 회원 삭제
DELETE FROM post_rating
WHERE memberno=1 AND postno=2;

COMMIT;

  -- *************** FK 자식 테이블일 경우 구현 시작 ***************
-- FK 컬럼 기준 카운트, 특정 그룹에 속한 레코드 갯수 산출
-- 1번 회원이 등록한 평점 갯수는?
SELECT COUNT(*) as cnt FROM post_rating WHERE memberno=1;

-- 1번 관광지 데이터의 평점 갯수는?
SELECT COUNT(*) as cnt FROM post_rating WHERE postno=1;

-- FK 컬럼 기준 삭제, 특정 그룹에 속한 레코드 모두 삭제
-- 1번 회원이 등록한 평점 삭제
DELETE FROM post_rating WHERE memberno=1;

-- 1번 관광지 데이터의 평점 삭제
DELETE FROM post_rating WHERE postno=1;

-- 부모 테이블과의 JOIN (관광지 데이터)
SELECT p.postno, p.adminno, p.cateno, p.posttitle, p.postcontent, p.postword, p.rdate, p.poststar, p.postcnt, p.postfile1, p.postfile1saved, p.postthumb1, p.postsize1,
           r.ratingno, r.memberno, r.postno, r.rating, r.rdate
FROM post p, post_rating r
WHERE p.postno = r.postno;

-- 부모 테이블과의 JOIN (회원)
SELECT m.memberno, m.memberid, m.memberpasswd, m.membername, m.tel, m.mdate,
           r.ratingno, r.memberno, r.postno, r.rating, r.rdate
FROM member m, post_rating r
WHERE m.memberno = r.memberno;

-- *************** FK 자식 테이블일 경우 구현 종료 ***************

UPDATE post_rating
SET rating = rating + 1
WHERE ratingno = 1;

