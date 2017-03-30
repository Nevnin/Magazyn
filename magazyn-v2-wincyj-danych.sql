-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 30 Mar 2017, 14:30
-- Wersja serwera: 10.1.13-MariaDB
-- Wersja PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `magazyn`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `bilans`
--

CREATE TABLE `bilans` (
  `IdBilans` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dostawca`
--

CREATE TABLE `dostawca` (
  `IdDostawca` int(11) NOT NULL,
  `NazwaSkrocona` varchar(100) CHARACTER SET latin1 NOT NULL,
  `NazwaPelna` varchar(100) CHARACTER SET latin1 NOT NULL,
  `NIP` varchar(10) CHARACTER SET latin1 NOT NULL,
  `Telefon1` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `Telefon2` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `Telefon3` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `NazwaDzialu` varchar(50) CHARACTER SET latin1 NOT NULL,
  `NrKonta` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Adres` varchar(50) CHARACTER SET latin1 NOT NULL,
  `KodPocztowy` varchar(6) CHARACTER SET latin1 NOT NULL,
  `Poczta` varchar(30) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `dostawca`
--

INSERT INTO `dostawca` (`IdDostawca`, `NazwaSkrocona`, `NazwaPelna`, `NIP`, `Telefon1`, `Telefon2`, `Telefon3`, `NazwaDzialu`, `NrKonta`, `Adres`, `KodPocztowy`, `Poczta`) VALUES
(1, 'Hurtownia "BHEU"', 'Hurtownia "BHEU"SP. z o.o.', '5645824795', '785315745', NULL, NULL, 'Sprzeda?', '43897654135416984', 'Majkowska 14', '99-999', 'Kalisz'),
(2, 'Pharma Store', 'Pharma Store Spó?ka z ograniczon? odpowiedzialno?ci? Spó?ka Komandytowa', '7401693405', '435096845', '549038504', NULL, 'Sprzedaz', '121234124594039405443', 'ul. Mazowiecka', '89-999', 'Torun'),
(3, 'Gadget-Master', 'GADGET-MASTER Hurtownia Dystrybutor Importer Gad?etów Upominków Prezentów Smolec hurtownia', '7830284593', '403854923', NULL, NULL, 'Sprzedaz', '21124125613591285', 'ul.Parczewskiego', '34-999', 'Smolec '),
(4, 'Best Buy', 'BEST BUTY Hurtownia obuwia sportowego Kostrzyn nad Odr? hurtownia', '1243234353', '122123512', NULL, NULL, 'Sprzedaz', '2152515412351561553', 'ul. Aleja Pokoju 12', '33-223', 'Kostrzyn nad Odr?'),
(5, 'SABO', 'SABO Sp.z o.o. Producent Hurtownia Opakowan\r\n\r\n', '9088766784', '450694039', NULL, NULL, 'Sprzedaz', '1205712501751430573', 'ul.Kolejarzy', '30-434', 'Krakow'),
(6, 'WIGO', 'P.P.H.U WIGO Hurtownia spo?ywcza Kraków', '8948594857', '334859458', NULL, NULL, 'Sprzedaz', '12591269365935612', 'ul.Tomickeigo 7', '31-982', 'Krakow');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dostawcatowar`
--

CREATE TABLE `dostawcatowar` (
  `IdDostawcaTowar` int(11) NOT NULL,
  `IdDostawca` int(11) NOT NULL,
  `IdTowar` int(11) NOT NULL,
  `Cena` float NOT NULL,
  `DataOd` date NOT NULL,
  `DataDo` date DEFAULT NULL,
  `KodTowaruWgDostawcy` varchar(50) CHARACTER SET latin1 NOT NULL,
  `NazwaTowaruWgDostawcy` varchar(50) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `dostawcatowar`
--

INSERT INTO `dostawcatowar` (`IdDostawcaTowar`, `IdDostawca`, `IdTowar`, `Cena`, `DataOd`, `DataDo`, `KodTowaruWgDostawcy`, `NazwaTowaruWgDostawcy`) VALUES
(1, 1, 1, 600, '2017-03-13', '2017-03-23', '648464168464', 'DELL 24"'),
(2, 2, 3, 250, '2017-03-07', '2017-03-30', '2655355253', 'Mysz Zowie EC1-A '),
(3, 2, 3, 249, '2017-03-31', '2017-04-04', '1551555131353', 'Mysz Zowie EC1-A '),
(4, 2, 3, 259, '2017-02-01', '2017-03-01', '3532673774775', 'Mysz Zowie EC1-A '),
(5, 3, 4, 1700, '2017-03-01', '2017-03-15', '235637643762636', 'AMD RADEON Fury X'),
(6, 3, 4, 1689, '2017-03-16', '2017-03-31', '25563674564646', 'AMD RADEON Fury X'),
(7, 4, 5, 289, '2017-03-01', '2017-03-14', '4364562466', 'Razer DeathAdder Black'),
(8, 4, 5, 279, '2017-03-15', '2017-03-24', '25225252523', 'Razer DeathAdder Black'),
(9, 4, 5, 250, '2017-03-24', '2017-03-31', '534646263', 'Razer DeathAdder Black'),
(10, 5, 6, 190, '2017-03-01', '2017-03-30', '1525253535', 'Zalman Z1 Black'),
(11, 5, 6, 178, '2017-03-31', '2017-04-12', '125245634747', 'Zalman Z1 Black'),
(12, 3, 7, 988, '2017-03-08', '2017-03-30', '1353736443', 'Intel i5-4460'),
(13, 3, 8, 1900, '2017-03-01', '2017-03-30', '3156463464', 'NVIDIA GeForce 1080'),
(14, 2, 9, 1200, '2017-03-01', '2017-03-31', '13543645673', 'Sony Bravia 23"'),
(15, 2, 10, 2100, '2017-03-01', '2017-03-31', '25624626262', 'Sony Bravia 37"');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `jednostkimiary`
--

CREATE TABLE `jednostkimiary` (
  `IdJednostkaMiary` int(11) NOT NULL,
  `Nazwa` varchar(30) CHARACTER SET latin1 NOT NULL,
  `NazwaSkrocona` varchar(15) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `jednostkimiary`
--

INSERT INTO `jednostkimiary` (`IdJednostkaMiary`, `Nazwa`, `NazwaSkrocona`) VALUES
(3, 'Sztuka', 'szt.'),
(4, 'Paleta', 'pal.'),
(5, 'Opakowanie', 'opak.');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategoria`
--

CREATE TABLE `kategoria` (
  `IdKategoria` int(11) NOT NULL,
  `Nazwa` varchar(50) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `kategoria`
--

INSERT INTO `kategoria` (`IdKategoria`, `Nazwa`) VALUES
(6, 'Karta Graficzna'),
(3, 'Klawiatura'),
(1, 'Monitor'),
(4, 'Mysz'),
(5, 'Obudowa'),
(7, 'Procesor'),
(2, 'Przykladowa');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `IdPracownik` int(11) NOT NULL,
  `Imie` varchar(50) CHARACTER SET latin1 NOT NULL,
  `Nazwisko` varchar(50) CHARACTER SET latin1 NOT NULL,
  `PESEL` varchar(11) CHARACTER SET latin1 NOT NULL,
  `Telefon` varchar(20) CHARACTER SET latin1 NOT NULL,
  `Adres` varchar(50) CHARACTER SET latin1 NOT NULL,
  `KodPocztowy` varchar(6) CHARACTER SET latin1 NOT NULL,
  `Poczta` varchar(30) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `pracownik`
--

INSERT INTO `pracownik` (`IdPracownik`, `Imie`, `Nazwisko`, `PESEL`, `Telefon`, `Adres`, `KodPocztowy`, `Poczta`) VALUES
(1, 'Jan', 'Kowalski', '85010201252', '785654246', 'Krótka 1', '99-999', 'Kalisz'),
(3, 'Adrian', 'Wisniewski', '91051101249', '754248767', 'Polna 24', '99-999', 'Kalisz'),
(4, 'Joanna', 'Pokrzywa', '92100903634', '604487521', 'Wojska Polskiego 14', '99-999', 'Kalisz'),
(5, 'Damian', 'Brzecki', '94020214434', '546664334', 'Tuliszkow 666', '66-666', 'Tuliszkow'),
(7, 'Dawid', 'Olkowicz', '94121101575', '609849755', 'Opatowek 0', '00-000', 'Opatowek'),
(8, 'Maciej', 'Furmaniak', '99121202322', '700880774', 'Brudzew 1', '98-235', 'Blaszki'),
(11, 'Bartek', 'Luczak', '93123301323', '786402945', 'Boryslawice 9 ', '98-235', 'Blaszki');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `towar`
--

CREATE TABLE `towar` (
  `IdTowar` int(11) NOT NULL,
  `NazwaTowaru` varchar(100) CHARACTER SET latin1 NOT NULL,
  `MinStanMagazynowy` int(11) NOT NULL,
  `MaxStanMagazynowy` int(11) NOT NULL,
  `StanMagazynowyRzeczywisty` int(11) NOT NULL,
  `StanMagazynowyDysponowany` int(11) NOT NULL,
  `StawkaVat` int(11) NOT NULL,
  `KodTowaru` varchar(50) CHARACTER SET latin1 NOT NULL,
  `IdKategoria` int(11) NOT NULL,
  `IdJednostkaMiary` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `towar`
--

INSERT INTO `towar` (`IdTowar`, `NazwaTowaru`, `MinStanMagazynowy`, `MaxStanMagazynowy`, `StanMagazynowyRzeczywisty`, `StanMagazynowyDysponowany`, `StawkaVat`, `KodTowaru`, `IdKategoria`, `IdJednostkaMiary`) VALUES
(1, 'Monitor DELL 24"', 5, 10, 8, 7, 23, '98764164135186497', 1, 3),
(2, 'Klawiatura Razer Chroma', 10, 25, 22, 18, 23, '5416168461536847', 1, 3),
(3, 'Zowie EC1-A', 5, 25, 4, 3, 23, '254235252', 4, 3),
(4, 'AMD RADEON Fury X', 5, 10, 7, 7, 23, '1425253521525', 6, 3),
(5, 'Razer DeathAdder', 5, 15, 4, 4, 23, '531525235223', 4, 3),
(6, 'Zalman Z1', 10, 23, 14, 13, 23, '125245235155', 5, 3),
(7, 'Intel i5-4460', 5, 15, 5, 4, 23, '154858546457455', 7, 3),
(8, 'NVIDIA GeForce 1080', 5, 15, 14, 14, 23, '136435476858', 6, 3),
(9, 'Sony Bravia 23"', 3, 8, 2, 2, 23, '2647434646346', 1, 3),
(10, 'Sony Bravia 37"', 3, 7, 5, 5, 23, '7568347444574', 1, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE `uzytkownik` (
  `IdUzytkownik` int(11) NOT NULL,
  `Login` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Haslo` varchar(50) CHARACTER SET latin1 NOT NULL,
  `IdPracownik` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uzytkownik`
--

INSERT INTO `uzytkownik` (`IdUzytkownik`, `Login`, `Haslo`, `IdPracownik`) VALUES
(3, 'jkowalski', 'haslo', 1),
(4, 'awisnia', 'haslo', 3),
(5, 'dbrzecki', 'haslo', 5),
(6, 'dolkowicz', 'haslo', 7),
(7, 'mfurmaniak', 'haslo', 8),
(8, 'bluczak', 'haslo', 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienie`
--

CREATE TABLE `zamowienie` (
  `IdZamowienie` int(11) NOT NULL,
  `TerminRealizacji` date NOT NULL,
  `DataRealizacji` date NOT NULL,
  `KosztZamowienia` float NOT NULL,
  `IdDostawcy` int(11) NOT NULL,
  `DataWystawienia` date NOT NULL,
  `NumerZamowienia` varchar(50) CHARACTER SET latin1 NOT NULL,
  `SposobDostawy` varchar(20) CHARACTER SET latin1 NOT NULL,
  `KosztDostawy` float NOT NULL,
  `WartoscTowarow` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zamowienie`
--

INSERT INTO `zamowienie` (`IdZamowienie`, `TerminRealizacji`, `DataRealizacji`, `KosztZamowienia`, `IdDostawcy`, `DataWystawienia`, `NumerZamowienia`, `SposobDostawy`, `KosztDostawy`, `WartoscTowarow`) VALUES
(1, '2017-03-21', '2017-03-20', 600, 1, '2017-03-20', '2017/03/20/1', 'Odbior osobity', 0, 600),
(2, '2017-03-21', '2017-03-20', 1000, 1, '2017-03-20', '2017/03/20/2', 'Odbior osobity', 0, 1000),
(3, '2017-03-23', '2017-03-22', 1550, 2, '2017-03-22', '2017/03/22/002', 'Kurier', 50, 1500),
(4, '2017-03-08', '2017-03-08', 1230, 1, '2017-03-08', '2017/03/08/020', 'Kurier', 30, 1200),
(5, '2017-03-29', '2017-03-29', 1025, 4, '2017-03-29', '2017/03/29/025', 'Kurier', 25, 1000),
(6, '2017-03-16', '2017-03-16', 289, 4, '2017-03-16', '2017/03/16/012', 'Kurier', 10, 279);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienietowar`
--

CREATE TABLE `zamowienietowar` (
  `IdZamowienieTowar` int(11) NOT NULL,
  `Lp` int(11) NOT NULL,
  `IdTowar` int(11) NOT NULL,
  `Cena` float NOT NULL,
  `Ilosc` int(11) NOT NULL,
  `WartoscNetto` float NOT NULL,
  `IdZamowienie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `zamowienietowar`
--

INSERT INTO `zamowienietowar` (`IdZamowienieTowar`, `Lp`, `IdTowar`, `Cena`, `Ilosc`, `WartoscNetto`, `IdZamowienie`) VALUES
(1, 1, 1, 100, 6, 600, 1),
(2, 1, 2, 100, 10, 1000, 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `bilans`
--
ALTER TABLE `bilans`
  ADD PRIMARY KEY (`IdBilans`);

--
-- Indexes for table `dostawca`
--
ALTER TABLE `dostawca`
  ADD PRIMARY KEY (`IdDostawca`),
  ADD UNIQUE KEY `NrKonta` (`NrKonta`);

--
-- Indexes for table `dostawcatowar`
--
ALTER TABLE `dostawcatowar`
  ADD PRIMARY KEY (`IdDostawcaTowar`),
  ADD KEY `IdDostawca` (`IdDostawca`),
  ADD KEY `IdTowar` (`IdTowar`);

--
-- Indexes for table `jednostkimiary`
--
ALTER TABLE `jednostkimiary`
  ADD PRIMARY KEY (`IdJednostkaMiary`),
  ADD UNIQUE KEY `Nazwa` (`Nazwa`);

--
-- Indexes for table `kategoria`
--
ALTER TABLE `kategoria`
  ADD PRIMARY KEY (`IdKategoria`),
  ADD UNIQUE KEY `Nazwa` (`Nazwa`);

--
-- Indexes for table `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`IdPracownik`),
  ADD UNIQUE KEY `PESEL` (`PESEL`);

--
-- Indexes for table `towar`
--
ALTER TABLE `towar`
  ADD PRIMARY KEY (`IdTowar`),
  ADD UNIQUE KEY `KodTowaru` (`KodTowaru`),
  ADD KEY `IdKategoria` (`IdKategoria`),
  ADD KEY `IdJednostkaMiary` (`IdJednostkaMiary`);

--
-- Indexes for table `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD PRIMARY KEY (`IdUzytkownik`),
  ADD UNIQUE KEY `Login` (`Login`),
  ADD KEY `IdPracownik` (`IdPracownik`);

--
-- Indexes for table `zamowienie`
--
ALTER TABLE `zamowienie`
  ADD PRIMARY KEY (`IdZamowienie`),
  ADD UNIQUE KEY `NumerZamowienia` (`NumerZamowienia`),
  ADD KEY `IdDostawcy` (`IdDostawcy`);

--
-- Indexes for table `zamowienietowar`
--
ALTER TABLE `zamowienietowar`
  ADD PRIMARY KEY (`IdZamowienieTowar`),
  ADD KEY `IdTowar` (`IdTowar`),
  ADD KEY `IdZamowienie` (`IdZamowienie`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `dostawca`
--
ALTER TABLE `dostawca`
  MODIFY `IdDostawca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT dla tabeli `dostawcatowar`
--
ALTER TABLE `dostawcatowar`
  MODIFY `IdDostawcaTowar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT dla tabeli `jednostkimiary`
--
ALTER TABLE `jednostkimiary`
  MODIFY `IdJednostkaMiary` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT dla tabeli `kategoria`
--
ALTER TABLE `kategoria`
  MODIFY `IdKategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `IdPracownik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT dla tabeli `towar`
--
ALTER TABLE `towar`
  MODIFY `IdTowar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  MODIFY `IdUzytkownik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT dla tabeli `zamowienietowar`
--
ALTER TABLE `zamowienietowar`
  MODIFY `IdZamowienieTowar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `dostawcatowar`
--
ALTER TABLE `dostawcatowar`
  ADD CONSTRAINT `dostawcatowar_ibfk_1` FOREIGN KEY (`IdTowar`) REFERENCES `towar` (`IdTowar`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `dostawcatowar_ibfk_2` FOREIGN KEY (`IdDostawca`) REFERENCES `dostawca` (`IdDostawca`) ON DELETE CASCADE;

--
-- Ograniczenia dla tabeli `towar`
--
ALTER TABLE `towar`
  ADD CONSTRAINT `towar_ibfk_1` FOREIGN KEY (`IdKategoria`) REFERENCES `kategoria` (`IdKategoria`) ON DELETE CASCADE,
  ADD CONSTRAINT `towar_ibfk_2` FOREIGN KEY (`IdJednostkaMiary`) REFERENCES `jednostkimiary` (`IdJednostkaMiary`) ON DELETE CASCADE;

--
-- Ograniczenia dla tabeli `uzytkownik`
--
ALTER TABLE `uzytkownik`
  ADD CONSTRAINT `uzytkownik_ibfk_1` FOREIGN KEY (`IdPracownik`) REFERENCES `pracownik` (`IdPracownik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ograniczenia dla tabeli `zamowienietowar`
--
ALTER TABLE `zamowienietowar`
  ADD CONSTRAINT `zamowienietowar_ibfk_2` FOREIGN KEY (`IdTowar`) REFERENCES `towar` (`IdTowar`) ON DELETE CASCADE,
  ADD CONSTRAINT `zamowienietowar_ibfk_3` FOREIGN KEY (`IdZamowienie`) REFERENCES `zamowienie` (`IdZamowienie`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
