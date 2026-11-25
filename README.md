# AutoElite — System Zarządzania Salonem Samochodowym

Aplikacja webowa typu **mini-Otomoto** wspierająca obsługę salonu i komisu samochodowego. Projekt został zrealizowany w technologii **Java EE / JSF** na serwerze **Apache TomEE** w oparciu o architekturę dwubazodanową uruchomioną w kontenerach **Docker**.

---

## Główne funkcjonalności

* **Uwierzytelnianie i autoryzacja (FORM Auth):** Deklaratywne logowanie i wylogowanie użytkowników oparte o zewnętrzną bazę danych ról i kont (`DataSourceRealm`) oraz ograniczenie dostępu do chronionych widoków aplikacji (`/views/*`) dla ról `admin` i `dealer`.
* **Bezpieczeństwo komunikacji (HTTPS + Filtr HTTP):**
  * Wymuszenie szyfrowanego połączenia **HTTPS** (`CONFIDENTIAL`) z wykorzystaniem własnego magazynu certyfikatów (`keystore`).
  * Autorski filtr serwletów (`SecurityAndCacheFilter`) blokujący pamięć podręczną przeglądarki (`Cache-Control`, `Pragma`, `Expires`) oraz zapisywanie plików cookies (`Set-Cookie`), przy jednoczesnym podtrzymaniu sesji poprzez adres URL (`tracking-mode`).
* **Współpraca komponentów zarządzanych (CDI):**
  * `@ApplicationScoped` (`AppConfigBean`) — globalne ustawienia salonu, aktualny kurs EUR oraz licznik wszystkich odsłon.
  * `@SessionScoped` (`SessionBean`) — stan sesji zalogowanego użytkownika, licznik wykonanych akcji, obsługa języka oraz wstrzykiwanie zależności (`@Inject`).
  * `@ViewScoped` (`DashboardViewBean`) — interaktywny kalkulator rat leasingowych (przeliczanie kwot w PLN i EUR w czasie rzeczywistym).
  * Komponent inicjalizowany deklaratywnie w `faces-config.xml` (`PromoBannerBean`) — obsługa globalnego baneru promocyjnego.
* **Internacjonalizacja (i18n):** Pełna obsługa języka polskiego i angielskiego (`messages.properties`, `messages_pl.properties`, `messages_en.properties`) z automatycznym wykrywaniem języka przeglądarki oraz możliwością ręcznego przełączania w pasku nawigacji.
* **Zarządzanie ofertami (CRUD w pamięci — bez AJAX oraz z AJAX):**
  * Widok klasyczny (`collectionTable.xhtml`) do dodawania, edycji w wierszu i usuwania roboczych ofert samochodowych.
  * Widok asynchroniczny (`ajaxTable.xhtml`) realizujący pełny CRUD bez przeładowywania strony z użyciem `<f:ajax>`.
* **Katalog pojazdów w bazie danych (JPA / DAO + TO):** Trwałe zarządzanie bazą ogłoszeń samochodowych (odczyt, dodawanie, modyfikacja, usuwanie) połączone z dedykowaną bazą danych PostgreSQL za pomocą warstwy JPA/DAO oraz obiektów transferowych (TO).

---

## Stos technologiczny

* **Język i platforma:** Java 8, Java EE 7 Web
* **Warstwa prezentacji:** JSF 2.2 (Facelets), PrimeFaces 11.0, HTML5, CSS3, AJAX
* **Kontener i wstrzykiwanie zależności:** CDI (`@Named`, `@Inject`, `@ApplicationScoped`, `@SessionScoped`, `@ViewScoped`)
* **Warstwa persystencji:** JPA (EclipseLink), wzorce DAO i Transfer Object (TO), JDBC
* **Serwer aplikacji:** Apache TomEE Plume 8.0.12
* **Bazy danych i konteneryzacja:** PostgreSQL 16 (Alpine), Docker & Docker Compose
* **Budowanie projektu:** Apache Maven

---

## Architektura baz danych (Docker)

Projekt wykorzystuje dwie odizolowane bazy danych PostgreSQL uruchamiane za pomocą `docker-compose.yml`:

1. **`auth_db` (port `5432`):** Przechowuje konta użytkowników (`users`) oraz przypisane im uprawnienia (`user_roles`) wykorzystywane przez mechanizm logowania serwera TomEE (`jdbc/dealershipAuth`).
2. **`app_db` (port `5433`):** Przechowuje właściwe dane biznesowe salonu — katalog pojazdów (`cars`) obsługiwany przez warstwę JPA (`jdbc/dealershipApp`).
