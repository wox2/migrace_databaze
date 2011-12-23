--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: jpa
--
DELETE FROM administrator;
DELETE FROM passanger;
DELETE FROM inactivepassanger;
DELETE FROM person;
DELETE FROM link;
DELETE FROM linkType;
DELETE FROM link;
DELETE FROM linkType;
DELETE FROM position;
DELETE FROM station;
DELETE FROM route;
DELETE FROM position;
DELETE FROM note;
DELETE FROM link_note;


INSERT INTO route (id) VALUES (1);
INSERT INTO route (id) VALUES (2);
INSERT INTO route (id) VALUES (3);
INSERT INTO route (id) VALUES (4);
INSERT INTO route (id) VALUES (5);
INSERT INTO route (id) VALUES (6);
INSERT INTO route (id) VALUES (7);
INSERT INTO route (id) VALUES (8);


INSERT INTO station (id, name) VALUES (300, 'Praha');
INSERT INTO station (id, name) VALUES (400, 'Brno');
INSERT INTO station (id, name) VALUES (500, 'Ostrava');
INSERT INTO station (id, name) VALUES (600, 'Èeská Tøebová');
INSERT INTO station (id, name) VALUES (700, 'Pøerov');
INSERT INTO station (id, name) VALUES (800, 'Havlíèkùv Brod');


INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (9, '0:00', 0, 300, 1);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (10, '1:40', 150, 600, 1);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (11, '2:30', 250, 400, 1);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (12, '0:00', 0, 300, 2);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (13, '1:40', 150, 600, 2);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (14, '2:20', 200, 700, 2);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (15, '2:55', 350, 500, 2);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (16, '0:00', 0, 400, 3);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (17, '1:00', 50, 700, 3);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (18, '2:00', 120, 500, 3);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (19, '0:00', 0, 300, 4);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (20, '1:20', 120, 800, 4);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (21, '2:40', 255, 400, 4);

INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (22, '2:30', 250, 300, 5);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (23, '0:50', 100, 600, 5);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (24, '0:00', 0, 400, 5);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (25, '2:55', 350, 300, 6);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (26, '1:15', 200, 600, 6);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (27, '0:35', 150, 700, 6);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (28, '0:00', 0, 500, 6);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (29, '2:00', 120, 400, 7);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (30, '1:00', 70, 700, 7);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (31, '0:00', 0, 500, 7);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (32, '2:40', 225, 300, 8);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (33, '1:20', 105, 800, 8);
INSERT INTO position (id, timeposition, kmposition, station_id, route_id) VALUES (34, '0:00', 0, 400, 8);

INSERT INTO linktype (id, name, costperkm, shortcut) VALUES (1000, 'rychlík', 2, 'R');
INSERT INTO linktype (id, name, costperkm, shortcut) VALUES (2000, 'EuroCity', 3, 'EC');

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (1001, '10:00', 5, 1, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (1002, '14:00', 7, 1, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (1003, '18:00', 9, 1, 1000);

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (2001, '08:00', 101, 2, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (2002, '12:00', 173, 2, 2000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (2003, '16:00', 175, 2, 1000);

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (3001, '05:30', 16, 3, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (3002, '14:00', 20, 3, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (3003, '20:50', 22, 3, 1000);

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (4001, '10:30', 19, 4, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (4002, '12:05', 21, 4, 2000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (4003, '17:12', 23, 4, 1000);

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (5001, '10:00', 6, 5, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (5002, '14:00', 8, 5, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (5003, '18:00', 10, 5, 1000);

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (6001, '08:00', 102, 6, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (6002, '12:00', 174, 6, 2000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (6003, '16:00', 178, 6, 1000);

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (7001, '05:30', 13, 7, 2000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (7002, '14:00', 23, 7, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (7003, '20:50', 25, 7, 1000);

INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (8001, '10:30', 18, 8, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (8002, '12:05', 24, 8, 1000);
INSERT INTO link (id, departureTime, linkNumber, route_id, linkType_id) VALUES (8003, '17:12', 26, 8, 1000);

INSERT INTO note (id, explanation) VALUES (1, 'doporuèená rezervace míst');
INSERT INTO note (id, explanation) VALUES (2, 'pøiøazeno nízkopodlažné vozidlo');

INSERT INTO link_note (links_id, notes_id) VALUES (8001, 1);
INSERT INTO link_note (links_id, notes_id) VALUES (7001, 1);
INSERT INTO link_note (links_id, notes_id) VALUES (7001, 2);
INSERT INTO link_note (links_id, notes_id) VALUES (8003, 1);
INSERT INTO link_note (links_id, notes_id) VALUES (1001, 1);
INSERT INTO link_note (links_id, notes_id) VALUES (2001, 2);
INSERT INTO link_note (links_id, notes_id) VALUES (3001, 1);
INSERT INTO link_note (links_id, notes_id) VALUES (3001, 2);


INSERT INTO person (username, groupname, password) VALUES ('admin', 'administrator', 'admin');
--INSERT INTO person (username, groupname, password, firstname, surname, city) VALUES ('inactive_passanger', 'inactive', 'passanger', 'P', 'A', 'S');
INSERT INTO administrator (username) VALUES ('admin');
--INSERT INTO inactivepassanger (username) VALUES ('inactive_passanger');

INSERT INTO person (username, groupname, password, firstname, surname, city) VALUES ('passanger', 'passanger', 'passanger', 'P', 'A', 'S');
INSERT INTO passanger (username) VALUES ('passanger');