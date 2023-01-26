/**********************************/
/* Table Name: 회원 */
/**********************************/

DROP TABLE MEMBER;
DROP TABLE MEMBER CASCADE CONSTRAINTS;

CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(10)		     NOT NULL		 PRIMARY KEY,
		MEMBERID                      		VARCHAR2(20)		 NOT NULL,  -- 회원 아이디
		MEMBERPASSWD                        VARCHAR2(60)		 NOT NULL,  -- 회원 비밀번호
		MEMBERNAME                         	VARCHAR2(30)		 NOT NULL,  -- 회원 이름
		TEL                           		VARCHAR2(14)		 NOT NULL,  -- 전화번호
        RECEIVER                            VARCHAR2(30)         NOT NULL,  -- 회원 이메일
        GRADE                               NUMBER(2)            NOT NULL, --(1~10: 회원 관리자, 11~20: 회원, 30~39: 정지 회원: 40~49: 탈퇴 회원)
		GENDER                              CHAR(1)              NOT NULL, -- 성별(남 : M, 여 : W)
        AGE                                 NUMBER(2)            NOT NULL,  -- 나이
        MDATE                         		DATE		         NOT NULL
);

COMMENT ON TABLE MEMBER is '관리자';
COMMENT ON COLUMN MEMBER.MEMBERNO is '회원 번호';
COMMENT ON COLUMN MEMBER.MEMBERID is '회원 ID';
COMMENT ON COLUMN MEMBER.MEMBERPASSWD is '회원 비밀번호';
COMMENT ON COLUMN MEMBER.MEMBERNAME is '회원 이름';
COMMENT ON COLUMN MEMBER.TEL is '전화번호';
COMMENT ON COLUMN MEMBER.RECEIVER is '이메일';
COMMENT ON COLUMN MEMBER.GRADE is '등급';
COMMENT ON COLUMN MEMBER.GENDER is '성별';
COMMENT ON COLUMN MEMBER.AGE is '나이';
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
<<<<<<< HEAD
INSERT INTO member(memberno, memberid, memberpasswd, membername, tel, receiver, grade, gender, mdate)
VALUES (member_seq.nextval, 'user1', '1234', '회원1', '010-1234-5678', 'fset663517@naver.com', 11, 'M', sysdate);
 
INSERT INTO member(memberno, memberid, memberpasswd, membername, tel, receiver, grade, gender, mdate)
VALUES (member_seq.nextval, 'user2', '1234', '회원2', '010-1111-2222', 'fset663517@gmail.com', 11, 'W', sysdate);
=======
INSERT INTO member(memberno, memberid, memberpasswd, membername, tel, receiver, grade, gender,age, mdate)
VALUES (member_seq.nextval, 'user1', '1234', '회원1', '010-1234-5678', 'fset663517@naver.com', 11, 'M', 22, sysdate);
 
INSERT INTO member(memberno, memberid, memberpasswd, membername, tel, receiver, grade, gender, age, mdate)
VALUES (member_seq.nextval, 'user2', '1234', '회원2', '010-1111-2222', 'fset663517@gmail.com', 11, 'W', 22, sysdate);
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f

COMMIT;

 
2. 목록
- 검색을 하지 않는 경우, 전체 목록 출력
 
SELECT memberno, memberid, memberpasswd, membername, tel, receiver, grade, gender, age, mdate
FROM member
ORDER BY memberid ASC;
     
     
3. 조회
 
1) user1 사원 정보 보기
SELECT memberno, memberid, memberpasswd, membername, tel, receiver, mdate
FROM member
WHERE memberno = 1;

SELECT memberno, memberid, memberpasswd, membername, tel, receiver, mdate
FROM member
WHERE memberid = 'user1';
 
SELECT membername
FROM member
WHERE memberno = 1;
    
    
4. 관리자 등급 수정
UPDATE member 
SET grade=1
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
SET passwd='1111'
WHERE memberno=1;

COMMIT;
 
 
7. 로그인
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberid='user1' AND passwd='1234';
 cnt
 ---
   0
 
 
 