# SecuringAPIs-2

This project is a Spring Boot application that implements security measures to protect APIs using authentication, authorization, and role-based access control (RBAC). The project integrates OAuth2 and JWT for secure token handling and role-based access to resources.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing the APIs](#testing-the-apis)
- [Security Features](#security-features)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before running the project, ensure you have the following installed:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Postman](https://www.postman.com/downloads/) or [cURL](https://curl.se/download.html) (for API testing)
- An IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) or [Eclipse](https://www.eclipse.org/downloads/)

## Running the Application

1. **Clone the repository:**

    ```bash
    git clone https://github.com/your-repo/SecuringAPIs-2.git
    cd SecuringAPIs-2
    ```

2. **Build the project using Maven:**

    ```bash
    mvn clean install
    ```

3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

   The application will start on port `8080`. You can access it at `http://localhost:8080`.

4. **Generate a JWT token:**

   Use the `/api/login` endpoint to log in and retrieve a JWT token, which will be needed to access the protected endpoints.

   **Example cURL request:**

    ```bash
    curl -X POST http://localhost:8080/api/login \
    -H "Content-Type: application/json" \
    -d '{"username": "testUser", "password": "testPassword"}'
    ```

   The response will contain a JWT token. Use this token for accessing protected endpoints by including it in the `Authorization` header as a `Bearer` token.

## API Endpoints

### Public Endpoints (No Authentication Required)
1. **Register a new user:**
    - `POST /api/register`
    - Payload Example:
      ```json
      {
        "username": "newUser",
        "password": "securePassword"
      }
      ```

2. **Login to generate a JWT token:**
    - `POST /api/login`
    - Payload Example:
      ```json
      {
        "username": "newUser",
        "password": "securePassword"
      }
      ```

### Protected Endpoints (Authentication Required)
1. **Access protected resource:**
    - `GET /api/protected`
    - Add `Authorization: Bearer <JWT Token>` in the request header.

2. **Admin-only resource:**
    - `GET /api/admin`
    - Add `Authorization: Bearer <JWT Token>` in the request header.
    - Only accessible to users with the `ADMIN` role.

## Testing the APIs

### Using Postman
1. **Register a new user**:
    - Use the `POST` request to `http://localhost:8080/api/register` with the following JSON body:
      ```json
      {
        "username": "newUser",
        "password": "securePassword"
      }
      ```

2. **Login and get the JWT token**:
    - Use the `POST` request to `http://localhost:8080/api/login` with the JSON body:
      ```json
      {
        "username": "newUser",
        "password": "securePassword"
      }
      ```
    - Copy the `token` from the response.

3. **Access a protected endpoint**:
    - Use the `GET` request to `http://localhost:8080/api/protected`.
    - In the `Headers` tab, add:
        - Key: `Authorization`
        - Value: `Bearer <JWT Token>` (replace `<JWT Token>` with the actual token from the login response)

### Running Unit Tests
Unit tests are written to test the security configuration and API functionality.

1. **Run the tests using Maven:**

    ```bash
    mvn test
    ```

   This will run all unit tests, including the tests for the security configuration and API endpoints.

2. **Test Classes:**
    - `JwtTokenUtilTest` – Tests the JWT generation and validation.
    - `OAuth2ConfigTest` – Tests OAuth2 configuration, ensuring proper access control.
    - `SecurityConfigTest` – Verifies security settings and access to endpoints.

## Security Features

- **Authentication**: OAuth2 and JWT-based authentication.
- **Authorization**: Role-based access control (RBAC) to protect endpoints (e.g., only `ADMIN` can access admin-specific routes).
- **CSRF Protection**: Disabled for simplicity, but can be enabled in production.
- **CORS Configuration**: Allows specific origins for API access.

## Contributing

If you’d like to contribute to this project, feel free to submit a pull request or open an issue. For major changes, please open an issue first to discuss what you’d like to change.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
