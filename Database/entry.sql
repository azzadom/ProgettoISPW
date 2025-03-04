START TRANSACTION;
-- Inserimento di organizzatori
INSERT INTO privateevents.organizer (Username, Password, Email, LastName, FirstName, InfoPayPal) VALUES ('gabry', '60ff2ed19e06fa04a72af317376e8f8bc9f5059028204be65e68a6606406537f3f88caeeacca19b190032fa9f3f0bb2494d81b9f0b4bf935bfbea8f576f0c718', 'gabry.cargo@gmail.com', 'Cargoni', 'Gabriele', 'gab_wallet@gmail.com');
INSERT INTO privateevents.organizer (Username, Password, Email, LastName, FirstName, InfoPayPal) VALUES ('luca', '69d187b8f9422bb34a15fbfcddb54f5f0736d7df0749aa1cfa89fa3345a5b98898e2ed634c6029754ccd794b1dc7a0b84f4edf572a2b2c922fce3bb22bdd5624', 'luca@outlook.it', 'Messieri', 'Luca', 'luca@gmail.com');
INSERT INTO privateevents.organizer (Username, Password, Email, LastName, FirstName, InfoPayPal) VALUES ('pippo', '3885ce3069f1b354b41683647e53ecf67f21c435b3411ddc55be2558773dad918e3bab7d416c83b48388030dc7cc0c09a7c977b74a0a28226fec131cad43b4c', 'pippo@gmail.com', 'Mascara', 'Pippo', 'pippo_wallet@gmail.com');
INSERT INTO privateevents.organizer (Username, Password, Email, LastName, FirstName, InfoPayPal) VALUES ('pluto', '6e4f38bc70846be5b7a43ba3a2773571e9db6f79775b05b70f1a4d2da88ee0c37b62e11366639f74e56d0a1c27eec24781578c8e790707edca178a77241e56ee', 'pluto@hotmail.com', 'Minati', 'Pluto', 'pluto@hotmail.com');


-- Inserimento di eventi
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (1, 'Farfalle nello stomaco', 'Milan', 'LIVIO RICCIARDI PRESENTA "FARFALLE NELLO STOMACO" // MERCOLEDI 20-07-2024 // AT MONK
Farfalle nello stomaco: un talk interattivo tratto dal primo libro di Livio Ricciardi \'È QUELLO CHE È\'.
Esploreremo insieme il mondo della sessualità e dell?affettività, per capire un po\' cosa sta succedendo all\'amore oggi.
Incontro con Livio Ricciardi e Martina Ferrari + firma copie.', 'MONK', 'Via Giuseppe Mirri, 35', '2024-07-20', '18:00:00', 0, 'pippo');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (2, 'Marc Rebillet', 'Paris', 'Marc Rebillet is a one-man improvised meltdown. No two shows are ever the same.', 'Le Zénith Paris - La Villette', '211 Avenue Jean Jaurès, 75019', '2024-08-02', '23:15:00', 0, 'pluto');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (3, 'Exo: Sunday', 'Milan', 'Terraforma Exo Sunday at Teatro Continuo di Burri, co-curated with Hundebiss and supported by K-Way', 'Teatro Continuo', 'Viale Enrico Ibsen, 20121', '2024-09-02', '21:00:00', 0, 'luca');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (4, 'Muse', 'Milan', 'Muse are an English rock band from Teignmouth, Devon, formed in 1994. The band consists of Matt Bellamy (lead vocals, guitar, keyboards), Chris Wolstenholme (bass guitar, backing vocals), and Dominic Howard (drums).', 'Lo Scantinato', 'Via Giuseppe di Vittorio, 6', '2024-10-02', '21:00:00', 0, 'luca');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (5, 'Boiler Room: Milan', 'Milan', 'Milan! We’re passing through the city for our World Tour again this year. Tickets are now live. Grab yours before they\'re gone.', 'Carroponte', 'Via Luigi Granelli 1, 20099 ', '2024-07-22', '15:00:00', 0, 'pippo');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (6, 'Robyn Hitchcock ', 'Milan', 'Torna le summer sessions di Unplugged In Monti: sulla terrazza di Industrie Fluviali torna dal vivo a Roma Robyn Hitchcock.
Sulla scena ormai da più di quarant’anni, da quando esordì con i Soft Boys, Robyn Hitchcock è stato definito in tanti modi: l’anello mancante fra John Lennon e Syd Barrett, il Folksinger punk e il poeta della più grande stagione della canzone d’autore inglese, gli anni ’80. Ma soprattutto lo straordinario autore di melodie pop visionarie e acide, così come folk e punk sono parte del suo immenso background culturale.Tra i suoi album più famosi “Fegmania” (con gli Egyptians) e quelli da solista come “I Often Dream of Trains”, recentemente reinciso con nuovi arrangiamenti, e ancora “Eye” e “Respect”. Robyn Hitchcock presenterà, oltre ai classici della sua lunga carriera, il nuovo “Shufflemania” uscito agli inizi di quest’anno.', 'Industrie Fluviali', 'Via del Porto Fluviale, 35', '2024-11-08', '17:20:00', 0, 'pluto');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (7, 'Magic Garden Festival', 'Milan', 'Il 14 giugno un giardino pieno di sorprese ti aspetta per vivere un’esperienza unica: benvenuti nel Magic Garden', 'Circolo Magnolia (Estivo)', 'Via Circonvallazione Idroscalo, 41', '2024-06-30', '18:00:00', 0, 'pippo');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (8, 'Club classics', 'Milan', 'Join us on Friday, July 5th, for Club Classics, an electrifying dance night dedicated to the iconic sounds of Charli XCX, SOPHIE, and A.G. Cook! Whether you\'re a superfan of Charli\'s futuristic beats, SOPHIE\'s groundbreaking productions, or A.G. Cook\'s PC Music magic, this night is for you.
- Dive into the glittering world of hyperpop and let the beats take over. Expect a night filled with:
- Non-stop dance anthems
- Glitzy visuals and light shows
- Fabulous vibes and community
Mark your calendars, gather your friends, and get ready to dance the night away in Detroit\'s very own El Club. It\'s going to be a night to remember!
Club Classics - Because every beat tells a story.
Doors open at 9 PM. See you on the dance floor!', 'El Club ', 'Via Luigi Rossi 3, 20099 ', '2024-07-05', '21:00:00', 0, 'luca');
INSERT INTO privateevents.event (IdEvent, Name, City, `Desc`, Location, Address, Date, Time, BookingClosed, Organizer) VALUES (9, 'Classical Soirée', 'Paris', 'Join us on Thursday, May 16th, for Classical Soirée, an elegant evening of timeless classical music performed by the finest orchestras and soloists. Highlights include: Symphonies and concertos, Solo performances, Exquisite venue ambiance, Wine and cheese reception. Classical Soirée - An evening of musical elegance. Doors open at 6 PM. A perfect night for classical music enthusiasts.', 'Philharmonie de Paris', '221 Avenue Jean Jaurès, 75019', '2024-09-16', '18:00:00', 0, 'luca');

 -- Inserimento dei ticket
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('All Day', 7, 33.85, 0, 'All day access', 1000);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('BASIC', 1, 30, 18, 'Standard ticket', 200);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('BRONZE', 3, 20, 21, 'Economy ticket', 300);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('Early Bird', 9, 30, 21, 'Limited discount tickets', 20);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('Economy', 4, 20, 19, 'Economy ticket', 300);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('General', 6, 25, 0, 'General ticket', 3000);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('General Admission', 8, 13.6, 21, 'General ticket', 1230);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('General Admission', 9, 40, 21, 'General access.', 100);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('GOLD', 3, 50, 21, 'VIP ticket', 100);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('Limited Access', 7, 25.75, 0, 'Access till 20:00', 1000);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('PLUS', 1, 50, 18, 'VIP ticket', 100);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('SILVER', 3, 30, 21, 'Standard ticket', 200);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('Standard', 2, 30, 14, 'Standard ticket', 200);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('Standard', 4, 30, 19, 'Standard ticket', 200);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('Unique', 5, 35.5, 18, 'Unique ticket ', 2000);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('VIP', 2, 50, 14, 'VIP ticket', 100);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('VIP', 4, 50, 19, 'VIP ticket', 100);
INSERT INTO privateevents.ticket (TypeName, Event, Price, MinimumAge, `Desc`, MaxTickets) VALUES ('VIP', 9, 80, 21, 'VIP Area + Drink', 100);


-- Inserimento di prenotazioni
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('24K0z0k0rI', 3, 'Neri', 'Paola', 22, 'neri.paola@gmail.com', '+391234567893', 'F', 'SILVER', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('24K0z0k0rI', 4, 'Neri', 'Paola', 22, 'neri.paola@gmail.com', '+391234567893', 'F', 'Standard', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('60Q0w0L02Q', 4, 'Verdi', 'Andrea', 21, 'verdi.andrea@gmail.com', '+39345662233', 'M', 'Economy', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('80c0w05016', 7, 'Mimmi', 'Domenico', 23, 'dom@gmail.com', '+393897445599', 'M', 'All Day', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('90d0V0Q0h3', 4, 'Bianchi', 'Andrea', 20, 'bianchi.andrea@gmail.com', '+393978995533', 'M', 'Economy', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('a0p0t0G0ry', 4, 'Giannini', 'Gabriele', 23, 'gabry.gianni@gmail.com', '+393789665509', 'M', 'Economy', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('A1R0l0G0LZ', 2, 'Gialli', 'Giovanni', 25, 'gialli.giovanni@gmail.com', '+391234567894', 'M', 'VIP', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('A1R0l0G0LZ', 3, 'Rossi', 'Mario', 25, 'rossi.mario@gmail.com', '+391234567890', 'M', 'GOLD', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('A3R0l0G0LZ', 1, 'Bianchi', 'Luca', 20, 'bianchi.luca@gmail.com', '+391234567892', 'M', 'BASIC', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('B0v0F0v0IH', 3, 'Veroni', 'Luca', 23, 'luca.veroni@gmail.com', '+393765880044', 'M', 'GOLD', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('b1109060DQ', 4, 'Rossi', 'Mario', 25, 'rossi.mario@gmail.com', '+391234567890', 'M', 'VIP', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('b7109060DQ', 3, 'Rosa', 'Maria', 30, 'rosa.maria@gmail.com', '+391234567895', 'F', 'BRONZE', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('F010Z0H0Ph', 1, 'Serratore', 'Anna Maria', 40, 'anna@gamil.com', '+393895996600', 'F', 'BASIC', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('F0h0V0F0f4', 4, 'Verdi', 'Giacomo', 22, 'giaco.verdi@gmail.com', '+393907665522', 'M', 'Economy', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('F0M070G06U', 2, 'Sentinella', 'Andrea', 30, 'andre.senti@gmail.com', '+393895997744', 'M', 'Standard', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('F0U0v0Q05u', 3, 'Bianchi', 'Mimmo', 25, 'mimmo@gmail.com', '+393895203040', 'M', 'BRONZE', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('g0R0L0a0MZ', 4, 'Bianchi', 'Francesco', 20, 'fra@gmail.com', '+392384256789', 'M', 'Standard', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('i0R0i0j0I3', 3, 'Rossi', 'Giovanni', 23, 'gio.rossi@gmail.com', '+393256890765', 'M', 'GOLD', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('I1r0l0G0qZ', 1, 'Rossi', 'Mario', 25, 'rossi.mario@gmail.com', '+391234567890', 'M', 'PLUS', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('I2r0l0G0qZ', 3, 'Verdi', 'Giuseppe', 30, 'verdi.giuseppe@gmail.com', '+391234567891', 'M', 'GOLD', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('I3r0l0G0qZ', 2, 'Blu', 'Luca', 20, 'blu.luca@gmail.com', '+391234567896', 'M', 'Standard', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('I8r0l0G0qZ', 3, 'Blu', 'Luca', 20, 'blu.luca@gmail.com', '+391234567896', 'M', 'BRONZE', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('J0K0m0X0t1', 6, 'Azzarito', 'Domenico', 13, 'dom@gmail.com', '+393895887755', 'M', 'General', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('j0U040P04i', 4, 'Rossi', 'Andrea', 20, 'rossi.andrea@gmail.com', '+393245667788', 'M', 'Standard', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('J0V0F060zV', 1, 'Sentinella', 'Andrea', 23, 'andre@gmail.com', '+393287890504', 'M', 'BASIC', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('m0G0D0F0Nz', 4, 'Gialli', 'Gabriele', 24, 'gabry.gialli@gmail.com', '+393765662288', 'M', 'Economy', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('M5d0l0G0pZ', 4, 'Gialli', 'Giovanni', 25, 'gialli.giovanni@gmail.com', '+391234567894', 'M', 'Standard', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('M6d0l0G0pZ', 3, 'Verde', 'Paola', 22, 'verde.paola@gmail.com', '+391234567897', 'F', 'BRONZE', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('P0C0H0u0A5', 1, 'Minati', 'Domenico', 25, 'dom@gmail.com', '+393230999684', 'M', 'BASIC', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('p2w0e0S0ND', 4, 'Verdi', 'Giuseppe', 30, 'verdi.giuseppe@gmail.com', '+391234567891', 'M', 'VIP', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('p3w0e0S0ND', 3, 'Bianchi', 'Luca', 20, 'bianchi.luca@gmail.com', '+391234567892', 'M', 'SILVER', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('p4w0e0S0ND', 1, 'Neri', 'Paola', 22, 'neri.paola@gmail.com', '+391234567893', 'F', 'BASIC', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('p4w0e0S0ND', 2, 'Verde', 'Paola', 22, 'verde.paola@gmail.com', '+391234567897', 'F', 'Standard', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('q000i0u0Up', 3, 'Verdoni', 'Gabriele', 26, 'gabry.verdoni@gmail.com', '+393253678006', 'M', 'BRONZE', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('Q0l0b010Mk', 1, 'Bianchi', 'Francesco', 20, 'bianchi@gmail.com', '+393897766554', 'M', 'BASIC', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('r070M0t0ab', 1, 'Azzarito', 'Domenico', 20, 'mimmo@gmail.com', '+393976554489', 'M', 'PLUS', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('R08060v0gc', 1, 'Azzarito', 'Domenico', 23, 'dommy@outlook.it', '+393284142360', 'M', 'BASIC', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('R0u0c0o0pg', 3, 'Gialli', 'Luca', 28, 'luca.gialli@gmail.com', '+393896775544', 'M', 'GOLD', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('s060D0Y0qb', 2, 'Cargoni', 'Gabriele', 21, 'gabry.cargo@gmail.com', '+393311573863', 'M', 'VIP', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('V0U0w0Y0F5', 4, 'Bianchi', 'Franceso', 20, 'fra.bianchi@gmail.com', '+393326789504', 'M', 'Economy', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('V5i0c0T0wP', 3, 'Gialli', 'Giovanni', 25, 'gialli.giovanni@gmail.com', '+391234567894', 'M', 'BRONZE', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('x0l0j0D00w', 3, 'Bianchi', 'Andrea', 23, 'andre.bianchi@gmail.com', '+39389556677', 'M', 'BRONZE', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('Y2N0a040In', 1, 'Verdi', 'Giuseppe', 30, 'verdi.giuseppe@gmail.com', '+391234567891', 'M', 'BASIC', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('Y2N0a040In', 2, 'Rosa', 'Maria', 30, 'rosa.maria@gmail.com', '+391234567895', 'F', 'Standard', 1);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('Y3N0a040In', 4, 'Bianchi', 'Luca', 20, 'bianchi.luca@gmail.com', '+391234567892', 'M', 'Standard', 0);
INSERT INTO privateevents.booking (CodeBooking, Event, LastName, FirstName, Age, Email, Telephone, Gender, TicketType, OnlinePayment) VALUES ('z0t0F0I0je', 4, 'Volpi', 'Gabriele', 23, 'volpi.gabry@gmail.com', '+393728990066', 'M', 'Economy', 0);


-- Inserimento di notifiche
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (37, '2024-05-27 03:25:54', 'I1r0l0G0qZ', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (38, '2024-05-27 03:25:54', 'Y2N0a040In', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (39, '2024-05-27 03:26:24', 'I3r0l0G0qZ', 'Marc Rebillet', 'DELETE', 'pluto');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (40, '2024-05-27 03:25:54', 'p4w0e0S0ND', 'Marc Rebillet', 'NEW', 'pluto');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (41, '2024-05-27 03:25:54', 'V5i0c0T0wP', 'Exo: Sunday', 'NEW', 'luca');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (42, '2024-05-27 03:25:54', 'M6d0l0G0pZ', 'Exo: Sunday', 'MODIFY', 'luca');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (43, '2024-05-27 03:26:54', 'I1r0l0G0qZ', 'Farfalle nello stomaco', 'MODIFY', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (44, '2024-05-27 03:25:54', '24K0z0k0rI', 'Exo: Sunday', 'MODIFY', 'luca');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (46, '2024-05-27 03:25:54', 'p2w0e0S0ND', 'Muse', 'DELETE', 'luca');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (47, '2024-05-27 03:25:54', 'b1109060DQ', 'Muse', 'DELETE', 'luca');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (48, '2024-05-27 03:30:54', 'I1r0l0G0qZ', 'Farfalle nello stomaco', 'DELETE', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (51, '2024-05-27 19:35:01', 'F0U0v0Q05u', 'Exo: Sunday', 'DELETE', 'luca');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (53, '2024-06-11 14:55:25', 'P0C0H0u0A5', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (54, '2024-06-12 03:56:47', 's060D0Y0qb', 'Marc Rebillet', 'NEW', 'pluto');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (55, '2024-06-12 21:11:27', 'F010Z0H0Ph', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (56, '2024-06-13 00:32:27', 'Q0l0b010Mk', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (71, '2024-06-13 18:23:46', 'R08060v0gc', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (72, '2024-06-13 18:24:49', 'J0V0F060zV', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (73, '2024-06-13 18:35:43', 'F0M070G06U', 'Marc Rebillet', 'NEW', 'pluto');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (74, '2024-06-13 21:32:38', 'J0K0m0X0t1', 'Robyn Hitchcock ', 'NEW', 'pluto');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (75, '2024-06-13 22:00:52', 'r070M0t0ab', 'Farfalle nello stomaco', 'NEW', 'pippo');
INSERT INTO privateevents.notif (idNotif, DateTime, BookingCode, EventName, Type, Organizer) VALUES (76, '2024-06-13 22:15:12', '80c0w05016', 'Magic Garden Festival', 'NEW', 'pippo');

COMMIT;



