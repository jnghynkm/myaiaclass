-- test 

--#2 요구사항에 맞는 오브젝트를 생성하는 sql을 작성하시오.
--  1. 대리키 IDX, 회원 아이디, 이름, 비밀번호, 사진이름, 가입일 을 저장할 수 있는
--    이름이 MEMBER인 테이블을 생성하세요.
--    회원 아이디에는 기본 키 제약설정을 하고, 이름과 비밀번호에는 not null,
--    가입 일은 기본 값으로 sysdate가 입력되도록 하시오.
create table MEMBER (
    IDX number(4) not null unique,
    userid number primary key,
    username varchar2(12) not null,
    password varchar2(12) not null,
    picname varchar2(12),
    joindate Timestamp DEFAULT SYSDATE
);

--  2. MEMBER 테이블에서 이름 컬럼으로 인덱스 객체를 생성하시오.
create index index_member_username on member(username);


--  3. MEMBER 테이블에서 회원 아이디, 이름, 사진 정보만을 출력하는 view 객체를 생성하시오.
--view의 이름은 member_view 로 생성하시오.
create or replace view member_view
as
select userid, username, picname
from member
;


--=====================================================================


--#1 아래 요구사항에 맞도록 기본 SQL 문을 작성하시오.
--1. 아래 조건에 맞는 DDL을 작성하시오.
--===== 전화번호 부( 테이블 이름 : Contact )
------ 기본정보(not null)
-- 대리키 : 일련번호 -> pIdx 기본키로 설정
-- 이름, 전화번호, 주소, 이메일 
-- 주소값과 이메일은 입력이 없을 때 기본값 입력
-- 친구의 타입 (카테고리) : univ, com, cafe 세가지 값만 저장 가능
------ 선택 정보
-- 전공, 학년
-- 회사이름, 부서이름, 직급
-- 모임이름, 닉네임

create table contact (
    idx number(6) primary key,
    f_name varchar2(20) not null,
    f_phonenum varchar2(20) not null,
    f_address varchar2(20) not null default '-',
    f_email varchar2(30) not null default '-',
    f_regdate date default sysdate
);

create table phoneinfo_univ(
    idx number(6) primary key,
    f_u_major varchar(20) default 'N',
    f_u_year number(1) check(f_u_year between 5 and 1),
    f_ref number(6) not null references contact(idx)
);

create table phoneinfo_com(
    idx number(6) primary key,
    f_c_company varchar2(20) default 'N',
    f_c_dname varchar2(20) default 'N',
    f_c_job varchar2(20) default 'N',
    f_ref number(6) not null references contact(idx)
);

create table phoneinfo_cafe(
    idx number(6) primary key,
    f_cf_cafe varchar2(20) default 'N',
    f_cf_nickname varchar2(20) default 'N',
    f_ref number(6) not null references contact(idx)
);


--2. DEPT 테이블에 데이터를 삽입하는 SQL을 작성하시오. 입력 데이터는 임의로 작성하시오.
insert into dept valuse (50, '개발팀', '판교');

--3. DEPT 테이블에 위에서 삽입한 데이터의 dname, loc 데이터를 변경하는 SQL을 작성하시오.
--입력 데이터는 임의로 작성하시오.
update dept set dname = '디자인', loc='강남' where deptno=50;

--4. DEPT 테이블에 위에서 삽입한 데이터를 deptno 컬럼의 값을 기반으로 삭제하는 SQL을 작성하시오.
delete from dept where deptno=50;

--5. 사용자가 보유한 테이블 목록을 확인하는 SQL문을 작성하시오.
select * from tab;

--6. EMP 테이블의 구조를 확인하는 SQL을 작성하시오.
desc emp;

--7. 사용자가 정의한 제약조건들을 확인하는 SQL문을 작성하시오.
select * from user_constraints;

--#2 아래 요구사항에 맞도록 고급 SQL 문을 작성하시오.
--1. EMP 테이블의 ename 컬럼에 인덱스를 생성하는 SQL을 작성하시오. 인덱스의 이름은 emp_index
create index emp_index on emp(ename);

--2. EMP 테이블과 DEPT 테이블을 조인하는 SQL을 기반으로 view 객체를 생성하는 SQL을 작성하시오.
--view 의 이름은 emp_view 로 하시오.
create or replace view emp_view
as
select e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm, d.deptno, d.dname, d.loc
from emp e inner join dept d on e.deptno=d.deptno;

--3. EMP 테이블에서 모든 사원의 부서번호를 이름이 'SCOTT'인 사원의 부서번호로 변경하는 SQL을 작성하시오.
update emp set deptno = (select deptno from emp where ename='SCOTT')

