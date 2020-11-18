-- 2020.11.16

-- PhoneBook DDL : 테이블 정의서를 참고해서 DDL을 작성한다.

create table phoneInfo_basic (
    idx number(6) constraint pi_basic_idx_PK primary key,
    fr_name varchar2(20) not null,
    fr_phonenumber varchar2(20) not null,
    fr_email varchar2(20),
    fr_address varchar2(20),
    fr_regdate date default sysdate
)
;

desc phoneInfo_basic;

-- 제약 조건 확인 user_constraints
desc user_constraints;

select constraint_name, constraint_type
from user_constraints
where table_name='PHONEINFO_BASIC'
;

-- 질의 : READ
select * from phoneinfo_basic;

-- 시퀀스 : 숫자 재생기
create SEQUENCE SEQ_PIBASIC_IDX
MINVALUE 0
start with 0
increment by 1
;

-- insert : CREATE
insert into phoneinfo_basic 
values (1, 'SCOTT', '010-0000-0000', 'SCOTT@gmail.com', '서울', sysdate);

insert into phoneinfo_basic 
values (SEQ_PIBASIC_IDX.nextval, 'SCOTT', '010-0000-0000', 'SCOTT@gmail.com', '서울', sysdate);

insert into phoneinfo_basic (idx, fr_name, fr_phonenumber)
values (SEQ_PIBASIC_IDX.nextval, 'KING', '010-9999-9999');

-- 데이터 삭제 : DELETE
DELETE FROM phoneinfo_basic;

-- 데이터 업데이트 : UPDATE
update phoneinfo_basic
set fr_email='KING@naver.com', fr_address='부산'
where idx=10
;

-- C(create), R(read), U(Update), D(delete)
-- insert, select, update, delete


-- PhoneInfo_univ
drop table phoneinfo_univ;
create table phoneinfo_univ (
    idx number(6),
    fr_u_major varchar2(20) default 'N',
    fr_u_year number(1) default 1 check (fr_u_year between 1 and 4),
    fr_ref number(6) not null,
    constraint pi_univ_idx_PK primary key (idx),
    constraint pi_univ_ref_FK FOREIGN key (fr_ref) REFERENCES phoneInfo_basic (idx)
);

desc phoneinfo_univ;
select * from user_constraints where table_name='PHONEINFO_UNIV';


-- CRUD


-- create : 대학친구 입력
-- 기본 정보
insert into phoneinfo_basic (idx, fr_name, fr_phonenumber)
values (SEQ_PIBASIC_IDX.nextval, 'KING', '010-9999-9999');
-- 대학 정보
insert into phoneinfo_univ values (1, 'computer', 2, 9); -- select * from phoneinfo_basic;
insert into phoneinfo_univ values (2, 'computer', 2, 1); -- 부모 키 없음
insert into phoneinfo_univ values (3, 'computer', 2, SEQ_PIBASIC_IDX.currval);

-- read
select * from phoneinfo_univ;

select * 
from phoneinfo_basic pb,phoneinfo_univ pu
where pb.idx=pu.fr_ref
;

-- update : phoneinfo_univ  where idx=3;
update phoneinfo_univ
set fr_u_major='체육', fr_u_year=4
where idx=3;

-- delete : idx=11 행을 삭제
delete phoneinfo_univ where fr_ref=11;
delete phoneinfo_basic where idx=11;


-- phoneinfor_com 
create table phoneinfo_com(
    idx number(6) constraint pi_com_idx_PK primary key,
    fr_c_company varchar2(20) default 'N',
    fr_ref number(6) not null constraint pi_com_ref_FK REFERENCES phoneinfo_basic (idx)
);

-- 회사 친구 정보 입력
-- 기본 정보
insert into phoneinfo_basic (idx, fr_name, fr_phonenumber)
values (SEQ_PIBASIC_IDX.nextval, '손흥민', '010-1111-1111');

-- 회사 정보 입력
insert into phoneinfo_com values (1, '토트넘', SEQ_PIBASIC_IDX.currval);

-- READ
-- 회사 친구
select *
from phoneinfo_basic pb, phoneinfo_com pc
where pb.idx=pc.fr_ref
and pb.name = '손흥민'
;

-- update 
update phoneinfo_com
set fr_c_company='레알'
where idx=1;

-- delete
-- 자식 테이블의 행부터 삭제하고 부모 테이블 행을 삭제
delete from phoneinfo_com where fr_ref=12;
delete from phoneinfo_basic where idx=12;






select * 
from phoneinfo_basic pb, phoneinfo_univ pu, phoneinfo_com pc
where pb.idx=pu.fr_ref(+) and pb.idx=pc.fr_ref(+)
;






















