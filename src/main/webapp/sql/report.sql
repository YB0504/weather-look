select * from daily;
select * from review;
select * from community;

-------------------------------------------------------------

select * from daily_report
left join daily
on daily_report.post_id = daily.post_id
;