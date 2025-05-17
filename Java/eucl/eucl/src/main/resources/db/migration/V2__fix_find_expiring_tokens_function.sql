DROP FUNCTION IF EXISTS find_expiring_tokens();

CREATE OR REPLACE FUNCTION find_expiring_tokens()
RETURNS SETOF purchased_tokens AS $$
BEGIN
RETURN QUERY
SELECT *
FROM purchased_tokens
WHERE
    token_status = 'NEW'
  AND purchase_date + (token_value_days * INTERVAL '1 day') - NOW() >= INTERVAL '4 hours 59 minutes'
  AND purchase_date + (token_value_days * INTERVAL '1 day') - NOW() < INTERVAL '5 hours';

END;
$$ LANGUAGE plpgsql;
