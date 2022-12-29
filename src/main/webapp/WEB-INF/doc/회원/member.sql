/**********************************/
/* Table Name: 회원 */
/**********************************/

DROP TABLE MEMBER;
DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(10)		     NOT NULL		 PRIMARY KEY,
		MEMBERID                      		VARCHAR2(20)		 NOT NULL,
		MEMBERPASSWD                        VARCHAR2(60)		 NOT NULL,
		MEMBERNAME                         	VARCHAR2(30)		 NOT NULL,
		TEL                           		VARCHAR2(14)		 NOT NULL,
		MDATE                         		DATE		         NOT NULL
);

COMMENT ON TABLE MEMBER is '관리자';
COMMENT ON COLUMN MEMBER.MEMBERNO is '회원 번호';
COMMENT ON COLUMN MEMBER.MEMBERID is '회원 ID';
COMMENT ON COLUMN MEMBER.MEMBERPASSWD is '회원 비밀번호';
COMMENT ON COLUMN MEMBER.MEMBERNAME is '회원 이름';
COMMENT ON COLUMN MEMBER.TEL is '전화번호';
COMMENT ON COLUMN MEMBER.MDATE is '가입일';

DROP SEQUENCE MEMBER_seq;

CREATE SEQUENCE MEMBER_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
 
 commit;
 
1. 등록
 
1) id 중복 확인(null 값을 가지고 있으면 count에서 제외됨)
SELECT COUNT(memberid)
FROM member
WHERE memberid='user1';

SELECT COUNT(memberid) as cnt
FROM member
WHERE memberid='user1';
 
 cnt
 ---
   0   ← 중복 되지 않음.
   
2) 등록
-- 회원 계정
INSERT INTO member(memberno, memberid, memberpasswd, membername, tel, mdate)
VALUES (member_seq.nextval, 'user1', '1234', '회원1', '010-1234-5678', sysdate);
 
INSERT INTO member(memberno, memberid, memberpasswd, membername, tel, mdate)
VALUES (member_seq.nextval, 'user2', '1234', '회원2', '010-1111-2222', sysdate);

COMMIT;

 
2. 목록
- 검색을 하지 않는 경우, 전체 목록 출력
 
SELECT memberno, memberid, memberpasswd, membername, tel, mdate
FROM member
ORDER BY memberid ASC;
     
     
3. 조회
 
1) user1 사원 정보 보기
SELECT memberno, memberid, memberpasswd, membername, tel, mdate
FROM member
WHERE memberno = 1;

SELECT memberno, memberid, memberpasswd, membername, tel, mdate
FROM member
WHERE memberid = 'user1';
 
SELECT membername
FROM member
WHERE memberno = 1;
    
4. 수정
UPDATE member 
SET membername='아로미', tel='111-1111-1111'
WHERE memberno=1;

COMMIT;

 
5. 삭제
1) 모두 삭제
DELETE FROM member;
 
2) 특정 회원 삭제
DELETE FROM member
WHERE memberno=1;

COMMIT;

 
6. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=1 AND passwd='1234';
 
2) 패스워드 수정
UPDATE member
SET passwd='0000'
WHERE memberno=1;

COMMIT;
 
 
7. 로그인
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberid='user1' AND passwd='1234';
 cnt
 ---
   0
 
 
 