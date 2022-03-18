create table users(
                      id serial primary key,
                      username varchar(200),
                      password varchar(200),
                      deleted boolean
);

create table twitter(
                        id serial primary key ,
                        description varchar(280),
                        users_id integer,
                        deleted boolean,
                        constraint fk_users_id foreign key (users_id) references users(id)
);


create table comment(
                        id serial primary key ,
                        description varchar(280),
                        users_id integer,
                        created_date date,
                        twitter_id integer,
                        deleted boolean,
                        constraint fk_users_id foreign key (users_id) references users(id)
);