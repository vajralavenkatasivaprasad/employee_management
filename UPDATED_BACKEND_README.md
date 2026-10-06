# Employee Management Backend - Updated Version

This backend includes:
- Spring Security + JWT authentication
- BCrypt password encryption
- Admin-only employee CRUD APIs
- Dashboard statistics
- Search, sorting, and pagination
- PDF export
- Excel export
- Employee photo upload
- Email notification after employee creation
- Dockerfile

## Replace / copy these files into your backend project

### Root
- `pom.xml`
- `Dockerfile`
- `.dockerignore`

### Resources
- `src/main/resources/application.properties`

### Entity
- `src/main/java/com/example/employee_management/entity/Employee.java`
- `src/main/java/com/example/employee_management/entity/User.java`

### Repository
- `src/main/java/com/example/employee_management/repository/EmployeeRepository.java`
- `src/main/java/com/example/employee_management/repository/UserRepository.java`

### DTO
- `src/main/java/com/example/employee_management/dto/RegisterRequest.java`
- `src/main/java/com/example/employee_management/dto/LoginRequest.java`
- `src/main/java/com/example/employee_management/dto/AuthResponse.java`
- `src/main/java/com/example/employee_management/dto/DashboardStats.java`

### Security and Config
- `src/main/java/com/example/employee_management/security/JwtUtil.java`
- `src/main/java/com/example/employee_management/security/JwtFilter.java`
- `src/main/java/com/example/employee_management/config/SecurityConfig.java`
- `src/main/java/com/example/employee_management/config/WebConfig.java`

### Controller
- `src/main/java/com/example/employee_management/controller/AuthController.java`
- `src/main/java/com/example/employee_management/controller/EmployeeController.java`
- `src/main/java/com/example/employee_management/controller/DashboardController.java`
- `src/main/java/com/example/employee_management/controller/ExportController.java`

### Service
- `src/main/java/com/example/employee_management/service/EmployeeService.java`
- `src/main/java/com/example/employee_management/service/EmployeeServiceImpl.java`
- `src/main/java/com/example/employee_management/service/EmailService.java`
- `src/main/java/com/example/employee_management/service/ExcelExportService.java`
- `src/main/java/com/example/employee_management/service/PdfExportService.java`

## Important database cleanup

Because old users were saved with plain passwords, delete existing users and register again:

```sql
USE employee_db;
DELETE FROM users;
```

## Run backend

```bash
mvn clean install
mvn spring-boot:run
```

## Test APIs

### Register
POST `http://localhost:8080/api/auth/register`

```json
{
  "name": "Prasad",
  "email": "admin@gmail.com",
  "password": "admin123"
}
```

### Login
POST `http://localhost:8080/api/auth/login`

```json
{
  "email": "admin@gmail.com",
  "password": "admin123"
}
```

Copy the token from the response.

### Use token for protected APIs
Header:

```text
Authorization: Bearer YOUR_TOKEN_HERE
```

### Protected API examples
- GET `http://localhost:8080/api/employees`
- GET `http://localhost:8080/api/employees/page?page=0&size=5&sortBy=id&sortDir=asc`
- GET `http://localhost:8080/api/employees/search?keyword=IT&page=0&size=5`
- GET `http://localhost:8080/api/dashboard/stats`
- GET `http://localhost:8080/api/export/pdf`
- GET `http://localhost:8080/api/export/excel`
- POST `http://localhost:8080/api/employees/{id}/photo`

## Email setup

In `application.properties`, set:

```properties
spring.mail.username=your-email@gmail.com
spring.mail.password=your-gmail-app-password
```

Use Gmail App Password, not your normal Gmail password.

## Docker build

First build jar:

```bash
mvn clean package -DskipTests
```

Then:

```bash
docker build -t employee-backend .
docker run -p 8080:8080 employee-backend
```
