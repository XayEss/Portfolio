-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Ноя 17 2021 г., 14:14
-- Версия сервера: 10.4.17-MariaDB
-- Версия PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `webdb`
--

-- --------------------------------------------------------

--
-- Структура таблицы `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Raccoons'),
(2, 'Pandas'),
(3, 'Ravens'),
(4, 'Magical Creatures');

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`id`, `name`, `description`, `price`) VALUES
(1, 'Raccoon', 'Loves to eat bananas and turn over your garbage!', 2000),
(2, 'Red Panda', 'Eats bamboo', 19999),
(3, 'Raven', 'Very smart birds. They will steal jewelry and bring it to you. Furthermore, they may throw stones at other people. ', 5000),
(4, 'Crow', 'Not as smart as ravens, but will behave similarly. ', 3000),
(5, 'Unicorn', 'Has a shiny horn.', 40000),
(6, 'Phoenix', 'Is literally immortal.', 56999),
(7, 'Panda', 'Is big and fluffy.', 14999),
(8, 'Hydra', 'Have enough heads to scare your neighbors off.', 49999),
(9, 'Kraken', 'Anyone\'s nightmare.', 99999),
(10, 'Matvey', 'Good boy', 1000000),
(11, 'Defender', 'lolola', 1456);

-- --------------------------------------------------------

--
-- Структура таблицы `producttocategory`
--

CREATE TABLE `producttocategory` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `producttocategory`
--

INSERT INTO `producttocategory` (`id`, `product_id`, `category_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(5, 4, 3),
(6, 8, 4),
(7, 9, 4),
(8, 7, 2),
(9, 6, 4),
(10, 3, 3),
(11, 5, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(40) NOT NULL,
  `location` varchar(30) NOT NULL,
  `comment` varchar(300) NOT NULL,
  `repeatPassword` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `name`, `gender`, `location`, `comment`, `repeatPassword`) VALUES
(1, 'admin', '123456789', 'Administrator', 'Masculine', 'Crimea', 'Boss of this GYM!!!', NULL),
(2, 'user', '123', 'User', 'Feminine', 'ДНР', 'A regular user', NULL),
(5, 'alex.seljuk@gmail.com', 'Alex123456', 'alex.seljuk@gmail.com', 'M', 'LNR', 'ghfdtdfrjher346', NULL),
(6, 'nino.futaro@anime.com', 'Alex12345', 'Nino', 'F', 'LNR', 'Kentaro is the best', NULL),
(7, 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Adm', 'M', 'Ukraine', 'I like you', NULL),
(8, 'lllllll@camero.com', '98a65a51d4fef43cfcac71341aa0f02e4689fad90e2f1fd6378ecc6a212a1925', 'Sasha Seljuk', 'M', 'LNR', 'dgdfhftghdh', NULL),
(9, 'sasao@sasageyo.com', '27f185724828b39e65311087d2c117145dd2b9be2b21a84dfcd9f1731ac07d46', 'Sasha Seljuk', 'F', 'LNR', 'ggdsfgsdfg', NULL),
(10, 'albedo@overlord.com', 'c228f6a59b06a4b9f01dabdbc43f44208137cf2bca247938af3cfc0ea2c376d4', 'Albedo', 'F', 'DNR', 'I love Einz', NULL),
(11, 'groo@mmm.cap', 'c228f6a59b06a4b9f01dabdbc43f44208137cf2bca247938af3cfc0ea2c376d4', 'Girl', 'F', 'LNR', 'fffgfd', NULL),
(12, 'a@o.i', 'c228f6a59b06a4b9f01dabdbc43f44208137cf2bca247938af3cfc0ea2c376d4', 'Hentai', 'M', 'LNR', 'fghfgh', NULL),
(13, 'mthhw@gmail.com', 'acd05bbc98cd4c7053fe4e2fb0c99854af005d2d4f661d2ca175b41fb4a6dc76', 'Matvey ', 'M', 'DNR', 'Kramatoriy', NULL),
(14, 'dsf@fgh.gf', '25743a40c8df415674ecb10b03957c38cfd16c6d8294499e4ec332408e5c80e6', 'AOAOAOAOAAAA', 'M', 'LNR', 'YSYSAAA', NULL),
(15, 'denis.lolka@gomag.com', 'Denois1435', 'Denois', 'M', 'LNR', 'aoaoaoaoaoaoaoaoaoao', NULL);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `producttocategory`
--
ALTER TABLE `producttocategory`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT для таблицы `producttocategory`
--
ALTER TABLE `producttocategory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Ограничения внешнего ключа таблицы `producttocategory`
--
ALTER TABLE `producttocategory`
  ADD CONSTRAINT `producttocategory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `producttocategory_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
