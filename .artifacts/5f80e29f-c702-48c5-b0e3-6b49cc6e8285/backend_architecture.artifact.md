# ResQ247 (Cứu Hộ 247) - Backend Architecture & API Blueprint

This document outlines the complete Backend (BE) system architecture, database schema, and REST API specification required to power the full ResQ247 ecosystem (Customer App, Provider/Technician App, and Dispatch Admin Dashboard).

---

## 1. Technology Stack Recommendation

- **Runtime / Framework:** Node.js with Express (or Spring Boot / FastAPI)
- **Database:** PostgreSQL + PostGIS (Best for geospatial queries like finding nearby rescue teams within a 5km radius) or MongoDB.
- **Real-time Communication:** Socket.io (WebSockets) for live technician GPS tracking and status updates.
- **Authentication:** JWT (JSON Web Tokens) with phone number OTP verification.
- **Payment Gateways:** MoMo SDK, VNPay Sandbox, Stripe / Apple Pay / Google Pay.

---

## 2. Database Schema (Entities)

### Users (Customers & Technicians)
- `_id` (ObjectId / UUID)
- `phoneNumber` (String, Unique)
- `fullName` (String)
- `role` (Enum: `CUSTOMER`, `PROVIDER`, `ADMIN`)
- `avatarUrl` (String)
- `fcmToken` (String - for Push Notifications)
- `createdAt` (Timestamp)

### Providers (Rescue Teams / Tow Trucks)
- `_id` (ObjectId / UUID)
- `userId` (Reference to User)
- `name` (String - e.g., "Đội cứu hộ 116 Hà Nội")
- `vehiclePlate` (String - e.g., "29C-774.82")
- `vehicleType` (String - e.g., "Xe sàn trượt Isuzu")
- `rating` (Float, e.g., 4.9)
- `ratingCount` (Integer)
- `isVerified` (Boolean)
- `isAvailable` (Boolean)
- `location` (GeoJSON Point: `[longitude, latitude]`)

### Requests (Rescue Tickets)
- `_id` (ObjectId / UUID)
- `requestId` (String - e.g., "#RQ-8842")
- `customerId` (Reference to User)
- `providerId` (Reference to Provider, Optional initially)
- `vehicleType` (String: Ô tô, Xe máy, Bán tải, Xe điện)
- `plateNumber` (String)
- `issueTypes` (Array of Strings: Nổ lốp, Hết bình, etc.)
- `pickupLocation` (Object: `address`, `latitude`, `longitude`)
- `status` (Enum: `PENDING`, `ACCEPTED`, `EN_ROUTE`, `ARRIVED`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`)
- `estimatedCost` (Float)
- `actualCost` (Float)
- `createdAt` (Timestamp)

### Payments & Ratings
- **Payments:** `_id`, `requestId`, `amount`, `method` (MOMO, VNPAY, CASH, CARD), `status` (`PENDING`, `SUCCESS`), `transactionId`
- **Ratings:** `_id`, `requestId`, `customerId`, `providerId`, `stars` (1-5), `feedbackTags` (Array), `comment`, `createdAt`

---

## 3. REST API Endpoints Specification

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/api/auth/otp/request` | Request Zalo/SMS OTP code for a phone number |
| **POST** | `/api/auth/otp/verify` | Verify OTP code, return JWT token & user profile |
| **POST** | `/api/requests` | Create a new roadside assistance request |
| **GET** | `/api/requests/{id}` | Get request status & assigned technician details |
| **PUT** | `/api/requests/{id}/cancel` | Cancel an active rescue request |
| **GET** | `/api/users/{userId}/requests` | Get customer's rescue history |
| **GET** | `/api/providers/nearby` | Find available rescue teams (`lat`, `lng`, `vehicleType`) |
| **PUT** | `/api/requests/{id}/provider` | Assign selected rescue provider to a request |
| **POST** | `/api/payments` | Create payment transaction (MoMo/VNPay/Cash) |
| **PUT** | `/api/payments/{id}/confirm` | Confirm payment success & issue electronic invoice |
| **POST** | `/api/ratings` | Submit 5-star rating and feedback tags |

---

## 4. Real-time WebSocket Events (`/ws/tracking`)

- `client:join_room` (roomId: requestId) -> Connect customer and assigned technician.
- `provider:update_location` -> Technician sends live `(lat, lng)` and ETA.
- `server:broadcast_tracking` -> Broadcast technician's live location and stage (`ACCEPTED` ➔ `EN_ROUTE` ➔ `ARRIVED` ➔ `IN_PROGRESS` ➔ `COMPLETED`) to the customer app in real time.
