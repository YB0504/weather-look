-- 1차 검색페이지 union 작업

select type_id, post_id, reg_date, title, nick, read_count from daily
union
select type_id, post_id, reg_date, title, nick, read_count from review
union 
select type_id, post_id, reg_date, title, nick, read_count from community
order by reg_date desc;

-- 2차 rownum 부여

select rownum rnum, s1.* from
(select type_id, post_id, reg_date, title, nick, read_count from daily
union
select type_id, post_id, reg_date, title, nick, read_count from review
union 
select type_id, post_id, reg_date, title, nick, read_count from community
order by reg_date desc) s1;

-- 3차 갯수 커팅 (이 작업 전 검색기능 추가)

select type_id, post_id, reg_date, title, nick, read_count from
(select rownum rnum, s1.* from
(select type_id, post_id, reg_date, title, nick, read_count from daily
union
select type_id, post_id, reg_date, title, nick, read_count from review
union 
select type_id, post_id, reg_date, title, nick, read_count from community
order by reg_date desc) s1)
where rnum >= 1 and rnum <= 10
;

-- 4차 게시판명 결합

select board_type.type_name type_name, s2.* from
(select type_id, post_id, reg_date, title, nick, read_count from
(select rownum rnum, s1.* from
(select type_id, post_id, reg_date, title, nick, read_count from daily
union
select type_id, post_id, reg_date, title, nick, read_count from review
union 
select type_id, post_id, reg_date, title, nick, read_count from community
order by reg_date desc) s1)
where rnum >= 1 and rnum <= 10) s2
left join
board_type
on
s2.type_id = board_type.type_id
;



-- 5차 좋아요, 스크랩 카운팅
select type_id, post_id, count(*) likes_count from likes group by type_id, post_id;
select type_id, post_id, count(*) scraps_count from scrap group by type_id, post_id;

-- 6차

select s2.*, board_type.type_name type_name, nvl(likes.likes_count,0) likes, nvl(scraps.scraps_count,0) scraps from
    (select type_id, post_id, reg_date, title, nick, read_count from
        (select rownum rnum, s1.* from
            (select type_id, post_id, reg_date, title, nick, read_count from daily
                union
                select type_id, post_id, reg_date, title, nick, read_count from review
                union 
                select type_id, post_id, reg_date, title, nick, read_count from community
                order by reg_date desc) s1)
            where rnum >= 1 and rnum <= 10) s2
left join
board_type
on
s2.type_id = board_type.type_id
left join
(select type_id, post_id, count(*) likes_count from likes group by type_id, post_id) likes
on
s2.type_id = likes.type_id and s2.post_id = likes.post_id
left join
(select type_id, post_id, count(*) scraps_count from scrap group by type_id, post_id) scraps
on
s2.type_id = scraps.type_id and s2.post_id = scraps.post_id
;


-- 검색결과 출력 갯수

select count(*) listcount from
            (select type_id, post_id, reg_date, title, nick from daily
                union
                select type_id, post_id, reg_date, title, nick from review
                union 
                select type_id, post_id, reg_date, title, nick from community
                order by reg_date desc)
;