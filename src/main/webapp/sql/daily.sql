select * from(
select rownum rnum, d1.* from ( 
select * from daily
where 
region like '%%' or region is null
and
(temperature between 5 and 15) 
order by reg_date desc) d1)
where rnum between 1 and 10

<<<<<<< HEAD
-- daily 본문
insert into daily (post_id, title, read_count, reg_date, content, temperature, latitude, longitude, nick)
values(DAILY_SEQ.nextval, DAILY_SEQ.currval || 'daily daily daily daily daily daily daily daily', 0, sysdate, '글내용은 스킵', 10.5, 36.2, 128.4, '준혁');

-- daily 댓글
insert into DAILY_REPLY(re_id, re_content, re_ref, re_level, re_step, re_regdate, post_id, nick)
values (DAILY_REPLY_SEQ.nextval, DAILY_REPLY_SEQ.currval || '번 daily 댓글', 1, 1, 1, sysdate, DAILY_SEQ.currval, '준혁');

commit;
=======
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
>>>>>>> main-page
