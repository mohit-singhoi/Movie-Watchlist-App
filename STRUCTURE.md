# 🎬 Movie Watchlist Application - Project Structure

This file explains the organization of the Movie Watchlist Application and the purpose of its main packages and files.

## 📁 Project Structure

```text
Movie-Watchlist-App/
│
├── src/
│   │
│   ├── main/
│   │   │
│   │   ├── java/
│   │   │   └── com/example/mohit/watchlist/
│   │   │
│   │   │       ├── WatchlistApplication.java
│   │   │       │
│   │   │       ├── config/
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   └── PasswordConfig.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── HomeController.java
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── DashboardController.java
│   │   │       │   ├── MovieController.java
│   │   │       │   ├── UserController.java
│   │   │       │   └── FeedbackController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   ├── LoginRequest.java
│   │   │       │   └── SignupRequest.java
│   │   │       │
│   │   │       ├── entity/
│   │   │       │   ├── User.java
│   │   │       │   ├── Movie.java
│   │   │       │   └── Feedback.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── UserRepo.java
│   │   │       │   ├── MovieRepo.java
│   │   │       │   └── FeedbackRepo.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── MovieService.java
│   │   │       │   ├── DashboardService.java
│   │   │       │   ├── RatingService.java
│   │   │       │   └── UserService.java
|   |   |       |   |__ FeedbackService.java 
│   │   │       │
│   │   │       ├── security/
│   │   │       │   ├── CustomUserDetails.java
│   │   │       │   └── CustomUserDetailsService.java
│   │   │       │
│   │   │       └── validations/
│   │   │           ├── Priority.java
│   │   │           ├── Rating.java
│   │   │           ├── PriorityAnnotationLogic.java
│   │   │           └── RatingAnnotationLogic.java
│   │   │
│   │   └── resources/
│   │       │
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── Images/
│   │       │
│   │       ├── templates/
│   │       │   ├── fragments/
│   │       │   │   ├── navbar.html
│   │       │   │   └── footer.html
│   │       │   │
│   │       │   ├── login.html
│   │       │   ├── signup.html
│   │       │   ├── dashboard.html
│   │       │   ├── watchlist.html
│   │       │   ├── watchlistItemForm.html
│   │       │   ├── watchlistItem.html
│   │       │   ├── about.html
│   │       │   ├── contact.html
│   │       │   └── feedback.html
│   │       │
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── README.md
├── WORKING.md
└── STRUCTURE.md
```

---

## 📦 Package & File Overview

### `config/`

Contains application configuration and Spring Security configuration.

- **SecurityConfig.java** — Configures Spring Security, authentication, authorization, login, and logout.
- **PasswordConfig.java** — Provides password encoding configuration for securely storing user passwords.

### `controller/`

Contains controllers responsible for handling HTTP requests and connecting the UI with the application logic.

- **HomeController.java** — Handles the application's home page.
- **AuthController.java** — Handles login and signup-related pages and authentication requests.
- **DashboardController.java** — Handles the user dashboard and dashboard statistics.
- **MovieController.java** — Handles movie/watchlist operations.
- **UserController.java** — Handles user-related operations.
- **FeedbackController.java** — Handles feedback and suggestions submitted by authenticated users.

### `dto/`

Contains Data Transfer Objects used for receiving and transferring request data.

- **LoginRequest.java** — Stores login request information.
- **SignupRequest.java** — Stores signup/registration request information.

### `entity/`

Contains JPA entity classes that represent database tables.

- **User.java** — Represents application users.
- **Movie.java** — Represents movies stored in the user's watchlist.
- **Feedback.java** — Represents user feedback and suggestions.

### `repository/`

Contains Spring Data JPA repositories used for database operations.

- **UserRepo.java** — Performs database operations related to users.
- **MovieRepo.java** — Performs database operations related to movies.
- **FeedbackRepo.java** — Performs database operations related to feedback.

### `service/`

Contains the application's business logic.

- **MovieService.java** — Handles movie/watchlist database operations.
- **DashboardService.java** — Calculates dashboard statistics such as total movies, average rating, high-priority movies, review count, and recent movies.
- **RatingService.java** — Handles movie rating-related operations and external movie rating retrieval.
- **UserService.java** — Handles user-related business logic.
- **FeedbackService.java** -- Handle User Feedback.

### `security/`

Contains classes used to integrate custom users with Spring Security.

- **CustomUserDetails.java** — Converts the application's `User` entity into Spring Security's `UserDetails`.
- **CustomUserDetailsService.java** — Loads users from the database during authentication.

### `validations/`

Contains custom validation annotations and their validation logic.

- **Priority.java** — Custom validation annotation for movie priority.
- **Rating.java** — Custom validation annotation for movie rating.
- **PriorityAnnotationLogic.java** — Implements the validation logic for movie priority.
- **RatingAnnotationLogic.java** — Implements the validation logic for movie rating.

### `templates/fragments/`

Contains reusable Thymeleaf UI fragments.

- **navbar.html** — Reusable navigation bar.
- **footer.html** — Reusable footer.

### `templates/auth`

Contains the application's Thymeleaf HTML pages.

- **login.html** — User login page.
- **signup.html** — User registration page.

### `templates/`

Contains the application's Thymeleaf HTML pages.

- **dashboard.html** — Personalized user dashboard.
- **index.html** — Displays Home Page  Movie Watchlist App.
- **watchlistItemForm.html** — Form for adding or editing movies.
- **watchlistItem.html** — Displays movie/watchlist item details.
- **about.html** — About page.
- **contact.html** — Contact page.
- **feedback.html** — Feedback and suggestions page.


### `static/`

Contains static resources used by the frontend.

- **css/** — Stylesheets.
- **js/** — JavaScript files.
- **Images/** — Images and background assets.

### Root Files

- **pom.xml** — Maven configuration and project dependencies.
- **README.md** — Project overview, features, technologies, setup, and usage information.
- **WORKING.md** — Step-by-step explanation of how the application works.
- **STRUCTURE.md** — Project structure and package/file responsibilities.