/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Kelechi
 * Created: 23-Jul-2018
 */
INSERT INTO Library.Genre (GENRE) VALUES ('Novel');
INSERT INTO Library.Genre (GENRE) VALUES ('News');
INSERT INTO Library.Genre (GENRE) VALUES ('Academic');
INSERT INTO Library.Genre (GENRE) VALUES ('General');
INSERT INTO Library.Genre (GENRE) VALUES ('Literature');
INSERT INTO Library.Genre (GENRE) VALUES ('Article');

INSERT INTO Library.Security_Question (QUESTION) VALUES('What was the name of your first pet?');
INSERT INTO Library.Security_Question (QUESTION) VALUES('What was the name of your first car?');
INSERT INTO Library.Security_Question (QUESTION) VALUES('What was the name of your town of birth?');
INSERT INTO Library.Security_Question (QUESTION) VALUES('What was the name of your first girlfriend?');

UPDATE Library.UserAccount SET USER_ROLE='ADMIN' WHERE ID='LIBUSR-1';

