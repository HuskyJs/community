create table table_comment
(
    id           BIGINT auto_increment primary key,
    parent_id    bigint not null ,
    type         int not null ,
    commentator  int not null ,
    gmt_create   BIGINT not null ,
    gmt_modified bigint not null ,
    like_count   bigint default 0
);

