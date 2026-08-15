# Movie Watchlist App — Working Flow

## 1. Project Overview

Movie Watchlist App is a Spring Boot web application that allows registered users to manage their personal movie watchlist, submit feedback, and track their activities.

The application also provides an Admin Panel for managing users, movies, feedback, activities, and email communication with users.

The application follows a layered architecture using:

* Spring Boot
* Spring MVC
* Spring Security
* Spring Data JPA / Hibernate
* PostgreSQL
* Thymeleaf
* HTML, CSS, JavaScript
* JavaMail / Spring Mail

---

# 2. Application Architecture

The application follows a layered architecture:

```text
User Interface
     │
     ▼
Controllers
     │
     ▼
Services
     │
     ▼
Repositories
     │
     ▼
Entities
     │
     ▼
PostgreSQL Database
```

Additional layers handle:

* Authentication and authorization
* Form validation
* Email communication
* DTO-based request handling

---

# 3. Application Startup

The application starts from:

```text
WatchlistApplication.java
```

Spring Boot initializes:

1. Application context
2. Database connection
3. JPA/Hibernate
4. Spring Security
5. Controllers
6. Services
7. Repositories
8. Thymeleaf templates
9. Email configuration

After successful startup, the application is available through the configured local server port.

---

# 4. User Registration and Authentication

## 4.1 Signup

A new user accesses the signup page:

```text
/signup
```

The signup form collects user information such as:

* Full name
* Email
* Password

The request is handled by:

```text
AuthController
```

The application uses:

```text
SignupRequest
```

as the DTO for signup data.

The password is securely encoded using the configured password encoder before being stored in the database.

---

## 4.2 User Login

Users can access:

```text
/userlogin
```

The login process is handled through Spring Security.

The application:

1. Receives the user's credentials.
2. Finds the user using `CustomUserDetailsService`.
3. Loads the corresponding `User` entity.
4. Verifies the password.
5. Authenticates the user.
6. Redirects the user according to the authentication-success configuration.

The application uses:

```text
CustomUserDetails
CustomUserDetailsService
CustomAuthenticationSuccessHandler
```

for custom authentication handling.

---

# 5. Role-Based Access

The application supports different user roles.

The main roles are:

```text
USER
ADMIN
```

`SecurityConfig.java` controls access to protected routes.

Users cannot access administrative functionality without the required role.

Admin-specific pages and operations are protected through Spring Security.

---

# 6. User Dashboard Workflow

After successful authentication, the user can access the dashboard.

The dashboard is handled by:

```text
DashboardController
DashboardService
```

The dashboard provides an overview of the user's application activity and watchlist-related information.

The dashboard template is:

```text
dashboard.html
```

---

# 7. Movie Watchlist Workflow

## 7.1 Add Movie

Users can add movies to their personal watchlist.

The movie functionality is handled by:

```text
MovieController
MovieService
MovieRepo
Movie
```

The movie form is:

```text
watchlistItemForm.html
```

The submitted movie information is validated before being saved.

---

## 7.2 Movie Data

The movie entity stores information related to the user's watchlist.

Movie data is persisted through:

```text
MovieRepo
```

using Spring Data JPA.

---

## 7.3 Movie Rating

Movie ratings are handled using:

```text
RatingService
Rating.java
RatingAnnotationLogic.java
```

The validation layer ensures that the submitted rating follows the application's configured rules.

---

## 7.4 Watchlist Display

The user's movies are displayed through:

```text
watchlist.html
watchlistItem.html
```

The application retrieves the user's movie records from the database and displays them in the watchlist interface.

---

# 8. Feedback Workflow

Authenticated users can submit feedback through:

```text
/feedback
```

The request is handled by:

```text
FeedbackController
FeedbackService
FeedbackRepo
```

The feedback contains information such as:

* Category
* Rating
* Message
* User

The logged-in user is automatically associated with the submitted feedback.

The application also creates an activity entry when feedback is submitted.

---

# 9. Activity Tracking

User activities are managed through:

```text
ActivityService
ActivityRepo
Activity
```

Activities can record important user actions such as feedback submission.

Each activity is associated with the relevant user.

The Admin Panel can display these activities through:

```text
activities.html
activity-details.html
```

---

# 10. Admin Panel Workflow

The Admin Panel is available only to authorized administrators.

Admin functionality is managed mainly through:

```text
AdminController
```

The Admin Panel contains sections for:

```text
Admin Dashboard
Users
Movies
Feedback
Activities
```

The Admin UI uses dedicated templates and shared admin navigation/footer fragments.

---

# 11. Admin User Management

Administrators can view registered users through:

```text
/admin/users
```

The user list is loaded using:

```text
UserService
UserRepo
```

The administrator can open individual user details and manage user-related information.

---

# 12. Complete User Deletion

The Admin Panel supports complete user deletion.

The deletion workflow is:

```text
Admin
  │
  ▼
Select User
  │
  ▼
Delete User
  │
  ▼
Confirmation
  │
  ▼
AdminController
  │
  ▼
UserService
  │
  ▼
Delete Related Records
  │
  ▼
Delete User
  │
  ▼
Redirect to Users
```

Associated user data includes records such as:

* Movies
* Feedback
* Feedback responses
* Activities

The database relationships and cascade/orphan-removal configuration ensure that dependent records are handled correctly.

After successful deletion, the application displays:

```text
✅ User deleted successfully.
```

The success popup automatically disappears after the configured duration.

---

# 13. Admin Feedback Management

Administrators can view all submitted feedback through the Admin Panel.

The feedback section provides:

* Feedback ID
* Category
* Rating
* User
* Email
* Message

Administrators can open individual feedback details.

The relevant template is:

```text
admin/feedback-details.html
```

---

# 14. Admin Email Response

Administrators can respond directly to users through email.

The workflow is:

```text
Admin opens feedback
        │
        ▼
Writes response
        │
        ▼
Submit response
        │
        ▼
EmailService
        │
        ▼
Email sent to user's email
        │
        ▼
Response saved in database
```

The email functionality is handled by:

```text
EmailService
```

The response is sent to the email address associated with the feedback user.

---

# 15. Email Response History

Every successful admin response is stored in the:

```text
feedback_responses
```

database table.

The corresponding entity is:

```text
FeedbackResponse
```

The response contains:

* Feedback
* Admin email
* Response message
* Email status
* Response date/time

The relationship is:

```text
Feedback
    │
    └── FeedbackResponse
            ├── Admin Email
            ├── Response Message
            ├── Email Status
            └── Responded At
```

Response history is retrieved using:

```text
FeedbackResponseService
FeedbackResponseRepository
```

and displayed on the feedback details page.

This allows administrators to review previous responses sent for the same feedback.

---

# 16. Email Sending Process

The application uses Spring Mail for sending responses.

The process is:

```text
FeedbackController
        │
        ▼
EmailService
        │
        ▼
SMTP Server
        │
        ▼
User Email
```

Email authentication is configured using environment variables rather than storing credentials directly in source code.

The application uses an email application password where required by the configured email provider.

Sensitive credentials should never be committed to GitHub.

---

# 17. Validation

The application uses custom validation components for application-specific input validation.

Validation classes include:

```text
Priority.java
PriorityAnnotationLogic.java

Rating.java
RatingAnnotationLogic.java
```

These validations help ensure that invalid data is rejected before being persisted.

---

# 18. Database Layer

The application uses PostgreSQL as its persistent database.

Hibernate/JPA manages communication between Java entities and database tables.

Main entities include:

```text
User
Movie
Feedback
Activity
FeedbackResponse
```

Repositories extend Spring Data JPA repositories to perform database operations.

Example architecture:

```text
Movie
  ↓
MovieRepo
  ↓
PostgreSQL

Feedback
  ↓
FeedbackRepo
  ↓
PostgreSQL

FeedbackResponse
  ↓
FeedbackResponseRepository
  ↓
PostgreSQL
```

---

# 19. Entity Relationships

The main relationships are:

```text
User
 ├── Movies
 ├── Feedback
 └── Activities

Feedback
 └── FeedbackResponses
```

A feedback record belongs to a user.

A feedback response belongs to a feedback record.

When dependent records are deleted, the configured JPA relationships ensure that database foreign-key constraints are respected.

---

# 20. Frontend Structure

The frontend uses:

```text
Thymeleaf
HTML
CSS
JavaScript
```

Templates are separated into:

```text
templates/
├── fragments/
├── admin/
├── auth/
└── user pages
```

Static resources are organized into:

```text
static/
├── css/
├── js/
└── Images/
```

---

# 21. Navigation and Layout

Common user navigation and footer elements are maintained using Thymeleaf fragments:

```text
fragments/navbar.html
fragments/footer.html
```

The Admin Panel uses:

```text
admin/admin-navbar.html
admin/admin-footer.html
```

This keeps common UI components reusable across multiple pages.

---

# 22. Success and Error Messages

The application uses redirect flash attributes for user feedback.

Examples include:

```text
✅ Response successfully sent to the User.
✅ User deleted successfully.
❌ Failed to send response.
❌ Failed to delete user.
```

These messages are displayed using popup components.

JavaScript controls the automatic disappearance of popup messages after a configured time.

---

# 23. Security Workflow

Spring Security protects authenticated and administrative functionality.

The security configuration is managed by:

```text
SecurityConfig.java
PasswordConfig.java
```

The application:

1. Authenticates users.
2. Loads user information.
3. Encodes passwords.
4. Applies role-based authorization.
5. Protects administrative routes.
6. Restricts unauthorized access.

---

# 24. Complete User Workflow

```text
Open Application
       │
       ▼
Signup / Login
       │
       ▼
User Dashboard
       │
       ├───────────────┐
       ▼               ▼
Manage Watchlist     Submit Feedback
       │               │
       ▼               ▼
Movie Database      Feedback Database
       │               │
       └───────┬───────┘
               ▼
          User Activities
```

---

# 25. Complete Admin Workflow

```text
Admin Login
     │
     ▼
Admin Dashboard
     │
     ├── Manage Users
     │      └── View / Delete User
     │
     ├── Manage Movies
     │      └── View Movie Details
     │
     ├── Manage Feedback
     │      ├── View Feedback
     │      ├── Respond by Email
     │      └── View Response History
     │
     └── Manage Activities
            ├── View Activities
            └── View Activity Details
```

---

# 26. Error Handling

The application handles common application errors through controller-level validation and exception handling.

For example:

* Feedback not found
* User information unavailable
* Missing email address
* Empty response message
* Email authentication failure
* Database constraint violations

Error messages are returned to the appropriate page using redirect attributes where applicable.

---

# 27. Development Workflow

During development, changes are implemented in layers:

```text
Entity
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
Thymeleaf Template
   ↓
CSS / JavaScript
   ↓
Testing
```

For new database functionality:

1. Create/update entity.
2. Configure entity relationships.
3. Create repository.
4. Create service methods.
5. Add controller endpoints.
6. Update Thymeleaf UI.
7. Add CSS/JavaScript where required.
8. Test database operations.
9. Test the complete user workflow.

---

# 28. Final Testing Workflow

Before publishing the project, verify:

### User Side

* Signup
* Login
* Logout
* Dashboard
* Add movie
* Update movie
* Delete movie
* Movie rating
* Submit feedback
* Activity creation

### Admin Side

* Admin login
* Admin dashboard
* View users
* View user details
* View movies
* View movie details
* View feedback
* View feedback details
* Send email response
* View email response history
* View activities
* Delete individual records
* Completely delete user

### System

* Database operations
* Foreign-key relationships
* Email configuration
* Authentication
* Authorization
* Success popups
* Error popups
* Redirects
* Responsive UI

---

# 29. Production / GitHub Preparation

Before pushing the project to GitHub:

* Remove passwords from source code.
* Remove email application passwords.
* Use environment variables for sensitive configuration.
* Verify `.gitignore`.
* Remove unnecessary generated files.
* Remove unused code.
* Check database configuration.
* Update README.
* Update project structure documentation.
* Update workflow documentation.

Sensitive configuration must never be committed to the public repository.

---

# 30. Final Project Flow

The final application can be summarized as:

```text
                 MOVIE WATCHLIST APP
                         │
          ┌──────────────┴──────────────┐
          │                             │
        USER                           ADMIN
          │                             │
     Authentication                 Authentication
          │                             │
     Dashboard                    Admin Dashboard
          │                             │
     Watchlist              ┌───────────┼───────────┐
          │                 │           │           │
     Feedback             Users       Movies     Feedback
          │                 │                       │
     Activities        User Management        Email Response
                                                  │
                                                  ▼
                                           Response History
```

The application combines authentication, watchlist management, feedback management, activity tracking, administrative management, email communication, and response-history tracking into a single Spring Boot application.
