CREATE TABLE IF NOT EXISTS EXCHANGE_RATE (id INT NOT NULL AUTO_INCREMENT, SOURCE_CURRENCY VARCHAR(255), TARGET_CURRENCY VARCHAR(255), AMOUNT_EXCHANGE_RATE DOUBLE, PRIMARY KEY (id));
CREATE TABLE IF NOT EXISTS USER_EXCHANGE (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), username VARCHAR(255), password VARCHAR(255), rol VARCHAR(255),PRIMARY KEY (id));