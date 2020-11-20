-- 2020.11.11 수업 리뷰

-- JOiN

-- cross join : 조건 없이 테이블과 테이블을 붙여준다
select * 
from emp, dept
;

-- ansi join : cross join
select * from emp cross join dept;

-- equi join
select * from emp, dept where emp.deptno=dept.deptno;

-- SCOTT 의 사원번호와 이름, -- EMP
-- 부서이름, 지역을 출력  -- DEPT
select empno, ename, dept.deptno, dname, loc from emp, dept 
where emp.deptno=dept.deptno and ename='SCOTT';

-- ansi -> t1 inner join t2 on 조인의 조건
select * from emp inner join dept on emp.deptno=dept.deptno;

select * from emp join dept 
--on emp.deptno=dept.deptno
using(deptno) -- 공통 컬럼은 하나만 가지게 해준다. 공통컬럼을 구별할 필요가 없다.
;

-- natural join : 동일 컬럼 조인의 조건을 생략, 같은 이름의 컬럼은 하나씩
select ename, deptno, dname from emp natural join dept;

-- non equi join : 동등 비교가 아닌 비교연산이 가능
-- 급여 등급을 5개로 나누어 놓은 salgrade에서 정보를 얻어 와서 
-- 각 사원의 급여 등급을 지정해보도록 합시다. 
-- 이를 위해서 사원(emp) 테이블과 급여 등급(salgrade) 테이블을 조인하도록 합시다. 
-- 사원의 급여가 몇 등급인지 살펴보자

-- cross join -> 조건
select * from emp e, salgrade s where e.sal between s.losal and s.hisal;

-- Self JOIN : 자신의 테이블을 조인
-- SMITH의 매니저 이름이 무엇인지
select e.ename, e.mgr, m.empno, m.ename from emp e, emp m where e.mgr=m.empno and e.ename='SMITH';

-- outer join : 정보 부족해도 출력하도록 하는 join
select * from emp e, emp m where e.mgr=m.empno(+);

-- ansi outer join
-- t1 [left | right | full] outer join t2
select *
from emp m right outer join emp e
on e.mgr=m.empno
;

select *
from emp e left outer join emp m
on e.mgr=m.empno
;

-- 회원, 구매
select *
from customer c, orders o
where c.custid=o.custid(+)
;

-- 회원별 구매 횟수
select c.name, count(o.orderid), sum(o.saleprice)
from customer c, orders o
where c.custid=o.custid(+)
group by c.name
;

-- 서브 쿼리
-- 스칼라 부속질의 - select 부속질의, 단일행 단일열
-- 인라인 뷰      - from 부속질의, 복합행 복합열
-- 중첩질의       - where 부속질의, 단일열, 단순비교 -> 단일행, 집합비교 -> 복합행

-- 중첩 질의 : 단순 비교

-- 평균 급여(sub query)보다 더 많은 급여를 받는 사원을 출력
select ename, sal
from emp
where sal > (select avg(sal) from emp)
;

-- sal 3000 이상 받는 사원이 소속된 부서
select ename, deptno, sal
from emp
where deptno = (select distinct deptno from emp where sal > 3000)
;