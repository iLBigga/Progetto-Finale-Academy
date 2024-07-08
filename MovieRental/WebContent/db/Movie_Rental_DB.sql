DROP DATABASE IF EXISTS movie_rental;
CREATE DATABASE movie_rental;
USE movie_rental;

CREATE TABLE IF NOT EXISTS utente(
    id_u INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nome VARCHAR(40) not null,
    cognome VARCHAR(40) not null,
    username VARCHAR(40) NOT NULL, 
    passw VARCHAR(40) NOT NULL,
    ruolo BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS film(
    id_f INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    titolo VARCHAR(100) NOT NULL, 
    descrizione VARCHAR(3000) NOT NULL,
    prezzo DOUBLE NOT NULL,
    genere VARCHAR(40),
    img VARCHAR(100) NOT NULL,
    disponibile boolean not null default true
);

CREATE TABLE IF NOT EXISTS noleggio(
    id_u INT NOT NULL,
    id_f INT NOT NULL,
    data_noleggio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_u) REFERENCES utente(id_u),
    FOREIGN KEY (id_f) REFERENCES film(id_f),
    restituito boolean not null default false
);

insert into utente (username, passw, nome, cognome, ruolo) values
("cirmis@gmail.com","qwerty123!","ciruzzo", "mispruzzo", true),
("vanlet@gmail.com","qwerty456!","vanes", "letames", false),
("ottsbo@gmail.com","qwerty789!","otto", "sboccadibotto", false),
("kekoli@gmail.com","qwerty000!","kekko", "lione", true);

insert into film(titolo, descrizione, prezzo, genere, img)
values
('La compagnia dell anello', 'Un giovane hobbit e un variegato gruppo,composto da umani, un nano, un elfo e altri hobbit, partono per un delicata missione, guidati dal potente mago Gandalf.Devono distruggere un anello magico e sconfiggere così il malvagio Sauron.',  5.0, 'Fantasy', 'img/compagnia_dell_anello.jpg'),
('Arancia Meccanica', 'In cerca di emozioni forti, Alex quotidianamente compie azioni criminali. Viene arrestato e sottoposto ad un trattamento che lo condiziona alla non violenza. Uscito di galera, tutte le persone che hanno subito da lui violenze gli si ritorcono contro.', 6.5, 'Giallo', 'img/arancia_meccanica.jpg'),
('Deadpool & Wolverine', 'Deadpool & Wolverine è un film del 2024 co-scritto, diretto e co-prodotto da Shawn Levy. Basato sui personaggi di Deadpool e Wolverine di Marvel Comics, è il 34º film del Marvel Cinematic Universe, nonché sequel del film Deadpool 2, appartenente alla serie di film X-Men.', 4.5, 'Azione', 'img/deadpool&wolverine.jpeg'),
('Il Padrino', 'La vita di Vito Corleone, impegnato nel difficile compito di tenere la famiglia unita e l impero mafioso compatto.', 5.5, 'Drammatico', 'img/il_padrino.jpg');
 
INSERT INTO noleggio (id_u , id_f) VALUES
(1, 3),
(3,4),
(2,2); 


