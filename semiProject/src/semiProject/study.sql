create table study(
   	num number,
    studyid number primary key,
   	userid varchar2(20),
	subject varchar2(100),
	reg_date date,
	readcount number default 0, 
	semicontent varchar2(1000),
    content clob,  -- clob ��û ū �ڷ�?����?�� ���� �� �����
	main_photo varchar2(300)
); --9

create sequence study_num_seq 
start with 1 
increment by 1
nocache
nocycle;

select * from study;

delete from study where num=19;
drop sequence study_num_seq;  --������ ����

drop table study; --���̺� ����

insert into study
values(study_num_seq.nextval, study_num_seq.nextval, 'User01',
'����ó����� �ʱ� + �Ǳ� 60�� �ȿ� �ڻ� ����!!',sysdate,0,
'�������� �׽�Ʈ.......','���� �׽�Ʈ.......','math_img_1.jpg');

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