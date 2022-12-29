/**********************************/
/* Table Name: 공지사항 */
/**********************************/

DROP TABLE notice CASCADE CONSTRAINTS;
DROP TABLE notice;

CREATE TABLE notice(
noticeno                         NUMBER(10)  NOT NULL  PRIMARY KEY,
adminno NUMBER(10) NOT NULL, --FK
noticetitle VARCHAR2(300) NOT NULL,
noticecontent CLOB NOT NULL,
cnt NUMBER(7) NOT NULL,
noticeword VARCHAR2(300) NOT NULL,
rdate DATE NOT NULL,
FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE notice is '공지사항';
COMMENT ON COLUMN notice.noticeno is '공지사항번호';
COMMENT ON COLUMN notice.noticetitle is '공지사항제목';
COMMENT ON COLUMN notice.noticecontent is '공지사항내용';
COMMENT ON COLUMN notice.cnt is '조회수';
COMMENT ON COLUMN notice.noticeword is '검색어';
COMMENT ON COLUMN notice.rdate is '등록일';

-- SEQUENCE
DROP SEQUENCE notice_seq;

CREATE SEQUENCE notice_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- CREATE
INSERT INTO notice(noticeno, adminno, noticetitle, noticecontent, cnt, noticeword, rdate)
VALUES (notice_seq.nextval, 1, '공지사항', '공지사항', 1, '공지사항', sysdate);

INSERT INTO notice(noticeno, adminno, noticetitle, noticecontent, cnt, noticeword, rdate)
VALUES (notice_seq.nextval, 1, '2022년도 12월 공지사항', '[2022학년도 서일 핵심역량 사후진단 참여 독려 요청]
1. 2022학년도 재학생 서일 핵심역량 사후진단 참여를 다음과 같이 실시하오니 재학생 참여 독려를 부탁드립니다.
  가. 내용: 2022학년도 서일 핵심역량 사후진단 참여 독려 요청
  나. 진단대상: 실시일 기준 우리학교 재학생(전공심화 포함)
  다. 진단목적
    - 서일 핵심역량 진단을 통해 학생들의 역량 파악
    - 서일 핵심역량 기반 교육운영 성과 측정 및 관리
  라. 진단기간: 2022. 12. 5.(월)~23.(금)
  마. 진단방법: 학생성공관리시스템(https://success.seoil.ac.kr/) 접속 후 진단 실시
  바. 담당: 교육혁신본부 교육혁신팀원 고동우(02-490-7598)
  사. 기타: 대학 중장기발전계획 및 혁신지원사업 주요지표로서 재학생 전원이 진단에 참여할 수 있도록 지도 요청.', 1, '공지사항', sysdate);

commit;

-- SELECT 목록
-- PK 컬럼은 최초 등록시 값이 sequence에의해 고정됨.
SELECT noticeno, adminno, noticetitle, noticecontent, cnt, noticeword, rdate
FROM notice
ORDER BY noticeno ASC;

-- SELECT 조회
SELECT noticeno, adminno, noticetitle, noticecontent, cnt, word, rdate
FROM notice
WHERE noticeno = 1;

-- UPDATE
-- 최초 등록 날짜 유지, 수정 날짜 추가
UPDATE notice
SET name='수정사항', seqno=1, udate=sysdate
WHERE noticeno=1;

commit;

-- DELETE
DELETE FROM notice
WHERE noticeno=5;

commit;

-- 모든 레코드 삭제
DELETE FROM notice;
commit;

-- COUNT(*)
SELECT COUNT(*) as cnt
FROM notice;

SELECT * FROM notice;

commit;

-- 글수의 증가
UPDATE notice
SET cnt = cnt + 1
WHERE noticeno=1;

commit;

SELECT * FROM notice;

-- 글수의 감소
UPDATE notice
SET cnt = cnt - 1
WHERE noticeno=1;

commit;

SELECT * FROM notice;

-- FK 컬럼 기준 카운트, 특정 그룹에 속한 레코드 갯수 산출
-- 1번 관리자가 등록한 공지사항 갯수는?
SELECT COUNT(*) as cnt FROM notice WHERE adminno=1;

-- 1번 공지사항 카테고리의 공지사항 등록 갯수는?
SELECT COUNT(*) as cnt FROM notice WHERE noticeno=1;

-- FK 컬럼 기준 삭제, 특정 그룹에 속한 레코드 모두 삭제
-- 1번 관리자가 등록한 공지사항 삭제
DELETE FROM notice WHERE adminno=1;

-- 1번 공지사항 카테고리의 공지사항 삭제
DELETE FROM notice WHERE noticeno=1;

-- 부모 테이블과의 JOIN
-- 부모 테이블과의 JOIN
SELECT c.adminno, c.adminname, c.adminid, c.adminpasswd,
          n.noticeno, n.title, n.cnt as notice_cnt, n.rdate, n.file1saved, n.thumb1
FROM admin c, notice n
WHERE c.adminno = n.adminno;
