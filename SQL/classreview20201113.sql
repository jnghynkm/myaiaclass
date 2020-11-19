-- 2020.11.13 수업 리뷰

-- Transaction
-- 여러개의 SQL을 하나의 단위로 처리하는 것.
-- 트랜젝션이 가지는 모든 작업이 모두 정상 처리되어야
-- 트랜젝션이 완료되었다고 하는 것.
-- 처리과정에서 오류가 발생하면 처음으로 돌아가 
-- 다시 트랜젝션을 시작

select * from emp10;
desc emp10;
select * from dept01;

-- 부서 테이블에 데이터 하나를 저장 - 정상
-- 사원 테이블에 새로운 사원을 입력 - 오류 rollback

-- Transaction 시작
insert into dept01 values (50, 'RD', 'SEOUL');
insert into emp10 (empno, ename, job, sal) values('ten', 'TEN', 'MANAGER', 2500);
rollback; -- 오류가 발생해서 초기화 (마지막 저장 commint 단계)
select * from dept01;

-- 새로운 Transaction 시작
insert into dept01 values (50, 'RD', 'SEOUL');
insert into emp10 (empno, ename, job, sal) values (7777, 'SEVEN', 'MANAGER', 2500);
select * from dept01;
select * from emp10;
commit;

-- 새로운 Transaction 시작
update emp10
set mgr=(select empno from emp10 where ename='KING')
;
select * from emp10;
-- 잘못 처리된 작업이다!!! rollback
rollback;

-- 가상테이블 VIEW
-- 실제 테이블을 기반으로 논리적인 가상테이블을 -> VIEW
-- create [or replace] view view_name as subquery

-- 자주 사용되는 30번 부서에 소속된 사원들의
-- 사번과 이름과 부서번호를 출력하기 위한 SELECT문을
-- 하나의 뷰로 정의해 봅시다.
create view view_emp30
as select empno, ename, deptno
from emp where deptno=30;
--grant create view to scott;
select * from view_emp30;

-- 전사 정보 출력 (사원 정보, 부서정보)
select *
from emp, dept
where emp.deptno=dept.deptno
and emp.deptno=10
;
select * from view_emp_dept
where deptno=10
;

select * from user_constraints;

