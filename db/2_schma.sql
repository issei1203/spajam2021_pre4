CREATE TABLE IF NOT EXISTS log(
    id INTEGER AUTO_INCREMENT PRIMARY KEY ,
    start VARCHAR(11) NOT NULL,
    first VARCHAR(11) NOT NULL,
    current VARCHAR(11) NOT NULL,
    user VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS sample(
      id INTEGER AUTO_INCREMENT PRIMARY KEY ,
      time CHAR(8) NOT NULL,
      user VARCHAR(25) NOT NULL
);