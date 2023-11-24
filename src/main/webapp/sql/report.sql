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
daily_report_seq.nextval, '테스트2', 4)
;
insert into review_report values(
review_report_seq.nextval, '테스트2', 2)
;
insert into community_report values(
community_report_seq.nextval, '테스트2', 2)
;

-- 신고된 게시글 수집
select * from
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