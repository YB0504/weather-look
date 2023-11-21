select * from COMMUNITY;

-- community 본문
insert into COMMUNITY (POST_ID, TITLE, READ_COUNT, REG_DATE, CONTENT, TYPE_ID, NICK)
values(COMMUNITY_SEQ.nextval, COMMUNITY_SEQ.currval || '번 community', 0, sysdate, '글내용은 스킵', 3, '준혁');

-- community 댓글
insert into COMMUNITY_REPLY(re_id, re_content, re_ref, re_level, re_step, re_regdate, post_id, nick)
values (COMMUNITY_REPLY_SEQ.nextval, COMMUNITY_REPLY_SEQ.currval || '번 community 댓글', 2, 1, 1, sysdate, 2, '준혁');

commit;