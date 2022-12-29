/**********************************/
/* Table Name: 관광지 카테고리 */
/**********************************/
DROP TABLE cate CASCADE CONSTRAINTS;
DROP TABLE cate;

CREATE TABLE CATE(
      cateno                              NUMBER(10)       NOT NULL       PRIMARY KEY,
      catename                            VARCHAR2(30)       NOT NULL,
      rdate                               DATE     NOT NULL,
      udate                             DATE     NULL,
      seqno                            NUMBER(10)   DEFAULT 0       NOT NULL,
      visible                            CHAR(1)      DEFAULT 'N'     NOT NULL -- Y, N
);

COMMENT ON TABLE CATE is '관광지 카테고리';
COMMENT ON COLUMN CATE.cateno is '관광지 카테고리 번호';
COMMENT ON COLUMN CATE.catename is '관광지 카테고리 이름';
COMMENT ON COLUMN cate.rdate is '등록일';
COMMENT ON COLUMN cate.udate is '수정일';
COMMENT ON COLUMN cate.seqno is '출력 순서';
COMMENT ON COLUMN cate.visible is '출력 모드';

DROP SEQUENCE cate_seq;

CREATE SEQUENCE cate_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지

-- 등록
INSERT INTO cate(cateno, catename, rdate, seqno, visible)
VALUES(cate_seq.nextval, 'Festival', sysdate, 1, 'Y');

INSERT INTO cate(cateno, catename, rdate, seqno, visible)
VALUES(cate_seq.nextval, 'Hotels', sysdate, 2, 'Y');

INSERT INTO cate(cateno, catename, rdate, seqno, visible)
VALUES(cate_seq.nextval, 'Trip', sysdate, 3, 'Y');

INSERT INTO cate(cateno, catename, rdate, seqno, visible)
VALUES(cate_seq.nextval, 'Life', sysdate, 4, 'Y');

INSERT INTO cate(cateno, catename, rdate, seqno, visible)
VALUES(cate_seq.nextval, 'Food', sysdate, 5, 'Y');

INSERT INTO cate(cateno, catename, rdate, seqno, visible)
VALUES(cate_seq.nextval, 'Cafe', sysdate, 6, 'Y');

INSERT INTO cate(cateno, catename, rdate, seqno, visible)
VALUES(cate_seq.nextval, 'tt', sysdate, 7, 'Y');

-- SELECT 목록
-- PK 컬럼은 최초 등록시 값이 sequence에의해 고정됨.
SELECT cateno, catename, rdate, udate, seqno, visible
FROM cate
ORDER BY cateno ASC;

-- SELECT 조회
SELECT cateno, catename, rdate, udate, seqno, visible
FROM cate
WHERE cateno = 1;

-- UPDATE
-- 최초 등록 날짜 유지, 수정 날짜 추가
UPDATE cate
SET cateno=6, udate=sysdate
WHERE cateno=22;

commit;

-- DELETE
DELETE FROM cate
WHERE cateno=23;

-- 모든 레코드 삭제
DELETE FROM cate;

commit;

-- COUNT(*)
SELECT COUNT(*) as cnt
FROM cate;

-- seqno 출력 순서 기준 목록
SELECT cateno, catename, rdate, udate, seqno
FROM cate
ORDER BY seqno ASC;

-- 출력 순서 올림(상향, 10 등 -> 1 등), seqno: 10 -> 1
UPDATE cate
SET seqno = seqno - 1
WHERE cateno = 1;

-- 출력 순서 내림(하향, 1 등 -> 10 등), seqno: 1 -> 10
UPDATE cate
SET seqno = seqno + 1
WHERE cateno = 1;

-- 출력 모드의 변경
UPDATE cate
SET visible = 'Y'
WHERE cateno=1;

UPDATE cate
SET visible = 'N'
WHERE cateno=1;

-- visible이 'Y'인 카테고리 출력하기
SELECT cateno, catename, rdate, udate, seqno, visible
FROM cate
WHERE visible='Y'
ORDER BY seqno ASC;

SELECT * FROM cate;
