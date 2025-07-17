### Hexlet tests and linter status:

[![Actions Status](https://github.com/ElsaAkhmatyanova/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ElsaAkhmatyanova/java-project-71/actions)
![CI](https://github.com/ElsaAkhmatyanova/java-project-71/actions/workflows/ci.yml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ElsaAkhmatyanova_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ElsaAkhmatyanova_java-project-71)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ElsaAkhmatyanova_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ElsaAkhmatyanova_java-project-71)

# Gendiff

Программа для сравнения файлов и вывода различий в удобочитаемом виде.

## Запуск и входные аргументы программы

Для удобства предоставлен run.sh скрипт вызывающий собранное приложение в `./build/install/app/bin/app` с аргументами

| Аргументы        | Описание                                                          |
|------------------|-------------------------------------------------------------------|
| `filepath1`      | Путь к первому файлу                                              |
| `filepath2`      | Путь ко второму файлу                                             |
| `-f`, `--format` | Формат вывода программы. Возможные варианты: json, stylish, plain |
| `-h`, `--help`   | вызов помощи программы                                            |

## Примеры запуска

Пример вывода помощи
![help_example.png](images/help_example.png)

Пример сравнения двух json файлов (с дефолтным stylish форматом вывода)
![compare_two_json_files_default_stylish.png](images/compare_two_json_files_default_stylish.png)

Пример сравнения двух yml файлов (с дефолтным stylish форматом вывода)
![compare_two_yml_files_default_stylish.png](images/compare_two_yml_files_default_stylish.png)

Пример сравнения двух файлов с выбранным форматом вывода результата
![compare_two_json_files_plain.png](images/compare_two_json_files_plain.png)
