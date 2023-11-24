--
-- Data for Name: claim; Type: TABLE DATA; Schema: public; Owner: testuser
--
insert into public.claim (id, claimtext) values (1, 'eerste claim');
insert into public.claim (id, claimtext) values (2, 'tweede claim');
insert into public.claim (id, claimtext) values (3, 'derde claim');
insert into public.claim (id, claimtext) values (4, 'vierde claim');
insert into public.claim (id, claimtext) values (5, 'vijfde claim');
insert into public.claim (id, claimtext) values (6, 'zesde claim');
insert into public.claim (id, claimtext) values (7, 'zevende claim');
insert into public.claim (id, claimtext) values (8, 'achtste claim');

SELECT setval('claim_id_seq', (select max(id) from claim), true);

ALTER TABLE ONLY public.claim
    ADD CONSTRAINT claim_pkey PRIMARY KEY (id);