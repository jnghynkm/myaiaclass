--2020.11.11 수업 리뷰

-- JOIN

-- CROSS JOIN : 조건없이 테이블과 테이블을 붙여준다
select *
from emp, dept
;

-- ANSI JOIN : CROSS JOIN
select *
from emp cross join dept
;

-- Equi JOIN
select *
from emp, dept
where emp.deptno=dept.deptno
;

-- SCOTT의 사원번호와 이름, -- EMP
-- 부서이름, 지역을 출력    -- DEPT
select empno, ename, dept.deptno, dname, loc
from emp, dept
where emp.deptno=dept.deptno and ename='SCOTT'
;

select e.empno, e.ename, d.dname, d.loc
from emp e, dept d
where e.deptno=d.deptno and e.ename='SCOTT'
;

-- ANSI -> T1 INNER JOIN T2 ON 조인의 조건
select *
from emp inner join dept
on emp.deptno=dept.deptno
;

select *
from emp inner join dept
--on emp.deptno=dept.deptno
using(deptno)   -- 공통 컬럼은 하나만 가지게 해준다
;

-- NATURAL JOIN : 동일 컬럼 조인의 조건을 생략, 같은 이름의 컬럼은 하나씩
select ename, deptno, dname
from emp NATURAL JOIN dept
;

-- Non Equi JOIN : 동등 비교가 아닌 비교연산이 가능
-- 급여 등급을 5개로 나누어 놓은 salgrade에서 정보를 얻어 와서
-- 각 사원의 급여 등급을 지정해보도록 하자.
-- 이를 위해 사원 테이블과 급여등급 테이블을 조인하도록 하자.
-- 사원의 급여가 몇 등급인지 살펴보자.

-- 1. CROSS JOIN -> 조건
select *
from emp e, salgrade s
where e.sal between s.losal and s.hisal
;

-- Self JOIN : 자신의 테이블을 조인
-- SMITH의 매니저 이름이 무엇인지
select e.ename, e.mgr, m.ename
from emp e, emp m
where e.mgr=m.empno
and e.ename = 'SMITH'
;

-- OUTER JOIN : 정보가 부족해도 출력하도록 하는 JOIN
select e.ename, e.mgr, m.ename
from emp e, emp m
where e.mgr=m.empno(+)
;


-- ANSI outer JOIN
-- T1 [LEFT | RIGHT | FULL] OUTER JOIN T2

select *
from emp m right outer join emp e
on e.mgr=m.empno
;

select *
from emp e left outer join emp m
on e.mgr=m.empno
;

select *
from customer c, orders o
where c.custid=o.custid(+)
;

-- 회원별 구매 횟수
select c.name, count(o.orderid), sum(o.saleprice), avg(o.saleprice)
from customer c, orders o 
where c.custid=o.custid(+)
group by c.name
;

--============================================================

-- 서브 쿼리
-- 스칼라 부속질의 - select 부속질의, 단일 행 단일 열
-- 인라인 뷰      - from 부속지르이, 복합 행 복합 열
-- 중첩질의       - where 부속질의, 단일 열, 단순비교 -> 단일 행, 집합 비교 -> 복합 행

-- 중첩 질의 : 단순 비교

-- 평균 급여(sub query)보다 더 많은 급여를 받는 사원을 출력
select avg(sal) from emp;

select *
from emp
where sal > (select avg(sal) from emp)
;

-- sal 300 이상 받는 사원이 소속된 부서
select distinct deptno from emp where sal >= 3000;

select *
from emp
where deptno in (select distinct deptno from emp where sal >= 3000)
;

-- SCOTT 사원과 같은 부서에 근무하는 사원의 리스트를 출력
select ename, deptno
from emp
where deptno=(select deptno from emp where ename='SCOTT')
;

-- 30번 소속 사원들 중에서 급여를 가장 많이 받는 사원보다
-- 더 많은 급여를 받는
-- 사람의 이름, 급여를 출력하는 쿼리문을 작성해 봅시다.
select ename, sal
from emp
--where sal > (select max(sal) from emp where deptno=30)
--where sal > all (select sal from emp where deptno=30) -- and
where sal > any (select sal from emp where deptno=30) -- or
;

-- rownum
select rownum, empno, ename, hiredate
from emp
--where rownum < 4
order by hiredate desc
;

select rownum, empno, ename, hiredate
from (select rownum, empno, ename, hiredate from emp order by hiredate desc) -- 인라인 뷰
--where rownum<4
;

-- 가장 급여를 많이 받는 사원 3명을 출력하자
select rownum, ename, sal
from (select rownum, ename, sal from emp order by sal desc)
where rownum<4
;

select name from customer where custid=1;

-- 스칼라 부속질의 : select 절 프리젠테이션 영역에 쓸 수 있는 부속질의, 단일행, 단일열, 단일갑스이 결과만 나와야한다.
select custid, (select name from customer where customer.custid=orders.custid), 
        saleprice, orderdate
from orders
;

-- 마당서점의 고객별  group by 
-- 판매액을 보이시오 orders
-- (결과는 고객이름과 고객별 판매액을 출력).
select c.custid, c.name, nvl(sum(o.saleprice),0)
from customer c, orders o
where c.custid=o.custid(+)
group by c.custid, c.name
order by nvl(sum(o.saleprice),0) desc
;

select o.custid,
    (
        select name from customer c where o.custid=c.custid
    )
    , sum(o.saleprice)
from orders o
group by o.custid
;



