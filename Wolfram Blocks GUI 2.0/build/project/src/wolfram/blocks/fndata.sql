CREATE SCHEMA IF NOT EXISTS FUNCTION_TESTING

#function with some bundled attributes
CREATE TABLE IF NOT EXISTS FUNCTION_TESTING.FUNCTIONS (
	NAME VARCHAR(100) PRIMARY KEY,
	LABEL VARCHAR(100) NOT NULL,
	NUMINPUTS INT NOT NULL,
	HASOUTPUT BOOLEAN DEFAULT TRUE NOT NULL,
	COLLAPSIBLE BOOLEAN DEFAULT TRUE NOT NULL,
	ATTRIBUTES BOOLEAN DEFAULT FALSE NOT NULL
);

#list of all inputs. Uniquely identified by pair of number and parent function
CREATE TABLE IF NOT EXISTS FUNCTION_TESTING.INPUTS (
	IN_ID INT NOT NULL,
	FN_NAME VARCHAR(100) REFERENCES FUNCTION_TESTING.FUNCTIONS(NAME),
	LABEL VARCHAR(100),
	CONSTRAINT PK_INPUT PRIMARY KEY (IN_ID, FN_NAME)
);

#LUT for input attributes
CREATE TABLE IF NOT EXISTS FUNCTION_TESTING.INPUT_ATTRIBUTES (
	ID INT PRIMARY KEY,
	NAME VARCHAR(250) NOT NULL
);

#Pairs unique input with its attributes
CREATE TABLE IF NOT EXISTS FUNCTION_TESTING.INPUT_DETAILS (
	IN_ID INT,
	FN_NAME VARCHAR(100),
	ATNAME INT REFERENCES FUNCTION_TESTING.INPUT_ATTRIBUTES,
	CONSTRAINT FK_INPUT FOREIGN KEY (IN_ID, FN_NAME) REFERENCES FUNCTION_TESTING.INPUTS(IN_ID, FN_NAME)
);

#LUT for block attributes
CREATE TABLE IF NOT EXISTS FUNCTION_TESTING.FN_ATTRIBUTES (
	ID INT PRIMARY KEY,
	NAME VARCHAR(100) NOT NULL
);

#pairs each function type with its block attributes
CREATE TABLE IF NOT EXISTS FUNCTION_TESTING.FN_DETAILS (
	FN_NAME VARCHAR(100) REFERENCES FUNCTION_TESTING.FUNCTIONS(NAME),
	ATNAME INT REFERENCES FUNCTION_TESTING.FN_ATTRIBUTES
);

#Populate input attribute LUT
INSERT INTO FUNCTION_TESTING.INPUT_ATTRIBUTES(ID, NAME)
VALUES (1, 'NAMELESS'); #I believe this is being depricated

INSERT INTO FUNCTION_TESTING.INPUT_ATTRIBUTES(ID, NAME)
VALUES (2, 'SHORTINPUTFIELD');

INSERT INTO FUNCTION_TESTING.INPUT_ATTRIBUTES(ID, NAME)
VALUES (3, 'MEDIUMINPUTFIELD');

INSERT INTO FUNCTION_TESTING.INPUT_ATTRIBUTES(ID, NAME)
VALUES (4, 'LONGINPUTFIELD');

INSERT INTO FUNCTION_TESTING.INPUT_ATTRIBUTES(ID, NAME)
VALUES (5, 'INTINPUTFIELD');

INSERT INTO FUNCTION_TESTING.INPUT_ATTRIBUTES(ID, NAME)
VALUES (6, 'MATHEMATICAINPUTFIELD');

INSERT INTO FUNCTION_TESTING.INPUT_ATTRIBUTES(ID, NAME)
VALUES (7, 'DOUBLEINPUTFIELD');

#Populate function attribute LUT
INSERT INTO FUNCTION_TESTING.FN_ATTRIBUTES(ID, NAME)
VALUES (1, 'TIGHTFIT');

INSERT INTO FUNCTION_TESTING.FN_ATTRIBUTES(ID, NAME)
VALUES (2, 'HASOPTIONS');

#Populate FUNCTIONS table
SET SCHEMA FUNCTION_TESTING;
INSERT INTO FUNCTIONS (NAME, LABEL, NUMINPUTS, HASOUTPUT, COLLAPSIBLE, ATTRIBUTES)
VALUES('String', 'String', 0, TRUE, TRUE, TRUE);

INSERT INTO FUNCTIONS (NAME, LABEL, NUMINPUTS, HASOUTPUT, COLLAPSIBLE, ATTRIBUTES)
VALUES('Style', 'Style', 1, TRUE, TRUE, TRUE);

INSERT INTO FUNCTIONS (NAME, LABEL, NUMINPUTS, HASOUTPUT, COLLAPSIBLE, ATTRIBUTES)
VALUES('List', 'List', 3, TRUE, TRUE, TRUE);

#Add input(s) and attributes for Style
INSERT INTO INPUTS(IN_ID, FN_NAME, LABEL)
VALUES(1, 'Style', '');

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(1, 'Style', 4);

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(1, 'Style', 4);

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(1, 'Style', 6);

#Add input(s) and attributes for List
INSERT INTO INPUTS(IN_ID, FN_NAME, LABEL)
VALUES(1, 'List', '');

INSERT INTO INPUTS(IN_ID, FN_NAME, LABEL)
VALUES(2, 'List', '');

INSERT INTO INPUTS(IN_ID, FN_NAM, LABEL)
VALUES(3, 'List', '');

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(1, 'List', 4);

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(1, 'List', 6);

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(2, 'List', 4);

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(2, 'List', 6);

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(3, 'List', 4);

INSERT INTO INPUT_DETAILS(IN_ID, FN_NAME, ATNAME)
VALUES(3, 'List', 6);


