
-- Поиск строк в границах дат
SELECT * FROM expense WHERE date >= '2023-01-01' AND date <='2023-01-31';

-- Количество книг купленных в феврале 2023
SELECT count(*)
FROM expense
WHERE date >= '2023-02-01' AND date <='2023-02-28';

-- Сколько всего будет потрачено денег в феврале на покупку книг.
SELECT SUM (amount) as total
FROM expense
WHERE date >= '2023-02-01' AND date <='2023-02-28';

-- Максимальный,минимальный, средний расход в Феврале.
SELECT MIN (amount) AS min,MAX (amount) AS max,AVG (amount) AS avg
FROM expense
WHERE date >= '2023-02-01' AND date <='2023-02-28';

-- Сколько денег ушло на печатку по категориям
SELECT category, SUM(amount) AS amount FROM expense GROUP BY category

-- Вывести расходы на категории где расходы были больше 300
SELECT category, SUM(amount) AS amount FROM expense GROUP BY category
HAVING SUM (amount) >300

--Вывести 1-3 записи
SELECT * FROM expense LIMIT 3;

--Вывести 4-7 записи
SELECT * FROM expense OFFSET 3;

--Вывести 4-6 записи
SELECT * FROM expense OFFSET 3 LIMIT 3;

--Вывести 7 запись
SELECT * FROM expense OFFSET 6 LIMIT 3;