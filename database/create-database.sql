-- Created by Vertabelo (http://vertabelo.com)
-- Script type: create
-- Scope: [tables, views, references, sequences, procedures]
-- Generated at Sat Oct 11 22:55:55 UTC 2014




-- tables
-- Table: post_message
CREATE TABLE post_message (
    id int  NOT NULL,
    user_account_id int  NOT NULL,
    content varchar(1000)  NOT NULL,
    insert_ts timestamp  NOT NULL,
    CONSTRAINT post_message_pk PRIMARY KEY (id)
);



-- Table: private_message
CREATE TABLE private_message (
    id int  NOT NULL,
    sender_id int  NOT NULL,
    recipient_id int  NOT NULL,
    subject varchar(100)  NOT NULL,
    content varchar(1000)  NOT NULL,
    insert_ts timestamp  NOT NULL,
    CONSTRAINT private_message_pk PRIMARY KEY (id)
);



-- Table: user_account
CREATE TABLE user_account (
    id int  NOT NULL,
    login varchar(100)  NOT NULL,
    password varchar(100)  NOT NULL,
    full_name varchar(100)  NOT NULL,
    creation_ts timestamp  NOT NULL,
    CONSTRAINT login_ak UNIQUE (login) NOT DEFERRABLE  INITIALLY IMMEDIATE ,
    CONSTRAINT user_account_pk PRIMARY KEY (id)
);



-- Table: users_connection
CREATE TABLE users_connection (
    user_account_1_id int  NOT NULL,
    user_account_2_id int  NOT NULL,
    CONSTRAINT users_connection_pk PRIMARY KEY (user_account_1_id,user_account_2_id)
);

CREATE INDEX reverse_pk_idx on users_connection (user_account_2_id ASC,user_account_1_id ASC);







-- views
-- View: user_connections_view
CREATE VIEW user_connections_view AS
select
  uc.user_account_1_id as user_account_id,
  uc.user_account_2_id as connected_user_id,
  ua.full_name as connected_user_full_name
from users_connection uc
join user_account ua on (ua.id = uc.user_account_2_id)

union all

select
  uc.user_account_2_id as user_account_id,
  uc.user_account_1_id as connected_user_id,
  ua.full_name as connected_user_full_name
from users_connection uc
join user_account ua on (ua.id = uc.user_account_1_id);

-- View: private_message_view
CREATE VIEW private_message_view AS
select
  pm.id,
  pm.sender_id,
  uas.full_name as sender_full_name,
  pm.recipient_id,
  uar.full_name as recipient_full_name,
  pm.subject,
  pm.content,
  pm.insert_ts
  
from
  private_message pm
  join user_account uas on (pm.sender_id = uas.id)
  join user_account uar on (pm.recipient_id = uar.id);

-- View: wall_posts_view
CREATE VIEW wall_posts_view AS
select
  pm.id as post_id,
  pm.user_account_id as post_user_id,
  pm.content as post_content,
  pm.insert_ts as post_insert_ts,
  ucv.connected_user_full_name as post_user_full_name,
  ucv.user_account_id as wall_user_id

from post_message pm
  join user_connections_view ucv
    on (pm.user_account_id = ucv.connected_user_id)

union all
select
  pm.id as post_id,
  pm.user_account_id as post_user_id,
  pm.content as post_content,
  pm.insert_ts as post_insert_ts,
  ua.full_name as post_user_full_name,
  ua.id as wall_user_id

from post_message pm
  join user_account ua
    on (pm.user_account_id = ua.id);




-- foreign keys
-- Reference:  post_message_user_account (table: post_message)


ALTER TABLE post_message ADD CONSTRAINT post_message_user_account 
    FOREIGN KEY (user_account_id)
    REFERENCES user_account (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  private_message_receiver (table: private_message)


ALTER TABLE private_message ADD CONSTRAINT private_message_receiver 
    FOREIGN KEY (recipient_id)
    REFERENCES user_account (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  private_message_sender (table: private_message)


ALTER TABLE private_message ADD CONSTRAINT private_message_sender 
    FOREIGN KEY (sender_id)
    REFERENCES user_account (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  users_connection_1 (table: users_connection)


ALTER TABLE users_connection ADD CONSTRAINT users_connection_1 
    FOREIGN KEY (user_account_1_id)
    REFERENCES user_account (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  users_connection_2 (table: users_connection)


ALTER TABLE users_connection ADD CONSTRAINT users_connection_2 
    FOREIGN KEY (user_account_2_id)
    REFERENCES user_account (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;




-- sequences
-- Sequence: post_message_seq



CREATE SEQUENCE post_message_seq
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1000 
      
      NO CYCLE
      
;


-- Sequence: private_message_seq



CREATE SEQUENCE private_message_seq
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1000 
      
      NO CYCLE
      
;


-- Sequence: user_account_seq



CREATE SEQUENCE user_account_seq
      INCREMENT BY 1
      NO MINVALUE
      NO MAXVALUE
      START WITH 1000 
      
      NO CYCLE
      
;






-- TEST DATA

insert into user_account (id, login, password, full_name, creation_ts) values (1, 'mkolodziejski', 'qwerty', 'Michał Kołodziejski', NOW());
insert into user_account (id, login, password, full_name, creation_ts) values (2, 'abacki', 'qwerty', 'Adam Abacki', NOW());
insert into user_account (id, login, password, full_name, creation_ts) values (3, 'babacki', 'qwerty', 'Bartek Babacki', NOW());
insert into user_account (id, login, password, full_name, creation_ts) values (4, 'cabacki', 'qwerty', 'Czarek Cabacki', NOW());

insert into users_connection (user_account_1_id, user_account_2_id) values (1, 2);
insert into users_connection (user_account_1_id, user_account_2_id) values (3, 1);
insert into users_connection (user_account_1_id, user_account_2_id) values (4, 1);
insert into users_connection (user_account_1_id, user_account_2_id) values (2, 3);

insert into post_message (id, user_account_id, content, insert_ts) values (1, 1, 'Michała pierwszy post', NOW());
insert into post_message (id, user_account_id, content, insert_ts) values (2, 2, 'Adam pisze', NOW());
insert into post_message (id, user_account_id, content, insert_ts) values (3, 3, 'Bartosz napisał', NOW());
insert into post_message (id, user_account_id, content, insert_ts) values (4, 4, 'Cezarego post pierwszy', NOW());

insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (1, 1, 2, 'Hello', 'Jeden od Michała', NOW());
insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (2, 1, 2, 'Re: Hello', 'Dwa od Michała', NOW());
insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (3, 2, 1, 'Re: Re: Hello', 'Trzy od Adama do Michała', NOW());
insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (4, 1, 2, 'Re: Re: Re: Hello', 'Cztery od Michała', NOW());
insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (5, 3, 1, 'Testowa', 'Pierwsza od Bartka do Michała', NOW());
insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (6, 1, 3, 'Re: Testowa', 'Druga od Michała', NOW());
insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (7, 3, 1, 'Re: Re: Testowa', 'Trzecia od Bartka do Michała', NOW());
insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values (8, 2, 3, 'PRIVATE', 'Od Adama do Bartka', NOW());

-- END OF TEST DATA

-- End of file.

