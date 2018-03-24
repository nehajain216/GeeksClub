delete from user_skill;
delete from Skills;
delete from users;
delete from user_role;


insert into Skills(id,name) values
(1,'JAVA'),
(2,'.Net'),
(3,'GO')
;

insert into users(id,name,email,password) values
(1,'Neha','neha@admin.com','$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS'),
(2,'Siva','siva@admin.com','$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS'),
(3,'Chakri','chakri@admin.com','$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS')
;

insert into user_skill(user_id,skill_id) values
(1,1),
(1,2),
(2,1),
(2,3),
(3,3)
;

insert into roles(id,name) values
(1,'ROLE_ADMIN'),
(2,'ROLE_MEMBER')
;

insert into user_role(user_id,role_id) values
(1,1),
(2,2),
(3,2)
;
