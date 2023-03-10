/**********************************/
/* Table Name: 문의 카테고리 */
/**********************************/
DROP TABLE SERVICECATE;
CREATE TABLE SERVICECATE(
      SERVICECATENO NUMERIC(10) NOT NULL PRIMARY KEY,
      SERVICETYPE_CONTENT VARCHAR(50) NOT NULL,
      CNT NUMERIC(10) NOT NULL,
      ORDER_INDEX NUMERIC(10) NOT NULL,
      VISIBLE CHARACTER(1) NOT NULL
);
COMMENT ON TABLE SERVICECATE is '문의 카테고리';
COMMENT ON COLUMN SERVICECATE.SERVICECATENO is '문의 카테고리 번호';
COMMENT ON COLUMN SERVICECATE.SERVICETYPE_CONTENT is '고객센터 글 종류';
COMMENT ON COLUMN SERVICECATE.CNT is '관련 문의 수';
COMMENT ON COLUMN SERVICECATE.ORDER_INDEX is '출력순서';
COMMENT ON COLUMN SERVICECATE.VISIBLE is '출력모드';

DROP SEQUENCE SERVICECATE_seq;
CREATE SEQUENCE SERVICECATE_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지DROP SEQUENCE ADMIN_seq;


-- 등록: 1건 이상
INSERT INTO SERVICECATE (SERVICECATENO, SERVICETYPE_CONTENT, CNT, ORDER_INDEX, VISIBLE) 
VALUES (SERVICECATE_seq.nextval, '신고하기', 0, 1, 'Y');
INSERT INTO SERVICECATE (SERVICECATENO, SERVICETYPE_CONTENT, CNT, ORDER_INDEX, VISIBLE) 
VALUES (SERVICECATE_seq.nextval, '건의하기', 0, 1, 'Y');
INSERT INTO SERVICECATE (SERVICECATENO, SERVICETYPE_CONTENT, CNT, ORDER_INDEX, VISIBLE) 
VALUES (SERVICECATE_seq.nextval, '문의하기', 0, 1, 'Y');

-- 전체 목록
SELECT SERVICECATENO, SERVICETYPE_CONTENT, CNT, ORDER_INDEX, VISIBLE
FROM SERVICECATE;

-- 조회
SELECT SERVICECATENO, SERVICETYPE_CONTENT, CNT, ORDER_INDEX, VISIBLE
FROM SERVICECATE
WHERE SERVICECATENO=1;

-- 글 수정
UPDATE SERVICECATE
SET SERVICETYPE_CONTENT='확인하기', CNT=0, ORDER_INDEX=2, VISIBLE='N'
WHERE SERVICECATENO=1;

-- 삭제
DELETE FROM SERVICECATE
WHERE SERVICECATENO=1;

-- 레코드 갯수
SELECT COUNT(*)
FROM SERVICECATE;