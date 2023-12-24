# Дипломый проект #

## Требования:

Для работы требуется установить следующие инструменты:
* Docker
* IntelliJ IDEA
* Git

## Скачивание проекта:

Необходимо склонировать проект и открыть его в IDE.

### Запуск контейнеров, тестов и создание отчёта:

1. Перейти в терминале в папку проекта и набрать команду запуска контейнеров:

   `docker-compose up -d`

2. Проверить, что контейнеры запущены корректно:

   `docker-compose ps`

Должны быть запущены три контейнера - 'mysqlserver', 'postgres' и 'gate'

3. Теперь можно запускать непосредственно проект. 
Выберите базу данных для запуска.
К примеру, для выбора Mysql команда выглядит так:

   `java -jar .\artifacts\aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`

Для Postgres так:

   `java -jar .\artifacts\aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`

4. Открыть параллельно другой терминал и запустить тесты так:

Для запуска тестов на Mysql
   `.\gradlew clean test -Ddatabase=mysql`

Для запуска тестов на Postgres
`.\gradlew clean test -Ddatabase=postgresql`

5. В конце, можно открыть отчёт для анализа результатов:

   `.\gradlew allureServe`

6. Для остановки контейнеров:

   `docker-compose down`

   или

   `docker stop mysqlserver postgres gate`