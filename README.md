# XSTAY

This is a RESTful API service for managing hotel bookings. It allows users to browse available hotels, book rooms, and manage bookings.

## Technologies Used
- Java
- Spring Boot
- MySQL
- Spring Security with JWT authentication

## Setup Instructions

1.  Clone the repository:
    `git clone https://github.com/adityaag530/xstay.git`

2.  Navigate to the project directory:
    `cd book-rental`

3. Configure the database connection in `application.properties`.

4. Build the project:
    `gradle build`

5. Run the application:
    `java -jar build/libs/xStay-0.0.1-SNAPSHOT.jar`

    Alternatively, you can run the application using Gradle:
    `gradle bootRun`

## API Endpoints
###### Do add bearer token in request header to all the request except("/regiser" & "/login")
### User Registration
- **Endpoint:** `POST /register`
- **Request Body:**
  ```json
  {
      "email": "user@example.com",
      "password": "password",
      "firstName": "John",
      "lastName": "Doe"
  }
  ```

### User Login
- **Endpoint:** `POST /login`
- **Request Body:**
  ```json
  {
      "email": "user@example.com",
      "password": "password"
  }
  ```

### Hotel Management
- **Browse All Hotels:** `GET /hotels`
- **Create Hotel (Admin Only):** `POST /hotels`
- **Update Hotel (Hotel Manager Only):** `PUT /hotels/{hotelId}`
- **Delete Hotel (Admin Only):** `DELETE /hotels/{hotelId}`

### Booking Management
- **Book a Room:** `POST /hotels/{hotelId}/book`
    - **Request Body:**
      ```json
      {
          "userId": 1
      }
      ```
- **Cancel Booking (Hotel Manager Only):** `DELETE /bookings/{bookingId}`

## Postman Collection
For testing the API endpoints, you can import the Postman collection provided below.

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/12734353-8feb01bb-d0cb-4b65-a744-8a6b93b599f5?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D12734353-8feb01bb-d0cb-4b65-a744-8a6b93b599f5%26entityType%3Dcollection%26workspaceId%3Deb6039ff-527d-4e42-a8c0-36baf4ffd0b0)
