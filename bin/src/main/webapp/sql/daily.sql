select * from DAILY;

insert into daily (post_id, title, read_count, reg_date, content, temperature, latitude, longitude, type_id, nick)
values(1, '선홍의 글 제목', 0, sysdate, '글내용은 스킵', 10.5, 36.2, 128.4, 1, '선홍');