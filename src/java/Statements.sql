/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Kelechi
 * Created: 23-Jul-2018
 */
INSERT INTO Genre (GENRE) VALUES ('Novel');
INSERT INTO Genre (GENRE) VALUES ('News');
INSERT INTO Genre (GENRE) VALUES ('Academic');
INSERT INTO Genre (GENRE) VALUES ('General');
INSERT INTO Genre (GENRE) VALUES ('Literature');
INSERT INTO Genre (GENRE) VALUES ('Article');

INSERT INTO Security_Question (QUESTION) VALUES('What was the name of your first pet?');
INSERT INTO Security_Question (QUESTION) VALUES('What was the name of your first car?');
INSERT INTO Security_Question (QUESTION) VALUES('What was the name of your town of birth?');
INSERT INTO Security_Question (QUESTION) VALUES('What was the name of your first girlfriend?');

UPDATE UserAccount SET USER_ROLE='ADMIN' WHERE ID='LIBUSR-1';

