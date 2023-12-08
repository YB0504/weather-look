select * from report;
select * from daily;
select * from review;
select * from community;
select * from board_type;

insert into report values(
report_seq.nextval, 'Å×½ºÆ®', 4, 1);



select reports.*, board_type.type_name type_name from
(select report.report_id,report.reason, d.type_id, d.post_id, d.title from report, daily d
where d.type_id = report.type_id and d.post_id = report.post_id
union
select report.report_id,report.reason, r.type_id, r.post_id, r.title from report, review r
where r.type_id = report.type_id and r.post_id = report.post_id
union
select report.report_id,report.reason, c.type_id, c.post_id, c.title from report, community c
where c.type_id = report.type_id and c.post_id = report.post_id
order by report_id desc) reports
, board_type
where reports.type_id = board_type.type_id
;


select count(*) reports from
(select report.report_id,report.reason, d.type_id, d.post_id, d.title from report, daily d
where d.type_id = report.type_id and d.post_id = report.post_id
union
select report.report_id,report.reason, r.type_id, r.post_id, r.title from report, review r
where r.type_id = report.type_id and r.post_id = report.post_id
union
select report.report_id,report.reason, c.type_id, c.post_id, c.title from report, community c
where c.type_id = report.type_id and c.post_id = report.post_id
order by report_id desc)
;

select * from(
select reports.*, board_type.type_name type_name from
(select report.report_id,report.reason, d.type_id, d.post_id, d.title from report, daily d
where d.type_id = report.type_id and d.post_id = report.post_id
union
select report.report_id,report.reason, r.type_id, r.post_id, r.title from report, review r
where r.type_id = report.type_id and r.post_id = report.post_id
union
select report.report_id,report.reason, c.type_id, c.post_id, c.title from report, community c
where c.type_id = report.type_id and c.post_id = report.post_id
order by report_id desc) reports
, board_type
where reports.type_id = board_type.type_id)
;


select reports.* , board_type.type_name type_name from
(select report.report_id, report.reason, d.type_id, d.post_id, d.title from report, daily d
where d.type_id = report.type_id and d.post_id = report.post_id
union
select report.report_id, report.reason, r.type_id, r.post_id, r.title from report, review r
where r.type_id = report.type_id and r.post_id = report.post_id
union
select report.report_id, report.reason, c.type_id, c.post_id, c.title from report, community c
where c.type_id = report.type_id and c.post_id = report.post_id
order by report_id desc) reports, board_type
where reports.type_id = board_type.type_id
order by report_id desc
;
