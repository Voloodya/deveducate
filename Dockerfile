FROM openjdk:8-jdk-alpine
#Устанавливаем метку
LABEL maintainer="vlutsenko@cinimex.ru"
#Создаем группу, пользователя и устанавливаем пользователю группу
RUN addgroup -S spring && adduser -S spring -G spring
#Задаем пользователя под которы будет запущено приложение
USER spring:spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} deveducate-core.jar
# Задаём текущую рабочую директорию
WORKDIR /usr/src/my_app_directory
# Копируем код из локального контекста в рабочую директорию образа
COPY ./deveducate-core/build/libs/deveducate.jar .
ENTRYPOINT ["java","-jar","deveducate.jar"]
# Открываем порты
EXPOSE 8080
# Создаём том для хранения данных
VOLUME /my_volume