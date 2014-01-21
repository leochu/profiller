# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table base_model (
  id                        varchar(40) not null,
  constraint pk_base_model primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists base_model;

SET REFERENTIAL_INTEGRITY TRUE;

