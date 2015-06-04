Крестики-нолики на java / Swing
===============================

**Предметная область:**
* Клетка
* Поле
* Игрок (человек или AI)
* Состояние игры

**MVC - Model View Controller**
* Model - классы, описывающие предметную область
* View - классы, описывающие пользовательский интерфейс 
* Controller - управляющие классы, которые знают о модели и view

Модель "ничего не знает" об остальных компонентах системы.
View "знает" "интерфейсные" данные из модели.
Контроллер знает всё о модели и view и может их заменить другой реализацией.