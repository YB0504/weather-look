select * from daily;
select * from review;
select * from community;

-------------------------------------------------------------
-- 메인페이지 작업, 최신순 데일리, 쇼핑후기 (사진있는 테이블 출력)
select * from
(select rownum rnum, m1.* from
(select 'daily' as type_name, post_id, title, reg_date from daily
union
select 'review' as type_name, post_id, title, reg_date from review
order by reg_date desc) m1)
where rnum >=1 and rnum <= 4
;

-- 위 목록 카운팅
select count(*) maincount from
(select 'daily' as type_name, post_id, title, reg_date from daily
union
select 'review' as type_name, post_id, title, reg_date from review
order by reg_date desc)
;