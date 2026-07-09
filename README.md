# 🎬 Movie Watchlist App

A feature-rich **Movie Watchlist Management System** built using **Spring Boot**, **Thymeleaf**, **Spring Data JPA**, **Hibernate**, and **H2 Database**. The application allows users to organize their favorite movies, automatically fetch IMDb ratings using the **OMDb API**, assign priorities, and manage their watchlist through a clean and responsive web interface.

---

## 📖 Overview

Movie Watchlist App is a full-stack web application that helps users create and manage a personalized movie watchlist. It integrates with the **OMDb API** to automatically retrieve IMDb ratings and intelligently assigns movie priorities. For movies unavailable on IMDb, users can manually provide ratings and priorities, ensuring complete flexibility.

---

## ✨ Features

- 🎥 Add new movies to your watchlist
- 📝 Update existing movie details
- 🗑️ Delete movies from the watchlist
- ⭐ Automatically fetch IMDb ratings using OMDb API
- ✍️ Manual rating support for movies not available on IMDb
- 🚦 Automatic priority assignment based on IMDb ratings
- 🔤 Flexible priority input (L, Low, M, Medium, H, High)
- ✅ Custom Bean Validation for rating and priority
- 📋 Display all movies in a responsive watchlist table
- 💾 Persistent data storage using H2 Database
- 🎨 Clean and responsive UI built with Thymeleaf and Bootstrap

---

## 🛠️ Tech Stack

### Backend
- Java 21+
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

### Frontend
- Thymeleaf
- HTML5
- CSS3
- Bootstrap 4

### Database
- H2 Database

### API Integration
- OMDb API

### Build Tool
- Maven

### Validation
- Jakarta Bean Validation
- Custom Validation Annotations

---

## 🏗️ Project Architecture

```
src
├── controller
├── service
├── repository
├── entity
├── entity
│   └── validations
├── templates
├── static
└── resources
```

---

## 📸 Application Modules

### 🏠 Home Page

- Welcome page of the application.

### 🎬 Watchlist

- View all saved movies
- IMDb Rating
- Source Link
- Priority
- Comments

### ➕ Add Movie

- Add a new movie
- Automatic IMDb rating retrieval
- Manual rating if unavailable on IMDb

### ✏️ Update Movie

- Edit movie details
- Update rating and priority

### ❌ Delete Movie

- Remove movies from the watchlist

---

## 📂 Database

The application uses the **H2 In-Memory Database** for lightweight and fast development.

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone https://github.com/your-username/Movie-Watchlist-App.git
```

### Navigate

```bash
cd Movie-Watchlist-App
```

### Run

```bash
mvn spring-boot:run
```

or simply run

```
WatchlistApplication.java
```

---

## 🌐 Application URL

```
http://localhost:8080/
```

---

## 🗄️ H2 Database Console

```
http://localhost:8080/h2-console
```

Example Configuration

```
Driver Class : org.h2.Driver

JDBC URL : jdbc:h2:mem:moviesdb

Username : sa

Password :
```

---

## 🎯 Validation Features

- Movie title cannot be blank
- Rating must be between 5 and 10
- Custom Priority Validation
- Comment length validation
- Manual validation support for non-IMDb movies

---

## 📦 Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- H2 Database
- Jackson
- Maven

---

## 📌 Future Enhancements

- 🔐 User Authentication & Authorization
- ❤️ Favorite Movies
- 🎭 Movie Categories & Genres
- 🔍 Search & Filter Movies
- 📄 Pagination & Sorting
- 🌙 Dark Mode
- ⭐ Personal Ratings & Reviews
- 🎬 Movie Posters
- 🎥 Trailer Integration
- ☁️ MySQL/PostgreSQL Support
- 📱 REST API Version
- 🐳 Docker Deployment

---

## 👨‍💻 Developed By

**Mohit Kumar**

MCA Student | Java Full Stack Developer | Spring Boot Developer

---

## 📄 License

This project is licensed under the **MIT License**.

---

## ⭐ Support

If you found this project helpful, please consider giving it a **⭐ Star** on GitHub. It motivates me to build and share more open-source projects!

```

## 💯 This README includes:
- Professional project overview
- Feature list
- Complete tech stack
- Architecture
- Setup instructions
- H2 database configuration
- Future enhancements
- License
- Developer section
- GitHub-friendly formatting with emojis

It is suitable for showcasing the project to recruiters and on your portfolio.
