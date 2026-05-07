# Research: PetStore Product Gallery

## Decision: Backend stack
- **Chosen**: Java 17+, Spring Boot 3, Spring Data JPA, PostgreSQL.
- **Rationale**: This matches the user request and the constitution. Spring Boot 3 is stable with Java 17 and offers strong database integration, opinionated configuration, and compatibility with Render free-tier containers.
- **Alternatives considered**:
  - Node/Express or Python/Flask: rejected because the feature explicitly requires Java Spring Boot.
  - Spring Boot with H2 only: rejected because the requirement demands PostgreSQL.

## Decision: Frontend stack
- **Chosen**: React with Vite, Tailwind CSS, and MUI.
- **Rationale**: Vite offers fast development builds and React is ideal for a responsive gallery plus client-side search/filter behavior. Tailwind and MUI together allow rapid styling, responsive layout, and accessible components with consistent design.
- **Alternatives considered**:
  - Create React App: rejected in favor of Vite for smaller bundle sizes and faster startup.
  - Plain CSS without Tailwind/MUI: rejected because component consistency and rapid responsive layout are important for the UI.

## Decision: API contract and naming
- **Chosen**: expose all backend endpoints under `/salac`, with resources like `/salac/pets`, `/salac/cart`, and `/salac/wishlist`.
- **Rationale**: The constitution mandates `/salac` as the public API base path, so this is the required contract.
- **Alternatives considered**:
  - Using `/api` or `/store`: rejected because it violates the constitution requirement.

## Decision: Data modeling and cart/wishlist scope
- **Chosen**: represent pets as a first-class entity, with optional cart and wishlist actions supported via REST API. Cart/wishlist state may initially be session-scoped or browser-local until authentication is added.
- **Rationale**: This preserves the requested optional cart/wishlist behavior while keeping the first release focused on browsing and discovery.
- **Alternatives considered**:
  - Full user accounts and persistent carts: rejected as out of scope for the initial feature and constitution-aligned MVP.

## Decision: Deployment approach
- **Chosen**: design for Render free-tier using lightweight containers for backend and frontend. Backend should be a minimal Spring Boot JAR container, frontend should be a static site or Node-based preview that Render can host.
- **Rationale**: Free-tier deployment constraints favor small images, few services, and environment-variable driven configuration. Render supports separate web services for backend and frontend.
- **Alternatives considered**:
  - Monolithic app serving React from Spring Boot: viable but rejected to preserve development separation and ease of frontend iteration.
  - Using a paid managed service for the database: rejected because the constitution requires free-tier compatibility.
