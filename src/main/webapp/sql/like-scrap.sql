select *
from SCRAP;

insert into SCRAP
values (SCRAP_SEQ.nextval, 3, 1, '준혁');

with myScrap as (select *
                 from SCRAP
                 where NICK like '%준혁%'),
     allScrap as (select myScrap.SCRAP_ID, myScrap.POST_ID, myScrap.TYPE_ID, DAILY.TITLE, DAILY.NICK as writer_nick
                  from myScrap,
                       DAILY
                  where myScrap.POST_ID = DAILY.POST_ID
                    and myScrap.TYPE_ID = DAILY.TYPE_ID
                  union
                  select myScrap.SCRAP_ID, myScrap.POST_ID, myScrap.TYPE_ID, REVIEW.TITLE, REVIEW.NICK as writer_nick
                  from myScrap,
                       REVIEW
                  where myScrap.POST_ID = REVIEW.POST_ID
                    and myScrap.TYPE_ID = REVIEW.TYPE_ID
                  union
                  select myScrap.SCRAP_ID, myScrap.POST_ID, myScrap.TYPE_ID, COMMUNITY.TITLE, COMMUNITY.NICK as writer_nick
                  from myScrap,
                       COMMUNITY
                  where myScrap.POST_ID = COMMUNITY.POST_ID
                    and myScrap.TYPE_ID = COMMUNITY.TYPE_ID)
select *
from allScrap
order by SCRAP_ID desc;