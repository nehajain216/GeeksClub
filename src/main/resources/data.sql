delete from user_skill;
delete from Skills;
delete from users;
delete from user_role;
delete from announcements;
delete from jobs;
delete from categories;
delete from links;


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

insert into announcements(id,title,description,created_on,created_by) values
(1,'The Incredible Shrinking Java Platform','In an earlier blog, I wrote about the changes for deployment of Java applications that will take effect in JDK 11. Specifically, the removal of support for Applets and Java Web Start. In this post, I thought I’d delve a bit deeper into the suggested way to ship application code when you make the move to JDK 9 or later, so can take advantage of the Java Platform Module System (JPMS).','2017-12-12',1),
(2,'Tracking Cookies and GDPR','The GDPR is the new data protection regulation, as you probably already know. I have given detailed practical advice for what it means for developers (and product owners). However, there is one thing missing there - cookies. The elephant in the room.','2016-01-30',2),
(3,'Working With XML in Scala','Scala treats XML as a first-class citizen. So, instead of embedding XML documents into strings, you can place them inline in your code like you would place an int or double value.','2015-06-26',1);

insert into jobs(id,title,description,created_on,created_by) values
(1,'SpringBoot developers with 3+ years of experience','SpringBoot developers','2018-01-25',1),
(2,'Kotlin developers with 3+ years of experience','Kotlin developers','2018-02-25',2),
(3,'Scala developers with 2+ years of experience','Scala developers','2017-01-25',3);

insert into categories(id,name) values
(1,'JAVA'),
(2,'AGILE'),
(3,'CLOUD'),
(4,'BIGDATA')
;

insert into links(id,title,url,category_id,created_on,created_by) values
(1,'Introduction to Java Bytecode','https://dzone.com/articles/introduction-to-java-bytecode',1,'2017-10-26',1),
(2,'Tips to Be a Cross-Platform Game Developer','https://dzone.com/articles/tips-to-be-a-cross-platform-game-developer',2,'2017-09-26',1),
(3,'3 Reasons Why Unplanned Meetings Do More Harm Than Good','https://dzone.com/articles/tips-to-be-a-cross-platform-game-developer',2,'2017-11-26',1),
(4,'The Importance of Graph Visualization Tools: Exploring the Paradise Papers','https://dzone.com/articles/tips-to-be-a-cross-platform-game-developer',4,'2018-03-26',1),
(5,'5 Ways Data Analytics Is Disrupting Business Models','https://dzone.com/articles/tips-to-be-a-cross-platform-game-developer',4,'2018-02-26',2),
(6,'Top 11 Continuous Delivery Tools for Kubernetes','https://dzone.com/articles/tips-to-be-a-cross-platform-game-developer',3,'2017-11-26',2),
(7,'A Serverless Message Queue Without the Glue','https://dzone.com/articles/tips-to-be-a-cross-platform-game-developer',3,'2017-11-26',2)
;




