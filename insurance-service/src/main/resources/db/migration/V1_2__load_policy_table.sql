--
-- Data for Name: policy; Type: TABLE DATA; Schema: public; Owner: testuser
--
insert into public.policy (id, policytext)
values (1, 'eerste policy');
insert into public.policy (id, policytext)
values (2, 'tweede policy');
insert into public.policy (id, policytext)
values (3, 'derde policy');
insert into public.policy (id, policytext)
values (4, 'vierde policy');
insert into public.policy (id, policytext)
values (5, 'vijfde policy');
insert into public.policy (id, policytext)
values (6, 'zesde policy');
insert into public.policy (id, policytext)
values (7, 'zevende policy');
insert into public.policy (id, policytext)
values (8, 'achtste policy');

SELECT setval('policy_id_seq', (select max(id) from policy), true);

ALTER TABLE ONLY public.policy
    ADD CONSTRAINT policy_pkey PRIMARY KEY (id);