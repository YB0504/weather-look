-- mypage 게시글

with allpost as (select POST_ID, TITLE, READ_COUNT, REG_DATE, CONTENT, NICK
                 from DAILY
                 union
                 select POST_ID, TITLE, READ_COUNT, REG_DATE, CONTENT, NICK
                 from REVIEW
                 union
                 select POST_ID, TITLE, READ_COUNT, REG_DATE, CONTENT, NICK
                 from COMMUNITY)
select *
from allpost
where NICK like '%준혁%'
order by REG_DATE desc;

-- mypage 댓글 목록
with allcomment as (select *
                    from DAILY_REPLY
                    union
                    select *
                    from REVIEW_REPLY
                    union
                    select *
                    from COMMUNITY_REPLY)
select *
from allcomment
where NICK like '%준혁%'
order by RE_REGDATE desc;

with myLike as (select *
                from LIKES
                where NICK like '%준혁%'),
     allLike as (select myLike.LIKE_ID, myLike.POST_ID, myLike.TYPE_ID, DAILY.CONTENT, DAILY.NICK as writer_nick
                 from myLike,
                      DAILY
                 where myLike.POST_ID = DAILY.POST_ID
                   and myLike.TYPE_ID = DAILY.TYPE_ID
                 union
                 select myLike.LIKE_ID, myLike.POST_ID, myLike.TYPE_ID, REVIEW.CONTENT, REVIEW.NICK as writer_nick
                 from myLike,
                      REVIEW
                 where myLike.POST_ID = REVIEW.POST_ID
                   and myLike.TYPE_ID = REVIEW.TYPE_ID
                 union
                 select myLike.LIKE_ID, myLike.POST_ID, myLike.TYPE_ID, COMMUNITY.CONTENT, COMMUNITY.NICK as writer_nick
                 from myLike,
                      COMMUNITY
                 where myLike.POST_ID = COMMUNITY.POST_ID
                   and myLike.TYPE_ID = COMMUNITY.TYPE_ID)
select *
from allLike
order by LIKE_ID desc;

-- 댓글 리스트 출력
with allcomment as (select 'D' as BOARD_TYPE, D.POST_ID, D.TITLE, D.NICK as WRITER_NICK, D.REG_DATE as POST_REG_DATE, D.READ_COUNT, RE_REGDATE
                    from DAILY D , DAILY_REPLY DR
                    where D.POST_ID=DR.POST_ID and
                            DR.NICK='준혁'
                    union
                    select 'R' as BOARD_TYPE, R.POST_ID, R.TITLE, R.NICK as WRITER_NICK, R.REG_DATE as POST_REG_DATE, R.READ_COUNT, RE_REGDATE
                    from REVIEW R, REVIEW_REPLY RR
                    where R.POST_ID=RR.POST_ID and
                        RR.NICK='준혁'
                    union
                    select 'C' as BOARD_TYPE, C.POST_ID, C.TITLE, C.NICK as WRITER_NICK, C.REG_DATE as POST_REG_DATE, C.READ_COUNT, RE_REGDATE
                    from COMMUNITY C, COMMUNITY_REPLY CR
                    where C.POST_ID=CR.POST_ID and
                        CR.NICK='준혁')
select *
from allcomment
order by RE_REGDATE desc;

with allpost as (select * from DAILY
                 union
                 select * from REVIEW
                 union
                 select * from COMMUNITY)
select *
from allpost
where NICK='준혁';

-- 페이징 적용된 postList
with allpost as (select 'daily' as BOARD_TYPE, POST_ID, TITLE, NICK, REG_DATE, READ_COUNT
                 from DAILY
                 union
                 select 'review' as BOARD_TYPE, POST_ID, TITLE, NICK, REG_DATE, READ_COUNT
                 from REVIEW
                 union
                 select 'community' as BOARD_TYPE, POST_ID, TITLE, NICK, REG_DATE, READ_COUNT
                 from COMMUNITY),
     sortedpost as (
         select *
         from allpost
         where NICK = '준혁'
         order by REG_DATE desc),
     rownumpost as (
         select ROWNUM rnum, board_type, post_id, title, nick, reg_date, read_count
         from sortedpost
     )
select * from rownumpost where rnum between 1 and 10