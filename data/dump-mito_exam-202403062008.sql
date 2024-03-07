--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2024-03-06 20:08:04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE mito_exam;
--
-- TOC entry 3348 (class 1262 OID 16758)
-- Name: mito_exam; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE mito_exam WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Peru.1252';


ALTER DATABASE mito_exam OWNER TO postgres;

\connect mito_exam

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 16878)
-- Name: course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course (
    id_course integer NOT NULL,
    acronym character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    status boolean NOT NULL
);


ALTER TABLE public.course OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16877)
-- Name: course_id_course_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.course_id_course_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_id_course_seq OWNER TO postgres;

--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 209
-- Name: course_id_course_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.course_id_course_seq OWNED BY public.course.id_course;


--
-- TOC entry 214 (class 1259 OID 16894)
-- Name: enrollment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enrollment (
    id_enrollment integer NOT NULL,
    date_enrollment timestamp(6) without time zone NOT NULL,
    status boolean NOT NULL,
    id_student integer NOT NULL
);


ALTER TABLE public.enrollment OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16901)
-- Name: enrollment_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.enrollment_detail (
    id_enrollment_detail integer NOT NULL,
    classroom character varying(255) NOT NULL,
    id_course integer NOT NULL,
    id_enrollment integer NOT NULL
);


ALTER TABLE public.enrollment_detail OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16900)
-- Name: enrollment_detail_id_enrollment_detail_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.enrollment_detail_id_enrollment_detail_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.enrollment_detail_id_enrollment_detail_seq OWNER TO postgres;

--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 215
-- Name: enrollment_detail_id_enrollment_detail_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.enrollment_detail_id_enrollment_detail_seq OWNED BY public.enrollment_detail.id_enrollment_detail;


--
-- TOC entry 213 (class 1259 OID 16893)
-- Name: enrollment_id_enrollment_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.enrollment_id_enrollment_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.enrollment_id_enrollment_seq OWNER TO postgres;

--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 213
-- Name: enrollment_id_enrollment_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.enrollment_id_enrollment_seq OWNED BY public.enrollment.id_enrollment;


--
-- TOC entry 212 (class 1259 OID 16885)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    id_student integer NOT NULL,
    age integer NOT NULL,
    dni character varying(8) NOT NULL,
    last_name character varying(100) NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16884)
-- Name: student_id_student_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.student_id_student_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_id_student_seq OWNER TO postgres;

--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 211
-- Name: student_id_student_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.student_id_student_seq OWNED BY public.student.id_student;


--
-- TOC entry 3179 (class 2604 OID 16881)
-- Name: course id_course; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course ALTER COLUMN id_course SET DEFAULT nextval('public.course_id_course_seq'::regclass);


--
-- TOC entry 3181 (class 2604 OID 16897)
-- Name: enrollment id_enrollment; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment ALTER COLUMN id_enrollment SET DEFAULT nextval('public.enrollment_id_enrollment_seq'::regclass);


--
-- TOC entry 3182 (class 2604 OID 16904)
-- Name: enrollment_detail id_enrollment_detail; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment_detail ALTER COLUMN id_enrollment_detail SET DEFAULT nextval('public.enrollment_detail_id_enrollment_detail_seq'::regclass);


--
-- TOC entry 3180 (class 2604 OID 16888)
-- Name: student id_student; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student ALTER COLUMN id_student SET DEFAULT nextval('public.student_id_student_seq'::regclass);


--
-- TOC entry 3336 (class 0 OID 16878)
-- Dependencies: 210
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.course VALUES (1, 'MAT', 'Matemática', true);
INSERT INTO public.course VALUES (3, 'LEN', 'Lenguaje', true);
INSERT INTO public.course VALUES (4, 'HIS', 'Historia', true);
INSERT INTO public.course VALUES (5, 'QUI', 'Química', true);
INSERT INTO public.course VALUES (6, 'GEO', 'Geografía', true);
INSERT INTO public.course VALUES (7, 'RV', 'Razonamiento Verbal', true);
INSERT INTO public.course VALUES (8, 'RM', 'Razonamiento Matemático', true);


--
-- TOC entry 3340 (class 0 OID 16894)
-- Dependencies: 214
-- Data for Name: enrollment; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.enrollment VALUES (1, '2024-03-04 15:53:16', true, 1);
INSERT INTO public.enrollment VALUES (2, '2024-03-04 15:53:16', true, 3);
INSERT INTO public.enrollment VALUES (3, '2024-03-04 15:53:16', true, 4);
INSERT INTO public.enrollment VALUES (4, '2024-03-04 15:53:16', true, 5);
INSERT INTO public.enrollment VALUES (5, '2024-03-04 15:53:16', true, 6);
INSERT INTO public.enrollment VALUES (6, '2024-03-04 15:53:16', true, 7);
INSERT INTO public.enrollment VALUES (7, '2024-03-04 15:53:16', true, 8);


--
-- TOC entry 3342 (class 0 OID 16901)
-- Dependencies: 216
-- Data for Name: enrollment_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.enrollment_detail VALUES (1, 'A', 1, 1);
INSERT INTO public.enrollment_detail VALUES (2, 'A', 3, 1);
INSERT INTO public.enrollment_detail VALUES (3, 'A', 4, 1);
INSERT INTO public.enrollment_detail VALUES (4, 'A', 5, 1);
INSERT INTO public.enrollment_detail VALUES (5, 'A', 1, 2);
INSERT INTO public.enrollment_detail VALUES (6, 'A', 3, 2);
INSERT INTO public.enrollment_detail VALUES (7, 'A', 4, 2);
INSERT INTO public.enrollment_detail VALUES (8, 'A', 5, 2);
INSERT INTO public.enrollment_detail VALUES (9, 'A', 1, 3);
INSERT INTO public.enrollment_detail VALUES (10, 'A', 3, 3);
INSERT INTO public.enrollment_detail VALUES (11, 'A', 4, 3);
INSERT INTO public.enrollment_detail VALUES (12, 'A', 5, 3);
INSERT INTO public.enrollment_detail VALUES (13, 'A', 1, 4);
INSERT INTO public.enrollment_detail VALUES (14, 'A', 3, 4);
INSERT INTO public.enrollment_detail VALUES (15, 'A', 4, 4);
INSERT INTO public.enrollment_detail VALUES (16, 'A', 5, 4);
INSERT INTO public.enrollment_detail VALUES (17, 'A', 1, 5);
INSERT INTO public.enrollment_detail VALUES (18, 'A', 3, 5);
INSERT INTO public.enrollment_detail VALUES (19, 'A', 4, 5);
INSERT INTO public.enrollment_detail VALUES (20, 'A', 5, 5);
INSERT INTO public.enrollment_detail VALUES (21, 'A', 6, 5);
INSERT INTO public.enrollment_detail VALUES (22, 'A', 7, 5);
INSERT INTO public.enrollment_detail VALUES (23, 'A', 8, 5);
INSERT INTO public.enrollment_detail VALUES (24, 'A', 1, 6);
INSERT INTO public.enrollment_detail VALUES (25, 'A', 3, 6);
INSERT INTO public.enrollment_detail VALUES (26, 'A', 4, 6);
INSERT INTO public.enrollment_detail VALUES (27, 'A', 5, 6);
INSERT INTO public.enrollment_detail VALUES (28, 'A', 6, 6);
INSERT INTO public.enrollment_detail VALUES (29, 'A', 7, 6);
INSERT INTO public.enrollment_detail VALUES (30, 'A', 8, 6);
INSERT INTO public.enrollment_detail VALUES (31, 'A', 1, 7);
INSERT INTO public.enrollment_detail VALUES (32, 'A', 3, 7);
INSERT INTO public.enrollment_detail VALUES (33, 'A', 4, 7);
INSERT INTO public.enrollment_detail VALUES (34, 'A', 5, 7);
INSERT INTO public.enrollment_detail VALUES (35, 'A', 6, 7);
INSERT INTO public.enrollment_detail VALUES (36, 'A', 7, 7);
INSERT INTO public.enrollment_detail VALUES (37, 'A', 8, 7);


--
-- TOC entry 3338 (class 0 OID 16885)
-- Dependencies: 212
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.student VALUES (1, 32, '34543454', 'Palacios', 'Gregory');
INSERT INTO public.student VALUES (3, 38, '56453487', 'Paredes', 'Jose');
INSERT INTO public.student VALUES (4, 26, '45673454', 'Torres', 'Melissa');
INSERT INTO public.student VALUES (5, 29, '43237656', 'Maldonado', 'Juana');
INSERT INTO public.student VALUES (6, 45, '67984534', 'Medina', 'Pedro');
INSERT INTO public.student VALUES (7, 23, '34875645', 'Toledo', 'Leonardo');
INSERT INTO public.student VALUES (8, 39, '65823214', 'Palacios', 'Jorge');


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 209
-- Name: course_id_course_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.course_id_course_seq', 8, true);


--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 215
-- Name: enrollment_detail_id_enrollment_detail_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.enrollment_detail_id_enrollment_detail_seq', 37, true);


--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 213
-- Name: enrollment_id_enrollment_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.enrollment_id_enrollment_seq', 7, true);


--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 211
-- Name: student_id_student_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.student_id_student_seq', 8, true);


--
-- TOC entry 3184 (class 2606 OID 16883)
-- Name: course course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id_course);


--
-- TOC entry 3192 (class 2606 OID 16906)
-- Name: enrollment_detail enrollment_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment_detail
    ADD CONSTRAINT enrollment_detail_pkey PRIMARY KEY (id_enrollment_detail);


--
-- TOC entry 3190 (class 2606 OID 16899)
-- Name: enrollment enrollment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT enrollment_pkey PRIMARY KEY (id_enrollment);


--
-- TOC entry 3186 (class 2606 OID 16890)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id_student);


--
-- TOC entry 3188 (class 2606 OID 16892)
-- Name: student uk_o8153perxn0cilnt1n4tydceo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT uk_o8153perxn0cilnt1n4tydceo UNIQUE (dni);


--
-- TOC entry 3194 (class 2606 OID 16912)
-- Name: enrollment_detail fk_detail_course; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment_detail
    ADD CONSTRAINT fk_detail_course FOREIGN KEY (id_course) REFERENCES public.course(id_course);


--
-- TOC entry 3195 (class 2606 OID 16917)
-- Name: enrollment_detail fk_detail_enrollment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment_detail
    ADD CONSTRAINT fk_detail_enrollment FOREIGN KEY (id_enrollment) REFERENCES public.enrollment(id_enrollment);


--
-- TOC entry 3193 (class 2606 OID 16907)
-- Name: enrollment fk_enroll_student; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.enrollment
    ADD CONSTRAINT fk_enroll_student FOREIGN KEY (id_student) REFERENCES public.student(id_student);


-- Completed on 2024-03-06 20:08:04

--
-- PostgreSQL database dump complete
--

