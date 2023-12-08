select * from DAILY;

insert into daily
values(daily_seq.nextval, '데일리', 0, sysdate, '글내용은 스킵', 4.0, 37.3, 125.6, 1, '선홍');


delete from daily where nick = '선홍';