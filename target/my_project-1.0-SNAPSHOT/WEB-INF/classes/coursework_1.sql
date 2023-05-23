-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Май 22 2023 г., 15:24
-- Версия сервера: 10.4.27-MariaDB
-- Версия PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `coursework`
--

-- --------------------------------------------------------

--
-- Структура таблицы `concerts`
--

CREATE TABLE `concerts` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `ticket_price_s` decimal(10,2) NOT NULL DEFAULT 0.00,
  `ticket_price_v` decimal(10,2) NOT NULL DEFAULT 0.00,
  `date` varchar(100) NOT NULL,
  `time` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `concerts`
--

INSERT INTO `concerts` (`id`, `name`, `location`, `ticket_price_s`, `ticket_price_v`, `date`, `time`) VALUES
(1, 'Rock Fest', 'Madison Square Garden, New York', '50.00', '100.00', '2023-06-01', '20:00'),
(2, 'Classical Night', 'Royal Albert Hall, London', '75.00', '150.00', '2023-06-05', '19:30'),
(3, 'Jazz Jam', 'Blue Note, Tokyo', '40.00', '80.00', '2023-06-10', '21:00');

-- --------------------------------------------------------

--
-- Структура таблицы `concert_musicians`
--

CREATE TABLE `concert_musicians` (
  `concert_id` int(11) NOT NULL,
  `musician_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `concert_musicians`
--

INSERT INTO `concert_musicians` (`concert_id`, `musician_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(3, 5),
(3, 6),
(3, 7);

-- --------------------------------------------------------

--
-- Структура таблицы `musicians`
--

CREATE TABLE `musicians` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `bio` text DEFAULT NULL,
  `music_style` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `musicians`
--

INSERT INTO `musicians` (`id`, `first_name`, `last_name`, `bio`, `music_style`) VALUES
(1, 'Foo', 'Fighters', 'American rock band formed in Seattle, Washington in 1994', 'Rock'),
(2, 'Red Hot', 'Chili Peppers', 'American rock band formed in Los Angeles, California in 1983', 'Rock'),
(3, 'Pearl', 'Jam', 'American rock band formed in Seattle, Washington in 1990', 'Rock'),
(4, 'London Symphony', 'Orchestra', 'British orchestra based in London, founded in 1904', 'Classical'),
(5, 'Herbie', 'Hancock', 'American pianist, keyboardist, bandleader, composer and actor', 'Jazz'),
(6, 'Miles', 'Davis', 'American trumpeter, bandleader, and composer who is among the most influential and acclaimed figures in the history of jazz and 20th century music', 'Jazz'),
(7, 'John', 'Coltrane', 'American jazz saxophonist and composer, widely considered one of the most significant and influential jazz musicians of the 20th century', 'Jazz');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(1, 'John Smith', 'johnsmith@example.com', 'password123');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `concerts`
--
ALTER TABLE `concerts`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `concert_musicians`
--
ALTER TABLE `concert_musicians`
  ADD PRIMARY KEY (`concert_id`,`musician_id`),
  ADD KEY `fk_musician` (`musician_id`);

--
-- Индексы таблицы `musicians`
--
ALTER TABLE `musicians`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `concerts`
--
ALTER TABLE `concerts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `musicians`
--
ALTER TABLE `musicians`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `concert_musicians`
--
ALTER TABLE `concert_musicians`
  ADD CONSTRAINT `fk_concert` FOREIGN KEY (`concert_id`) REFERENCES `concerts` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_musician` FOREIGN KEY (`musician_id`) REFERENCES `musicians` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
