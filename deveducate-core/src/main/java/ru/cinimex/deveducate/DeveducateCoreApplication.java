package ru.cinimex.deveducate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Класс конфигурации и запуска приложения.
// Spring Boot поддерживает новую аннотацию @SpringBootApplication, которая эквивалентна использованию @Configuration, @EnableAutoConfiguration и @ComponentScan
// с их атрибутами по умолчанию. Таким образом, нужно создать класс, аннотированный с помощью @SpringBootApplication, а Spring Boot включит автоматическую настройку
// и отсканирует ваши ресурсы в текущем пакете.
//The actual application will show the banner (as overridden by configuration) and uses three sources for the ApplicationContext. The application sources are:
//        DeveducateCoreApplication (from the code)
//        MyDatabaseConfig (from the external config)
//        MyJmsConfig(from the external config)

@SpringBootApplication
public class DeveducateCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeveducateCoreApplication.class, args);
    }

}
