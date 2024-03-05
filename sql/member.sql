drop table if exists 'member';
create table member(
    'id' bigint comment 'id',
    'mobile' varchar(11) comment '手机号',
    primary key ('id'),
    unique key 'mobile_unique' (mobile)
) engine=innodb default charset = uft8mb4 comment='会员';