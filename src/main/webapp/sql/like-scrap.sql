-- 스크랩
insert into DAILY_SCRAP(scrap_id, nick, post_id) values (DAILY_SCRAP_SEQ.nextval, '준혁', DAILY_SEQ.currval);
insert into REVIEW_SCRAP values (REVIEW_SCRAP_SEQ.nextval, '준혁', REVIEW_SEQ.currval);
insert into COMMUNITY_SCRAP(scrap_id, nick, post_id) values (COMMUNITY_SCRAP_SEQ.nextval, '준혁', 3);

-- 좋아요
insert into DAILY_LIKE(LIKE_ID, nick, post_id) values (DAILY_LIKE_SEQ.nextval, '준혁', 3);
insert into REVIEW_LIKE(LIKE_ID, nick, post_id) values (REVIEW_LIKE_SEQ.nextval, '준혁', 3);
insert into COMMUNITY_LIKE(LIKE_ID, nick, post_id) values (COMMUNITY_LIKE_SEQ.nextval, '준혁', 3);