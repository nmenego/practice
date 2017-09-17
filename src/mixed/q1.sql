/*
 * @author nmenego
 *
 * Problem Statement:
 * Write a query to rank order the following table in MySQL by votes, display the rank as one of the columns.
 * CREATE TABLE votes ( name CHAR(10), votes INT );
 * INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);
 */
DROP TABLE IF EXISTS votes;
CREATE TABLE votes ( name CHAR(10), votes INT );
INSERT INTO votes VALUES ('Smith',10), ('Jones',15), ('White',20), ('Black',40), ('Green',50), ('Brown',20);
SELECT * FROM votes ORDER BY votes DESC;
SELECT @rank := @rank + 1 as rank, name, votes FROM votes v, (SELECT @rank := 0 ) vr order by votes desc;