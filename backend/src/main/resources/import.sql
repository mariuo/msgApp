INSERT INTO TB_USER (NAME, PASSWORD, IMAGE_URL_PROFILE) VALUES('mario', '$2a$10$UoZsTxHmdN2zho36tBHYl.wNEM7M.UAB3Lma4oJzi5ZQYSfmYv/ly', 'https://avatars.githubusercontent.com/u/30843415?v=4');
INSERT INTO TB_USER (NAME, PASSWORD, IMAGE_URL_PROFILE) VALUES('felipe', '$2a$10$UoZsTxHmdN2zho36tBHYl.wNEM7M.UAB3Lma4oJzi5ZQYSfmYv/ly', 'https://avatars.githubusercontent.com/u/12265211?v=4');
INSERT INTO TB_USER (NAME, PASSWORD, IMAGE_URL_PROFILE) VALUES('bruno', '$2a$10$UoZsTxHmdN2zho36tBHYl.wNEM7M.UAB3Lma4oJzi5ZQYSfmYv/ly', 'https://avatars.githubusercontent.com/u/17804667?v=4');
INSERT INTO TB_USER (NAME, PASSWORD, IMAGE_URL_PROFILE) VALUES('paulo', '$2a$10$UoZsTxHmdN2zho36tBHYl.wNEM7M.UAB3Lma4oJzi5ZQYSfmYv/ly', 'https://avatars.githubusercontent.com/u/55068173?v=4');

INSERT INTO TB_ROLE(AUTHORITY) VALUES ('ROLE_ADMIN');
INSERT INTO TB_ROLE(AUTHORITY) VALUES ('ROLE_USER');

INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID) VALUES(1,1);
INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID) VALUES(1,2);
INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID) VALUES(2,2);
INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID) VALUES(3,2);
INSERT INTO TB_USER_ROLE(USER_ID, ROLE_ID) VALUES(4,2);


INSERT INTO TB_POST(USER_ID, CONTENT, CREATED, IMAGE_URL) VALUES(1,'Hello everyone, this is Mario', TIMESTAMP WITH TIME ZONE '2021-11-20T04:00:00Z', 'https://github.githubassets.com/images/modules/profile/achievements/starstruck-default.png');
INSERT INTO TB_POST(USER_ID, CONTENT, CREATED, IMAGE_URL) VALUES(2,'Hello everyone, this is Felipe my second post.', TIMESTAMP WITH TIME ZONE '2021-11-20T04:00:00Z', 'https://github.githubassets.com/images/modules/profile/achievements/starstruck-default.png');

INSERT INTO TB_COMMENT(POST_ID, USER_ID, CONTENT) VALUES(1,2,'Welcome Mariooo');
INSERT INTO TB_COMMENT(POST_ID, USER_ID, CONTENT) VALUES(1,3,'Hope to see more here....');
INSERT INTO TB_COMMENT(POST_ID, USER_ID, CONTENT) VALUES(1,4,'Congrats!!!');


INSERT INTO TB_COMMENT(POST_ID, USER_ID, CONTENT) VALUES(2,1,'Felipe mannn thats nice!');
INSERT INTO TB_COMMENT(POST_ID, USER_ID, CONTENT) VALUES(2,3,'Great news....');

INSERT INTO TB_POST_USER_LIKES(POST_ID, USER_ID) VALUES(1,2);
INSERT INTO TB_POST_USER_LIKES(POST_ID, USER_ID) VALUES(1,3);
INSERT INTO TB_POST_USER_LIKES(POST_ID, USER_ID) VALUES(1,4);

INSERT INTO TB_POST_USER_LIKES(POST_ID, USER_ID) VALUES(2,3);
INSERT INTO TB_POST_USER_LIKES(POST_ID, USER_ID) VALUES(2,4);

INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 0, 1, 2);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 0, 1, 3);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 0, 1, 4);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 1, 1, 2);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 1, 1, 3);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 1, 1, 4);

INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 0, 2, 1);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 0, 2, 3);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 0, 2, 4);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 1, 2, 1);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 1, 2, 3);
INSERT INTO TB_NOTIFICATION(CONTENT, DELIVERED, READED, NOTIFICATIONS_TYPE, USER_TO_ID, USER_FROM_ID) VALUES('Recebeu', 'false', 'false', 1, 2, 4);