select * from daily;
select * from review;
select * from community;

-------------------------------------------------------------
select count(*) searchcount from
(select 'daily' as type_name, post_id, nick, reg_date from daily
where nick like '%%' or title like '%%' or content like '%%'
union
select 'review' as type_name, post_id, nick, reg_date from review
where nick like '%%' or title like '%%' or content like '%%'
union
select 'community' as type_name, post_id, nick, reg_date from community
where nick like '%%' or title like '%%' or content like '%%'
order by reg_date)
;