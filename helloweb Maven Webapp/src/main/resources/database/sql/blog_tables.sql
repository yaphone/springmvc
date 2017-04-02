/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/3 0:48:37                             */
/*==============================================================*/


drop table if exists blog;

drop table if exists blog_comment;

drop table if exists sub_comment;

/*==============================================================*/
/* Table: blog                                                  */
/*==============================================================*/
create table blog
(
   id                   bigint not null auto_increment,
   title                char(255),
   content              text,
   publish_time         date,
   update_time          date,
   classify             char(255),
   keywords             char(255),
   music_url            char(255),
   reading_count        bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: blog_comment                                          */
/*==============================================================*/
create table blog_comment
(
   id                   bigint not null auto_increment,
   nick_name            char(255),
   publish_time         date,
   content              text,
   email                char(255),
   blog_id              bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: sub_comment                                           */
/*==============================================================*/
create table sub_comment
(
   id                   bigint not null auto_increment,
   comment_id           bigint not null,
   nick_name            char(255),
   content              text,
   email                char(255),
   publish_time         date,
   primary key (id)
);

