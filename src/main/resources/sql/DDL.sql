--Schema oaasis
--Member Table
CREATE TABLE member(
                       user_id varchar(20) primary key,
                       user_pass varchar(100) not null,
                       user_nm varchar(20) not null,
                       user_email varchar(30) not null,
                       user_hpno varchar(20) null,
                       user_role varchar(20) null,
                       otp int4 null,
                       expired boolean,
                       locked boolean,
                       enabled boolean,
                       reg_id varchar(20) not null,
                       reg_date timestamp not null,
                       mod_id varchar(20) not null,
                       mod_date timestamp not null
);

--Test Table
create table test (
                      id varchar(20) primary key,
                      name varchar(20),
                      reg_date timestamp
)

--Company Table
create table company (
    com_cd varchar(30) primary key,
    com_nm varchar(100) not null,
    sol_type varchar(1) not null,
    sot_detail_type varchar(1) not null,
    charge int4,
    cancel_yn varchar(1) not null,
    pos_type varchar(1),
    pda_type varchar(1),
    join_date timestamp ,
    reg_id varchar(20),
    reg_date timestamp ,
    mod_id varchar(20),
    mod_date timestamp
)