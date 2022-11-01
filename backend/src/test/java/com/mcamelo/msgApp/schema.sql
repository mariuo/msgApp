    create table if not exists  tb_comment (
       id  bigserial not null,
        content varchar(255),
        post_id int8,
        user_id int8,
        primary key (id)
    );

    create table if not exists tb_notification (
       id  bigserial not null,
        content varchar(255),
        delivered boolean not null,
        notifications_type int4,
        readed boolean not null,
        user_from_id int8,
        user_to_id int8,
        primary key (id)
    );

    create table if not exists tb_post (
       id  bigserial not null,
        content varchar(255),
        created TIMESTAMP WITHOUT TIME ZONE,
        image_url varchar(255),
        user_id int8,
        primary key (id)
    );

    create table if not exists tb_post_user_likes (
       post_id int8 not null,
        user_id int8 not null,
        primary key (post_id, user_id)
    );

    create if not exists table tb_role (
       id  bigserial not null,
        authority varchar(255),
        primary key (id)
    );

    create if not exists table tb_user (
       id  bigserial not null,
        image_url_profile varchar(255),
        name varchar(255),
        password varchar(255),
        primary key (id)
    );

    create if not exists table tb_user_role (
       user_id int8 not null,
        role_id int8 not null,
        primary key (user_id, role_id)
    );

    alter table tb_user
       add constraint UK_jus3xa0l3009mkpbt9i5ilxx3 unique (name);

    alter table tb_comment
       add constraint FKebak8c8m45519djplq0wanuj3
       foreign key (post_id)
       references tb_post;

    alter table tb_comment
       add constraint FK45c1cuqlljd60ihc9j0962ekq
       foreign key (user_id)
       references tb_user;

    alter table tb_notification
       add constraint FKer168j1suppnokgrar8et2kgd
       foreign key (user_from_id)
       references tb_user;

    alter table tb_notification
       add constraint FKpvrswrm9jwmt50cnbs8w41x4
       foreign key (user_to_id)
       references tb_user;

    alter table tb_post
       add constraint FKhx7a7k3pf66vpddqg5pr12anw
       foreign key (user_id)
       references tb_user;

    alter table tb_post_user_likes
       add constraint FKl8d8buniaq5opn5eh8s3xjno9
       foreign key (user_id)
       references tb_user;

    alter table tb_post_user_likes
       add constraint FKhvfavvgq4p1fi2mrs0df20m0p
       foreign key (post_id)
       references tb_post;

    alter table tb_user_role
       add constraint FKea2ootw6b6bb0xt3ptl28bymv
       foreign key (role_id)
       references tb_role;

    alter table tb_user_role
       add constraint FK7vn3h53d0tqdimm8cp45gc0kl
       foreign key (user_id)
       references tb_user;



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