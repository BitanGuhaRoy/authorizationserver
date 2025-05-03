# 🛡️ Spring Authorization Server with JWT

This project is a custom **Authorization Server** built using **Spring Authorization Server**, **JWT**, and **Spring Security**. It provides secure authentication and token generation based on user credentials stored in a database.

---

## 🚀 Features

- ✅ **JWT-based Authentication**
  - Users receive a signed JWT after successful login.
  - JWT payload includes custom claims and roles.
- 🔐 **Spring Security Integration**
  - CSRF protection disabled for Postman testing.
- 🧾 **User Management**
  - Signup and login endpoints available.
  - Credentials stored securely in the database.
- 🛠️**Custom Claims Support**
  - Add custom fields (e.g., mobile number, user ID) to the JWT.
  - Roles and user details included in the token claims.
- 🧩 **Role-based Authorization**
  - Assign roles like `ADMIN`, `USER`, etc.
  - Roles automatically embedded in the JWT claims.

---

## 📬 Getting JWT Token

1. **Sign up** a user:

  API:
  
   POST: <server>/auth/signup
   
  Payload:
  ```json
    {
    "email": "bitan",
    "password": "password",
    "username": "Bits"
  }
```

3. **JWT Payload Example**:
```json
{
  "sub": "sdas",
  "aud": "oidc-client",
  "nbf": 1746306216,
  "user_id": 1,
  "user_name": "sdas",
  "scope": [
    "EMAIL"
  ],
  "roles": [
    "ADMIN",
    "TA"
  ],
  "iss": "http://localhost:8081",
  "mobile": "7044654415",
  "exp": 1746309816,
  "iat": 1746306216,
  "jti": "ceb4713b-2797-492d-9377-5d5cc4a02296"
}
```

🧑‍💻 Tech Stack
Java 17

Spring Boot 3

Spring Security

Spring Authorization Server

JWT 

MySQL (for user data)

Maven


Getting Started
1.Clone the repository:
```bash
git clone https://github.com/BitanGuhaRoy/authorizationserver.git

cd authorizationserver
```

2. Update database configs in application.properties.

3. Run the application ::
```
./mvnw spring-boot:run
```







