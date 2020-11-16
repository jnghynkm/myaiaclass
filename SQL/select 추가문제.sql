--select 추가 문제

desc book;
desc customer;
desc orders;

--1 마당서점의고객이요구하는다음질문에대해SQL 문을작성하시오.
--(1) 도서번호가1인도서의이름 : book
select *
from book
where bookid=1
;

--(2) 가격이20,000원이상인도서의이름 : book
select bookname
from book
where price>=20000
;

--(3) 박지성의 총 구매액(박지성의 고객번호는 1번으로 놓고 작성)
select sum(saleprice) as totalPrice
from orders
where custid=1  --박지성의 custid
;

select sum(saleprice) as totalPrice
from orders
where custid=(select custid from customer where name='박지성')  --박지성의 custid
;

--(4) 박지성이 구매한 도서의 수(박지성의 고객번호는 1번으로 놓고 작성)
-- 박지성의 구매 횟수 = 구매한 도서의 수
select count(*) as "구매횟수"
from orders
where custid=1
;

--2 마당서점의운영자와경영자가요구하는다음질문에대해SQL 문을작성하시오.
--(1) 마당서점 도서의 총개수 : book의 총 row의 개수
select count(*)
from book
;

--(2) 마당서점에 도서를 출고하는 출판사의 총개수 : book
select count(distinct publisher)
from book
order by publisher
;

--(3) 모든 고객의 이름, 주소
select name, address
from customer
;


--(4) 2014년7월4일 ~ 7월7일 사이에 주문 받은 도서의 주문번호 : orders
select orderid
from orders
where orderdate between '2014-07-04' and '2014-07-07'
;

--(5) 2014년7월4일~7월7일사이에주문받은도서를제외한도서의주문번호
select orderid
from orders
where orderdate not between '2014-07-04' and '2014-07-07'
;

--(6) 성이‘김’씨인고객의이름과주소 : customer like '김%'
select name, address
from customer
where name like '김%'
;

--(7) 성이‘김’씨이고 이름이‘아’로 끝나는 고객의 이름과 주소 : name '김%아'
select name, address
from customer
where name like '김%아'
;