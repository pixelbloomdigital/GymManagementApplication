# Gym Management Application - Microservices Architecture

## Overview
A Spring Boot 3.x microservices application for gym management with MySQL database support.

## Technical Stack
- **Framework**: Spring Boot 3.x
- **Language**: Java 21
- **Database**: MySQL 8.x (separate schema per service)
- **ORM**: Spring Data JPA with Hibernate
- **Mapping**: MapStruct
- **Documentation**: Swagger/OpenAPI 3.0
- **Connection Pool**: HikariCP

## Project Structure
```
gymapp/
├── src/main/java/com/gym/gymapp/
│   ├── member/
│   │   ├── controller/
│   │   │   └── MemberController.java
│   │   ├── dto/
│   │   │   └── MemberDTO.java
│   │   ├── entity/
│   │   │   └── Member.java (with soft delete)
│   │   ├── mapper/
│   │   │   └── MemberMapper.java
│   │   ├── repository/
│   │   │   └── MemberRepository.java
│   │   ├── service/
│   │   │   └── MemberService.java
│   │   └── serviceimpl/
│   │       └── MemberServiceImpl.java
│   │
│   ├── membership/
│   │   ├── controller/
│   │   │   └── MembershipController.java
│   │   ├── dto/
│   │   │   └── MembershipDTO.java
│   │   ├── entity/
│   │   │   └── Membership.java
│   │   ├── mapper/
│   │   │   └── MembershipMapper.java
│   │   ├── repository/
│   │   │   └── MembershipRepository.java
│   │   ├── service/
│   │   │   └── MembershipService.java
│   │   └── serviceimpl/
│   │       └── MembershipServiceImpl.java
│   │
│   ├── attendance/
│   │   ├── controller/
│   │   │   └── AttendanceController.java
│   │   ├── dto/
│   │   │   ├── AttendanceDTO.java
│   │   │   ├── CheckInRequest.java
│   │   │   └── MonthlyReportDTO.java
│   │   ├── entity/
│   │   │   └── Attendance.java (with composite index)
│   │   ├── mapper/
│   │   │   └── AttendanceMapper.java
│   │   ├── repository/
│   │   │   └── AttendanceRepository.java (with custom JPQL query)
│   │   ├── service/
│   │   │   └── AttendanceService.java
│   │   └── serviceimpl/
│   │       └── AttendanceServiceImpl.java
│   │
│   └── GymappApplication.java
│
├── src/main/resources/
│   └── application.yml
└── pom.xml
```

## Database Setup

### Create MySQL Databases
```sql
CREATE DATABASE gym_member_db;
CREATE DATABASE gym_membership_db;
CREATE DATABASE gym_attendance_db;
```

### Update application.yml
Update the database credentials in `application.yml`:
```yaml
spring:
  datasource:
    username: your_username
    password: your_password
```

## Running the Services

### Member Service (Port 8081)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=member-service
```

### Membership Service (Port 8082)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=membership-service
```

### Attendance Service (Port 8083)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=attendance-service
```

## API Endpoints

### Member Service (http://localhost:8081)
- `POST /api/members` - Create member
- `GET /api/members/{id}` - Get member by ID
- `GET /api/members` - Get all members
- `PUT /api/members/{id}` - Update member
- `DELETE /api/members/{id}` - Soft delete member

### Membership Service (http://localhost:8082)
- `POST /api/memberships` - Create membership
- `GET /api/memberships/{id}` - Get membership by ID
- `GET /api/memberships` - Get all memberships
- `GET /api/memberships/member/{memberId}` - Get memberships by member ID
- `PUT /api/memberships/{id}` - Update membership
- `DELETE /api/memberships/{id}` - Delete membership

### Attendance Service (http://localhost:8083)
- `POST /api/attendance/check-in` - Record check-in
- `GET /api/attendance/report/{memberId}/{month}/{year}` - Get monthly report

## Swagger Documentation
Access Swagger UI for each service:
- Member Service: http://localhost:8081/swagger-ui.html
- Membership Service: http://localhost:8082/swagger-ui.html
- Attendance Service: http://localhost:8083/swagger-ui.html

## Key Features

### Member Service
- **Soft Delete**: Members are marked as deleted but retained in database for historical data
- Uses `@SQLDelete` and `@Where` annotations

### Attendance Service
- **Composite Index**: Optimized queries on `member_id` and `check_in_time`
- **Custom JPQL Query**: Monthly report using JPQL for counting visits
- **Automatic Timestamp**: Check-in time recorded automatically

### Connection Pooling
- **HikariCP** configured with optimal settings:
  - Maximum pool size: 10-20 connections
  - Minimum idle: 5-10 connections
  - Connection timeout: 30 seconds

## Sample API Requests

### Check-in Request
```json
POST /api/attendance/check-in
{
  "memberId": 1
}
```

### Monthly Report
```
GET /api/attendance/report/1/12/2024
```

Response:
```json
{
  "memberId": 1,
  "month": 12,
  "year": 2024,
  "totalVisits": 15
}
```

## Build and Package
```bash
mvn clean package
```

## Notes
- Each service uses a separate MySQL database schema
- Hibernate `ddl-auto` is set to `update` for automatic schema generation
- MapStruct generates mapper implementations at compile time
- Lombok reduces boilerplate code
