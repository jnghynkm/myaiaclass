--2020.11.12 수업 리뷰

-- DDL

-- 테이블 생성 :
-- create table table_name
-- (
--      column_name domain [constraint],
--      column_name domain [constraint],
--      ...
-- )
drop table test_tbl;
create table test_tbl(
    no number(4),
    user_name varchar2(10),
    user_id varchar2(16),
    user_password varchar2(12),
    reg_date Timestamp default sysdate
);
desc test_tbl;

-- 사원 테이블과 유사한 구조의 사원번호, 사원이름, 급여 3개의 칼럼으로 구성된 EMP01 테이블을 생성해 봅시다.
desc emp;
drop table emp01;
create table emp01 (
    empno number(4),
    ename varchar2(10),
    sal number(7,2)
);
desc emp01;

-- Create table ~ as ~ : 테이블 복사, 제약조건은 복사되지 않는다.
-- create table 명령어 다음에 컬럼을 일일이 정의하는 대신
-- AS 절을 추가하여 EMP 테이블과 동일한 내용과 구조를 갖는
-- EMP02 테이블을 생성해 봅시다.
drop table emp02;
create table emp02
as
select * from emp;

desc emp02;
select * from emp02;

-- 서브 쿼리문의 select 절에 * 대신 원하는 컬럼명을 명시하면
-- 기존 테이블에서 일부의 컬럼만 복사할 수 있습니다.
drop table emp03;
create table emp03
as
select empno, ename, sal from emp
;
select * from emp03;

-- 서브 쿼리문의 select 문을 구성할 때
-- where 절을 추가하여 원하는 조건을 제시하면
-- 기존 테이블에서 일부의 행만 복사합니다.
drop table emp04;
create table emp04
as
select * from emp where deptno=10
;
select * from emp04;

-- where 조건 절에 항상 거짓이 되는 조건을 지정하게 되면
-- 테이블에서 얻어질 수 있는 로우가 없게 되므로 빈 테이블이 생성됨
drop table emp05;
create table emp05
as
select * from emp where 1=2
;
select * from emp05;

-- 테이블의 삭제 : 저장공간을 삭제 -> 저장되어있는 데이터도 모두 삭제
-- drop table table_name
drop table test_tbl;

-- truncate : 테이블의 모든 행을 바로 삭제 물리적인 적용도 바로 진행
drop table emp06;
create table emp06
as
select * from emp;
select * from emp06;
truncate table emp06;

-- rename 현재 테이블 이름 to 새 이름
rename emp06 to new_emp;
select * from tab;
drop table hot_emp;

-- 테이블 구조의 변경
-- alter table table_name {add(컬럼 추가) | modify(컬럼 수정) | drop(컬럼 삭제)}

-- 기존 테이블에 속성 추가 : 각 행의 컬럼 데이터는 null 값으로 채워짐
-- emp01 job 컬럼 추가 job varchar2(10)
desc emp01;
alter table emp01
add (deptno number(2))
;

-- 기존 테이블의 컬럼 변경 : 새롭게 정의된 컬럼으로 교체하는 것
alter table emp01
modify (deptno number(10))
;

-- 기존 테이블의 컬럼 삭제 : 데이터도 모두 삭제
alter table emp01
drop (deptno)
;

-- 제약 조건 정의
insert into dept values(10, 'test', 'seoul');

desc emp01;
insert into emp01 values(null, null, 1000, 'tester');
select * from emp01;

-- emp01 테이블 삭제
drop table emp01;
-- emp01 테이블 생성 : empno, ename 에 null 값이 들어가지 않도록 제약
create table emp01 (
    empno number(4) not null,
    ename varchar2(10) not null,
    job varchar2(9),
    deptno number(2)
);
insert into emp01 values(null, null, 'tester', 10);
insert into emp01 values(1, 'test1', 'tester', 10);
select * from emp01 where empno=1;

-- 데이터의 중복 금지 : unique
drop table emp02;
create table emp02(
    empno number(4) unique,
    ename varchar2(10) not null
);
desc emp02;
insert into emp02 values(1, 'test1');
insert into emp02 values(1, 'test2');
select * from emp02;

-- empno not null, unique 제약을 동시에 적용
drop table emp03;
create table emp03(
    empno number(4) not null unique,
    ename varchar2(10) not null
);
insert into emp03 values(null, 'test1');
insert into emp03 values(1, null);
insert into emp03 values(1, 'test1'); -- 성공
insert into emp03 values(1, 'test2');
insert into emp03 values(2, 'test2'); -- 성공
select * from emp03;

-- 기본키 제약 : 기본키 설정 -> not null, unique
drop table emp04;
create table emp04(
    empno number(4) primary key,
    ename varchar2(10) not null
);
insert into emp04 values(null, 'test');
insert into emp04 values(1, 'test');

-- 외래키 제약 : 참조하는 테이블과 컬럼을 정의
drop table emp05;
create table emp05(
    empno number(4) primary key,
    ename varchar2(10) not null,
    deptno number(2) references dept(deptno)
);

insert into emp05 values(1, 'test', 10); --성공
insert into emp05 values(2, 'test', 60);
insert into emp05 values(2, 'test', null); -- 성공
select * from emp05;
select * from dept;

-- check : 특정 범위 제한














