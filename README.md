# **PAP Desktop Project**

# Etap 1

## Koncepcja projektu
  * Logowanie do aplikacji
  * Różne stopnie uprawnień użytkowników
  * Dodawanie pracowników
  * Usuwanie pracowników
  * Wyświetlanie informacji o:
    * pracowniku
    * statusie zamówienia
    * placówkach firmy
    * zamówieniu
  * Zapisywanie/Odrzucanie zmian
  * Filtracja/sortowanie zamówień

---

# Etap 2
Postawione cele i ich realizacja:
* utworzenie okienkowej aplikacji(wyświetlanie, rozmieszczenie przycisków
  * utworzone zostały okienka na:
    * logowanie
      * logowanie następuje po wpisaniu loginu i hasła, pozwoli to na uruchomienie się odpowiednich paneli w zależności od uprawnień użytkownika, które będą pobierane z bazy danych(aktualnie loginy i hasła są zahardcodowane)
    * panel kuriera(login: courier, hasło: courier)
      * wyświetlanie listy przesyłek, ich nadawców, odbiorców, adresów itp.
      * możliwość zmiany statusu zamówienia(w magazynie, w drodze, dostarczona, w punkcie odbioru)
      * wyszukiwarka(sama implementacja, funkcjonalność zostanie dorobiona na następny etap)
      * przycisk logout i commit --> implementacja w przyszłym etapie
    * panel magazyniera(login: storekeeper, hasło: storekeeper)
      * wyświetlanie listy paczek z możliwością modyfikacji każdej komórki
      * dodawanie nowych przesyłek
      * usuwanie paczek
      * wydanie wielu paczek jednocześnie
      * przydzielenie samochodu do kuriera(sama implementacja, funkcjonalność zostanie dorobiona na następny etap)
      * wyszukiwarka(tak jak u kuriera)
      * przycisk commit, który połączy się z bazą danych i zaktualizuje wartości zmienione(implementacja przycisku w kolejnym etapie)
      * przycisk logout --> implementacja w przyszłym etapie
    * alerty
      * wprowadzono niepoprawne login/hasło
      * wyjście z aplikacji
      * wprowadzono niepoprawny typ danych
* utworzenie bazy danych(testowej bazy)
  * wygenerowanie losowych danych do bazy
  * utworzenie odpowiednich zależności pomiędzy tabelami (wstawienie kluczy obcych)
* utworzenie modelu logicznego bazy danych(właściwej bazy)
  * model logiczny:
![ML](/uploads/687d0ef4d9fff8173850b4891c89ab0c/ML.png)
  * model relacyjny:
![MR](/uploads/3f8a56ff7a0def5a52ad6de35c16aca4/MR.png)
* połączenie bazy danych z aplikacją dekstopową(bez zapewnienia funkcjonalności, na razie komunikacja między aplikacją a bazą)
  * utworzenie kilku klas które pobierają dane z bazy (Data Accessors)
  * łączenie się z bazą danych:
    * łączenie się z bazą za pomocą sterownika OJDBC
    * wysyłanie zapytań do bazy danych za pomocą “prepared statements”

# Etap 3
Postawione cele i ich realizacja
* ukończenie aplikacji desktopowej
  * Dodanie widoku managera
    * Panel managera(np. login: manager, hasło: manager(wczytywane z bazy danych))
      * Wyświetlanie listy paczek, pojazdów, departamentów, stanowisk, pracowników z możliwością modyfikacji każdej komórki
      * Dodawanie nowych rekordów do wymienionych tabel
      * Usuwanie rekordów
      * Przycisk commit, który połączy się z bazą danych i aktualizuje wartości zmienione
      * Przycisk logout
  * Ulepszenie aplikacji pod względem graficznym poprzez dodanie arkusza stylów css
* Ukończenie bazy danych i dodanie funkcjonalności do aplikacji
  * Dodanie funkcji do bazy(np. login - generowanie loginu pracowników)
  * Implementacji przycisku commit oraz logout
* Wyodrębnienie logiki aplikacji od interfejsu i zahardcodowanych rzeczy
  * Logowanie do aplikacji realizowane jest poprzez sprawdzenie automatycznie uprawnień użytkownika i przełączenie go na odpowiedni layout
  * Konfiguracja różnych stałych elementów aplikacji typu szerokość, wysokość okna, teksty dialogowe umieszczone w plikach .json
  * Oddzielenie layoutu logowania do odrębnego pliku, nie znajduje się on już w głównym pliku
* Komentarze
  * Dodanie komentarzy w kluczowych miejscach opisujących najważniejsze działania aplikacji
* Testy
  * Zostały napisane testy jednostkowe do sprawdzania dostępu do bazy danych, funkcjonalności Data Accessorów i edycji zawartości bazy




---

## **Cele na kolejne etapy**

### *Etap 2*
- [x] utworzenie okienkowej aplikacji(wyświetlanie, rozmieszczenie przycisków
- [x] utworzenie bazy danych(testowej bazy)
- [x] utworzenie modelu logicznego bazy danych(właściwej bazy)
- [x] połączenie bazy danych z aplikacją dekstopową(bez zapewnienia funkcjonalności, na razie komunikacja między aplikacją a bazą)

### *Etap 3*
- [ ] ukończenie aplikacji desktopowej
- [ ] ukończenie bazy danych i dodanie funkcjonalności do aplikacji

### *Etap 4*
- [ ] stworzenie strony internetowej
- [ ] umieszczenie bazy danych na serwerze
- [ ] połączenie strony z bazą danych
- [ ] postawienie strony na serwerze

---
# Ogólne informacje

## Środowisko pracy
  * JavaFx (okienko aplikacji)
  * Spring (łączenie się z bazą danych)
  * Baza danych(jeszcze nie wiemy, sqlDeveloper, mySQL?)
  * GitLab -> prosta możliwość wspólnej edycji kodu

## Narzędzia pracy:
  * Visual Studio Code, Windows 10
  * Intellij, Windows 10
  * GitLab

## Research
  * JavaFX
    * [JavaFX commands](https://www.youtube.com/playlist?list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG)
    * [JavaFX Instalation](https://www.youtube.com/watch?v=H67COH9F718)
  * Spring 
    * [Spring Tutorial](https://www.javatpoint.com/spring-boot-tutorial)
  * Bazy danych 
    * [SQL Tutorial](https://www.youtube.com/watch?v=7S_tz1z_5bA&t=1863s&ab_channel=ProgrammingwithMosh)
  * Git 
    * [Git commands](https://git-scm.com/book/en/v2)

## Instalacja aplikacji
  * Desktop
    * Instalacja JavaFX zgodnie z instrukcją z researchu([JavaFX Instalation](https://www.youtube.com/watch?v=H67COH9F718)). Film ten jednak zawiera jedno niedopowiedzenie, gdyż javafx-sdk powinno być pod jakimś łatwiejszym w dostępie miejscu, np bezpośrednio na dysku C 
    * Dodatkowo do działania bazy danych potrzebny jest sterownik oracle - [OJDBC](https://download.oracle.com/otn-pub/otn_software/jdbc/198/ojdbc10.jar). Należy ten sterownik pobrać i dołączyć do projektu javy w taki sam sposób jak pliki z javafx
    * Testy jednostkowe wykorzystują bibliotekę [JUnit](https://github.com/junit-team/junit4/wiki/Download-and-Install)
    * Pobieraniu pliku w formacie JSON wykorzystuje bibliotekę [JSON](http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm)
    
---

### **Został wybrany pomysł firmy kurierskiej, połaczony z bazą danych i wystawiony na webie.**

#### *Reszta pomysłów zostaje w celach dokumentacyjnych.*
---

### Desktop app pomysły:

* Firma pracowników:
    * Lista pracowników
    * Dodawać/usuwać stanowisko
    * Wyszukiwać/sortować pracowników

* Firma kurierska/przewozowa:
    * Paczki do dostarczenia
    * Statusy paczek
    * Historia paczek
    * Informacje o zatrudnionych pracownikach
    * Adresy magazynów/placówek/punktów odbioru
    * Ewentualne połączenie z bazą danych

* Sklep internetowy
    * Baza danych produktów
    * Kup produkt
    * Wyszukiwanie/sortowanie produktów 

* Arkanoid
    * Przeszkody do rozbicia na planszy
    * Licznik żyć gracza
    * Punkty gracza
    * Przyspieszająca piłeczka

* Saper/Pac-man/Sudoku


* (WEB) Kantor walutowy
    * Pobieranie kursów walut z API
    * Możliwość wyboru walutu, przewalutowania na inną
    * Przedstawienie kursu na wykresie, z możliwością ustawienia przedziału czasu(kurs z 7 dni, z miesiąca itp.)



## Pomysły odnośnie projektu:
* Arkanoid(WEB) + Firma kurierska(Java)
* Landing page firmy(WEB) + Firma kurierska(Java)
* Kantor walutowy(WEB) + coś(Java)


### Środowisko pracy
* Java + ewentualne frameworki po researchu -> Visual Studio Code/IntelliJ
* Web -> Visul Studio Code
* Repozytorium zdalne na gitlabie


#### Uwagi i inne 
* Pomysł połączenia projektu z PAP z projektem z BD1
* Mail do prowadzącego laby z BD1 w celu określenia wymagań na projekt

