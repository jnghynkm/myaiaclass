--2020/11/10 수업 리뷰
--함수

-- 단일행, 집합함수

-- 단일행 함수 : 숫자, 문자, 날짜, 변환

desc dual;

-- 숫자함수
select ABS(-15.5) from dual;    -- 절댓값
select FLOOR(15.5) from dual;   -- 소수점 아래 잘라냄
select round(15.672) from dual; -- 반올림
select round(15.672,1) from dual;
select log(10, 100) from dual;  -- 로그 
select power(3,3) from dual;    -- 제곱

-- 문자함수
select concat('나는 ', '손흥민 입니다') from dual; -- 문자의 값을 연결
select concat('저의 이름은 ',ename) from emp where job='MANAGER';
select LOWER('MS. KIM') "Lowercase" from dual;  -- 소문자
select lower(ename) from emp;
select lpad('Page 1',15,'*') from dual;         -- 문자열 반환
select rpad('page 1',15,'*') from dual;
select substr('abcdefg',3,4) from dual;          -- 문자 잘라서 추출
select rpad(substr('930917-0111111',1,8),14,'*') from dual;

select Ltrim('  =from= ',' ') as Ltrim from dual;
select Rtrim('  =from= ',' ') as Rtrim from dual;
select trim('=' from '= =from= =') as trim from dual;

select replace('back and bill','b','j') from dual;
select replace('121212-343434','-','') from dual;

-- 날짜 함수
select sysdate from dual;
select sysdate+21 from dual;

select add_months(sysdate, 2) from dual;
select last_day(sysdate) from dual;

-- 변환 함수
-- to_char : 날짜->문자, 숫자->문자

-- 날짜->문자
select sysdate, to_char(sysdate, 'yyyy.mm.dd day dy am pm hh hh24 mi ss') from dual;
-- 2020/11/11 요일 00:00:00
select to_char(sysdate, 'yyyy/mm/dd day hh24:mi:ss') from dual;

-- 숫자->문자
select to_char(59900.123, 'L000,000.00') from dual;
select to_char(59900.123, 'L999,999.99') from dual;

select sal, to_char(sal*1100, 'L00,000,000') from emp;
select sal, to_char(sal*1100, 'L99,999,999') from emp;
select ename, job, sal, to_char(sal*1100*12+nvl(comm,0)*1100, 'L99,999,999') as "연봉" from emp order by sal desc;

-- 문자->날짜
-- 2020.11.01
select to_date('2009/12/11', 'yyyy/mm/dd') from dual;
select to_date('2020.11.01.', 'yyyy.mm.dd.') from dual;

-- 오늘이 2020년 1월 1일에서 며칠이 지났는지 확인
select trunc(sysdate - to_date('20-01-01','yy-mm-dd')) from dual; -- trunc 절산

-- 며칠을 살았는지 확인
select trunc(sysdate-to_date('1997.08.08','yyyy.mm.dd')) as "영훈 살아온 날" from dual;

-- 문자->숫자
select to_number('100,000.00', '999,999.99')/to_number('10.00','999,999.99') as "100000/10" from dual;

-- decode 함수 : switch 문과 비슷 
-- 사원이름, 부서번호, 부서이름 출력
select ename, deptno,
    decode( deptno,
        10, 'ACCOUNTING',
        20, 'RESEARCH',
        30, 'SALES',
        40, 'OPERATIONS'
    ) as dname
from emp order by deptno asc
;

select ename, job, sal,
    decode(job,
        'ANALYST', sal*1.05,
        'SALESMAN', sal*1.1,
        'MANAGER', sal*1.15,
        'CLERK', sal*1.2
    ) as upsal
from emp
order by sal
;

select ename as name, deptno as dno,
    case
        when deptno=10 then 'ACCOUNTING'
        when deptno=20 then 'RESEARCH'
        when deptno=30 then 'SALES'
        when deptno=40 then 'OPERATIONS'
    end as dname
from emp
order by dname
;
-- 그룹함수 : 하나의 행의 컬럼을 대상으로 하는 것이 아닌 
-- 행의 그룹의 컬럼들을 묶어 그룹화하고 연산하는 함수
-- 그룹함수 : sum, avg, count, max, min

select
    to_char(sum(sal)*1100, 'L999,999,999') as "월 급여 총액",
    round(avg(sal)) as "월 급여 평균",
    count(sal) as "전체 사원 수",
    count(comm) as "커미션 수", -- null 안 셈
    max(sal) as "최고 급여", 
    min(sal) as "최저 급여"
from emp
;

select
    sum(comm),
    avg(nvl(comm,0)),
    count(nvl(comm,0))
from emp
;

-- 전체 행을 구한다
select
    count(*) as "Manager의 수",
    avg(sal) as "매니저 평균급여",
    max(sal),
    min(sal)
from emp
where job='MANAGER'
;

-- 직무의 개수
select count(distinct job) as "직무 개수"
from emp
order by job
;

-- 특정 컬럼을 기준으로 그룹핑 : group by 컬럼
select deptno, comm
from emp
group by deptno, comm
order by deptno
;

-- 각 부서의 소속 인원 구하기
select deptno, count(*) as "부서 인원"
from emp
group by deptno
order by deptno
;

-- 부서별 총 급여액
select deptno, sum(sal)
from emp
group by deptno
order by deptno
;

-- 부서별 급여 평균
select deptno, round(avg(sal),2)
from emp
group by deptno
order by deptno
;

-- 부서별로 그룹지은 후(group by),
-- 그룹 지어진 부서별 평균 급여
-- 평균 급여가 2000 이상인 (having)
-- 부서번호와 부서별 평균 급여를 출력
select deptno, round(avg(sal),2)
from emp
group by deptno
having not avg(sal)<2000
order by deptno
;

-- 부서별 급여의 최대값과 최소값을 구하되
-- 최대 급여가 2900이상인 부서만 출력
select deptno, max(sal), min(sal)
from emp
group by deptno
having max(sal)>=2900
order by deptno
;

-- 직무별 지표 : 사원의 수, 급여의 총합, 
-- 평균 급여, 최대 급여, 최소 급여
select job,
    count(*) || '명' as "사원 수",
    to_char(sum(sal)*1100, 'L999,999,999') as "급여 총합",
    to_char(avg(sal)*1100, 'L999,999,999') as "평균 급여",
    to_char(max(sal)*1100, 'L999,999,999') as "최고 급여",
    to_char(min(sal)*1100, 'L999,999,999') as "최저 급여"
from emp
where job!='PRESIDENT'
group by job
order by job
;