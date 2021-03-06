create table user
(
    id    bigint not null auto_increment,
    email varchar(255),
    login varchar(255),
    nome  varchar(255),
    senha varchar(255),
    primary key (id)
);

create table role
(
    id   bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null
);

alter table user_roles
    add constraint FK_user_roles_role
        foreign key (role_id)
            references role (id);

alter table user_roles
    add constraint FK_user_roles_user
        foreign key (user_id)
            references user (id);

insert into role(id, nome)
values (1, 'ROLE_USER');
insert into role(id, nome)
values (2, 'ROLE_ADMIN');

insert into user(id, nome, email, login, senha)
values (1, 'Reinaldo Junior', 'reijunior100@gmail.com', 'reijunior100',
        '$2a$10$CRbotml3KSybEumat.K.AeNTBfdaBUUMerXVcnFVZBSywW.DjHJF.');
insert into user(id, nome, email, login, senha)
values (2, 'Admin', 'admin@gmail.com', 'admin', '$2a$10$CRbotml3KSybEumat.K.AeNTBfdaBUUMerXVcnFVZBSywW.DjHJF.');
insert into user(id, nome, email, login, senha)
values (3, 'User', 'user@gmail.com', 'user', '$2a$10$CRbotml3KSybEumat.K.AeNTBfdaBUUMerXVcnFVZBSywW.DjHJF.');


insert into user_roles(user_id, role_id)
values (1, 1);
insert into user_roles(user_id, role_id)
values (2, 2);
insert into user_roles(user_id, role_id)
values (3, 1);

select *
from role;
select *
from user;
select *
from user_roles;