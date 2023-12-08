select * from daily;
select * from review;
select * from community;

-------------------------------------------------------------
-- 신고테이블

select * from daily_report;
select * from review_report;
select * from community_report;

alter table daily_report add report_date date;
alter table review_report add report_date date;
alter table community_report add report_date date;

update  daily_report set report_date = sysdate where report_date is null;
update  review_report set report_date = sysdate where report_date is null;
update  community_report set report_date = sysdate where report_date is null;

commit;

insert into daily_report values(
daily_report_seq.nextval, '테스트2', 63, sysdate)
;
insert into review_report values(
review_report_seq.nextval, '테스트2', 2)
;
insert into community_report values(
community_report_seq.nextval, '테스트2', 2)
;

-- 신고된 게시글 수집
select * from (
select rownum rnum, re2.* from(
select re1.* from
(select daily_report.*, 'daily' as type_name, daily.title from daily_report
left join daily
on daily_report.post_id = daily.post_id
union
select review_report.*, 'review' as type_name, review.title from review_report
left join review
on review_report.post_id = review.post_id
union
select community_report.*, 'community' as type_name, community.title from community_report
left join community
on community_report.post_id = community.post_id) re1
order by report_date desc) re2)
;

-- 신고글 갯수
select count(*) reports from
(select daily_report.*, 'daily' as type_name, daily.title from daily_report
left join daily
on daily_report.post_id = daily.post_id
union
select review_report.*, 'review' as type_name, review.title from review_report
left join review
on review_report.post_id = review.post_id
union
select community_report.*, 'community' as type_name, community.title from community_report
left join community
on community_report.post_id = community.post_id)
order by report_date desc;

-- 신고글 삭제
delete from daily where post_id = 62;

commit;



--신고 댓글---------------------------------------------------

select * from re_comm_report;
select * from re_daily_report;
select * from re_review_report;


select * from(
select rownum rnum, re_re1.* from
(select 're_review_report' type_name, re_r_r.*,
re_r.re_content, re_r.nick, re_r.post_id
from re_review_report re_r_r  
left join
review_reply re_r
on
re_r_r.re_id = re_r.re_id

union

select 're_comm_report' type_name, re_c_r.*,
re_c.re_content, re_c.nick, re_c.post_id
from re_comm_report re_c_r  
left join
community_reply re_c
on
re_c_r.re_id = re_c.re_id

union

select 're_daily_report' type_name, re_d_r.*,
re_d.re_content, re_d.nick, re_d.post_id
from re_daily_report re_d_r  
left join
daily_reply re_d
on
re_d_r.re_id = re_d.re_id) re_re1

order by report_date desc)
where rnum between 1 and 10
;


-- 신고된 댓글 갯수 count


select count(*) from
(select 're_review_report' type_name, re_r_r.*,
re_r.re_content, re_r.nick, re_r.post_id
from re_review_report re_r_r  
left join
review_reply re_r
on
re_r_r.re_id = re_r.re_id

union

select 're_comm_report' type_name, re_c_r.*,
re_c.re_content, re_c.nick, re_c.post_id
from re_comm_report re_c_r  
left join
community_reply re_c
on
re_c_r.re_id = re_c.re_id

union

select 're_daily_report' type_name, re_d_r.*,
re_d.re_content, re_d.nick, re_d.post_id
from re_daily_report re_d_r  
left join
daily_reply re_d
on
re_d_r.re_id = re_d.re_id) re_re1

order by report_date desc

;


