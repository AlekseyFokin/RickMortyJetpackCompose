Демонстрационное android - приложение просмотра персонажей и другой информации по мультфильму Rick&Morty. 
Источник данных -  api с адресом https://rickandmortyapi.com. Интерфейс приложения написал на Jetpack Compose с использованием Navigation, LazyColumn и Paging3.
Приложение состоит из 3-х экранов:
- Пагинирующий кликабельный список героев мультфильма 
- Характеристика выбранного на первом экране героя
- Пагинирующий список локаций
Обработка ошибок подключения к REST API выполнена с помощью Paging3/LoadState.

Два основных сценария работы приложения покрыто UI-тестами, для этого использовано Kaspresso.
Сценарии тестирования:
1) На главном экране, после загрузки персонажей в LazyColumn, выбирается 2 персонаж и поле Name сравнивается с заданным в тесте значением, затем по персонажу осуществляется клик, после этого открывается окно с этим отдельным персонажем и значение поля, содержащего имя персонажа снова сравнивается с заданным в тесте значением. Таким образом проверяется, что при клике на определенного персонажа из списка открывается отдельный экран именно с этим персонажем.
2) Запускается приложение, затем осуществляется выключение сети с помощью adb сервера. Тест переходит на экран с локациями. Локации не загружаются, так как нет интернет-соединения и на экране должно появится сообщение об этом вместе с кнопкой повторной загрузки. В тесте осуществляется поиск кнопки и проверяется надпись на ней (сравнивается со значением, заданным в тесте). В блоке after осуществляется включение сети.Таким образом проверятся адекавтное поведение приложения при отключенном интернет-соединении.


<img src="https://github.com/AlekseyFokin/RickMortyJetpackCompose/blob/main/presentation1.gif" alt="gif" width="360" height="800">
    

<img src="https://github.com/AlekseyFokin/RickMortyJetpackCompose/blob/main/presentation2.gif" alt="gif" width="360" height="800">

  
