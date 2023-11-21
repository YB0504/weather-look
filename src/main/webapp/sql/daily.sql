select * from DAILY;

-- daily 본문
insert into daily (post_id, title, read_count, reg_date, content, temperature, latitude, longitude, type_id, nick)
values(DAILY_SEQ.nextval, DAILY_SEQ.currval || '번 daily', 0, sysdate, '글내용은 스킵', 10.5, 36.2, 128.4, 1, '준혁');

-- daily 댓글
insert into DAILY_REPLY(re_id, re_content, re_ref, re_level, re_step, re_regdate, post_id, nick)
values (DAILY_REPLY_SEQ.nextval, DAILY_REPLY_SEQ.currval || '번 daily 댓글', 2, 1, 1, sysdate, 2, '혜련');