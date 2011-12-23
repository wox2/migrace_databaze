--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: jpa
--

INSERT INTO course VALUES ('x33eja', 'Enterprise Java');
INSERT INTO course VALUES ('x33mis', 'Manazerske informacni systemy');
INSERT INTO course VALUES ('x33izs', 'Informacni a znalostni systemy');


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: jpa
--

INSERT INTO person VALUES (1, 'student', 'Novak', 'Frantisek');
INSERT INTO person VALUES (2, 'teacher', 'Kremen', 'Petr');
INSERT INTO person VALUES (3, 'student', 'Blazkova', 'Hana');
INSERT INTO person VALUES (4, 'teacher', 'Kouba', 'Zdenek');
INSERT INTO person VALUES (5, 'teacher', 'Valek', 'Frantisek');
INSERT INTO person VALUES (6, 'teacher', 'Smid', 'Marek');
INSERT INTO person VALUES (7, 'student', 'Kozina', 'Jan');


--
-- Data for Name: course_teacher; Type: TABLE DATA; Schema: public; Owner: jpa
--



--
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: jpa
--

INSERT INTO department VALUES (1, 'K333');
INSERT INTO department VALUES (2, 'K336');


--
-- Data for Name: organization_teacher; Type: TABLE DATA; Schema: public; Owner: jpa
--



--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: jpa
--

INSERT INTO student VALUES (1, 2);
INSERT INTO student VALUES (3, 4);
INSERT INTO student VALUES (7, 4);


--
-- Data for Name: student_course; Type: TABLE DATA; Schema: public; Owner: jpa
--



--
-- Data for Name: teacher; Type: TABLE DATA; Schema: public; Owner: jpa
--

INSERT INTO teacher VALUES (2, 1);
INSERT INTO teacher VALUES (5, 1);
INSERT INTO teacher VALUES (6, 1);
INSERT INTO teacher VALUES (4, 2);
