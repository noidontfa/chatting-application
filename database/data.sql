CREATE TABLE _users(
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    nickname TEXT NOT NULL,
    username TEXT NOT NULL,
    password text NOT NULL
);

CREATE TABLE _friends (
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
	user_id BIGINT NOT NULL,
    friend_user_id BIGINT NULL
);

ALTER TABLE _friends ADD CONSTRAINT fk_friend_user_id FOREIGN KEY (user_id)
	REFERENCES _users(id);
    
CREATE TABLE _private_message (
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    msg TEXT NULL,
    createddate TIMESTAMP NULL,
    friend_id BIGINT NOT NULL
);

ALTER TABLE _private_message ADD CONSTRAINT fk_private_message_private_chat_id FOREIGN KEY (friend_id)
	REFERENCES _friends(id);
    
CREATE TABLE _groups (
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
	groupname TEXT NOT NULL
);

CREATE TABLE _group_member (
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
	group_id BIGINT NOT NULL,
    user_id BIGINT NULL
);

ALTER TABLE _group_member ADD CONSTRAINT fk_group_member_group_id FOREIGN KEY (group_id)
	REFERENCES _groups(id);

ALTER TABLE _group_member ADD CONSTRAINT fk_group_member_member_id FOREIGN KEY (user_id)
	REFERENCES _users(id);
    
    
CREATE TABLE _group_message (
	id BIGINT NOT NULL PRIMARY KEY auto_increment,
    msg TEXT NULL,
    createddate TIMESTAMP NULL,
    group_id BIGINT NOT NULL
);

ALTER TABLE _group_message ADD CONSTRAINT fk_group_message_group_id FOREIGN KEY (group_id)
	REFERENCES _group_member (id);
    

ALTER TABLE _private_message 
ADD COLUMN filebytes LONGBLOB NULL AFTER createddate; 

ALTER TABLE _group_message 
ADD COLUMN filebytes LONGBLOB NULL AFTER createddate;

ALTER TABLE _group_message 
ADD COLUMN user_id BIGINT NULL AFTER filebytes;
    







