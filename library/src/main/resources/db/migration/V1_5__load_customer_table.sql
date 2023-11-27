--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: testuser
--
insert into public.customer (id, customerid, policyid, claimid)
values (1, 1, 1, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (2, 2, 1, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (3, 2, 1, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (4, 3, 2, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (5, 3, 2, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (6, 3, 3, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (7, 3, 3, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (8, 4, 4, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (9, 6, 1, 1);
insert into public.customer (id, customerid, policyid, claimid)
values (10, 7, 1, 1);


SELECT setval('customer_id_seq', (select max(id) from customer), true);

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);