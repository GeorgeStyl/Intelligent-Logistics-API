# Intelligent Logistics API - Master's Course Final Project

## Project Description
This repository contains the final project for a specific course within my **Master's Degree** program. The application is an **Intelligent Logistics Management System** built with **Java 17** and **Spring Boot 3.x**. It serves as a practical implementation of advanced software engineering principles, specifically focusing on **Clean Code**, **Design Patterns**, and **High-Performance Concurrency**.

The system manages the lifecycle of logistics operations: from the dynamic creation of diverse vehicle types (Drones, Vans, Trucks) using factory hierarchies to the execution of complex shipping cost algorithms. By utilizing an asynchronous architecture, the API handles high-volume request processing without blocking the main execution thread.

---

## 🛠 Architectural Design Patterns

### 1. Abstract Factory Pattern

We use this pattern to manage the instantiation of different vehicle types. This decouples the client code from concrete classes, allowing the system to introduce new transport methods without modifying existing service logic. This enforces the **Dependency Inversion Principle**.

### 2. Strategy Pattern

Shipping logic is encapsulated within interchangeable strategy classes. This eliminates brittle conditional logic (if/else) and allows the application to switch between Standard, Express, and Free shipping models at runtime, adhering to the **Open/Closed Principle**.

---

## 🧵 Concurrency & Multithreading

### Custom Thread Management
The system uses a dedicated `ThreadPoolTaskExecutor` defined in `AsyncConfig`. This ensures that asynchronous operations are handled by a controlled pool of workers, preventing resource exhaustion and providing better monitoring.

**Package:** `org.stylianopoulos.logistics.config`
**Reason:** This package is reserved for configuration classes that bootstrap the Spring Context. By placing the `AsyncConfig` here, we follow the **Single Responsibility Principle** at the package level, separating system-wide settings from business logic.

### Single Responsibility: "One File for One Use"
To maintain a high level of **Clean Code**, the project follows the principle of having a single file for a single, well-defined use case. This is visible in our DTO and Factory structures, where each class has exactly one reason to change, significantly reducing side effects during refactoring.

### Multithreading Verification
To verify true multithreading, the following screenshot displays the console logs showing the **Start -> Sleep -> End** lifecycle. You can observe the `LogisticsWorker-X` thread names executing tasks in parallel, proving that the system does not process orders sequentially.



---

## ⚡ Performance Optimization: The Warmup Bot

During initial testing, it was observed that the first HTTP request to the API consistently exceeded **200ms**, while subsequent requests averaged around **7ms**. This latency is a known behavior in Java environments due to JIT (Just-In-Time) compilation, class loading, and the initialization of network sockets/connection pools.

To mitigate this, I implemented an **Internal Warmup Bot**.

* **Technique:** Upon application startup, the system triggers a "dummy" internal request (`WARMUP_BOT`).
* **Result:** This bot "consumes" the initial overhead of the JVM and Spring infrastructure. Consequently, the very first user-initiated request benefits from a pre-warmed environment, achieving the same high-speed performance (low latency) as subsequent calls.

---

## 🚀 API Testing & Documentation

The API endpoints were rigorously tested using **Postman**. The following screenshots demonstrate successful vehicle creation, order placement, and dynamic shipping calculations.



---

## 🗄 Infrastructure & Standards
* **Database:** PostgreSQL (Managed via Docker)
* **Build Tool:** Maven
* **IDE:** IntelliJ IDEA Ultimate
* **Clean Code:** Strictly **No Lombok**; all POJO elements (constructors, getters, setters) are manually written for maximum transparency.
* **Polymorphism:** Strategically preferred over conditionals to ensure a scalable and maintainable architecture.

---
