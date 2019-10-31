use chattingapplication;

CREATE TABLE user (
	id 			bigint NOT NULL PRIMARY KEY auto_increment,
    nickname	text NOT NULL,
    username	text NOT NULL,
    password	text NOT NULL
);

CREATE TABLE message (
	id 			bigint NOT NULL PRIMARY KEY auto_increment,
    userid		bigint NOT NULL,
    roomid		bigint NOT NULL,
    touserid	bigint NULL,
	msg			text NULL,
    createddate	TIMESTAMP null
);
ALTER TABLE message ADD CONSTRAINT fk_message_user FOREIGN KEY (userid) REFERENCES user(id);


CREATE TABLE room (
	id 			bigint NOT NULL PRIMARY KEY auto_increment,
    name		text NOT NULL
);

ALTER TABLE message ADD CONSTRAINT fk_message_room FOREIGN KEY (roomid) REFERENCES room(id);

CREATE TABLE loggedinuser (
	id 			bigint NOT NULL PRIMARY KEY auto_increment,
    userid		bigint NOT NULL,
    roomid		bigint NOT NULL
);

ALTER TABLE loggedinuser ADD CONSTRAINT fk_loggedinuser_user FOREIGN KEY (userid) REFERENCES user(id);
ALTER TABLE loggedinuser ADD CONSTRAINT fk_loggedinuser_room FOREIGN KEY (roomid) REFERENCES room(id);

