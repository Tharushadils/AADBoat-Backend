# 🌊 Aquaventure

### Riverside Boating Reservation REST API

📄 **Project Report:**  
[View Aquaventure Project Report](https://docs.google.com/document/d/18pA6xac3zT7vHEpxkC1rQNgR3Vh0SHLo8ZyE5_8dwuY/edit?usp=sharing)

---

## 📌 Project Overview

**Aquaventure** is a modern, API-first **Riverside Boating Reservation System** designed to digitize and streamline boating reservations in Sri Lanka.

The system replaces traditional manual and phone-based reservation methods with a secure and scalable RESTful API. It provides customers with a guided reservation experience while allowing receptionists and administrators to efficiently manage the complete boating operation.

---

## ✨ Key Features

- 🔐 JWT-based authentication and authorization
- 👥 Role-Based Access Control (RBAC)
- 🚤 Boat management
- 🏷️ Boat category management
- 📍 Dock management
- 🕐 Time-slot management
- 📅 Complete reservation management
- 💳 Payment management
- ⭐ Customer reviews
- 🛍️ Souvenir management
- ➕ Add-on service management
- 🤖 AI-powered customer assistance chatbot
- 🗄️ MySQL database
- 🔎 JPQL, Derived Queries, and Native SQL
- 🛡️ BCrypt password encryption
- 📝 RESTful API with standard HTTP status codes

---

## 🎯 Project Objectives

The main objectives of Aquaventure are:

1. Replace traditional manual and phone-based reservation processes.
2. Provide customers with a convenient online reservation system.
3. Allow receptionists to manage walk-in and phone reservations.
4. Provide administrators with centralized system management.
5. Secure the system using JWT authentication and Role-Based Access Control.
6. Manage boats, docks, slots, reservations, payments, and additional services.
7. Improve customer interaction using an AI-powered chatbot.

---

## 👥 User Roles

| Role | Responsibilities |
|---|---|
| **CUSTOMER** | Makes reservations, selects boats and services, purchases souvenirs, and interacts with the AI chatbot. |
| **RECEPTIONIST** | Handles walk-in and phone reservations, updates reservation statuses, and monitors operational availability. |
| **ADMIN** | Manages and performs CRUD operations across the entire system. |

### 🔒 Access Control

User creation, updating, role assignment, and deletion are restricted to **ADMIN** users.

---

# 🚤 Reservation Workflow

The customer reservation process follows a sequential workflow:

```text
Select Date
      ↓
Select Time Slot
      ↓
Select Starting Dock
      ↓
Select Destination Dock
      ↓
Select Boat Category
      ↓
Select Available Boat
      ↓
Select Add-on Services
      ↓
Select Souvenirs
      ↓
Confirm & Reserve
