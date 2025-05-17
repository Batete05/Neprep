CREATE OR REPLACE FUNCTION find_expiring_tokens()
RETURNS TABLE (
    id integer,
    purchaseDate timestamp without time zone,
    tokenValueDays integer,
    tokenStatus text,
    -- include all columns you return
    token text,
    amount numeric,
    meter_id integer
) AS $$
BEGIN
RETURN QUERY
SELECT *
FROM purchased_tokens
WHERE
    EXTRACT(EPOCH FROM (NOW() - purchaseDate)) / 3600 >= (tokenValueDays * 24 - 5)
  AND EXTRACT(EPOCH FROM (NOW() - purchaseDate)) / 3600 < (tokenValueDays * 24)
  AND tokenStatus = 'NEW';
END;
$$ LANGUAGE plpgsql;
