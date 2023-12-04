select * from REVIEW;

-- review 본문
insert into REVIEW (POST_ID, TITLE, READ_COUNT, REG_DATE, CONTENT, ITEM_TYPE, NICK)
values(REVIEW_SEQ.nextval, REVIEW_SEQ.currval || '번 review', 0, sysdate, '글내용은 스킵', '상의', '준혁');

-- review 댓글
insert into REVIEW_REPLY(re_id, re_content, re_ref, re_level, re_step, re_regdate, post_id, nick)
values (REVIEW_REPLY_SEQ.nextval, REVIEW_REPLY_SEQ.currval || '번 review 댓글', 2, 1, 1, sysdate, REVIEW_SEQ.currval, '혜련');

commit;