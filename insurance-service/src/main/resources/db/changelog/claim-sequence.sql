update claim_id_seq set last_value = (select max(id) from claim);
