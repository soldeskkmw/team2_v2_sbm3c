/**********************************/
/* Table Name: 관리자 */
/**********************************/

DROP TABLE ADMIN;
DROP TABLE ADMIN CASCADE CONSTRAINTS; 

CREATE TABLE ADMIN(
      ADMINNO                             NUMBER(10)         NOT NULL,
      ADMINID                             VARCHAR2(20)       NOT NULL,
      ADMINPASSWD                         VARCHAR2(20)       NOT NULL,
      ADMINNAME                           VARCHAR2(20)       NOT NULL
);

COMMENT ON TABLE ADMIN is '관리자';
COMMENT ON COLUMN ADMIN.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN.ADMINID is '관리자 ID';
COMMENT ON COLUMN ADMIN.ADMINPASSWD is '관리자 비밀번호';
COMMENT ON COLUMN ADMIN.ADMINNAME is '관리자 이름';

DROP SEQUENCE ADMIN_seq;

CREATE SEQUENCE ADMIN_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999 --> NUMBER(7) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지

INSERT INTO admin(adminno, adminid, adminpasswd, adminname)
VALUES(admin_seq.nextval, 'admin1', '1234', '관리자1');

INSERT INTO admin(adminno, adminid, adminpasswd, adminname)
VALUES(admin_seq.nextval, 'admin2', '1234', '관리자2');

INSERT INTO admin(adminno, adminid, adminpasswd, adminname)
VALUES(admin_seq.nextval, 'admin3', '1234', '관리자3');

commit;

SELECT adminno, adminid, adminpasswd, adminname FROM admin ORDER BY adminno ASC;
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
   ADMINNO ADMINID               ADMINPASSWD    ADMINNAME      
---------- -------------------- --------------- -------
         1 admin1               1234            관리자1             

UPDATE admin
SET adminpasswd='1234', adminname='관리자1'
WHERE ADMINNO=1;

COMMIT;
         
DELETE FROM admin WHERE adminno=1;
-- ORA-02292: integrity constraint (KD.SYS_C007226) violated - child record found
-- 자식 테이블에서 adminno: 1을 이용하고있기 때문에 삭제 못함. 

-- 로그인
SELECT COUNT(*) as cnt
FROM admin
WHERE adminid='admin1' AND adminpasswd='1234';

  
  