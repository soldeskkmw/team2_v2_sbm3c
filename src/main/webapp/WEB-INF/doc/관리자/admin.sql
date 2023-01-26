/**********************************/
/* Table Name: 관리자 */
/**********************************/

DROP TABLE ADMIN;
DROP TABLE ADMIN CASCADE CONSTRAINTS; 

CREATE TABLE ADMIN(
      ADMINNO                             NUMBER(10)         NOT NULL           PRIMARY KEY,  -- 관리자 번호
      ADMINID                             VARCHAR2(20)       NOT NULL,  -- 관리자 아이디
      ADMINPASSWD                         VARCHAR2(20)       NOT NULL,  -- 관리자 비밀번호
      ADMINNAME                           VARCHAR2(20)       NOT NULL,  -- 관리자 이름
      ADMINTEL                            VARCHAR(14)        NOT NULL, -- 관리자 전화번호
      ADMINRECEIVER                       VARCHAR2(30)       NOT NULL, -- 관리자 이메일
      ZIPCODE                             VARCHAR(5)         NOT NULL, -- 우편번호, 12345
      ADDRESS1                            VARCHAR(80)        NOT NULL, -- 주소 1
      ADDRESS2                            VARCHAR(50)        NULL, -- 주소 2
      ADMINGRADE                          NUMBER(2)          NOT NULL, -- 관리자 등급(1~10: 메인 관리자, 11~20: 관리자, 21~29: 정지 관리자,31~40 탈퇴 관리자)
      ADMINGENDER                         CHAR(1)            NOT NULL, -- 관리자 성별(남 : M, 여 : W)
      ADMINAGE                            NUMBER(2)          NOT NULL,  -- 나이
<<<<<<< HEAD
      PERMISSION                          CHAR(1)            DEFAULT 'X'     NOT NULL, -- O, X  (가입 허가)
=======
      PERMISSION                          CHAR(1)            DEFAULT 'X'     NOT NULL, -- O, X
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f
      MDATE                               DATE               NOT NULL -- 가입일
);

COMMENT ON TABLE ADMIN is '관리자';
COMMENT ON COLUMN ADMIN.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN.ADMINID is '관리자 ID';
COMMENT ON COLUMN ADMIN.ADMINPASSWD is '관리자 비밀번호';
COMMENT ON COLUMN ADMIN.ADMINNAME is '관리자 이름';
COMMENT ON COLUMN ADMIN.ADMINTEL is '관리자 전화번호';
COMMENT ON COLUMN ADMIN.ADMINRECEIVER is '관리자 이메일';
COMMENT ON COLUMN ADMIN.ZIPCODE is '관리자 우편번호';
COMMENT ON COLUMN ADMIN.ADDRESS1 is '관리자 주소1';
COMMENT ON COLUMN ADMIN.ADDRESS2 is '관리자 주소2';
COMMENT ON COLUMN ADMIN.ADMINGRADE is '관리자 등급';
COMMENT ON COLUMN ADMIN.ADMINGENDER is '관리자 성별';
COMMENT ON COLUMN ADMIN.ADMINAGE is '관리자 나이';
COMMENT ON COLUMN ADMIN.PERMISSION is '가입 허가';
COMMENT ON COLUMN ADMIN.MDATE is '가입일';

DROP SEQUENCE ADMIN_seq;

CREATE SEQUENCE ADMIN_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지



<<<<<<< HEAD
INSERT INTO admin(adminno, adminid, adminpasswd, adminname, admintel, adminreceiver, zipcode, address1, address2, admingrade, admingender, mdate)
VALUES(admin_seq.nextval, 'admin1', '1234', '관리자1');
=======
INSERT INTO admin(adminno, adminid, adminpasswd, adminname, admintel, adminreceiver, zipcode, address1, address2, admingrade, admingender, adminage, mdate)
VALUES(admin_seq.nextval, 'admin1', '1234', '관리자1','010-0000-0000', 'abcd@gmail.com', '41346', '서울시', '동대문구', 0, 'W', 21, sysdate);
>>>>>>> e4cf39784193f12e790d7b6a5d51711db347cc8f

INSERT INTO admin(adminno, adminid, adminpasswd, adminname)
VALUES(admin_seq.nextval, 'admin2', '1234', '관리자2');

INSERT INTO admin(adminno, adminid, adminpasswd, adminname)
VALUES(admin_seq.nextval, 'admin3', '1234', '관리자3');

commit;

SELECT adminno, adminid, adminpasswd, adminname FROM admin ORDER BY adminno ASC;


                                                
SELECT adminno, adminid, adminpasswd, adminname, admintel, adminreceiver, zipcode, address1, address2, admingrade, admingender, adminage, permission, mdate
FROM admin
ORDER BY adminno ASC;

   ADMINNO ADMINID              ADMINPASSWD     ADMINNAME               
---------- -------------------- --------------- -------
         1 admin1               1234            관리자1  
         2 admin2               1234            관리자2  
         3 admin3               1234            관리자3  
         
SELECT adminno, adminid, adminpasswd, adminname
FROM admin
WHERE adminno=1;

   ADMINNO ADMINID              ADMINPASSWD     ADMINNAME     
---------- -------------------- --------------- -------
         1 admin1               1234            관리자1   

SELECT adminno, adminid, adminpasswd, adminname
FROM admin
WHERE adminid='admin1';

SELECT adminno, adminid, adminpasswd, adminname, admintel, adminreceiver, zipcode, address1, address2, admingrade, admingender, adminage, permission, mdate
FROM admin
WHERE permission = 'X'
ORDER BY admingrade ASC, adminid ASC;


   ADMINNO ADMINID               ADMINPASSWD    ADMINNAME      
---------- -------------------- --------------- -------
         1 admin1               1234            관리자1             

UPDATE admin
SET adminpasswd='1234', adminname='관리자1'
WHERE ADMINNO=1;

4. 관리자 등급 수정


UPDATE admin 
SET admingrade=1, permission='O'
WHERE adminno=1;


UPDATE admin
SET permission='O'
WHERE adminno=1;

UPDATE admin
SET adminreceiver='kmw_99@naver.com'
WHERE ADMINNO=1;

COMMIT;

         
DELETE FROM admin WHERE adminno=1;
-- ORA-02292: integrity constraint (KD.SYS_C007226) violated - child record found
-- 자식 테이블에서 adminno: 1을 이용하고있기 때문에 삭제 못함. 

-- 로그인
SELECT COUNT(*) as cnt
FROM admin
WHERE adminid='admin1' AND adminpasswd='1234';

  
  