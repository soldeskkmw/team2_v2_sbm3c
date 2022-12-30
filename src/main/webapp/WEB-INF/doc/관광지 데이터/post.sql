DROP TABLE attachfile;
DROP TABLE post CASCADE CONSTRAINTS;
DROP TABLE post;

CREATE TABLE POST(
        POSTNO                              NUMBER(10)       NOT NULL       PRIMARY KEY,
        ADMINNO                             NUMBER(10)       NOT NULL,
        CATENO                              NUMBER(10)       NOT NULL,
        POSTTITLE                           VARCHAR2(400)       NOT NULL,
        POSTCONTENT                         CLOB       NOT NULL,
        POSTWORD                            VARCHAR2(400)       NULL ,
        RDATE                               DATE       NOT NULL,
        UDATE                               DATE       NULL ,
        POSTSTAR                            NUMBER(2,1)       NOT NULL,         -- 별점
        POSTCNT                             NUMBER(30)       DEFAULT 0    NOT NULL,       -- 게시글 조회수
        POSTFILE1                           VARCHAR2(200)       NULL ,          -- 원본 파일명 image
        POSTFILE1SAVED                      VARCHAR2(400)       NULL ,      -- 저장된 파일명, image
        POSTTHUMB1                          VARCHAR2(200)       NULL ,      -- preview image
        POSTSIZE1                           NUMBER(10)   DEFAULT 0    NULL,        
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
  
-- ADMIN 테이블
--   ADMINNO ADMINID              ADMINPASSWD          ADMINNAME           
------------ -------------------- -------------------- --------------------
--         1 admin1               1234                 김민욱              
--         2 admin2               1234                 이승원              
--         3 admin3               1234                 장지은    
         
-- CATE 테이블
--    CATENO CATENAME                      
------------ ------------------------------
--         1 Hotels                        
--         2 Festival                      
--         3 Trip                          
--         4 Life                          
--         5 Food                          
--         6 Cafe                          

-- 등록
INSERT INTO post(postno, adminno, cateno, posttitle, postcontent, postword, 
                    rdate, poststar, postcnt, postfile1, postfile1saved, postthumb1, postsize1)
VALUES(post_seq.nextval, 3, 6, '9시 이전 오픈 얼리버드 서울 카페', '일찍 일어나는 사람이 맛있는 커피를 마신다.', '서울 카페, 신상 카페, 감성 카페, 디저트, 서촌, 샌드위치, 장충동, 동대입구, 충무로, 구움과자, 바스크 치즈케이크, 합정, 망원동, 에스프레소 바, 충정로역, 서소문역사공원, 시청역, 카페투어, 카페 추천, 필터커피',
                    sysdate, 5, 0, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000);
                    
INSERT INTO post(postno, adminno, cateno, posttitle, postcontent, postword, 
                    rdate, poststar, postcnt, postfile1, postfile1saved, postthumb1, postsize1)
VALUES(post_seq.nextval, 3, 6, '요즘 대세는 스페셜티 커피! 서울 스페셜티 커피 전문점 4곳', '좋은 원두가 주는 다채로운 풍미의 향연, 일반 커피와 얼마나 다른지 궁금하다면?', '스페셜티 전문점, 디저트, 시그니처 커피, 스페셜티 커피, 커피 추천, 커피 맛집, 스페셜티 커피 추천, 서울 카페, 스페셜티 카페, 카페 추천, 카페 갈 곳, 데이트, 데이트 코스 추천, 카페 데이트, 바리스타, 원두, 신사동, 신사, 양재, 문래, 용산',
                    sysdate, 4.5, 0, 'special.jpg', 'special_1.jpg', 'special_t.jpg', 1000);
                    
INSERT INTO post(postno, adminno, cateno, posttitle, postcontent, postword, 
                    rdate, poststar, postcnt, postfile1, postfile1saved, postthumb1, postsize1)
VALUES(post_seq.nextval, 3, 6, '가을 하늘을 볼 수 있는 서울 하늘 뷰 맛집 4곳', '짧은 가을을 만끽하기 좋은 방법 중 하나는 푸른 하늘을 두 눈에 가득 담는 것. 하늘멍 때리기 좋은 서울 하늘 뷰 맛집 네 곳을 소개한다.', '뷰 맛집, 옥상 뷰, 서울 뷰, 전망, 하늘 뷰, 남산 뷰, 하늘 사진, 일몰 사진, 하늘 사진 찍기 좋은 곳, 서울 카페, 감성 카페, 와인바, 상수, 상수역, 상수동, 창신역, 한성대, 동묘, 혜화역, 위스키, 남산 카페, 남산, 남산 케이블카, 회현역, 후암동, 찻집, 부암동, 상명대, 서촌, 경복궁',
                    sysdate, 4.0, 0, 'view.jpg', 'view_1.jpg', 'view_t.jpg', 1000);
                    
COMMIT;

-- 전체 목록
SELECT postno, adminno, cateno, posttitle, postcontent, postword, rdate, udate, poststar, postcnt, postfile1, postfile1saved, postthumb1, postsize1
FROM post
ORDER BY postno ASC;

   POSTNO    ADMINNO     CATENO POSTTITLE                                                                                                                                                                                                                                                                                                                                                                                                        POSTCONTENT                                                                      POSTWORD                                                                                                                                                                                                                                                                                                                                                                                                         RDATE               UDATE                 POSTSTAR    POSTCNT POSTFILE1                                                                                                                                                                                                POSTFILE1SAVED                                                                                                                                                                                                                                                                                                                                                                                                   POSTTHUMB1                                                                                                                                                                                                POSTSIZE1
---------- ---------- ---------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------------------------------------------------------------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ------------------- ------------------- ---------- ---------- -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ----------
         1          3          6 9시 이전 오픈 얼리버드 서울 카페                                                                                                                                                                                                                                                                                                                                                                                 일찍 일어나는 사람이 맛있는 커피를 마신다.                                       서울 카페, 신상 카페, 감성 카페, 디저트, 서촌, 샌드위치, 장충동, 동대입구, 충무로, 구움과자, 바스크 치즈케이크, 합정, 망원동, 에스프레소 바, 충정로역, 서소문역사공원, 시청역, 카페투어, 카페 추천, 필터커피                                                                                                                                                                                                     2022-11-16 11:43:19                              5          0 space.jpg                                                                                                                                                                                                space_1.jpg                                                                                                                                                                                                                                                                                                                                                                                                      space_t.jpg                                                                                                                                                                                                    1000
         2          3          6 요즘 대세는 스페셜티 커피! 서울 스페셜티 커피 전문점 4곳                                                                                                                                                                                                                                                                                                                                                         좋은 원두가 주는 다채로운 풍미의 향연, 일반 커피와 얼마나 다른지 궁금하다면?     스페셜티 전문점, 디저트, 시그니처 커피, 스페셜티 커피, 커피 추천, 커피 맛집, 스페셜티 커피 추천, 서울 카페, 스페셜티 카페, 카페 추천, 카페 갈 곳, 데이트, 데이트 코스 추천, 카페 데이트, 바리스타, 원두, 신사동, 신사, 양재, 문래, 용산                                                                                                                                                                          2022-11-16 11:48:30                              5          0 special.jpg                                                                                                                                                                                              special_1.jpg                                                                                                                                                                                                                                                                                                                                                                                                    special_t.jpg                                                                                                                                                                                                  1000
         3          3          6 가을 하늘을 볼 수 있는 서울 하늘 뷰 맛집 4곳                                                                                                                                                                                                                                                                                                                                                                     짧은 가을을 만끽하기 좋은 방법 중 하나는 푸른 하늘을 두 눈에 가득 담는 것. 하늘멍 때리기 좋은 서울 하늘 뷰 맛집 네 곳을 소개한다. 뷰 맛집, 옥상 뷰, 서울 뷰, 전망, 하늘 뷰, 남산 뷰, 하늘 사진, 일몰 사진, 하늘 사진 찍기 좋은 곳, 서울 카페, 감성 카페, 와인바, 상수, 상수역, 상수동, 창신역, 한성대, 동묘, 혜화역, 위스키, 남산 카페, 남산, 남산 케이블카, 회현역, 후암동, 찻집, 부암동, 상명대, 서촌, 경복궁                                                                                                                                    2022-11-16 11:52:18                              4          0 view.jpg                                                                                                                                                                                                 view_1.jpg                                                                                                                                                                                                                                                                                                                                                                                                       view_t.jpg                                                                                                                                                                                                     1000

-- 조회
SELECT postno, adminno, cateno, posttitle, postcontent, rdate, udate, poststar, postcnt
FROM post
WHERE postno=1;

    POSTNO    ADMINNO     CATENO POSTTITLE                                                                                                                                                                                                                                                                                                                                                                                                        POSTCONTENT                                                                      RDATE               UDATE                 POSTSTAR    POSTCNT
---------- ---------- ---------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------------------------------------------------------------- ------------------- ------------------- ---------- ----------
         1          3          6 9시 이전 오픈 얼리버드 서울 카페 
         
-- 글수정
UPDATE post
SET posttitle='요즘 대세는 스페셜티 커피! 서울 스페셜티 커피 전문점 4', RDATE='2022-11-16 11:43:19 ', UDATE=sysdate
WHERE postno=2;

    POSTNO    ADMINNO     CATENO POSTTITLE                                                                                                                                                                                                                                                                                                                                                                                                        POSTCONTENT                                                                      RDATE               UDATE                 POSTSTAR    POSTCNT
---------- ---------- ---------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------------------------------------------------------------- ------------------- ------------------- ---------- ----------
         2          3          6 요즘 대세는 스페셜티 커피! 서울 스페셜티 커피 전문점 4                                                                                                                                                                                                                                                                                                                                                           좋은 원두가 주는 다채로운 풍미의 향연, 일반 커피와 얼마나 다른지 궁금하다면?     2022-11-16 11:43:19 2022-11-16 11:57:22          5          0

-- 파일 수정
UPDATE post
SET postfile1='cafe.jpg', postfile1saved='cafe_1.jpg', postthumb1='cafe_1_t.jpg', postsize1=3000
WHERE postno=1 and adminno=3;

-- 조회수 증가
UPDATE post
SET postcnt = postcnt + 1
WHERE postno=1;

-- 삭제
DELETE FROM post
WHERE postno=4;

-- 모든 레코드 삭제
DELETE FROM post;

-- 범위 내 레코드 삭제
DELETE FROM post
WHERE postno=12 AND postno <= 41;

commit;

-- 레코드 갯수
SELECT COUNT(*) as cnt FROM post;

-- *************** FK 자식 테이블일 경우 구현 시작 ***************
-- FK 컬럼 기준 카운트, 특정 그룹에 속한 레코드 갯수 산출
-- 1번 관리자가 등록한 공지사항 갯수는?
SELECT COUNT(*) as cnt FROM post WHERE adminno=3;

-- 1번 공지사항 카테고리의 공지사항 등록 갯수는?
SELECT COUNT(*) as cnt FROM post WHERE cateno=6;

-- FK 컬럼 기준 삭제, 특정 그룹에 속한 레코드 모두 삭제
-- 1번 관리자가 등록한 공지사항 삭제
DELETE FROM post WHERE adminno=1;

-- 1번 공지사항 카테고리의 공지사항 삭제
DELETE FROM post WHERE cateno=6;

-- 부모 테이블과의 JOIN
SELECT a.adminno, a.adminid, a.adminpasswd, a.adminname,
          p.postno, p.adminno, p.cateno, p.posttitle, p.postcontent, p.postword, p.rdate, p.poststar, p.postcnt, p.postfile1, p.postfile1saved, p.postthumb1, p.postsize1
FROM admin a, post p
WHERE a.adminno = p.adminno;

SELECT c.cateno, c.catename,
          p.postno, p.adminno, p.cateno, p.posttitle, p.postcontent, p.postword, p.rdate, p.poststar, p.postcnt, p.postfile1, p.postfile1saved, p.postthumb1, p.postsize1
FROM cate c, post p
WHERE c.cateno = p.cateno;

-- *************** FK 자식 테이블일 경우 구현 종료 ***************

-- 검색 목록(구현 권장)
SELECT postno, adminno, cateno, posttitle, postcontent, postword, rdate, udate, poststar, postcnt
FROM post
WHERE postword LIKE '%디저트%'
ORDER BY postno ASC;

    POSTNO    ADMINNO     CATENO POSTTITLE                                                                                                                                                                                                                                                                                                                                                                                                        POSTCONTENT                                                                      POSTWORD                                                                                                                                                                                                                                                                                                                                                                                                         RDATE               UDATE                 POSTSTAR    POSTCNT
---------- ---------- ---------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------------------------------------------------------------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ------------------- ------------------- ---------- ----------
         1          3          6 9시 이전 오픈 얼리버드 서울 카페                                                                                                                                                                                                                                                                                                                                                                                 일찍 일어나는 사람이 맛있는 커피를 마신다.                                       서울 카페, 신상 카페, 감성 카페, 디저트, 서촌, 샌드위치, 장충동, 동대입구, 충무로, 구움과자, 바스크 치즈케이크, 합정, 망원동, 에스프레소 바, 충정로역, 서소문역사공원, 시청역, 카페투어, 카페 추천, 필터커피                                                                                                                                                                                                     2022-11-16 11:43:19                              5          0
         2          3          6 요즘 대세는 스페셜티 커피! 서울 스페셜티 커피 전문점 4                                                                                                                                                                                                                                                                                                                                                           좋은 원두가 주는 다채로운 풍미의 향연, 일반 커피와 얼마나 다른지 궁금하다면?     스페셜티 전문점, 디저트, 시그니처 커피, 스페셜티 커피, 커피 추천, 커피 맛집, 스페셜티 커피 추천, 서울 카페, 스페셜티 카페, 카페 추천, 카페 갈 곳, 데이트, 데이트 코스 추천, 카페 데이트, 바리스타, 원두, 신사동, 신사, 양재, 문래, 용산                                                                                                                                                                          2022-11-16 11:43:19 2022-11-16 11:57:22          5          0


-- 검색 + 페이징 목록(구현 권장)
-- 검색, 정렬 -> rownum -> 분할

SELECT postno, adminno, cateno, posttitle, postcontent, postword, rdate, udate, poststar, postcnt, r
FROM(
     SELECT postno, adminno, cateno, posttitle, postcontent, postword, rdate, udate, poststar, postcnt, rownum as r
     FROM (
          SELECT postno, adminno, cateno, posttitle, postcontent, postword, rdate, udate, poststar, postcnt 
          FROM post
          WHERE postword LIKE '%디저트%'
          ORDER BY postno ASC
     )  
)
WHERE r >= 1 AND r <= 3;

    POSTNO    ADMINNO     CATENO POSTTITLE                                                                                                                                                                                                                                                                                                                                                                                                        POSTCONTENT                                                                      POSTWORD                                                                                                                                                                                                                                                                                                                                                                                                         RDATE               UDATE                 POSTSTAR    POSTCNT          R
---------- ---------- ---------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- -------------------------------------------------------------------------------- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ------------------- ------------------- ---------- ---------- ----------
         1          3          6 9시 이전 오픈 얼리버드 서울 카페                                                                                                                                                                                                                                                                                                                                                                                 일찍 일어나는 사람이 맛있는 커피를 마신다.                                       서울 카페, 신상 카페, 감성 카페, 디저트, 서촌, 샌드위치, 장충동, 동대입구, 충무로, 구움과자, 바스크 치즈케이크, 합정, 망원동, 에스프레소 바, 충정로역, 서소문역사공원, 시청역, 카페투어, 카페 추천, 필터커피                                                                                                                                                                                                     2022-11-16 11:43:19                              5          0          1
         2          3          6 요즘 대세는 스페셜티 커피! 서울 스페셜티 커피 전문점 4                                                                                                                                                                                                                                                                                                                                                           좋은 원두가 주는 다채로운 풍미의 향연, 일반 커피와 얼마나 다른지 궁금하다면?     스페셜티 전문점, 디저트, 시그니처 커피, 스페셜티 커피, 커피 추천, 커피 맛집, 스페셜티 커피 추천, 서울 카페, 스페셜티 카페, 카페 추천, 카페 갈 곳, 데이트, 데이트 코스 추천, 카페 데이트, 바리스타, 원두, 신사동, 신사, 양재, 문래, 용산                                                                                                                                                                          2022-11-16 11:43:19 2022-11-16 11:57:22          5          0          2

commit;
