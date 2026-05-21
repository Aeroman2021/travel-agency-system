# ✈️ Travel Agency System (Spring Boot + Event-Driven Architecture)

A modular, event-driven backend system for managing flight bookings, passenger management, payments, and ticket issuance.

This project simulates a real-world airline booking workflow with a focus on **clean architecture, domain separation, concurrency safety, and event-driven communication**.

---

## 🧠 System Overview

The system is designed around a multi-stage booking lifecycle:

```
Booking → Flight Reservation → Passenger Registration → Payment → Ticket Issuance
```

Each stage is handled by a separate domain module to ensure scalability and maintainability.

---

## 🏗️ Architecture Style

* Modular Monolith (Spring Modulith-ready)
* Event-Driven Architecture (Spring Application Events)
* Domain Separation (Booking / Flight / Passenger / Payment)
* Transactional consistency with Optimistic Locking
* DTO-based API boundaries
* Facade pattern for cross-module communication

---

## 🔥 Core Modules

### 📦 Booking Module

* Creates initial booking intent
* Holds user + flight selection
* Calculates total price
* Triggers flight reservation workflow

---

### ✈️ Flight Module

* Manages flight inventory (available seats)
* Handles seat validation and reservation
* Implements Optimistic Locking (@Version)
* Provides pricing and availability services

---

### 👤 Passenger Module

* Collects passenger information after reservation
* Stores passenger details linked to booking/reservation
* Publishes passenger completion events

---

### 💳 Payment Module (Planned / In Progress)

* Handles payment lifecycle
* Listens to passenger completion events
* Triggers ticket issuance flow

---

### 🎫 Ticketing Module (Planned)

* Issues tickets after successful payment
* Generates PNR and ticket numbers

---

## 🔄 Event Flow

```
BookingInitiatedEvent
    ↓
FlightReservationCreatedEvent
    ↓
FlightReservedEvent
    ↓
PassengerInformationCompletedEvent
    ↓
PaymentProcessingEvent
    ↓
TicketIssuedEvent
```

---

## ⚙️ Key Design Decisions

### ✔️ Optimistic Locking

Used in Flight entity to prevent race conditions on seat booking:

```java
@Version
private Long version;
```

Ensures safe concurrent seat reservation.

---

### ✔️ Seat Reservation Strategy

* Seats are validated AND decremented in a single transactional boundary
* Prevents TOCTOU (Time-of-check Time-of-use) issues

---

### ✔️ Event-Driven Communication

Modules are loosely coupled using domain events instead of direct service calls.

---

### ✔️ Passenger Workflow Separation

Passenger information is collected after reservation, not during booking creation.

This improves:

* UX
* Flexibility
* Domain clarity

---

## 🧩 API Design Highlights

### Booking

```
POST /bookings
```

### Flight Reservation

```
POST /reservations/{reservationId}
```

### Passenger Registration

```
POST /reservations/{reservationId}/passengers
```

---

## 🧪 Example Flow

1. User creates booking
2. System reserves flight seats
3. User submits passenger details
4. System processes payment
5. System issues tickets

---

## 🛡️ Concurrency Handling

* Optimistic locking on flight inventory
* Transactional boundaries per domain operation
* Event-based decoupling between modules

---

## 🧱 Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Event System
* Hibernate
* MySQL
* Lombok
* MapStruct

---

## 🚧 Future Improvements

* Outbox Pattern for reliable event publishing
* Kafka integration for distributed event streaming
* Saga orchestration for payment + ticketing
* Redis caching for flight search
* API Gateway for modular exposure

---

## 📌 Notes

This project is intentionally designed to mimic real-world airline booking systems where:

* Reservation ≠ Ticket
* Passenger data is collected after seat allocation
* Payment is a separate lifecycle stage
* Concurrency issues are expected, not theoretical

---

## 🧠 Author Insight

Built as a learning-focused production-style architecture emphasizing:

* Domain-driven design thinking
* Event-driven workflows
* Real-world booking system constraints
