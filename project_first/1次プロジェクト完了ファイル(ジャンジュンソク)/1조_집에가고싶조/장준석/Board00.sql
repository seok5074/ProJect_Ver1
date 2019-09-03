
create sequence seq_mainboard_idx;
DROP SEQUENCE seq_mainboard_idx;

drop table mainboard;
create table mainboard
(
	idx int PRIMARY KEY,					--게시글 번호	
	subject varchar2(255),		--타이틀
	name varchar2(100),			--작성자
	content	CLOB,				--내용
	regdate date,				--작성일
	readhit	int,				--조회수
	likehit int				    --추천수
);

select * from mainboard;

-- 아래를 5번 입력한다.

INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'관심을 가져 주셔서...','관리자','안녕하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '관리자 입니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '이번에 변경된 회원 약관에 대해 관심을 가져 주셔서 감사합니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '사이트를 운영하다 보니.. ' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '어쩔수 없이 어쩌구 저쩌구 해서 약관이 변경되었습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 좋은 하루 되시길 바랍니다.','2016-04-27 05:44:32',35,7);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'저두 궁금했었는데','회원2','안녕하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '저두 궁금했었는데...' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '님께서 질문을 해 주셔서 고맙습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '즐공하시고 행복한 하루 되세요..^_^','2016-04-27 05:44:32',103,23);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'당연히 회원이면 관심을 가져야죠^_^','회원1','안녕하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '당연히 회원이니까 약관 변경에 관심을 가져야죠' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '아무튼 오늘도 좋은 하루 보내세요','2016-04-27 05:44:32',42,16);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'많은 회원님들께서 관심을 가져주셔서 감사합니다.','관리자','안녕하세요' || CHR(13) || CHR(10) || '관리자 입니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '많은 회원분들께서 이번 약관 변경에 관심을 가져 주셔서 정말 고맙습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '앞으로 더욱 발전하는 사이트가 되겠습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 언제나 행복하세요.^_^','2016-04-27 16:30:41',36,9);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'사이트 발전에 관심이 많은 사람입니다.','회원3','안녕하삼~ 관리자님~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '사이트 발전에 관심이 많은 사람으로서 지금 보다 나은 사이트를 위해' || CHR(13) || CHR(10) || '운영계획과 약관을 변경하는 것은 잘 된 일이라 생각합니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 수고 많이 하삼~','2016-04-27 05:03:52',82,15);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'정말 정말 감사합니다.','관리자','안녕하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '관리자 입니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '회원님의 많은 관심에 몸둘바를 모르겠습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '언제나 해복하고 건강하시기 바라겠습니다.','2016-04-27 05:44:32',132,24);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'네 잘 알겠습니다.','회원4','안녕하세요...' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '역쉬~ 관리자님은 부지런 하십니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '하하하~ 사이트가 팍팍 발전해 나가는 것이 보이는 것 같습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '관리자님의 끊임없는 노력에 박수를 보냅니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 수고하세요','2016-04-27 03:37:44',97,19);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'내용을 줄 바꿈하지 않아서...','관리자','안녕하세요..' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '님께서 작성한 글이 줄 바꿈되지 않아서..' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '테이블이 늘어나내요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || 'ㅋㅋㅋ','2016-04-27 05:04:23',64,30);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'저두요~','회원5','저두 그게 궁금했는데...' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || 'ㅋㅋㅋ 님께서 대신 해주시네요...^_^','2016-04-27 05:44:32',15,27);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'당연하죠~','회원6','안녕하세요 관리자님~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '회원이니까 관심을 갖는건 당연하죠..' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '아무튼 수고가 많으시네요..','2016-04-27 05:44:32',9,21);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'별 말씀을 다하시네요','회원1','안녕하세요 관리자님~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '회원이면 당연히... 관심을 가져야져..' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '헤헤헤' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '수고하세요','2016-04-27 05:44:32',79,15);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'회원이면 당연한 것을...','회원3','ㅋㅋㅋ' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '님들도 다 같은 생각을 가지고 계시군요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '뭐 같은 회원이니...' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '아무튼 모두들 행복하삼~','2016-04-27 05:44:32',38,24);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'그러게요','회원3','아~ ' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '줄 바꿈 하지 않아도 자동으로 될 줄 알았죠..^_^' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '관리자님이 프로그램 잘 해주셔서... 줄 바꿈 해주삼~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 이만','2016-04-27 04:59:15',46,18);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'감사합니다.','관리자','안녕하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '답변글 감사합니다.\r\n\r\n\r\n그럼','2016-04-27 03:40:43',16,32);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'아 줄 바꿈 문제 해결 했습니다.','관리자','안녕하세요 관리자 입니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '먼저 엔터키를 치지않고 글 을 입력하면 줄 바꿈 되지 않는 문제가 발생했는데.. ' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '어제 해결 했습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 모두들 즐공 하삼~' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '','2016-04-27 04:58:45',35,22);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'저두 궁금했는데','회원7','안녕하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '저두 궁금한 점이 해결 되었습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '감사합니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 모두 수고하삼~','2016-04-27 05:44:32',81,36);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'궁금한게 해결 되었네요','회원8','안녕하세요' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '님 덕분에 궁금한점이 해결 되었습니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '감사합니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '그럼 이만...^_^','2016-04-27 05:50:21',77,11);
INSERT INTO mainboard (idx,subject,name,content,regdate,readhit,likehit) VALUES (seq_mainboard_idx.NEXTVAL,'감사합니다.','회원1','제 덕분에 궁금한게 해결 되었다니...' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '다행입니다.' || CHR(13) || CHR(10) || CHR(13) || CHR(10) || '즐공하삼~','2016-04-27 05:44:32',162,33);
COMMIT;
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
insert into mainboard values(seq_mainboard_idx.nextVal,'2','3','5',sysdate,0,0);
COMMIT


/* 먼저 HR 스키마에 reply 테이블과 reply 테이블에서 사용할 SEQUENCE를 생성한다.
 * 아래의 댓글 데이터를 reply 테이블에 추가한다.
 **/
DROP TABLE reply;
CREATE TABLE reply(
	no NUMBER(7) PRIMARY KEY,
	bbs_no NUMBER(7) NOT NULL,
	reply VARCHAR2(500 CHAR),
	writer VARCHAR2(20 CHAR) NOT NULL,
	reg_date TIMESTAMP NOT NULL,	
	CONSTRAINT reply_fk FOREIGN KEY(bbs_no) REFERENCES mainboard(idx)
);

-- 댓글 테이블의 시퀀스
DROP SEQUENCE reply_seq;
CREATE SEQUENCE reply_seq
      MAXVALUE 9999999
      INCREMENT BY 1      
      NOCACHE
      NOORDER
      NOCYCLE;


-- 댓글 데이터 추가하기 - 1번만 실행한다. --
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '항상 감사합니다.' || CHR(13) || CHR(10) || ' 땡큐!~', 'midas', '2016-05-08 13:44:32');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '저두 감사드립니다.', 'servlet', '2016-05-09 05:24:57');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '저두요~ ^_^', 'midas', '2016-05-09 09:19:23');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '나두 관심이 많은뎅~', 'servlet', '2016-05-09 11:54:45');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '헤헷~ 감사 합니다.', 'admin', '2016-05-09 12:16:51');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '아~ 다들 이 사이트가 잘되길 바라는 군요', 'midas', '2016-05-09 13:34:11');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '관리자님께서 워낙 신경을 쓰시니...' || CHR(13) || CHR(10) || '잘될 겁니다. ', 'servlet', '2016-05-09 14:03:37');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '저두 사이트 발전에 관심이 많습니다.' || CHR(13) || CHR(10) || '감사합니다.' , 'midas', '2016-05-09 14:39:29');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '와~ 대단하네요 우리 사이트~' || CHR(13) || CHR(10) || 'ㅋㅋㅋ~', 'servlet', '2016-05-09 14:41:18');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '우리 관리자님이 워낙 열심이시라~' || CHR(13) || CHR(10) || '그리고 회원님들도 열성으로 사이트에 충성 접속하시니...'|| CHR(13) || CHR(10) ||' 않될 수가 있나요...^_^', 'admin', '2016-05-09 14:52:48');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '네 맞아요~', 'admin', '2016-05-10 15:42:12');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '그렇죠 그렇고 말구요', 'servlet', '2016-05-11 15:44:57');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '항상 감사합니다.', 'midas', '2016-05-15 16:19:23');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '땡큐!~', 'servlet', '2016-05-16 17:31:45');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '저두요~ 땡큐!~', 'servlet', '2016-05-19 18:16:51');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '모두들 열성이네요~' || CHR(13) || CHR(10) || '헤헤~' || CHR(13) || CHR(10) || '땡큐!~', 'admin', '2016-05-20 10:34:11');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '땡큐~ 여기 붙어라~ ^_^', 'midas', '2016-05-20 11:33:27');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 99, '땡큐~ 붙었어요~', 'admin', '2016-05-20 13:19:59');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '당연 회원이면 관심 가져야죠~', 'servlet', '2016-06-10 13:41:17');
INSERT INTO reply(no, bbs_no, reply, writer, reg_date)VALUES(reply_seq.NEXTVAL, 100, '동감 합니다.' || CHR(13) || CHR(10) ||' 땡큐!~', 'midas', '2016-06-12 14:52:48');

commit;
SELECT * FROM reply;
      
alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS';
alter session set nls_TIMESTAMP_format = 'YYYY-MM-DD HH24:MI:SS';

2016-04-27 05:44:32
