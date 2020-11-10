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
