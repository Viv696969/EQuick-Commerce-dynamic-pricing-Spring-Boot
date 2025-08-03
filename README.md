# 🧠 Region-Based Dynamic Price Optimization Microservice

Ever wondered how Blinkit, Zepto, or Zomato optimize dynamic pricing in real-time across cities and neighborhoods?

This microservice is a high-performance, region-aware pricing engine designed to adjust product prices in real-time based on demand, region, and behavioral signals. Built with **Spring Boot**, **DragonflyDB**, and **PostgreSQL**.

---

## 🚀 Features

- ⚡ **Real-Time Dynamic Pricing**  
  Adjust prices instantly based on demand patterns and user activity.

- 🧠 **Sigmoid-based Pricing Logic**  
  Smooth, bounded price adjustments that prevent extreme pricing shifts.

- 🌆 **Region-Specific Demand Tracking**  
  Unique keys based on `product name + pincode + city` for hyperlocal demand intelligence.

- ⏰ **Eating Hour Boosting**  
  Boosts prices automatically during key consumption windows (e.g., lunch/dinner).

- 🔁 **Live Demand Update Support**  
  System responds in real-time to changes in demand, views, or conversions.

- 🛒 **Add to Cart Functionality**  
  Tracks product-level intent and contributes to demandFactor for pricing adjustments.

- 💰 **Purchasing Functionality**  
  Finalized purchases trigger backend events that increase demand and update pricing accordingly.

- 📄 **Pagination for Product Retrieval**  
  Supports efficient paging when fetching large product lists with attached demand-based pricing data.
- **Jwt Authentication using filters** (Not Fully Implemented at Controller side).


---

## 🛠️ Tech Stack

| Layer       | Tech Used     | Purpose                                  |
|-------------|----------------|------------------------------------------|
| Backend     | Spring Boot    | Core REST APIs and pricing logic         |
| Cache       | DragonflyDB    | In-memory ultra-fast demand storage      |
| Database    | PostgreSQL     | Durable, structured storage              |
| API Testing | Postman | Interactive API testing and documentation|

---

## 📊 Pricing Formula (Simplified)

The dynamic price for a product is calculated based on multiple real-time signals like demand, peak hours, and eating time sensitivity. The Demand is stored in cache when a user clicks to view product , adds product to cart,purchases product.

### 🧮 Formula:
price = base_price + sigmoid(demandFactor) + peakTimeFactor + eatingTimeFactor
### 📈 Explanation:

- ✅ `sigmoid(demandFactor)`  
  Smoothly scales the price based on real-time product demand (views, orders, etc.), keeping the adjustment bounded.

- 🕒 `peakTimeFactor`  
  Adds an extra price boost during peak hours (e.g., weekends, evening rush).

- 🍽️ `eatingTimeFactor`  
  Targets lunch/dinner timings for food-related products or services.

## 📐 Architecture Overview
<p align="center">
  <img src="https://github.com/Viv696969/EQuick-Commerce-dynamic-pricing-Spring-Boot/blob/main/algorithm-design.gif?raw=true"  alt="gif"/>
</p>

## 📦 How to Run

### 🖥️ Prerequisites

Make sure the following are installed and configured on your system:

- ✅ **Java 17+**
- ✅ **Maven**
- ✅ **PostgreSQL** (with a database created for the project)
- ✅ **DragonflyDB** running locally or via Docker

---

### 🏁 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/dynamic-pricing-microservice.git
   cd dynamic-pricing-microservice


2. **Configure application.properties**
    ```java
    # database config
    spring.datasource.url=jdbc:postgresql://localhost:5432/<Your Db Name>
    spring.datasource.username=<Your username>
    spring.datasource.password=<Your password>
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.format_sql=true
    logging.level.org.springframework.security=DEBUG
    # Redis configs
    spring.redis.host=localhost
    spring.redis.port=6379
    spring.redis.timeout=2000
    spring.redis.client-type=lettuce
   
3. **Run the Application**
    1. Either using IntelliJ or
    2. ```bash 
       ./mvnw spring-boot:run

4. Clicke [here](https://github.com/Viv696969/EQuick-Commerce-dynamic-pricing-Spring-Boot/blob/main/demand%20price%20optimization.postman_collection.json) to View and Downlaod Postman Collection To test the apis.

### 🔗 Connect With Me On
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/vivek-chouhan/)

