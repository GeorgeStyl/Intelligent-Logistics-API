# Intelligent Logistics API - Master's Course Final Project

## Project Description
This repository contains the final project for a course within my **Master's Degree** program. The application is an **Intelligent Logistics Management System** built with **Java 17** and **Spring Boot 3.x**. It serves as a practical implementation of advanced software engineering principles, specifically focusing on **Clean Code**, **Design Patterns**, and **High-Performance Concurrency**.

The system manages the lifecycle of logistics operations: from the dynamic creation of diverse vehicle types (Drones, Vans, Trucks) using factory hierarchies to the execution of complex shipping cost algorithms. By utilizing an asynchronous architecture, the API handles high-volume request processing without blocking the main execution thread.

---

## 🛠 Architectural Design Patterns

### 1. Abstract Factory Pattern

We use this pattern to manage the instantiation of different vehicle types. This decouples the client code from concrete classes, allowing the system to introduce new transport methods (like "Cargo Ships") without modifying existing service logic. This enforces the **Dependency Inversion Principle**.

### 2. Strategy Pattern

Shipping logic is encapsulated within interchangeable strategy classes. This eliminates brittle conditional logic (if/else) and allows the application to switch between Standard, Express, and Free shipping models at runtime, adhering to the **Open/Closed Principle**.

---

## 🧵 Concurrency & Multithreading

### Custom Thread Management
The system uses a dedicated `ThreadPoolTaskExecutor` defined in `AsyncConfig`. This ensures that asynchronous operations are handled by a controlled pool of workers, preventing resource exhaustion and providing better monitoring.

**Package:** `org.stylianopoulos.logistics.config`
**Reason:** This package is reserved for configuration classes that bootstrap the Spring Context. By placing the `AsyncConfig` here, we follow the **Single Responsibility Principle** at the package level.

**Implementation Detail:**
![AsyncConfig Implementation](image_52a0ad.png)

// * FLAG: CONCURRENCY & MULTITHREADING
As shown in the configuration above, we define a core pool of 5 threads that can scale to 10, ensuring the application remains responsive under load.

### Multithreading Verification & Logs
To verify true multithreading, the following logs demonstrate the **Start -> Sleep -> End** lifecycle. Notice the unique thread IDs and names, proving tasks run in parallel across different workers.

[INSERT PHOTO HERE: Multithreading logs showing parallel execution]

---

## ⚡ Performance Optimization: The Warmup Bot

During initial testing, the first HTTP request to the API consistently exceeded **200ms**, while subsequent requests averaged around **7ms**. This latency is a known behavior in Java environments due to JIT compilation and socket initialization.

* **Technique:** An internal `WARMUP_BOT` sends a dummy request upon startup.
* **Result:** The bot ensures the very first user-initiated request achieves low latency immediately.

---

## 🛰 API Endpoints & Request Samples

**Base URL:** `http://localhost:8080`

### 1. Vehicle Initialization
**Endpoint:** `GET /vehicles/init?type={type}`
**Parameters:** `type` (Drone / Van / Truck)
**Sample:** `{{baseUrl}}/vehicles/init?type=Drone`

### 2. Order Placement
**Endpoint:** `POST /orders`
**Request Body:**
```json
{
    "customerName": "Ziggy STANDARD",
    "weight": 7,
    "destination": "Mars",
    "shippingType": "STANDARD" 
}
```

### 3. Analytics
**Endpoint** `GET /analytics`
**Description:** Retrieves system-wide logs and processing metrics.

### Postman Verification & Documentation
I have uploaded the dedicated Postman Collection file: `Stylianopoulos_Project2.postman_collection.json`
