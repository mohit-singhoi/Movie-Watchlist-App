# 🎬 Movie Watchlist App

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-success?style=for-the-badge&logo=springboot)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-green?style=for-the-badge)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-brown?style=for-the-badge)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-JPA-blue?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)
![Bootstrap](https://img.shields.io/badge/Bootstrap-4-purple?style=for-the-badge&logo=bootstrap)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge)

A **full-stack Movie Watchlist Management System** built using **Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, Hibernate, H2 Database, and OMDb API**.

</div>

---

# 📖 Overview

Movie Watchlist App is a full-stack web application that enables users to create and manage a personal movie collection efficiently.

The application integrates with the **OMDb API** to automatically fetch IMDb ratings for movies. If a movie is unavailable on IMDb, users can manually enter the rating and priority, ensuring flexibility while maintaining a smooth user experience.

The project follows the **MVC (Model-View-Controller)** architecture and demonstrates best practices in Java Full Stack Development using Spring Boot.

---

# ✨ Key Features

### 🎥 Movie Management

- Add new movies
- Update movie information
- Delete movies
- View complete watchlist

---

### ⭐ Automatic IMDb Integration

- Fetch IMDb Rating automatically
- Uses OMDb REST API
- No manual rating required for available movies

---

### ✍ Manual Movie Support

If the movie is unavailable on IMDb:

- Manual Rating
- Manual Priority
- Manual Comments

can be added by the user.

---

### 🚦 Smart Priority Management

Automatic Priority Assignment

| IMDb Rating | Priority |
|-------------|----------|
| 0 - 2.9 | Low |
| 3 - 6.9 | Medium |
| 7 - 10 | High |

Manual Priority Input Supported

```
L
Low
M
Med
Medium
H
High
```

The application automatically normalizes these values.

---

### ✅ Custom Validation

Custom Bean Validation is implemented using Jakarta Validation.

Validation includes:

- Movie Title
- Rating
- Priority
- Comment Length

Custom Validators

- Priority Annotation
- Rating Annotation

---

### 📊 CRUD Operations

The application supports complete CRUD functionality.

- Create
- Read
- Update
- Delete

using Spring Data JPA Repository.

---

# 📸 Application Modules

## 🏠 Home Module

### Purpose

Landing page of the application.

Provides navigation to:

- Watchlist
- Submit Movie

---

## 🎬 Watchlist Module

Displays all movies stored inside the H2 Database.

Shows

- Movie Title
- IMDb Rating
- Source Link
- Priority
- Comments

Actions

- Update Movie
- Delete Movie

---

## ➕ Submit Movie Module

Allows users to

- Add new movie
- Fetch IMDb Rating automatically
- Add manual rating if unavailable
- Add priority
- Add comments

---

## ✏ Update Movie Module

Users can

- Edit movie details
- Update comments
- Modify source link
- Update rating
- Update priority

---

## ❌ Delete Module

Removes a movie permanently from the watchlist.

---

# 🏗 Project Architecture

The application follows the **MVC Architecture**.

```
                User
                  │
                  ▼
          Thymeleaf Views
                  │
                  ▼
          MovieController
                  │
                  ▼
          DatabaseService
                  │
         ┌────────┴────────┐
         ▼                 ▼
 RatingService        MovieRepository
         │                 │
         ▼                 ▼
     OMDb API         H2 Database
```

---

# 📂 Project Structure

```
src
│
├── main
│
├── java
│   ├── controller
│   │      MovieController.java
│   │
│   ├── service
│   │      DatabaseService.java
│   │      RatingService.java
│   │
│   ├── repository
│   │      MovieRepo.java
│   │
│   ├── entity
│   │      Movie.java
│   │
│   └── entity.validations
│          Priority.java
│          PriorityAnnotationLogic.java
│          Rating.java
│          RatingAnnotationLogic.java
│
├── resources
│   ├── templates
│   │      index.html
│   │      watchlist.html
│   │      watchlistItem.html
│   │
│   ├── static
│   └── application.properties
│
└── pom.xml
```

---

# 🛠 Technology Stack

## Backend

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate ORM

---

## Frontend

- Thymeleaf
- HTML5
- CSS3
- Bootstrap 4

---

## Database

- H2 Database

---

## API

- OMDb REST API

---

## Validation

- Jakarta Bean Validation
- Custom Validation Annotation

---

## Build Tool

- Maven

---

## IDE

- Eclipse IDE

---

# 🔄 Application Workflow

```
User
   │
   ▼
Submit Movie
   │
   ▼
MovieController
   │
   ▼
RatingService
   │
   ▼
OMDb API

Movie Found?
      │
 ┌────┴────┐
 │         │
Yes        No
 │         │
 ▼         ▼
Auto Rating  Manual Rating
 │         │
 ▼         ▼
Priority Generated
 │
 ▼
DatabaseService
 │
 ▼
H2 Database
 │
 ▼
Watchlist Page
```

---

# 🗄 Database

The project uses **H2 In-Memory Database**.

Advantages

- Lightweight
- No installation
- Fast
- Ideal for development
- Easy testing

---

## H2 Console

```
http://localhost:8080/h2-console
```

Configuration

```
Driver Class

org.h2.Driver

JDBC URL

jdbc:h2:mem:watchdb

Username

sa

Password

Leave Blank
```

---

# 🌐 REST API Used

### OMDb API

Automatically retrieves IMDb ratings.

Example

```
https://www.omdbapi.com/?apikey=YOUR_API_KEY&t=Avatar
```

---

# 📦 Maven Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Validation
- Spring Boot Starter Data JPA
- H2 Database
- Jackson
- Maven

---

# 🚀 Installation

Clone Repository

```bash
git clone https://github.com/yourusername/Movie-Watchlist-App.git
```

Open Project

```
Eclipse / IntelliJ IDEA
```

Run

```
WatchlistApplication.java
```

or

```bash
mvn spring-boot:run
```

---

# 🌐 Application URLs

Application

```
http://localhost:8080/
```

Watchlist

```
http://localhost:8080/watchlist
```

Submit Movie

```
http://localhost:8080/watchlistItemForm
```

H2 Console

```
http://localhost:8080/h2-console
```

---

# 📌 Future Enhancements

- 🔐 User Authentication
- ❤️ Favorite Movies
- 🎭 Movie Genres
- 🔎 Search Movies
- 📄 Pagination
- 📊 Sorting
- 🌙 Dark Mode
- 🎬 Movie Posters
- 🎥 Trailer Integration
- ⭐ User Reviews
- ☁ MySQL Integration
- PostgreSQL Support
- Docker Deployment
- REST API Version
- Spring Security
- JWT Authentication

---

# 👨‍💻 Developed By

## Mohit Kumar

**MCA Student | Java Full Stack Developer | Spring Boot Developer**


📧 Email: *mohitsinghoi501@gmail.com*

🔗 LinkedIn: *https://www.linkedin.com/in/mohit-kumar-0310a1257*

### Skills

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- SQL
- HTML
- CSS
- Bootstrap
- REST APIs
- Git & GitHub

---

# 📄 License

This project is licensed under the **MIT License**.

---

# ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub.

It motivates me to build more open-source Java and Spring Boot projects.

Happy Coding! 🚀
