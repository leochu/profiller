# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table base_model (
  id                        varchar(40) not null,
  constraint pk_base_model primary key (id))
;

create table prof_user (
  id                        varchar(40) not null,
  email                     varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  secret                    varchar(512),
  salt                      varchar(512),
  email_md5                 varchar(512),
  constraint uq_prof_user_email unique (email),
  constraint pk_prof_user primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists base_model;

drop table if exists prof_user;

SET REFERENTIAL_INTEGRITY TRUE;

