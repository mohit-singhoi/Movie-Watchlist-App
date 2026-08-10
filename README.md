# 🎬 Movie Watchlist App

A **full-stack Movie Watchlist Management System** built using **Java, Spring Boot, Spring MVC, Spring Security, Thymeleaf, Spring Data JPA, Hibernate, PostgreSQL/H2 Database, and OMDb API**.

The application allows users to securely register and log in, maintain their personal movie watchlist, automatically fetch IMDb ratings, manage movie priorities, add reviews/comments, view dashboard statistics, and submit feedback.

---

# 📖 Overview

**Movie Watchlist App** is a full-stack Java web application designed to help users manage and organize their personal movie collections.

The application provides secure user authentication and ensures that each logged-in user can manage their own movies.

It integrates with the **OMDb API** to automatically retrieve IMDb ratings when a movie is available. If a movie cannot be found through OMDb, the user can manually provide the rating and other movie information.

The application follows the **MVC (Model-View-Controller)** architecture and uses Spring Boot with Spring Data JPA and Hibernate for backend development.

---

# ✨ Key Features

## 🔐 User Authentication

The application provides user authentication using **Spring Security**.

Features include:

- User Registration
- User Login
- Secure Password Handling
- Logout
- Authentication-based page access
- User-specific movie watchlists
- Custom `UserDetailsService`
- Custom `UserDetails` implementation

Users must be authenticated to access protected functionality.

---

## 👤 User Management

Each user has their own account and movie collection.

The application stores user information using the `User` entity and `UserRepo`.

User-related functionality is handled through:

```text
UserController
UserService
UserRepo
CustomUserDetails
CustomUserDetailsService
```

---

# 🎥 Movie Management

Users can manage their personal movie watchlist.

Supported operations:

- ➕ Add Movie
- 👀 View Movies
- ✏️ Update Movie
- ❌ Delete Movie
- ⭐ Manage Rating
- 🚦 Manage Priority
- 📝 Add Comments/Reviews
- 🔗 Store Movie Source

The movie data is associated with the logged-in user.

---

# ⭐ Automatic IMDb Rating

The application integrates with the **OMDb REST API**.

When a user adds a movie, the application attempts to retrieve its IMDb rating automatically.

```text
User
  ↓
MovieController
  ↓
DatabaseService
  ↓
RatingService
  ↓
OMDb API
  ↓
IMDb Rating
```

If the movie is found:

```text
OMDb Rating → Movie Rating
```

If the movie is not found:

```text
Manual Rating → Movie Rating
```

This provides flexibility while maintaining automatic IMDb integration whenever possible.

---

# ✍️ Manual Movie Support

If a movie is unavailable through OMDb, users can manually provide movie information.

Supported manual information includes:

- Movie Title
- Rating
- Priority
- Comments
- Source

This prevents users from being blocked when a movie cannot be found through the external API.

---

# 🚦 Smart Priority Management

Movies can have three priority levels:

| Priority | Meaning |
|----------|---------|
| 🔥 H | High |
| ⭐ M | Medium |
| 💤 L | Low |

Priority values are normalized automatically.

Supported input values include:

```text
H
High

M
Med
Medium

L
Low
```

The application internally normalizes these values for consistent storage and validation.

---

# ⭐ Automatic Priority Assignment

For movies where IMDb rating is available, priority can be determined from the rating.

| IMDb Rating | Priority |
|-------------|----------|
| 0 - 2.9 | Low |
| 3 - 6.9 | Medium |
| 7 - 10 | High |

This makes it easier to organize movies according to their ratings.

---

# 📊 Dashboard

The application includes a personalized dashboard for authenticated users.

The dashboard displays:

### 🎬 Total Movies

Shows the total number of movies in the logged-in user's watchlist.

### ⭐ Average Rating

Calculates the average rating of movies that have a rating.

### 🔥 High Priority Movies

Displays the number of movies marked with high priority.

### 📝 Reviews

Counts movies that contain a user comment/review.

### 🎬 Recent Movies

Displays the latest movies from the user's watchlist.

### ⚡ Quick Actions

The dashboard provides quick access to:

- Add New Movie
- View Watchlist
- About
- Contact

---

# 💬 Feedback & Suggestions

Authenticated users can access the **Help Us Improve** section.

Users can submit:

- Feedback
- Suggestions
- Comments about the application

The feedback functionality is protected using authentication.

A successful submission displays a confirmation message.

---

# 🧩 Reusable Thymeleaf Fragments

The application uses reusable Thymeleaf fragments for common UI components.

## Navbar

```html
<div th:replace="~{fragments/navbar :: navbar}"></div>
```

## Footer

```html
<div th:replace="~{fragments/footer :: footer}"></div>
```

This keeps the navigation and footer consistent across application pages.

---

# ✅ Custom Validation

The application uses **Jakarta Bean Validation** along with custom validation annotations.

Custom validation package:

```text
validations/
```

Contains:

```text
Priority.java
PriorityAnnotationLogic.java

Rating.java
RatingAnnotationLogic.java
```

Validation is used for:

- Movie Rating
- Movie Priority
- Movie Title
- Comment length
- User input validation

---

# 📦 DTO Layer

The application uses DTOs for handling authentication-related form data.

DTO package:

```text
dto/
```

Contains:

```text
LoginRequest.java
SignupRequest.java
```

Using DTOs helps keep request/form data separate from entity objects.

---

# 🏗️ Application Architecture

The application follows the **MVC architecture** with separate layers for controllers, services, repositories, entities, DTOs, security, and validation.

```text
                         User
                          │
                          ▼
                  Thymeleaf Views
                          │
                          ▼
                     Controller
                          │
             ┌────────────┼────────────┐
             ▼            ▼            ▼
          Service      Security       DTO
             │            │
             ▼            ▼
        Repository   UserDetailsService
             │
             ▼
        JPA / Hibernate
             │
             ▼
       Database
```

For movie rating:

```text
MovieController
      │
      ▼
DatabaseService
      │
      ▼
RatingService
      │
      ▼
OMDb API
```

For dashboard:

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
User's Movies
```

---

# 📂 Project Structure

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
│   │   │       │   ├── UserController.java
│   │   │       │   ├── MovieController.java
│   │   │       │   ├── DashboardController.java
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
│   │   │       │   ├── DatabaseService.java
│   │   │       │   ├── DashboardService.java
│   │   │       │   ├── RatingService.java
│   │   │       │   └── UserService.java
│   │   │       │
│   │   │       ├── security/
│   │   │       │   ├── CustomUserDetails.java
│   │   │       │   └── CustomUserDetailsService.java
│   │   │       │
│   │   │       └── validations/
│   │   │           ├── Priority.java
│   │   │           ├── PriorityAnnotationLogic.java
│   │   │           ├── Rating.java
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

# 📁 Package Responsibilities

## `config`

Contains application configuration classes.

```text
SecurityConfig.java
PasswordConfig.java
```

Responsible for:

- Spring Security configuration
- Authentication rules
- Password encoding
- Protected routes
- Login/logout configuration

---

## `controller`

Handles HTTP requests and connects the frontend with the service layer.

```text
HomeController.java
AuthController.java
UserController.java
MovieController.java
DashboardController.java
FeedbackController.java
```

---

## `dto`

Contains Data Transfer Objects used for handling request data.

```text
LoginRequest.java
SignupRequest.java
```

---

## `entity`

Contains JPA entity classes representing database tables.

```text
User.java
Movie.java
Feedback.java
```

---

## `repository`

Contains Spring Data JPA repositories responsible for database operations.

```text
UserRepo.java
MovieRepo.java
FeedbackRepo.java
```

---

## `service`

Contains business logic.

```text
DatabaseService.java
DashboardService.java
RatingService.java
UserService.java
```

Responsibilities include:

- Movie CRUD operations
- Dashboard calculations
- IMDb rating retrieval
- User management
- Database operations

---

## `security`

Contains custom Spring Security components.

```text
CustomUserDetails.java
CustomUserDetailsService.java
```

`CustomUserDetailsService` loads users from the database during authentication.

---

## `validations`

Contains custom validation annotations and their validation logic.

```text
Priority.java
PriorityAnnotationLogic.java
Rating.java
RatingAnnotationLogic.java
```

---

# 🛠️ Technology Stack

## Backend

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate ORM
- Jakarta Validation

---

## Frontend

- HTML5
- CSS3
- Thymeleaf
- Bootstrap 4
- JavaScript

---

## Database

- H2 Database
- PostgreSQL
- Spring Data JPA
- Hibernate

---

## External API

- OMDb REST API

---

## Build Tool

- Maven

---

## Development Tools

- Eclipse IDE
- Git
- GitHub

---

# 🔄 Application Workflow

## 1️⃣ User Registration

```text
Signup Page
     │
     ▼
SignupRequest DTO
     │
     ▼
AuthController
     │
     ▼
UserService
     │
     ▼
PasswordEncoder
     │
     ▼
UserRepo
     │
     ▼
Database
```

---

## 2️⃣ User Login

```text
Login Page
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
CustomUserDetails
     │
     ▼
Authentication Successful
     │
     ▼
Dashboard
```

---

## 3️⃣ Add Movie

```text
Add Movie Form
      │
      ▼
MovieController
      │
      ▼
DatabaseService
      │
      ▼
RatingService
      │
      ▼
OMDb API
      │
      ▼
Rating / Manual Rating
      │
      ▼
Priority
      │
      ▼
MovieRepo
      │
      ▼
Database
```

---

## 4️⃣ Dashboard

```text
Login
  │
  ▼
DashboardController
  │
  ▼
DashboardService
  │
  ▼
MovieRepo
  │
  ├── Total Movies
  ├── Average Rating
  ├── High Priority Movies
  ├── Review Count
  └── Recent Movies
```

---

## 5️⃣ Feedback

```text
Authenticated User
       │
       ▼
Feedback Page
       │
       ▼
FeedbackController
       │
       ▼
FeedbackRepo
       │
       ▼
Database
```

---

# 🗄️ Database

The application uses **Spring Data JPA and Hibernate** for database interaction.

The application can be configured to use:

- H2 Database for development/testing
- PostgreSQL for persistent database storage

Database entities include:

```text
User
Movie
Feedback
```

The `Movie` entity is associated with a user so that each authenticated user can manage their own watchlist.

---

# 🌐 Application URLs

## Home

```text
http://localhost:8080/
```

## Login

```text
http://localhost:8080/login
```

## Signup

```text
http://localhost:8080/signup
```

## Dashboard

```text
http://localhost:8080/dashboard
```

## Watchlist

```text
http://localhost:8080/watchlist
```

## Add Movie

```text
http://localhost:8080/watchlistItemForm
```

## About

```text
http://localhost:8080/about
```

## Contact

```text
http://localhost:8080/contact
```

## Feedback

```text
http://localhost:8080/feedback
```

---

# 🌐 OMDb API

The application uses the **OMDb API** to retrieve movie information and IMDb ratings.

Example request:

```text
https://www.omdbapi.com/?apikey=YOUR_API_KEY&t=Avatar
```

> Replace `YOUR_API_KEY` with your own OMDb API key.

For security, API keys should not be hard-coded into publicly shared source code.

---

# 📦 Maven Dependencies

The project uses Maven for dependency management.

Main dependencies include:

- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Security
- Spring Boot Starter Validation
- Spring Boot Starter Data JPA
- Hibernate
- H2 Database
- PostgreSQL Driver
- Thymeleaf Extras Spring Security
- Jackson

---

# 🚀 Installation & Setup

## 1. Clone the Repository

```bash
git clone https://github.com/mohit-singhoi/Movie-Watchlist-App.git
```

## 2. Open the Project

Open the project using:

```text
Eclipse
IntelliJ IDEA
VS Code
```

## 3. Configure Database

Update the database configuration inside:

```text
src/main/resources/application.properties
```

Configure your database URL, username, and password.

## 4. Configure OMDb API

Add your OMDb API key to the application configuration.

Do not expose private API keys in GitHub.

## 5. Run the Application

Using the main class:

```text
WatchlistApplication.java
```

Or using Maven:

```bash
mvn spring-boot:run
```

## 6. Open the Application

```text
http://localhost:8080/
```

---

# 🔒 Security

The application uses **Spring Security** for authentication and authorization.

Security features include:

- Login authentication
- User registration
- Password encoding
- Protected dashboard
- Authenticated feedback
- User-specific movie data
- Custom `UserDetailsService`

---

# 📊 Dashboard Statistics

The dashboard calculates statistics from the logged-in user's movies.

| Statistic | Description |
|-----------|-------------|
| 🎬 Total Movies | Total movies in user's watchlist |
| ⭐ Average Rating | Average rating of rated movies |
| 🔥 High Priority | Number of high-priority movies |
| 📝 Reviews | Number of movies containing comments |
| 🎬 Recent Movies | Latest movies in watchlist |

---

# 📌 Project Documentation

Additional project documentation is maintained separately.

```text
README.md
WORKING.md
STRUCTURE.md
```

### `README.md`

Provides:

- Project overview
- Features
- Architecture
- Technology stack
- Installation
- Application workflow
- Package responsibilities

### `WORKING.md`

Explains **how the complete application works step-by-step**, from registration/login through movie management, dashboard, API integration, database operations, and feedback.

### `STRUCTURE.md`

Explains the **project structure and purpose of packages/files** without describing the complete application workflow.

---

# 🔮 Future Enhancements

Possible future improvements:

- ❤️ Favorite Movies
- 🎭 Movie Genres
- 🔎 Advanced Movie Search
- 📄 Pagination
- 📊 Advanced Sorting
- 🎬 Movie Posters
- 🎥 Trailer Integration
- ⭐ Detailed User Reviews
- 🔔 Notifications
- ☁ Cloud Deployment
- 🐳 Docker Support
- 🌐 REST API Version
- 📱 Mobile-Friendly Improvements
- 📈 Advanced Dashboard Analytics
- 🎞️ Movie Recommendation System

---

# 👨‍💻 Developed By

## Mohit Kumar

**MCA Student | Java Full Stack Developer | Spring Boot Developer**

📧 Email: **mohitsinghoi501@gmail.com**

🔗 LinkedIn: **https://www.linkedin.com/in/mohit-kumar-0379gu**

---

## 💻 Skills

- Java
- Core Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate
- REST APIs
- SQL
- PostgreSQL
- H2 Database
- Thymeleaf
- HTML
- CSS
- Bootstrap
- JavaScript
- Git
- GitHub

---

# 📄 License

This project is licensed under the **MIT License**.

---

# ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub.

Your support motivates me to continue building and improving Java, Spring Boot, and full-stack development projects.

**Happy Coding! 🚀**
