# Quickstart: PetStore Product Gallery

## Local development setup

1. Clone the repository:

```bash
git clone <repo-url> petstore
cd petstore
```

2. Prepare backend dependencies:

- Create `backend/` if not already present.
- Add Spring Boot 3 and Spring Data JPA dependencies.
- Add PostgreSQL JDBC driver and any required DevTools.

3. Prepare frontend dependencies:

```bash
cd frontend
npm install
```

4. Configure environment variables for local development:

```bash
# backend
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/petstore
SPRING_DATASOURCE_USERNAME=petstore
SPRING_DATASOURCE_PASSWORD=secret
SPRING_PROFILES_ACTIVE=local

# frontend
VITE_API_BASE_URL=http://localhost:8080/salac
```

5. Run PostgreSQL locally:

Use Docker or a local database instance with a `petstore` database.

6. Start the backend service:

```bash
cd backend
./mvnw spring-boot:run
```

7. Start the frontend service:

```bash
cd ../frontend
npm run dev
```

8. Open the site:

Visit `http://localhost:5173` (or the port shown by Vite) and verify the gallery loads.

## Build and containerize

### Backend

```bash
cd backend
./mvnw clean package -DskipTests
docker build -t petstore-backend .
```

### Frontend

```bash
cd ../frontend
npm run build
docker build -t petstore-frontend .
```

## Render deployment guidance

1. Create a Render account and add a new Web Service for the backend.
2. Use the backend Git repository and set the start command to run the Spring Boot JAR.
3. Set environment variables in Render for PostgreSQL and any required secrets.
4. Add a second Render Web Service for the frontend, or deploy the static build if the frontend is served from a static site.
5. Use Render's free-tier PostgreSQL if available, or a compatible free-tier managed Postgres instance.

## Notes

- The backend API base path must be `/salac` to comply with the constitution.
- The frontend should use `VITE_API_BASE_URL` to connect to the backend service.
- Keep containers lightweight and avoid extra paid services for the Render free-tier deployment.
