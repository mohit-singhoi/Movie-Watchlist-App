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
│   │   │       │   |__ AdminController.java 
|   |   |       |   
│   │   │       ├── dto/
│   │   │       │   ├── LoginRequest.java
│   │   │       │   └── SignupRequest.java
│   │   │       │
│   │   │       ├── entity/
│   │   │       │   ├── User.java
│   │   │       │   ├── Movie.java
│   │   │       │   └── Feedback.java
│   │   │       │   |__ Activity.java
|   |   |       |   |__ FeedbackResponse.java
|   |   |       |
│   │   │       ├── repository/
│   │   │       │   ├── UserRepo.java
│   │   │       │   ├── MovieRepo.java
│   │   │       │   └── FeedbackRepo.java
|   |   |       |   |__ ActivityRepo.java
|   |   |       |   |__ FeedbackResponseRepository.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── MovieService.java
│   │   │       │   ├── DashboardService.java
│   │   │       │   ├── RatingService.java
│   │   │       │   └── UserService.java
|   |   |       |   |__ FeedbackService.java 
|   |   |       |   |__ ActivityService.java
|   |   |       |   |__ FeedbackResponseService.java
|   |   |       |   |__ EmailService.java
│   │   │       │
│   │   │       ├── security/
│   │   │       │   ├── CustomUserDetails.java
│   │   │       │   └── CustomUserDetailsService.java
|   |   |       |   |__ CustomAuthenticationSuccessHandler.java
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
|   |       |   |  
|   |       |   |___ admin/
|   |       |   |     |__ admin-dashboard.html
|   |       |   |     |__ admin-navbar.html
|   |       |   |     |__ movies.html
|   |       |   |     |__ users.html
|   |       |   |     |__ user-details.html
|   |       |   |     |__ movie-details.html
|   |       |   |     |__ feedback.html
|   |       |   |     |__ feedback-details.html
|   |       |   |     |__ activities.html
|   |       |   |     |__ activity-details.html
|   |       |   |     |__ admin-footer.html
|   |       |   |     
|   |       |   |___auth/
|   |       |   |    |__ login.html
|   |       |   |    |__ adminlogin.html
|   |       |   |    |__ userlogin.html
|   |       |   |    |__ signup.html
│   │       │   │
│   │       │   |
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
- **AdminController.java**  - It Handles all admin Section

### `dto/`

Contains Data Transfer Objects used for receiving and transferring request data.

- **LoginRequest.java** — Stores login request information.
- **SignupRequest.java** — Stores signup/registration request information.

### `entity/`

Contains JPA entity classes that represent database tables.

- **User.java** — Represents application users.
- **Movie.java** — Represents movies stored in the user's watchlist.
- **Feedback.java** — Represents user feedback and suggestions.
- **Activity.java** - It represents the user activities like new user register , add movies, delete, update etc.
- **FeedbackResponse.java** - It Represents the user feedback response by admin support.


### `repository/`

Contains Spring Data JPA repositories used for database operations.

- **UserRepo.java** — Performs database operations related to users.
- **MovieRepo.java** — Performs database operations related to movies.
- **FeedbackRepo.java** — Performs database operations related to feedback.
- **ActivityRepo.java** - Perform database opeartion related to activity.
- **FeedbackResponseRepository.java** - Perform the database operation of user feedback response by admin support.


### `service/`

Contains the application's business logic.

- **MovieService.java** — Handles movie/watchlist database operations.
- **DashboardService.java** — Calculates dashboard statistics such as total movies, average rating, high-priority movies, review count, and  recent movies.
- **RatingService.java** — Handles movie rating-related operations and external movie rating retrieval.
- **UserService.java** — Handles user-related business logic.
- **FeedbackService.java** - Handle User Feedback.
- **ActivityService.java** - It handles activity operations.
- **FeedbackResponseService.java** - It handle the operation of feedback response by admin support.
- **EmailService.java** -  It handle email related operations.

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

Contains the application's Thymeleaf Authentitation HTML pages.

- **login.html** — User login page.
- **signup.html** — User registration page.
- **adminlogin.html** - Admin login page.
- **userlogin.html** - User login Page.


### `templates/admin`

Contains the application's Thymeleaf All Admin HTML pages.

- **admin-navbar.html** - Admin Navbar page 
- **movies.html** - Movie Page where admin manage all watchlist movie
- **users.html** - User page where admin manage all users 
- **user-details.html** - User details page where admin see all user details
- **movie-details.html** - All Movies details here that is stored in watchlist 
- **feedback.html** - Feedback page where admin see all user feedback
- **feedback-details.html** - Feedback details page 
- **activities.html** - User Activities page where admin manage all activities
- **activity-details.html** - Activity details page
- **admin-footer.html** - Admin footer page 



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

- **css/** —  all Stylesheets.
- **js/** — all JavaScript files.
- **Images/** — Images and background assets.

### Root Files

- **Dockerfile** - Use for project deployment on server
- **pom.xml** — Maven configuration and project dependencies.
- **README.md** — Project overview, features, technologies, setup, and usage information.
- **WORKING.md** — Step-by-step explanation of how the application works.
- **STRUCTURE.md** — Project structure and package/file responsibilities.



### 📚 Related Documentation

- 📖 [README.md](README.md) — Project overview, features, technology stack and setup
- 🏗️ [STRUCTURE.md](STRUCTURE.md) — Complete package and file structure
- ⚙️ [WORKING.md](WORKING.md) — Step-by-step application workflow