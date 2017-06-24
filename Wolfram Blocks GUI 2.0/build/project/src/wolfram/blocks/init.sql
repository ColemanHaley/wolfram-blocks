CREATE TABLE FUNCTIONS (
	NAME VARCHAR(100) PRIMARY KEY,
	LABEL VARCHAR(100) NOT NULL,
	NUMINPUTS INT NOT NULL,
	HASOUTPUT BOOLEAN DEFAULT TRUE NOT NULL,
	COLLAPSIBLE BOOLEAN DEFAULT TRUE NOT NULL,
	ATTRIBUTES BOOLEAN DEFAULT FALSE NOT NULL
);

CREATE TABLE INPUTS (
	IN_ID INT NOT NULL,
	FN_NAME VARCHAR(100) references FUNCTIONS(NAME),
	CONSTRAINT PK_INPUT PRIMARY KEY (IN_ID, FN_NAME)
);

CREATE TABLE INPUT_DETAILS (
	IN_ID INT,
	FN_NAME VARCHAR(100),
	ATNAME INT REFERENCES INPUT_ATTRIBUTES,
	CONSTRAINT FK_INPUT FOREIGN KEY (IN_ID, FN_NAME) REFERENCES INPUTS(IN_ID, FN_NAME)
);

CREATE TABLE INPUT_ATTRIBUTES (
	ID INT PRIMARY KEY,
	NAME VARCHAR(250) NOT NULL
);

CREATE TABLE FN_DETAILS (
	FN_NAME VARCHAR(100) REFERENCES FUNCTIONS(NAME),
	ATNAME INT REFERENCES FN_ATTRIBUTES
);

CREATE TABLE FN_ATTRIBUTES (
	ID INT PRIMARY KEY,
	NAME VARCHAR(100) NOT NULL
);