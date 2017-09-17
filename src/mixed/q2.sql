/*
 * @author nmenego
 *
 * Problem Statement:
 * Write a function to capitalize the first letter of a word in a given string;
 * Example: initcap(UNITED states Of AmERIca ) = United States Of America 
 */
DROP FUNCTION IF EXISTS initcap;

DELIMITER //

CREATE FUNCTION initcap(str VARCHAR(255)) 
	RETURNS VARCHAR(255)
BEGIN
	DECLARE len INT;
	DECLARE cnt INT;
	DECLARE output VARCHAR(255);

	SET len = CHAR_LENGTH(str);
	SET output = LOWER(str); -- to lower case
	SET cnt = 0;

	WHILE (cnt < len) DO
		-- if a space is encountered or start of string
		IF (SUBSTRING(output, cnt, 1) = ' ' OR cnt = 0) THEN
			SET output = CONCAT(LEFT(output, cnt), UPPER(SUBSTRING(output, cnt + 1, 1)), RIGHT(output, len - cnt - 1));
		END IF;
		SET cnt = cnt + 1; -- increment
	END WHILE;
    RETURN output;
END;
//

/* tests */
SELECT initcap("UNITED states Of AmERIca"), initcap("the QuicK broWn Fox"), initcap("jack aND jIll"), initcap("jack  aND  jIll")