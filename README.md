Сначала необходимо подключиться к БД MySQL и создать таблицу person c 3 пользователями:
![image](https://github.com/NikitaVolkov01/REST_App/assets/63566223/cb8aa860-be53-46db-8e52-a996d5c2606d)

Отправим GET запрос на адрес http://localhost:8080/people и получим от приложения список хранящихся пользователей в БД, в формате JSON:
![image](https://github.com/NikitaVolkov01/REST_App/assets/63566223/219542e6-b12e-418c-8838-09623ec71fa0)

Теперь попробуем получить пользователя из БД с определенным id:
![image](https://github.com/NikitaVolkov01/REST_App/assets/63566223/78e9a1a5-580d-4e41-90f3-952def8f73f7)

Если запросить пользователя с несуществующим id, то получим ошибку, также в формате JSON:
![image](https://github.com/NikitaVolkov01/REST_App/assets/63566223/e734466d-b2c2-4e51-9f51-415386cca3f2)

Также мы можем отправить POST запрос с данными в формате JSON на адрес http://localhost:8080/people:
![image](https://github.com/NikitaVolkov01/REST_App/assets/63566223/0efabd98-2a04-432a-9c68-05f874ecc362)

Сновы выполним GET запрос, чтобы проверить, что пользователь добавился в БД:
![image](https://github.com/NikitaVolkov01/REST_App/assets/63566223/cec70e64-6af4-4e7d-b902-a2a4a7ae18f8)

Как мы видим, пользователь c name = Masha добавился с id = 4.




