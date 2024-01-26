insert into parent (ID, USERNAME, PASSWORD, NICKNAME, CREATED, BIRTH, PHONE_NUMBER, DTYPE)
values (1, 'parent', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'parent', '2024/01/01', '2000/01/01', '010-1234-5678', 'parent');


insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_STUDENT');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_PARENT');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_TEACHER');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_PARENT');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (1, 'ROLE_ADMIN');