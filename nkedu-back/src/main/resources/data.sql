INSERT IGNORE INTO nkedu.authority (authority_name) VALUES ('ROLE_ADMIN');
INSERT IGNORE INTO nkedu.authority (authority_name) VALUES ('ROLE_USER');
INSERT IGNORE INTO nkedu.authority (authority_name) VALUES ('ROLE_STUDENT');
INSERT IGNORE INTO nkedu.authority (authority_name) VALUES ('ROLE_PARENT');
INSERT IGNORE INTO nkedu.authority (authority_name) VALUES ('ROLE_TEACHER');

INSERT INTO nkedu.user (`dtype`, `id`, `birth`,`created`, `nickname`,`password`, `phone_number`, `username`) VALUES ('admin', '1', '0001-01-01
00:00:00' ,'0001-01-01', 'admin','$2a$10$BARPN8j46OKRLaaLKdExUOVCufsQcXAwAoYcDQJ/9xgyvzF9FVxrS' ,'010-0000-0000', 'admin');


insert into nkedu.user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into nkedu.user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
