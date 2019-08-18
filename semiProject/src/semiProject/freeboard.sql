create table freeboard(
   	num number,
   	writer varchar2(20),
    password varchar2(20),
	subject varchar2(50),
	reg_date date,
	readcount number default 0, 
	ref number, 
	re_step number, 
	re_level number, 
	content varchar2(100),
	ip varchar2(20),
    upload varchar2(300)
);

create sequence freeboard_num_seq 
start with 1 increment by 1
nocache
nocycle;

insert into freeboard 
values(freeboard_num_seq.nextval, '홍길동','1234','제목1',sysdate,0,freeboard_num_seq.nextval,
0,0,'내용 테스트.......','127.0.0.1','sample.txt');

insert into freeboard 
values(freeboard_num_seq.nextval, '이옥자','1234','제목2',sysdate,0,freeboard_num_seq.nextval,
0,0,'내용 테스트.......','127.0.0.1','test.txt');

select*from freeboard order by num desc;

drop table freeboard;
drop sequence freeboard_num_seq;

commit;