# 🎬 Movie Watchlist App - Application Workflow

This document explains how the Movie Watchlist Application works internally, from user registration and login to adding movies, fetching IMDb ratings, managing priorities, viewing the dashboard, and submitting feedback.

---

# 📌 1. Application Startup

When the application starts, Spring Boot initializes the application using:

```text
WatchlistApplication.java
```

Spring Boot loads:

- Controllers
- Services
- Repositories
- Entities
- Security configuration
- Validation components

The application runs on:

```text
http://localhost:8080
```

---

# 🏠 2. Home Page Workflow

When a user opens:

```text
/
```

the request is handled by:

```text
HomeController
```

The controller returns the home page.

```text
User
  │
  ▼
Home Page
  │
  ├── Login
  ├── Sign Up
  ├── Watchlist
  └── About
```

The common navigation bar and footer are loaded using Thymeleaf fragments:

```html
<div th:replace="~{fragments/navbar :: navbar}"></div>
```

```html
<div th:replace="~{fragments/footer :: footer}"></div>
```

---

# 👤 3. User Registration Workflow

A new user can create an account through:

```text
/signup
```

The request is handled by:

```text
AuthController
```

The registration form uses:

```text
SignupRequest.java
```

for receiving user registration data.

### Workflow

```text
User
 │
 ▼
Signup Page
 │
 ▼
SignupRequest
 │
 ▼
AuthController
 │
 ▼
UserService
 │
 ▼
UserRepo
 │
 ▼
Database
```

The user information is stored in the database.

---

# 🔐 4. Login Workflow

The user logs in through:

```text
/login
```

Spring Security handles the authentication process.

The login form uses:

```text
LoginRequest.java
```

The login process uses:

```text
CustomUserDetailsService
```

and:

```text
CustomUserDetails
```

### Authentication Flow

```text
User
 │
 ▼
Login Page
 │
 ▼
Email + Password
 │
 ▼
Spring Security
 │
 ▼
CustomUserDetailsService
 │
 ▼
UserRepo
 │
 ▼
Find User by Email
 │
 ▼
CustomUserDetails
 │
 ▼
Authentication Successful
```

If the credentials are valid, the user is authenticated and can access protected features.

---

# 🛡️ 5. Spring Security Workflow

Security configuration is handled by:

```text
SecurityConfig.java
```

Password handling is configured through:

```text
PasswordConfig.java
```

Passwords should be stored securely using password encoding rather than plain text.

After successful authentication, the user can access authenticated pages such as:

```text
/dashboard
/watchlist
/watchlistItemForm
/feedback
```

---

# 📊 6. Dashboard Workflow

After login, the user can open:

```text
/dashboard
```

The request is handled by:

```text
DashboardController
```

The controller gets the currently logged-in user's email from Spring Security:

```text
Authentication
      │
      ▼
Logged-in User Email
      │
      ▼
UserRepo
      │
      ▼
User
```

The dashboard then uses:

```text
DashboardService
```

to calculate:

- Total Movies
- Average Rating
- High Priority Movies
- Review Count
- Recent Movies

### Dashboard Flow

```text
DashboardController
        │
        ▼
DashboardService
        │
        ▼
     MovieRepo
        │
        ▼
 Movies of Logged-in User
        │
        ├── Total Movies
        ├── Average Rating
        ├── High Priority
        ├── Review Count
        └── Recent Movies
```

The calculated data is sent to:

```text
dashboard.html
```

using the Thymeleaf model.

---

# 🎬 7. Watchlist Workflow

The watchlist page displays movies belonging to the logged-in user.

```text
/watchlist
```

The request is handled by:

```text
MovieController
```

The controller communicates with:

```text
DatabaseService
```

which uses:

```text
MovieRepo
```

to retrieve movies.

### Flow

```text
User
 │
 ▼
Watchlist Page
 │
 ▼
MovieController
 │
 ▼
DatabaseService
 │
 ▼
MovieRepo
 │
 ▼
Database
 │
 ▼
Movies
 │
 ▼
watchlist.html
```

Only movies associated with the current user are displayed.

---

# ➕ 8. Add Movie Workflow

The user can add a movie through:

```text
/watchlistItemForm
```

The form collects information such as:

- Movie Title
- Rating
- Priority
- Comment
- Source

The request is processed by:

```text
MovieController
```

---

# ⭐ 9. IMDb Rating Workflow

When a movie is submitted, the application attempts to retrieve its IMDb rating through the:

```text
OMDb API
```

The responsible service is:

```text
RatingService
```

### Workflow

```text
User enters movie title
          │
          ▼
    MovieController
          │
          ▼
     RatingService
          │
          ▼
       OMDb API
          │
      ┌───┴───┐
      ▼       ▼
   Found    Not Found
      │       │
      ▼       ▼
IMDb Rating  Manual Rating
      │       │
      └───┬───┘
          ▼
       Movie
          │
          ▼
   DatabaseService
          │
          ▼
       MovieRepo
          │
          ▼
       Database
```

If the movie is available through OMDb, the IMDb rating can be used automatically.

If the movie cannot be found, the user can provide a manual rating.

---

# 🚦 10. Priority Workflow

The application supports:

```text
H = High
M = Medium
L = Low
```

Priority can be assigned automatically based on the movie rating or entered manually.

Supported manual values include:

```text
H
High

M
Med
Medium

L
Low
```

The application normalizes accepted priority values.

For example:

```text
High → H
Medium → M
Low → L
```

---

# ✅ 11. Priority Validation

Priority validation is implemented using custom Bean Validation.

The validation components are:

```text
Priority.java
PriorityAnnotationLogic.java
```

The validation checks whether the supplied priority value is acceptable.

### Validation Flow

```text
User Input
    │
    ▼
Priority Validation
    │
    ▼
PriorityAnnotationLogic
    │
 ┌──┴──┐
 ▼     ▼
Valid Invalid
 │       │
 ▼       ▼
Continue Error Message
```

---

# ⭐ 12. Rating Validation

Rating validation is handled using:

```text
Rating.java
RatingAnnotationLogic.java
```

The entered rating is checked before the movie is saved.

```text
Rating Input
     │
     ▼
Rating Validation
     │
     ▼
RatingAnnotationLogic
     │
 ┌───┴───┐
 ▼       ▼
Valid   Invalid
 │        │
 ▼        ▼
Save    Show Error
```

---

# 💾 13. Saving a Movie

After validation and rating processing, the movie is passed to:

```text
DatabaseService
```

The service communicates with:

```text
MovieRepo
```

which extends:

```text
JpaRepository<Movie, Integer>
```

### Save Flow

```text
MovieController
      │
      ▼
DatabaseService
      │
      ▼
MovieRepo
      │
      ▼
JPA / Hibernate
      │
      ▼
Database
```

---

# ✏️ 14. Update Movie Workflow

Users can update an existing movie.

```text
Watchlist
   │
   ▼
Update Movie
   │
   ▼
MovieController
   │
   ▼
DatabaseService
   │
   ▼
MovieRepo
   │
   ▼
Database
```

The updated information may include:

- Movie title
- Rating
- Priority
- Comment
- Source

After the update, the user is redirected back to the appropriate page.

---

# ❌ 15. Delete Movie Workflow

When a user deletes a movie:

```text
Delete
  │
  ▼
MovieController
  │
  ▼
DatabaseService
  │
  ▼
MovieRepo
  │
  ▼
Database
```

The selected movie is permanently removed from the database.

---

# 💬 16. Feedback Workflow

Authenticated users can submit feedback through:

```text
/feedback
```

The feedback feature is protected so that only logged-in users can submit feedback.

### Workflow

```text
Logged-in User
      │
      ▼
Feedback Page
      │
      ▼
FeedbackController
      │
      ▼
Feedback
      │
      ▼
FeedbackRepo
      │
      ▼
Database
```

After successful submission, the application redirects back to the feedback page and displays the success message.

---

# 🧭 17. Navigation Workflow

The application uses a common navbar fragment:

```text
templates/fragments/navbar.html
```

Pages include the navbar using:

```html
<div th:replace="~{fragments/navbar :: navbar}"></div>
```

The footer is included using:

```html
<div th:replace="~{fragments/footer :: footer}"></div>
```

This keeps the navigation and footer consistent across the application.

---

# 🗂️ 18. Overall Application Workflow

The complete application flow can be summarized as:

```text
                         Movie Watchlist App
                                  │
                                  ▼
                              Home Page
                                  │
                    ┌─────────────┴─────────────┐
                    ▼                           ▼
                 Sign Up                      Login
                    │                           │
                    ▼                           ▼
               UserService              Spring Security
                    │                           │
                    ▼                           ▼
                 UserRepo              CustomUserDetailsService
                    │                           │
                    ▼                           ▼
                Database                 Authentication
                                                │
                                                ▼
                                           Dashboard
                                                │
                         ┌──────────────────────┼──────────────────────┐
                         ▼                      ▼                      ▼
                     Watchlist              Add Movie             Feedback
                         │                      │                      │
                         ▼                      ▼                      ▼
                  MovieController        MovieController       FeedbackController
                         │                      │                      │
                         ▼                      ▼                      ▼
                  DatabaseService        RatingService          FeedbackRepo
                         │                      │
                         ▼                      ▼
                     MovieRepo              OMDb API
                         │
                         ▼
                      Database
```

---

# 🧩 19. Main Components and Responsibilities

| Component | Responsibility |
|---|---|
| `Controller` | Handles HTTP requests and responses |
| `Service` | Contains application/business logic |
| `Repository` | Communicates with the database |
| `Entity` | Represents database entities |
| `DTO` | Transfers form/request data |
| `Security` | Handles authentication and user details |
| `Validation` | Validates user input |
| `Thymeleaf` | Renders dynamic HTML pages |
| `Spring Data JPA` | Provides database operations |
| `Hibernate` | ORM implementation |
| `OMDb API` | Provides movie/IMDb rating information |

---

# 🔄 20. MVC Request Flow

The general request flow of the application is:

```text
Browser
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
   │
   ▼
Service
   │
   ▼
Controller
   │
   ▼
Thymeleaf
   │
   ▼
HTML Response
   │
   ▼
Browser
```

---

# 🎯 Conclusion

The Movie Watchlist Application follows a layered Spring Boot architecture.

The main flow is:

```text
User
 ↓
Controller
 ↓
Service
 ↓
Repository
 ↓
Database
```

External movie information is obtained through:

```text
RatingService
      ↓
   OMDb API
```

Authentication is handled through:

```text
Spring Security
      ↓
CustomUserDetailsService
      ↓
UserRepo
```

This structure keeps the application organized, maintainable, secure, and easier to extend with future features.

---

## 📚 Related Documentation

- 📖 [README.md](README.md) — Project overview, features, technology stack and setup
- 🏗️ [STRUCTURE.md](STRUCTURE.md) — Complete package and file structure
- ⚙️ **WORKING.md** — Step-by-step application workflow