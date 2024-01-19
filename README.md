# WebLab4
## Запуск на Windows powershell:

1) Сборка FrontEnd в js файл:
```
yarn build
```
2) Сборка BackEnd с файлом JS(пункт 2) в jar файл:
```
./gradlew build
```
3) Установка переменных окружения:
```
${env:db_url}='your_url';
${env:db_login}='your_login';
${env:db_password}='your_pass';
${env:jwt_secret_access}='your_secret_access_key';
${env:jwt_secret_refresh}='your_secret_refresh_key';
```
3) Запуск jar файла
```
java -jar build/libs/{your_file_name}.jar
```
