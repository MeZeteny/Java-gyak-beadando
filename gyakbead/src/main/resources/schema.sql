DROP TABLE IF EXISTS eredmeny;
DROP TABLE IF EXISTS pilota;
DROP TABLE IF EXISTS gp;

CREATE TABLE IF NOT EXISTS eredmeny (
                          datum DATE NOT NULL,
                          pilotaaz INT NOT NULL,
                          helyezes INT,
                          hiba VARCHAR(100),
                          csapat VARCHAR(150) NOT NULL,
                          tipus VARCHAR(100) NOT NULL,
                          motor VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS pilota (
                        az INT NOT NULL,
                        nev VARCHAR(100) NOT NULL,
                        nem CHAR(1),
                        szuldat DATE,
                        nemzet VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS gp (
                    datum DATE NOT NULL,
                    nev VARCHAR(150) NOT NULL,
                    helyszin VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS uzenet (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nev VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    targy VARCHAR(200) NOT NULL,
    szoveg TEXT NOT NULL,
    kuldes_ideje TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
                                     username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS authorities (
                                           username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username),
    UNIQUE KEY uni_username_authority (username, authority)
    );