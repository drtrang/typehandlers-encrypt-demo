drop table if exists base_code;

create table base_code (
  id            int primary key auto_increment,
  code_type     varchar(64) not null,
  code_value    varchar(64) not null,
  remark        varchar(64) null,
  create_time   timestamp not null default current_timestamp,
  update_time   timestamp not null default current_timestamp
);