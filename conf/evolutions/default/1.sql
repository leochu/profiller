# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table base_model (
  id                        varchar(40) not null,
  constraint pk_base_model primary key (id))
;

create table user (
  id                        varchar(40) not null,
  email                     varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  secret                    varchar(255),
  salt                      varchar(255),
  email_md5                 varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists base_model;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;
