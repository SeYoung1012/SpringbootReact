

create table board (
	board_idx numeric(4) primary key,
	board_name varchar(20),
	board_ttl varchar(100),
	board_cn varchar(300),
	board_date timestamp  default current_timestamp
	
);

select * from board;

create sequence board_seq;



insert into board(board_idx, board_name,board_ttl,board_cn, board_date)
values(nextval('board_seq'), '피카츄', '글제목1', '글내용1', current_timestamp);

insert into board(board_idx, board_name,board_ttl,board_cn, board_date)
values(nextval('board_seq'), '파이리', '글제목2', '글내용2', current_timestamp);

insert into board(board_idx, board_name,board_ttl,board_cn, board_date)
values(nextval('board_seq'), '꼬부기', '글제목3', '글내용3', current_timestamp);


commit;

--reply_board_idx를 외래키로 쓸 예정.
create table reply (
                       reply_idx NUMERIC(4) primary key,
                       reply_name varchar(20),
                       reply_cn varchar(300),
                       reply_date timestamp default current_timestamp,
                       reply_board_id NUMERIC(4)
);

create sequence reply_board_seq;

commit;

insert into reply(reply_idx, reply_name, reply_cn, reply_date, reply_board_id)
values(nextval('reply_board_seq'), '김두부','댓글내용1', current_timestamp, 1);

insert into reply(reply_idx, reply_name,reply_cn,reply_date, reply_board_id)
values(nextval('reply_board_seq'), '오라라', '댓글내용2', current_timestamp, 2);

insert into reply(reply_idx, reply_name,reply_cn,reply_date, reply_board_id)
values(nextval('reply_board_seq'), '이루다', '댓글내용3', current_timestamp, 3);


select * from reply;
select * from board;