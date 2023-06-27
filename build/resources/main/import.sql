drop all objects;

CREATE TABLE CODE_GROUP(idx bigint not null auto_increment primary key, CODE_GROUP VARCHAR(50), CODE_GROUP_NAME VARCHAR(100), REG_DATE TIMESTAMP(6), UPD_DATE TIMESTAMP(6));
CREATE TABLE CODE(idx bigint not null auto_increment primary key, CODE VARCHAR(50), CODE_NAME VARCHAR(100), CODE_GROUP bigint, REG_DATE TIMESTAMP(6), UPD_DATE TIMESTAMP(6));

alter table code add constraint fk_code_group foreign key (code_group) references code_group (idx);

INSERT INTO code_group (code_group, code_group_name, reg_date, upd_date) VALUES('code_group1', '코드그룹1', now(), now());
INSERT INTO code_group (code_group, code_group_name, reg_date, upd_date) VALUES('code_group2', '코드그룹2', now(), now());
INSERT INTO code_group (code_group, code_group_name, reg_date, upd_date) VALUES('code_group3', '코드그룹3', now(), now());
INSERT INTO code_group (code_group, code_group_name, reg_date, upd_date) VALUES('code_group4', '코드그룹4', now(), now());

INSERT INTO code (code, code_name, code_group, reg_date, upd_date) values('code1', '코드1', 1, now(), now());
INSERT INTO code (code, code_name, code_group, reg_date, upd_date) values('code2', '코드2', 1, now(), now());
INSERT INTO code (code, code_name, code_group, reg_date, upd_date) values('code3', '코드3', 2, now(), now());
INSERT INTO code (code, code_name, code_group, reg_date, upd_date) values('code4', '코드4', 2, now(), now());
