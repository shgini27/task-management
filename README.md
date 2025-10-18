# Task Management Application

A full-stack agile task management tool built with Spring Boot and Vue.js, featuring real-time collaboration, team management, and file attachments.

## Features

- **User Management**: Registration, authentication with JWT tokens
- **Team Collaboration**: Create teams and manage team members
- **Board Management**: Create and organize project boards
- **Card System**: Create, edit, and organize tasks with descriptions
- **Real-time Updates**: WebSocket-based live collaboration
- **File Attachments**: Upload and manage task attachments with S3 support
- **Activity Tracking**: Monitor all board and card activities
- **Email Notifications**: Automated email notifications for user events
- **Multi-language Support**: Internationalization (English, Russian, Turkmen)

## Tech Stack

### Backend
- **Java 11** with Spring Boot 2.5.2
- **Spring Security** for authentication
- **Spring Data JPA** with Hibernate
- **MySQL** database
- **JWT** for token-based authentication
- **WebSocket** for real-time communication
- **RabbitMQ** for messaging
- **AWS S3** for file storage
- **FreeMarker** for email templates

### Frontend
- **Vue.js 2.6** with Vue Router and Vuex
- **Bootstrap 5** for UI components
- **Axios** for HTTP requests
- **SockJS** for WebSocket client
- **Vue-draggable** for drag-and-drop functionality
- **FontAwesome** icons

## Prerequisites

- Java 11+
- Node.js 14+
- MySQL 5.7+
- Maven 3.6+
- ImageMagick (for image processing)

## Installation

### 1. Clone the repository
```bash
git clone <repository-url>
cd task-management
```

### 2. Database Setup
```bash
# Create MySQL database
mysql -u root -p < setup/1.init-database.sql
mysql -u root -p < setup/2.refactoring-database.sql
```

### 3. Configure Application
Edit `src/main/resources/application.properties`:
```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/task_management
spring.datasource.username=your_username
spring.datasource.password=your_password

# File Storage (Local or S3)
app.file-storage.active=localFileStorage
app.file-storage.local-root-folder=/data/files
app.file-storage.temp-folder=/data/temp

# For S3 (optional)
app.file-storage.s3-access-key=<Your S3 Access Key>
app.file-storage.s3-secret-key=<Your S3 Secret Key>
app.file-storage.s3-bucket-name=<Your S3 Bucket Name>
```

### 4. Build and Run
```bash
# Build the application (includes frontend build)
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be available at `http://localhost:8000`

## Development

### Backend Development
```bash
# Run backend only
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Frontend Development
```bash
cd front-end
npm install
npm run serve
```

### Running Tests
```bash
# Backend tests
mvn test

# Frontend tests
cd front-end
npm run test:unit

# E2E tests
mvn clean install -P local-e2e
```

## Docker Deployment

```bash
# Build Docker image
docker build -f docker/Dockerfile -t task-management .

# Run with Docker Compose
docker-compose up -d
```

## API Endpoints

### Authentication
- `POST /api/authentications` - Login
- `DELETE /api/authentications` - Logout

### User Management
- `POST /api/registrations` - Register new user
- `GET /api/me` - Get current user info

### Boards
- `POST /api/boards` - Create board
- `GET /api/boards/{boardId}` - Get board details
- `POST /api/boards/{boardId}/members` - Add board member

### Cards
- `POST /api/cards` - Create card
- `PUT /api/cards/{cardId}` - Update card
- `POST /api/cards/{cardId}/comments` - Add comment
- `POST /api/cards/{cardId}/attachments` - Upload attachment

### Teams
- `POST /api/teams` - Create team
- `GET /api/teams` - List user teams

## Configuration

### Environment Profiles
- `dev` - Development environment
- `staging` - Staging environment  
- `production` - Production environment
- `e2e` - End-to-end testing

### File Storage Options
- **Local Storage**: Files stored on local filesystem
- **AWS S3**: Files stored in S3 bucket with CDN support

### Email Configuration
Configure SMTP settings in `application.properties` for email notifications.

## Architecture

The application follows Domain-Driven Design (DDD) principles:

- **Domain Layer**: Core business logic and entities
- **Application Layer**: Use cases and application services
- **Infrastructure Layer**: External integrations (database, email, file storage)
- **Web Layer**: REST controllers and WebSocket handlers

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is open source. See LICENSE file for details.