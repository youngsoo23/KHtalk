create table study(
   	num number,
    studyid number primary key,
   	userid varchar2(20),
	subject varchar2(100),
	reg_date date,
	readcount number default 0, 
	semicontent varchar2(1000),
    content clob,  -- clob 엄청 큰 자료?내용?을 받을 때 사용함
	main_photo varchar2(300)
); --9

create sequence study_num_seq 
start with 1 
increment by 1
nocache
nocycle;

select * from study;

delete from study where num=19;
drop sequence study_num_seq;  --시퀀스 삭제

drop table study; --테이블 삭제

insert into study
values(study_num_seq.nextval, study_num_seq.nextval, 'User01',
'정보처리기사 필기 + 실기 60일 안에 박살 내기!!',sysdate,0,
'간략내용 테스트.......','내용 테스트.......','math_img_1.jpg');

commit;

create table room(
userid VARCHAR2(20),
studyid number not null,
constraint room_pk foreign key (studyid) references study (studyid)
);

drop table room;
commit;
insert into room VALUES('hello',1);

select * from room;


create sequence user_num_seq 
start with 1 
increment by 1
nocache
nocycle;

select * from userTable;