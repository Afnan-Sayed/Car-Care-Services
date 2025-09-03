# Car Care Services

## Project Overview
**Car Care Services (CCS)** is a backend platform for on-demand mobile car care solutions, including car wash, tire replacement, towing, and battery services. It connects customers needing assistance with verified providers who deliver services at the customer's location.  

The system supports three main roles:
- **Customers**: Manage cars, request services, track orders, and provide feedback.  
- **Providers**: Manage profiles, accept requests, and update service status.  
- **Admins**: Manage providers, customers, services, pricing, and bookings.  

Built with a modular **Java Spring Boot** architecture, CCS ensures:
- Clean separation of concerns  
- Robust functionality  
- Secure access control  

It leverages **RESTful APIs**, **role-based security (JWT)**, and **database persistence** for a seamless experience.  

---

## Team Contributions

| Member   | Responsibilities |
|----------|------------------|
| [**Afnan**](https://github.com/Afnan-Sayed) | Developed **Admin Management** (provider approval/rejection, enable/disable users), and implemented **Reset Password**, **hardcoded Admins**, plus overall **project initialization and structure**. |
| [**Maya**](https://github.com/mayafouad) | Contributed to **Admin Management** (admin profiles, service prices), **Service Management** (CRUD operations), **Payment Management**, and **Customer Signup**. |
| [**Noureldin**](https://github.com/NORO-01) | Responsible for **Orders Management** (create, update, cancel orders, history, nearby providers, reviews). |
| [**Ahmed**](https://github.com/asabry18) | Worked on **User Management** (admin manage user profiles), **Customer Management** (cars, customer details), and **Settings & Enquiries** modules. |
| [**Kareem**](https://github.com/kareeem74) | Implemented **Provider Management** (provider profile, provider services), **Notifications**, and also handled **Provider Signup**. |

---

## Features

### 1. Authentication & User Management
- **Signup**: For customers (username, password, email, phone) and providers (location & national ID image)  
- **Login**: Secure authentication with email and password  
- **Logout**: Invalidate session  
- **Password Reset**: Forgot password, verify token, reset password  
- **Profile Updates**: Edit details like phone or location  

### 2. Customer Features
- Manage Cars: Add/update/remove cars (type, license)  
- Request Services: Select service (wash, tow, etc.) for a car, view cost estimation, submit with location  
- Track Orders: View status, history, and rate/review completed requests  
- Platform Info: View name, logo, about, terms & conditions, social/contact channels  
- Enquiries: Submit enquiries  

### 3. Provider Features
- Registration: Submit details and documents for verification  
- Manage Services: Add services with estimated time  
- Handle Orders: View nearby/incoming requests, accept/reject, update status (Initiated → In Progress → Completed/Canceled)  
- View History: Completed requests and reviews  
- Enquiries: Submit enquiries  

### 4. Admin Features
- Provider Management: Verify documents, approve/reject registrations, enable/disable providers, view pending providers  
- Customer Management: View customers, enable/disable  
- Service Management: CRUD services (name, description, price, car type)  
- Booking Management: View/filter bookings by provider/status  
- Enquiry Management: View/hide enquiries  
- Settings Management: Update platform name, logo, about, terms & conditions, contact info, price per km  

### 5. Payment 
- Add payments (credit/cash), view by ID or customer  

### 6. Security & Data
- Role-Based Access: JWT for customer/provider/admin roles  
- Data Models: Users, Providers, CustomerCars, Services, Requests, Payments, Enquiries, Reviews, Settings  

---

## Bonus Features
- Location-based order matching (nearby providers)  
- Cost estimation including distance (price per km)  
- Review and rating system for providers  
- Single-row settings configuration for platform info  

---

## 🛠️ Tech Stack

| Technology       | Description |
|------------------|-------------|
| Java 21          | Core language |
| Spring Boot      | Application framework |
| Spring Security  | Authentication & authorization (JWT) |
| JPA              | ORM for entities |
| MySQL            | Relational database |
| Maven            | Build & dependency management |
| REST (JAX-RS)    | RESTful APIs |
| Lombok           | Boilerplate reduction |

---

## API Documentation
APIs are documented in the provided Postman collection: **`CCS.postman_collection.json`**.  

### Authentication
- `POST /auth/signup/customer` — Register a new customer  
- `POST /auth/signup/provider` — Register a new provider  
- `POST /auth/login` — Authenticate user  
- `POST /auth/forgot-password` — Initiate password reset  
- `POST /auth/verify-token` — Verify reset token  
- `POST /auth/reset-password` — Reset password  
- `POST /auth/logout` — Logout user  

### Services Management
- `POST /services` — Create service *(Admin only)*  
- `GET /services` — Retrieve all services  
- `PUT /services/{id}` — Update service details/price  
- `DELETE /services/{id}` — Delete a service  

### Admin Management
- `PUT /admins/{id}` — Update admin profile  
- `GET /admins/{id}` — Get admin details  
- `GET /admin/customers` — View all customers  
- `POST /admin/customers/{id}/enable` — Enable customer  
- `POST /admin/customers/{id}/disable` — Disable customer  
- `GET /admin/providers/pending` — View pending providers  
- `PUT /admin/providers/{id}/verify` — Verify provider  
- `POST /admin/providers/{id}/approve` — Approve provider  
- `POST /admin/providers/{id}/reject` — Reject provider  
- `POST /admin/providers/{id}/enable` — Enable provider  
- `POST /admin/providers/{id}/disable` — Disable provider  

### Customer Management
- `PUT /customers/{id}` — Update customer profile  
- `GET /customers/{id}` — Get customer details  
- `GET /customers/{id}/orders` — View orders  
- `POST /customers/{id}/orders` — Create order  
- `PUT /customers/{id}/orders/{orderId}/cancel` — Cancel order  
- `POST /customers/{id}/orders/{orderId}/rate` — Rate/review order  
- `GET /customers/{id}/cars` — View cars  
- `POST /customers/{id}/cars` — Add car  
- `PUT /customers/{id}/cars/{carId}` — Update car  
- `DELETE /customers/{id}/cars/{carId}` — Delete car  

### Provider Management
- `GET /providers/{id}/orders` — View provider orders  
- `GET /providers/{id}/orders/nearby` — View nearby orders  
- `PUT /providers/{id}/orders/{orderId}/accept` — Accept order  
- `PUT /providers/{id}/orders/{orderId}/status/{status}` — Update order status  
- `GET /providers/{id}/reviews` — View provider reviews  
- `GET /providers/{id}/services` — View provider services  
- `POST /providers/{id}/services` — Add service to provider  

### Enquiry Management
- `POST /enquiries` — Submit enquiry  
- `GET /enquiries` — View all enquiries *(Admin only)*  

### Payment Management
- `POST /payments` — Add payment  
- `GET /payments/{id}` — Get payment by ID  
- `GET /payments/customers/{customerId}` — View customer payments  

### Settings Management
- `GET /settings` — View settings  
- `PUT /settings` — Update settings *(Admin only)*  

> 📂 For detailed request bodies and examples, import the Postman collection into **Postman**.  

---

## Installation & Setup

### Prerequisites
- Java JDK 21+  
- Maven 3.8+  
- MySQL 8.0+  
- Git  

### Steps

#### 1. Clone the Repository
```
git clone https://github.com/your-repo/car-care-services.git
cd car-care-services
```
2. Create MySQL Database
```
CREATE DATABASE ccs_db;
```
3. Configure Application Properties
Edit ```src/main/resources/application.properties:```
```
spring.datasource.url=jdbc:mysql://localhost:3306/ccs_db
spring.datasource.username=yourMySQLUsername
spring.datasource.password=yourMySQLPassword
spring.jpa.hibernate.ddl-auto=update
```
# Add JWT secret and other configs as needed
4. Build the Project
``` mvn clean install ```

6. Run the Application
``` mvn spring-boot:run ```

7. Access the App
The backend runs at: ``` http://localhost:8080 ```

8. Test the APIs
Import ``` CCS.postman_collection.json ``` into Postman and use the provided request bodies and parameters.
