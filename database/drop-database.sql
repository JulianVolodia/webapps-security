-- Created by Vertabelo (http://vertabelo.com)
-- Script type: drop
-- Scope: [tables, references, sequences, views, procedures]
-- Generated at Sat Oct 11 22:03:06 UTC 2014


-- DELETE DATA

delete from post_message;
delete from private_message;
delete from users_connection;
delete from user_account;






-- foreign keys
DROP SEQUENCE post_message_seq;


DROP SEQUENCE private_message_seq;


DROP SEQUENCE user_account_seq;





-- foreign keys
ALTER TABLE post_message DROP CONSTRAINT post_message_user_account;

ALTER TABLE private_message DROP CONSTRAINT private_message_receiver;

ALTER TABLE private_message DROP CONSTRAINT private_message_sender;

ALTER TABLE users_connection DROP CONSTRAINT users_connection_1;

ALTER TABLE users_connection DROP CONSTRAINT users_connection_2;




-- views
DROP VIEW wall_posts_view;DROP VIEW private_message_view;DROP VIEW user_connections_view;


-- tables
DROP TABLE post_message;
DROP TABLE private_message;
DROP TABLE user_account;
DROP TABLE users_connection;




-- End of file.

