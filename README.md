# GitHub Repository Fetcher

## Opis projektu
GitHub User Data fetcher jest API'm opartym na **Quarkus**, które umożliwia:
- Pobieranie listy nazw repozytoriów dla danego użytkownika GitHub,
- Filtrację repozytoriów, aby zwracać tylko te, które **nie są forkami**,
- Pobieranie listy nazwy branchy wraz z najnowszym SHA commita dla każdego repozytorium,
- Sprawdzanie, czy użytkownik istnieje na GitHubie.

API zwraca dane w formacie **JSON** i obsługuje błędy zwracając odpowiednie statusy HTTP.

---

## Wymagania
Aby uruchomić projekt, upewnij się, że masz zainstalowane:
- **JDK 21+**
- **Maven 3.8+**
- **Quarkus 3+**
- **Postman/curl** (do testowania API)

---

## Konfiguracja

Projekt korzysta z **MicroProfile REST Client** do komunikacji z API GitHub.
W pliku `application.properties` ustaw adres API:
```properties
github-api/mp-rest/url=https://api.github.com
github-api/mp-rest/scope=javax.inject.Singleton
```

Jeśli API GitHuba wymaga uwierzytelnienia, można dodać token, w przeciwnym razie jest ograniczona ilość możliwych zapytań do API GitHuba:
```properties
github-api/mp-rest/interceptors=com.example.auth.GitHubAuthInterceptor
github-api/token=ghp_your_personal_access_token
```

---

## Uruchamianie aplikacji
Aby uruchomić aplikację lokalnie, użyj polecenia:
```sh
mvn GitHubUserDataFetcher:dev
```

Aplikacja domyślnie działa na **http://localhost:8080**

---

## Dokumentacja API

### 1. Pobranie repozytoriów użytkownika (które nie są forkami)
**Endpoint:**
```http
GET /loadUserRepositoriesFromGit?login={username}
```
**Przykład użycia:**
```sh
curl -X GET "http://localhost:8080/loadUserRepositoriesFromGit?login=octocat" -H "Accept: application/json"
```
**Przykładowa odpowiedź:**
```json
[
  {
    "name": "Hello-World",
    "owner": "octocat",
    "branches": [
      { "name": "main", "lastCommitSha": "abc123" }
    ]
  }
]
```

### 2. Obsługa błędów
Jeśli użytkownik nie istnieje, API zwraca:
```json
{
  "status": 404,
  "message": "User could not be found"
}
```

---

## Testy
Aby uruchomić testy integracyjne:
```sh
mvn test
```
Testy sprawdzają poprawność pobierania repozytoriów obsługi błędów.

---

## Autor
Projekt stworzony przez **Kacpra Chyłę** w ramach zadania rekrutacyjnego.

