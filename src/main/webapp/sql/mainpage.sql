select * from daily;
select * from likes;

--1차 전체 리스트 출력
select type_id, post_id, reg_date, title, nick from daily
union
select type_id, post_id, reg_date, title, nick from review
order by reg_date desc;

--2차 갯수 출력
select rownum rnum, r1.* from(
select type_id, post_id, reg_date, title, nick from daily
union
select type_id, post_id, reg_date, title, nick from review
order by reg_date desc) r1;

-- 3차 갯수 컷팅
select type_id, post_id, reg_date, title, nick from(
select rownum rnum, r1.* from(
select type_id, post_id, reg_date, title, nick from daily
union
select type_id, post_id, reg_date, title, nick from review
order by reg_date desc) r1
) where rnum >= 1 and rnum <= 9;

-- 좋아요 카운팅
select type_id, post_id, count(*) likes_count from likes group by type_id, post_id;

-- 4차 좋아요 테이블 조인
select r2.*, nvl(likes_count,0)likes from
(select type_id, post_id, reg_date, title, nick from(
select rownum rnum, r1.* from(
select type_id, post_id, reg_date, title, nick from daily
union
select type_id, post_id, reg_date, title, nick from review
order by reg_date desc) r1
) where rnum >= 1 and rnum <= 9) r2
left join 
(select type_id, post_id, count(*) likes_count from likes group by type_id, post_id) l1
on
r2.type_id = l1.type_id and r2.post_id = l1.post_id
;