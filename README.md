# 🌥️ Spring Cloud Central Config — Task & Email Microservices

This project demonstrates a centralized configuration architecture using a Spring Cloud Config Server and two microservices: **Task Service** and **Email Service**. All configuration values are dynamically loaded from the Config Server using `spring.config.import`.

---

## 🧭 Overview

| Component      | Description                                                       |
|----------------|-------------------------------------------------------------------|
| 🗄️ Config Server | Serves profile-based configs from `classpath:/config`             |
| 📌 Task Service  | Creates & lists tasks, uses `task.max-tasks` from config         |
| ✉️ Email Service | Simulates email sending, uses `email.max-retries` from config   |

Each microservice supports runtime refresh via `@RefreshScope` + `/actuator/refresh`.

---

## 🎯 Requirements & Implementation

| Requirement                                                  | Status |
|--------------------------------------------------------------|--------|
| Spring Cloud Config Server in *native mode*                  | ✅ |
| Profile-based config files (`prod`, `test`)                  | ✅ |
| Microservices load config via `spring.config.import`         | ✅ |
| Config-based task limit                                      | ✅ |
| Config-based retry count                                     | ✅ |
| Runtime refresh (`@RefreshScope`, Actuator)                  | ✅ |
| Docker Compose orchestration                                 | ✅ |

---

## 🧱 Tech Stack

| Category      | Tools                                 |
|---------------|----------------------------------------|
| Framework     | Spring Boot 3, Spring Cloud Config     |
| Language      | Java                                   |
| REST          | Spring Web                             |
| Config Client | `spring-cloud-starter-config`          |
| Actuator      | Health & refresh endpoints             |
| Containers    | Docker, Docker Compose                 |

---

## 🗄️ Config Server

| Property        | Value                                        |
|-----------------|----------------------------------------------|
| Port            | `8888`                                       |
| Mode            | Native                                       |
| Search location | `classpath:/config`                          |
| Profiles        | `prod`, `test`                               |
| Files           | `task-service-prod.yml`, `task-service-test.yml`<br>`email-service-prod.yml`, `email-service-test.yml` |

---

## 📌 Task Service

| Feature             | Details                                      |
|---------------------|----------------------------------------------|
| Port                | From config (`8081`)                         |
| Config name         | `task-service`                               |
| Config loading      | `spring.config.import`                       |
| Runtime refresh     | `@RefreshScope`                              |
| Business rule       | Max tasks = `task.max-tasks` (from config)   |
| Endpoints           | `POST /tasks`, `GET /tasks`                  |

---

## ✉️ Email Service

| Feature             | Details                                      |
|---------------------|----------------------------------------------|
| Port                | From config (`8082`)                         |
| Config name         | `email-service`                              |
| Config loading      | `spring.config.import`                       |
| Runtime refresh     | `@RefreshScope`                              |
| Business rule       | Retry count = `email.max-retries` (from config) |
| Endpoint            | `POST /emails/send`                          |

---

## 🔁 Runtime Refresh

POST /actuator/refresh

Both services update configuration **without restart** thanks to `@RefreshScope`.

---

## 🐳 Docker Compose

To run all services:

docker-compose up --build

Environment variables used:

Environment variables used:
SPRING_PROFILES_ACTIVE=test
SPRING_CONFIG_IMPORT=optional:configserver:http://config-server:8888


Service URLs:

| Service         | URL                      |
|-----------------|--------------------------|
| Config Server   | http://localhost:8888    |
| Task Service    | http://localhost:8081    |
| Email Service   | http://localhost:8082    |

---

## 👤Made with by **Xadija Pashayeva**
 
📧 xadijapashayeva@gmail.com  

🔗 LinkedIn: https://www.linkedin.com/in/xadija-pashayeva


Enabled via:

