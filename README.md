# WebLab4
## Сборка проекта в jar файл:
1) Сборка FrontEnd в js файл:
```
yarn build
```
2) Сборка BackEnd с файлом JS(пункт 1) в jar файл:
```
./gradlew build
```

## Запуск PROD версии на Unix/Linux:
1) Установка переменных окружения:
```
export db_url='your_url';
export db_login='your_login';
export db_password='your_pass';
export jwt_secret_access='your_secret_access_key';
export jwt_secret_refresh='your_secret_refresh_key';
```
2) Запуск jar файла:
```
java -jar /{path}/{your_file_name}.jar
```

## Запуск PROD версии на Windows powershell:
1) Установка переменных окружения:
```
${env:db_url}='your_url';
${env:db_login}='your_login';
${env:db_password}='your_pass';
${env:jwt_secret_access}='your_secret_access_key';
${env:jwt_secret_refresh}='your_secret_refresh_key';
```
2) Запуск jar файла
```
java -jar /{path}/{your_file_name}.jar
```

## Запуск DEV версии на Windows powershell:
1) yarn start
2) Установка переменных окружения:
```
${env:spring.profile.active}='dev'
${env:db_url}='your_url';
${env:db_login}='your_login';
${env:db_password}='your_pass';
${env:jwt_secret_access}='your_secret_access_key';
${env:jwt_secret_refresh}='your_secret_refresh_key';
```
3) Запуск BackEnd с помощью среды разработки
