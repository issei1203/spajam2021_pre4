CREATE TABLE IF NOT EXISTS log(
    id INTEGER AUTO_INCREMENT PRIMARY KEY ,
    time CHAR(8) NOT NULL,
    user INTEGER
);