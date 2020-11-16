--20/11/09 수업리뷰
--select 기본

--해당 계정(ex. scott)이 소유한 테이블 객체 확인 : Tab
select * from tab;

--테이블의 구조 확인 : DESC
desc bonus;
desc dept;
desc salgrade;

-- 데이터의 조회 명령 : select

select      -- 조회하는 명령어
    *       -- 컬럼 이름들 기술. 기술순서에 따라 결과도 순서에 맞게 출력
from emp    -- 조회하고자하는 테이블 이름을 기술
;           -- sql의 종료

-- 부서 테이블의 모든 데이터 조회
select *
from dept
;

desc dept;

-- 부서테이블에서(from) 부서의 이름과 위치 출력(select)
select dname, loc
from dept;

-- 모든 사원 정보 출력
select * from emp;

-- 사원 이름, 사번, 직급(직무) 출력
select ename, empno, job
from emp;

-- select 절의 컬럼의 사칙연산 가능
-- 임시 테이블 dual : 컬럼 x 를 가지는 테이블
select 100+200, 200-100, 100*2, 100/5
from dual;

select ename, sal, sal*12
from emp;

-- null : 값 존재, 정해지지 않은 값 , 연산불가(사칙연산,비교연산)

-- nvl 함수 : null 값을 치환해주는 함수
-- nvl(컬럼, 대체값) : 대체값은 컬럼의 타입에 맞는 데이터여야 함
select ename, comm, nvl(comm,0), sal*12+nvl(comm,0) as "연봉" from emp;

-- 데이터베이스의 sql 내부의 문자열 처리는 작은 따옴표 ''
-- 문자열의 조합
select ename || '의 직무는 ' || job || '입니다.'
from emp;

-- distinct : 데이터의 중복값은 한번만 출력
select distinct deptno
from emp;

-- 특정 데이터를 추출하는 where 절
-- select 컬럼명 from 테이블 이름 where 조건식
select ename, job, sal
from emp 
where sal>=3000;

select ename, job from emp where deptno=10;

select * from emp where ename='FORD';

select * from emp where hiredate='81/11/17';

select ename, deptno, job
from emp
where deptno=10 and job='MANAGER';

select ename, deptno, job
from emp
where deptno=10 or job='MANAGER';

-- 부서번호 10번 아닌 경우
select ename, deptno, job
from emp
--where not deptno=10
--where deptno!=10
where deptno<>10
;

--급여 2000이상 3000이하
select *
from emp
--where sal>=2000 and sal<=3000
where sal between 2000 and 3000 -- 이상, 이하
;

--1987년 입사
select * from emp
--where hiredate between '1987/01/01' and '1987/12/31'
where hiredate>='87/01/01' and hiredate<='87/12/31'
;

select * from emp
--where comm=300 or comm=500 or comm=1400
where comm in(300,500,1400)
;

select * from emp
--where ename like 'F%' -- F로 시작, 뒤 문자 아무거나
--where ename like '%S' -- S로 끝남
--where ename like '%A%' -- A문자 포함
--where ename like '_A%' -- 첫번째 문자 아무거나, 두번째 문자 반드시 A
--where ename like '__R%' -- 첫번째랑 두번째 문자 아무거나, 세번째 문자 R
where ename not like '%A%' -- A 미포함
;

-- 커미션 받는 사원
select * from emp
where comm is not null and comm>0
;

select * from emp
--order by sal asc -- 오름차순
--order by sal desc -- 내림차순
--order by sal --생략:오름차순
--order by hiredate -- 날짜는 오래된 날짜가 작음 : 오름차순일 경우 오래된 날짜부터 최근 날짜로 정렬
order by hiredate desc, sal asc
;

