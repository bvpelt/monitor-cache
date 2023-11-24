update policy_id_seq set last_value = (select max(id) from policy);
