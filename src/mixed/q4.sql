/*
 * @author nmenego
 *
 * Problem Statement:
 * I have a table for bugs from a bug tracking software; let’s call the table “bugs”.
 * The table has four columns (id, open_date, close_date, severity). On any given day
 * a bug is open if the open_date is on or before that day and close_date is after
 * that day. For example, a bug is open on “2012-01-01”, if it’s created on or
 * before “2012-01-01” and closed on or after “2012-01-02”. I want an SQL to show
 * the number of bugs open each day for a range of dates. Hint: There are bugs that were
 * never closed.
 *
 * Assumptions made:
 * 1. open_date <= close_date
 * 2. no time information/timezones are considered
 */
 
DROP TABLE IF EXISTS bugs;
CREATE TABLE bugs (ID INT, open_date DATE, close_date DATE, severity VARCHAR(10));

-- test values
INSERT INTO bugs VALUES (1, '2017-5-23', '2017-5-31', 'A');
INSERT INTO bugs VALUES (2, '2017-5-24', NULL, 'B');
INSERT INTO bugs VALUES (3, '2017-5-24', '2017-5-24', 'C');
INSERT INTO bugs VALUES (4, '2017-5-25', '2017-5-31', 'A');

DROP PROCEDURE IF EXISTS get_bugs;

DELIMITER //
CREATE PROCEDURE get_bugs(start_date DATE, end_date DATE)
BEGIN
	DECLARE bug_count INT DEFAULT 0;
    DECLARE date_cursor DATE;
	DROP TEMPORARY TABLE IF EXISTS temptbl;
    CREATE TEMPORARY TABLE temptbl(date_0 DATE, bug_count INT);

	SET date_cursor = start_date;
	WHILE date_cursor <= end_date DO
			-- query
            SET bug_count = 
				(SELECT COUNT(id) FROM bugs 
					WHERE (
							DATE(open_date) <= date_cursor 
								AND DATE(close_date) > date_cursor) 
                        OR (
							DATE(open_date) <= date_cursor) 
								AND close_date IS NULL);
		   INSERT INTO temptbl VALUES (date_cursor, bug_count);
		   SET date_cursor = ADDDATE(date_cursor, INTERVAL 1 DAY);
	END WHILE;
	SELECT * FROM temptbl;
END;
//
CALL get_bugs('2017-5-23', '2017-5-31');
