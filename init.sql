create database if not exists tree_hole;

create table admin_table
(
    id      int auto_increment
        primary key,
    user_id int not null,
    constraint admin_table_user_id_uindex
        unique (user_id)
);

create table article_table
(
    id           int auto_increment
        primary key,
    author       int                                    not null,
    title        varchar(255) default ''                null,
    content      text                                   not null,
    emotion      varchar(255) default 'Happy'           null,
    images       text                                   null,
    `like`       int          default 0                 null,
    dislike      int          default 0                 null,
    readCount    int          default 0                 null,
    createTime   datetime     default CURRENT_TIMESTAMP null,
    lastEditTime datetime     default CURRENT_TIMESTAMP null,
    verify       int          default 0                 not null
);

create table comment_table
(
    id          int auto_increment
        primary key,
    author      int                                not null,
    article_id  int                                not null,
    comment_id  int                                null,
    content     text                               not null,
    agree       int      default 0                 null,
    disagree    int      default 0                 null,
    create_time datetime default CURRENT_TIMESTAMP null
);

create table diary_table
(
    diary_id int auto_increment
        primary key,
    title    varchar(100)   null,
    author   int            null,
    type     int default 0  null,
    url      varchar(1000)  null,
    content  varchar(10000) null
);

create table push_table
(
    id      int auto_increment
        primary key,
    type    int default 0 null,
    author  int           not null,
    title   varchar(255)  null,
    content text          not null,
    file    varchar(255)  null
);

create table report_table
(
    id            int auto_increment
        primary key,
    comment_id    int           null,
    article_id    int           null,
    report_author int           not null,
    report_type   int default 0 not null,
    reason        text          null
)
    collate = utf8_unicode_ci;

create table token_table
(
    token   varchar(255) not null,
    user_id int          not null,
    constraint token_table_token_uindex
        unique (token),
    constraint token_table_userId_uindex
        unique (user_id)
);

alter table token_table
    add primary key (token);

create table user_table
(
    id               int auto_increment,
    username         varchar(255)            not null,
    password         varchar(255)            not null,
    nickname         varchar(255) default '' null,
    motto            text                    null,
    head_image       text                    null,
    like_articles    text                    null,
    dislike_articles text                    null,
    like_comments    text                    null,
    dislike_comments text                    null,
    phone            varchar(255)            null,
    constraint user_table_id_uindex
        unique (id),
    constraint user_table_username_uindex
        unique (username)
);

alter table user_table
    add primary key (id);

