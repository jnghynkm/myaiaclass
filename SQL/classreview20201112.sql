-- 2020.11.12 수업 리뷰

--DDL

-- 테이블 생성 

-- create table ~ as ~ : 테이블 복사, 제약조건은 복사되지 않는다.
drop table emp02;
create table emp02
as
select * from emp;
desc emp02;
desc emp;
select * from emp02;

-- 서브 쿼리문의 select 절에 * 대신 원하는 컬럼명을 명시하면
-- 기존 테이블에서 일부의 컬럼만 복사 가능
drop table emp03;
create table emp03
as
select empno, ename, sal from emp
;

-- 서브 쿼리문의 select 문을 구성할 때
-- where 절을 추가하여 원하는 조건 제시하면
-- 기존 테이블에서 일부 행만 복사
drop table emp04;
create table emp04
as
select * from emp where deptno=10;
desc emp04;
select * from emp04;

-- WHERE 조건 절에 항상 거짓이 되는 조건을 지정하게 되면 
-- 테이블에서 얻어질 수 있는 로우가 없게 되므로 빈 테이블이 생성되게 됩니다.

-- 테이블의 삭제 ; 저장공간을 삭제 -> 저장되어 있는 데이터도 모두 삭제
-- drop table table_name
