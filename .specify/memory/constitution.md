<!--
Sync Impact Report
Version change: none -> 1.0.0
Modified principles: N/A (initial constitution)
Added sections: Core Principles, Technology & Deployment Constraints, Development Workflow, Governance
Removed sections: none
Templates requiring updates: .specify/templates/plan-template.md ⚠ validated no updates required, .specify/templates/spec-template.md ⚠ validated no updates required, .specify/templates/tasks-template.md ⚠ validated no updates required
Follow-up TODOs: none
-->

# PetStore Constitution

## Core Principles

### I. Product Clarity and Customer Value
PetStore exists to make buying and managing pet products easy, trustable, and transparent. Every feature MUST map to a clearly stated buyer journey for dogs, cats, birds, or fish, and every implementation decision MUST preserve the simplicity of the shopping and checkout experience.

### II. Full-Stack Consistency and Maintainability
The application MUST use a Java Spring Boot backend with PostgreSQL, and a React frontend styled with Tailwind and MUI. The backend and frontend MUST be designed so that data contracts are explicit, shareable, and testable.

### III. Package and API Naming Discipline
All Java code MUST use the `com.salac.petstore` package namespace. All public API endpoints MUST begin with `/salac` to make routing predictable, stable, and searchable. No alternate base paths are allowed without documented approval.

### IV. Testable Integration and Deployment Readiness
Every release candidate MUST include automated service-level tests for backend APIs and end-to-end validation of the React UI where feasible. Docker-based local builds MUST be runnable and must reflect the Render free-tier deployment constraints.

### V. Cost-Conscious Deployment and Free-Tier Discipline
PetStore MUST be designed for Render free-tier deployment: lightweight containers, minimal runtime dependencies, and simple environment configuration. Cloud infrastructure choices MUST avoid paid-only services and keep the deployment path reproducible.

## Technology and Deployment Constraints
PetStore’s technology stack is defined as Java Spring Boot backend, PostgreSQL database, Docker containerization, React frontend, Tailwind CSS, and MUI. Deployment MUST target Render free-tier services, using containers for both frontend and backend when possible. Secrets and environment configuration MUST be managed in a way that is compatible with Render’s free-tier environment variables.

## Development Workflow and Quality Gates
Development MUST follow a clear workflow:
- Define and prioritize user stories in the feature specification.
- Implement foundation work first: API structure, data model, and deployment configuration.
- Validate each story independently with tests before merging.
- Use code review to verify package naming, API path discipline, and deployment readiness.
- Document any deviations from the Render free-tier deployment assumptions in the PR description.

## Governance
This constitution defines the core rules for PetStore development and deployment. All implementation work MUST comply with these principles unless a formal amendment is approved.

Amendments require a documented PR that explains the change, the reason, and the impact on existing deployment or naming rules. Version increments follow semantic versioning:
- MAJOR for incompatible principle or governance changes
- MINOR for new principles or added mandatory sections
- PATCH for wording clarifications, typos, and non-behavioral refinements

All PRs MUST include a compliance check against this constitution for technology stack, API naming, deployment assumptions, and test coverage expectations. When a principle cannot be followed immediately, the deviation MUST be documented with a remediation plan.

**Version**: 1.0.0 | **Ratified**: 2026-05-06 | **Last Amended**: 2026-05-06
