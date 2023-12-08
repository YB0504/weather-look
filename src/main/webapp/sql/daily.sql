select * from(
select rownum rnum, d1.* from ( 
select * from daily
where 
region like '%%' or region is null
and
(temperature between 5 and 15) 
order by reg_date desc) d1)
where rnum between 1 and 10

;
select count(*) from(
select rownum rnum, d1.* from ( 
select * from daily
where 
(region like '%%' or region is null)
and
(temperature between 5 and 15) 
order by reg_date desc) d1)
;