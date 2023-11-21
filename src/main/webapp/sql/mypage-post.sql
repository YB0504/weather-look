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