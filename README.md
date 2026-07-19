# Phylx

> **Developer-first workflow automation platform built with Spring Boot.**

Phylx is an event-driven workflow automation platform that enables applications to automate business processes by publishing business events instead of embedding automation logic directly into application code.

Rather than asking your application to send emails, schedule reminders, call APIs, or trigger background jobs, your application simply reports **what happened**.

Phylx decides **what happens next.**

---

## Why Phylx?

Modern applications quickly become filled with automation logic:

- Send welcome emails
- Send SMS notifications
- Call third-party APIs
- Schedule delayed tasks
- Run background jobs
- Notify Slack or Microsoft Teams
- Update external systems
- Trigger follow-up business processes

As applications grow, this logic becomes tightly coupled to the application's core business logic, making systems difficult to maintain, test, and extend.

Phylx separates business events from business automation.

Applications emit events.

Phylx owns the workflows.

---

# Example

Instead of writing application code like this:

```
User Registers
        │
        ▼
Create User
        │
        ▼
Send Welcome Email
        │
        ▼
Create CRM Contact
        │
        ▼
Notify Slack
        │
        ▼
Schedule Reminder
```

Your application simply publishes an event.

```json
{
  "event": "user.created",
  "payload": {
    "id": 123,
    "name": "Mustapha",
    "email": "mustapha@example.com"
  }
}
```

Phylx receives the event and executes the configured workflow.

```
user.created
      │
      ▼
Send Welcome Email
      │
      ▼
Wait 24 Hours
      │
      ▼
Email Verified?
    /       \
  Yes       No
  │          │
Finish   Send Reminder
             │
             ▼
          Finish
```

The client application never knows any of this workflow exists.

It only reports that **a user was created**.

---

# Core Philosophy

> **Applications describe what happened.**
>
> **Phylx decides what should happen next.**

Applications should focus on business logic.

Phylx focuses on automation.

---

# Current Progress

Phylx is currently in active development.

The first milestone focuses on building a production-ready backend foundation before implementing the workflow engine.

### Authentication

- JWT Authentication
- Stateless Authentication
- Spring Security
- BCrypt Password Hashing

### Architecture

- Feature-based package structure
- DTO pattern
- Entity mappers
- Global exception handling
- Standardized API responses
- Business rule exceptions

### Persistence

- PostgreSQL
- Spring Data JPA
- Flyway database migrations

---

The project follows a **feature-based architecture** rather than grouping classes solely by type. This keeps related functionality together and allows the application to scale without becoming difficult to navigate.

---

# Tech Stack

- Java 26
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- JWT
- Maven
- Lombok

---

# Roadmap

The authentication system is only the beginning.

Upcoming features include:

- Event ingestion API
- Workflow definitions
- Workflow execution engine
- Delayed execution
- Conditional branching
- Retry policies
- Dead Letter Queue (DLQ)
- Queue workers
- Webhooks
- Email integrations
- SMS integrations
- HTTP actions
- Scheduled workflows
- Multi-tenancy
- Audit logs
- Real-time monitoring
- Plugin architecture
- Dashboard
- Workflow analytics

---

# Long-Term Vision

The goal is to build a production-grade workflow engine capable of powering automation for any application.

Whether it's:

- E-commerce
- Logistics
- FinTech
- Healthcare
- CRM
- Education
- SaaS

the integration remains exactly the same:

```
Application
      │
      ▼
Publishes Business Event
      │
      ▼
Phylx
      │
      ▼
Executes Workflows
      │
      ▼
Emails • SMS • APIs • Queues • Notifications • Scheduling
```

Applications remain simple.

Business automation lives inside Phylx.

---

# Contributing

Contributions, discussions, and architectural feedback are welcome.

If you're interested in Spring Boot, workflow engines, distributed systems, event-driven architecture, or backend engineering, feel free to open an issue or submit a pull request.

---

# Author

**Mustapha Tijani**

Building Phylx publicly while documenting the journey of learning Spring Boot, software architecture, and distributed systems.

If you find the project interesting, consider giving it a ⭐.