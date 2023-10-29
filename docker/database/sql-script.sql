create database if not exists 'syslogdb';
create user user identified by 'user';
grant all privileges on syslogdb.* to user;
show grants for user;

alter database 'syslogdb' character set utf8 collate utf8_unicode_ci;
use 'syslogdb';

drop table if exists syslogs;
create table syslogs (
    id integer not null auto_increment,
    filename varchar(25),
    primary key (id)
);

drop table if exists failed_logins;
create table failed_logins (
    id integer not null auto_increment,
    syslogs_id integer,
    logtime timestamp not null,
    message varchar(50),
    host varchar(50),
    user varchar(25),
    failure_count integer,
    primary key (id)
);

drop constraint failed_logins_syslogs
alter table failed_logins
    add constraint failed_logins_syslogs
        foreign key (syslogs_id)
            references syslogs (id)

drop table if exists success_logins;
create table success_logins (
    id integer not null auto_increment,
    syslogs_id integer,
    logtime timestamp not null,
    message varchar(50),
    url varchar(50),
    host varchar(50),
    login_time varchar(25),
    primary key (id)
);

drop constraint success_logins_syslogs
alter table success_logins
    add constraint success_logins_syslogs
        foreign key (syslogs_id)
            references syslogs (id)

