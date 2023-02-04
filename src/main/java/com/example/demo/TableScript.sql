-- Выбираем какую таблицу хотим изменить.
ALTER TABLE expense
-- Выбираем колонку которую хотим сделать не_нулл аннотацией SET NOT NULL
    ALTER COLUMN amount SET NOT NULL,
-- Выбираем
        ADD CONSTRAINT amount_constraint CHECK (amount >= 0),

