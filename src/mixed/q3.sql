/*
 * @author nmenego
 *
 * Problem Statement:
 * Write a procedure in MySQL to split a column into rows using a delimiter.
 * CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );
 * INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag');
 * For (2), example rows would look like >> “3, white”, “3, Snow” ...
 */

DROP TABLE IF EXISTS sometbl;
CREATE TABLE sometbl ( ID INT, NAME VARCHAR(50) );
INSERT INTO sometbl VALUES (1, 'Smith'), (2, 'Julio|Jones|Falcons'), (3, 'White|Snow'), (4, 'Paint|It|Red'), (5, 'Green|Lantern'), (6, 'Brown|bag');

DROP PROCEDURE IF EXISTS splittbl;

DELIMITER //
CREATE PROCEDURE splittbl()
BEGIN
	DECLARE PIPE VARCHAR(1) DEFAULT '|';
	DECLARE ID INT;
    DECLARE NAME VARCHAR(50);
	DECLARE pos INT DEFAULT 1;
    DECLARE rem_len INT DEFAULT 0;
	DECLARE finished INT DEFAULT 0;
    DECLARE temp_str VARCHAR(50);

	DECLARE crsr CURSOR FOR SELECT * FROM sometbl;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;

	-- temporary table
	DROP TEMPORARY TABLE IF EXISTS temptbl;
	CREATE TEMPORARY TABLE temptbl(
		ID INT,
		NAME VARCHAR(50)
	);    

	-- loop cursor
	OPEN crsr;
	crsr_loop: LOOP
		FETCH crsr INTO ID, NAME;
		IF finished THEN
		  LEAVE crsr_loop;
		END IF;

		-- this keeps track of the string we have to cover
		SET rem_len = (SELECT LENGTH(NAME) - LENGTH(REPLACE(NAME, pipe, '')) + 1);
		SET pos = 1;
		WHILE pos <= rem_len DO
			SET temp_str = (
			SELECT REPLACE(
				MID(SUBSTRING_INDEX(NAME, PIPE, pos), 
				LENGTH(
					SUBSTRING_INDEX(NAME, PIPE, pos - 1)) + 1), 
				PIPE, ''));

			-- insert item to temporary table
			INSERT INTO temptbl VALUES (ID, temp_str);
			SET pos = pos + 1;
		END WHILE; -- position loop
	END LOOP; -- cursor loop
    -- display data from temporary table
	SELECT * FROM temptbl;
	CLOSE crsr;
END;
//

CALL splittbl();