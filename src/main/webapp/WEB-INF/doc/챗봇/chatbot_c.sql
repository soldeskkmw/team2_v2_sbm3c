/**********************************/
/* Table Name: 챗봇 */
/**********************************/
drop table chatbot;

CREATE TABLE chatbot(
        id                                    NUMBER(10)         NOT NULL         PRIMARY KEY,
        intent                                VARCHAR2(30)         NULL ,
        ner                                   VARCHAR2(30)         NULL ,
        query                                 VARCHAR2(200)         NULL,
        answer                                VARCHAR2(200)         NOT NULL,
        answer_image                          VARCHAR2(100)         NULL ,
        answer_url                            VARCHAR2(200)         NULL 
);

COMMENT ON TABLE chatbot is '챗봇';
COMMENT ON COLUMN chatbot.id is '챗봇번호';
COMMENT ON COLUMN chatbot.intent is '의도';
COMMENT ON COLUMN chatbot.ner is '개체명';
COMMENT ON COLUMN chatbot.query is '질문';
COMMENT ON COLUMN chatbot.answer is '답변';
COMMENT ON COLUMN chatbot.answer_image is '답변 이미지';
COMMENT ON COLUMN chatbot.answer_url is '답변 url';

DROP SEQUENCE chatbot_seq;

CREATE SEQUENCE chatbot_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO chatbot(id, intent, ner, query, answer, answer_image, answer_url)
VALUES(chatbot_seq.nextval, '인사', '식사', '식사하셨어요?', '네 간단히 했습니다.', 'http://www.test.com/food.jpg', 'http://www.test.com');  

SELECT id, intent, ner, query, answer, answer_image, answer_url 
FROM chatbot 
ORDER BY id ASC;

DELETE FROM chatbot;
commit;