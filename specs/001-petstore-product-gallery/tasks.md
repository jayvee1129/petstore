# Tasks: PetStore Product Gallery

**Input**: Design documents from `/specs/001-petstore-product-gallery/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Tests are MANDATORY per constitution requirement for automated service-level tests for backend APIs and end-to-end validation of the React UI.

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

## Path Conventions

- **Single project**: `src/`, `tests/` at repository root
- **Web app**: `backend/src/`, `frontend/src/`
- Paths shown below assume single project - adjust based on plan.md structure

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and basic structure

- [X] T001 Create backend project structure per implementation plan
- [X] T002 Create frontend project structure per implementation plan
- [X] T003 [P] Initialize Spring Boot project with dependencies in backend/pom.xml
- [X] T004 [P] Initialize React project with Vite, Tailwind, and MUI in frontend/package.json

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core infrastructure that MUST be complete before ANY user story can be implemented

**⚠️ CRITICAL**: No user story work can begin until this phase is complete

Examples of foundational tasks (adjust based on your project):

- [X] T005 Setup PostgreSQL database schema and migrations in backend/src/main/resources/db/
- [X] T006 [P] Implement Pet entity model in backend/src/main/java/com/salac/petstore/model/Pet.java
- [X] T007 [P] Implement CartItem entity model in backend/src/main/java/com/salac/petstore/model/CartItem.java
- [X] T008 [P] Implement WishlistItem entity model in backend/src/main/java/com/salac/petstore/model/WishlistItem.java
- [X] T009 [P] Implement FilterCriteria model in backend/src/main/java/com/salac/petstore/model/FilterCriteria.java
- [X] T010 [P] Create PetRepository interface in backend/src/main/java/com/salac/petstore/repository/PetRepository.java
- [X] T011 [P] Create CartRepository interface in backend/src/main/java/com/salac/petstore/repository/CartRepository.java
- [X] T012 [P] Create WishlistRepository interface in backend/src/main/java/com/salac/petstore/repository/WishlistRepository.java
- [X] T013 Configure Spring Data JPA and PostgreSQL connection in backend/src/main/resources/application.yml
- [X] T014 Setup CORS and API base path configuration for /salac in backend/src/main/java/com/salac/petstore/api/
- [X] T015 Configure frontend API client for /salac endpoints in frontend/src/services/api.js

**Checkpoint**: Foundation ready - user story implementation can now begin in parallel

---

## Phase 3: User Story 1 - Browse pets in responsive gallery (Priority: P1) 🎯 MVP

**Goal**: Enable users to view a responsive gallery of pets with basic information and navigate to detail pages

**Independent Test**: Load the gallery page and verify pet cards display correctly, then click a card to view details

### Tests for User Story 1 (MANDATORY per constitution) ⚠️

- [X] T016 [P] [US1] Contract test for GET /salac/pets in backend/src/test/java/com/salac/petstore/api/PetControllerTest.java
- [X] T017 [P] [US1] Integration test for pet gallery UI in frontend/src/tests/PetGallery.test.jsx

### Implementation for User Story 1

- [X] T018 [P] [US1] Implement PetService for listing pets in backend/src/main/java/com/salac/petstore/service/PetService.java
- [X] T019 [US1] Implement GET /salac/pets endpoint in backend/src/main/java/com/salac/petstore/api/PetController.java
- [X] T020 [P] [US1] Create PetCard component in frontend/src/components/PetCard.jsx
- [X] T021 [P] [US1] Create PetGallery page component in frontend/src/pages/PetGallery.jsx
- [X] T022 [US1] Implement pet listing API call in frontend/src/services/petService.js
- [X] T023 [US1] Add routing for gallery and detail pages in frontend/src/routes/AppRouter.jsx
- [X] T024 [US1] Style gallery with responsive grid using Tailwind and MUI in frontend/src/pages/PetGallery.jsx

**Checkpoint**: At this point, User Story 1 should be fully functional and testable independently

---

## Phase 4: User Story 2 - Search and filter pets (Priority: P2)

**Goal**: Allow users to search and filter the pet gallery by various criteria

**Independent Test**: Enter search terms and apply filters, verify gallery updates with matching pets or empty state

### Tests for User Story 2 (MANDATORY per constitution) ⚠️

- [ ] T025 [P] [US2] Contract test for GET /salac/pets with query params in backend/src/test/java/com/salac/petstore/api/PetControllerTest.java
- [ ] T026 [P] [US2] Integration test for search and filter UI in frontend/src/tests/SearchFilter.test.jsx

### Implementation for User Story 2

- [ ] T027 [P] [US2] Enhance PetService with search and filter logic in backend/src/main/java/com/salac/petstore/service/PetService.java
- [ ] T028 [US2] Update GET /salac/pets endpoint to support query parameters in backend/src/main/java/com/salac/petstore/api/PetController.java
- [ ] T029 [P] [US2] Create SearchBar component in frontend/src/components/SearchBar.jsx
- [ ] T030 [P] [US2] Create FilterPanel component in frontend/src/components/FilterPanel.jsx
- [ ] T031 [US2] Update pet listing API call to include search and filters in frontend/src/services/petService.js
- [ ] T032 [US2] Integrate search and filters into PetGallery page in frontend/src/pages/PetGallery.jsx
- [ ] T033 [US2] Implement empty state component for no results in frontend/src/components/EmptyState.jsx

**Checkpoint**: At this point, User Stories 1 AND 2 should both work independently

---

## Phase 5: User Story 3 - Add pets to cart or wishlist (Priority: P3)

**Goal**: Enable users to add pets to cart or wishlist via REST API

**Independent Test**: From pet detail page, add to cart and wishlist, verify state updates

### Tests for User Story 3 (MANDATORY per constitution) ⚠️

- [ ] T034 [P] [US3] Contract test for cart endpoints in backend/src/test/java/com/salac/petstore/api/CartControllerTest.java
- [ ] T035 [P] [US3] Contract test for wishlist endpoints in backend/src/test/java/com/salac/petstore/api/WishlistControllerTest.java
- [ ] T036 [P] [US3] Integration test for cart/wishlist UI in frontend/src/tests/CartWishlist.test.jsx

### Implementation for User Story 3

- [ ] T037 [P] [US3] Implement CartService in backend/src/main/java/com/salac/petstore/service/CartService.java
- [ ] T038 [P] [US3] Implement WishlistService in backend/src/main/java/com/salac/petstore/service/WishlistService.java
- [ ] T039 [US3] Implement cart endpoints (GET, POST, DELETE /salac/cart) in backend/src/main/java/com/salac/petstore/api/CartController.java
- [ ] T040 [US3] Implement wishlist endpoints (GET, POST, DELETE /salac/wishlist) in backend/src/main/java/com/salac/petstore/api/WishlistController.java
- [ ] T041 [P] [US3] Create PetDetail page component in frontend/src/pages/PetDetail.jsx
- [ ] T042 [US3] Implement GET /salac/pets/{petId} endpoint in backend/src/main/java/com/salac/petstore/api/PetController.java
- [ ] T043 [US3] Add cart and wishlist API calls in frontend/src/services/cartService.js and frontend/src/services/wishlistService.js
- [ ] T044 [US3] Integrate Add to Cart and Add to Wishlist buttons in frontend/src/pages/PetDetail.jsx
- [ ] T045 [US3] Update PetCard to link to detail page in frontend/src/components/PetCard.jsx

**Checkpoint**: All user stories should now be independently functional

---

## Phase N: Polish & Cross-Cutting Concerns

**Purpose**: Improvements that affect multiple user stories

- [ ] T046 [P] Validate SC-001: 95% of users find pet type using search/filters within 3 interactions in frontend/src/tests/performance/
- [ ] T047 [P] Validate SC-002: gallery layout adapts without horizontal scroll on mobile/tablet in frontend/src/tests/responsive/
- [ ] T048 [P] Validate SC-003: 90% of users perform cart/wishlist actions without errors in frontend/src/tests/error-handling/
- [ ] T049 [P] Add error handling and user-friendly messages across frontend components
- [ ] T050 Code cleanup and refactoring in backend and frontend
- [ ] T051 [P] Add loading states and accessibility improvements in frontend
- [ ] T052 Configure Docker containers for backend and frontend deployment
- [ ] T053 Update quickstart.md with final setup instructions
- [ ] T054 Run quickstart.md validation

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - BLOCKS all user stories
- **User Stories (Phase 3+)**: All depend on Foundational phase completion
  - User stories can then proceed in parallel (if staffed)
  - Or sequentially in priority order (P1 → P2 → P3)
- **Polish (Final Phase)**: Depends on all desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational (Phase 2) - No dependencies on other stories
- **User Story 2 (P2)**: Can start after Foundational (Phase 2) - May integrate with US1 but should be independently testable
- **User Story 3 (P3)**: Can start after Foundational (Phase 2) - May integrate with US1/US2 but should be independently testable

### Within Each User Story

- Models before services
- Services before endpoints
- Core implementation before integration
- Story complete before moving to next priority

### Parallel Opportunities

- All Setup tasks marked [P] can run in parallel
- All Foundational tasks marked [P] can run in parallel (within Phase 2)
- Once Foundational phase completes, all user stories can start in parallel (if team capacity allows)
- Different user stories can be worked on in parallel by different team members

---

## Parallel Example: User Story 1

```bash
# Launch all models for User Story 1 together:
Task: "Implement PetService for listing pets in backend/src/main/java/com/salac/petstore/service/PetService.java"
Task: "Create PetCard component in frontend/src/components/PetCard.jsx"
Task: "Create PetGallery page component in frontend/src/pages/PetGallery.jsx"

# Launch all endpoints for User Story 1 together:
Task: "Implement GET /salac/pets endpoint in backend/src/main/java/com/salac/petstore/api/PetController.java"
Task: "Implement pet listing API call in frontend/src/services/petService.js"
```

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational (CRITICAL - blocks all stories)
3. Complete Phase 3: User Story 1
4. **STOP and VALIDATE**: Test User Story 1 independently
5. Deploy/demo if ready

### Incremental Delivery

1. Complete Setup + Foundational → Foundation ready
2. Add User Story 1 → Test independently → Deploy/Demo (MVP!)
3. Add User Story 2 → Test independently → Deploy/Demo
4. Add User Story 3 → Test independently → Deploy/Demo
5. Each story adds value without breaking previous stories

### Parallel Team Strategy

With multiple developers:

1. Team completes Setup + Foundational together
2. Once Foundational is done:
   - Developer A: User Story 1
   - Developer B: User Story 2
   - Developer C: User Story 3
3. Stories complete and integrate independently

---

## Notes

- [P] tasks = different files, no dependencies
- [Story] label maps task to specific user story for traceability
- Each user story should be independently completable and testable
- Verify tests fail before implementing
- Commit after each task or logical group
- Stop at any checkpoint to validate story independently
- Avoid: vague tasks, same file conflicts, cross-story dependencies that break independence