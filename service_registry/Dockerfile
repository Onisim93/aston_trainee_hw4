# Указываем базовый образ с Maven и Java 17
FROM maven:3.8.5-openjdk-17-slim AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY pom.xml .
COPY src ./src

# Собираем проект с помощью Maven
RUN mvn clean package -DskipTests

# Выбираем минимальный образ JDK для запуска приложения
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR-файл из стадии сборки
COPY --from=build /app/target/service_registry*.jar ./app.jar

# Определяем команду для запуска приложения
CMD ["java", "-jar", "app.jar"]