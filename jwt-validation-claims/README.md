# JWT Validation and Claims Extraction

A small Spring Boot 3 project implementing `POST /api/jwt/validate`. It verifies a JWT signature using a secret key, rejects expired/altered tokens, and returns username, roles, and claims.

## Requirements
- Java 17 or later
- Maven 3.8+ (or use the Maven wrapper if you add one)

## Run on Windows
1. Extract `jwt-validation-claims.zip`.
2. Open Command Prompt or PowerShell in the extracted `jwt-validation-claims` folder (the folder containing `pom.xml`).
3. Check Java: `java -version`
4. Start the application: `mvn spring-boot:run`
5. Open Swagger UI: http://localhost:8080/swagger-ui.html

## Test in Swagger UI
1. Expand **GET /api/jwt/demo-token** and click **Try it out**, then **Execute**.
2. Copy the `token` value from the response.
3. Expand **POST /api/jwt/validate**, click **Try it out**, and submit:
   ```json
   {
     "token": "PASTE_THE_TOKEN_HERE"
   }
   ```
4. A valid token returns HTTP 200 with `"message": "Token Valid"`, username `naveen`, roles, and claims.
5. To test tampering, change one or more characters in the token and submit it. It should return HTTP 401 with `"message": "Invalid Token"`.
6. To test expiration, wait until a token expires (the demo token lasts one hour), or create a token with an earlier expiration for a local test.

## Test with curl (optional)
```bash
curl -X POST http://localhost:8080/api/jwt/validate ^
  -H "Content-Type: application/json" ^
  -d "{\"token\":\"PASTE_THE_TOKEN_HERE\"}"
```
On macOS/Linux, replace `^` with `\` at line breaks, or send the command on one line.

## Important
The secret key and demo-token endpoint are for coursework/local testing only. In a real application, store a strong secret in an environment variable or secret manager, do not expose token-generation endpoints publicly, and use HTTPS.
