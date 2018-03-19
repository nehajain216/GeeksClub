delete from user_skill;
delete from Skills;
delete from users;

insert into Skills(id,name) values
(1,'JAVA'),
(2,'.Net'),
(3,'GO')
;

insert into users(id,name,email,password) values
(1,'Neha','neha@admin.com','admin'),
(2,'Siva','siva@admin.com','admin'),
(3,'Chakri','chakri@admin.com','admin')
;

insert into user_skill(user_id,skill_id) values
(1,1),
(1,2),
(2,1),
(2,3),
(3,3)
;

