# Shorten URL Service

## Overview
This is a URL Shortener service built using Java Spring Boot, PostgreSQL, and Redis. It provides RESTful APIs for shortening URLs, retrieving the original URL, and tracking click statistics. The service also implements caching using Redis and supports scheduled cache updates via a cron job.

## Tech Stack
- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **Caching:** Redis
- **Build Tool:** Gradle
- **Scheduling:** Spring Scheduler (Cron Jobs)

## Features
- Generate short URLs for given original URLs.
- Retrieve the original URL from a short URL.
- Track the number of clicks on a short URL.
- Cache URL data using Redis.
- Automatically refresh cache at scheduled intervals.
- Handle application shutdown by evicting cache.

## APIs

### 1. Shorten a URL
**Endpoint:** `POST /api/shortenurl`
**Request Body:**
```json
{
  "originalURL": "https://example.com"
}
```
**Response:**
```json
{
  "status": "201 CREATED",
  "message": "Short URL created"
}
```

### 2. Redirect to Original URL
**Endpoint:** `GET /api/{shortURL}`
**Response:** Redirects to the original URL.

### 3. Get All Short URLs
**Endpoint:** `GET /api/shortURLs`
**Response:**
```json
[
  {
    "id": "b0c1d458-f2fb-4bae-aca1-9de34c18dbcf",
    "clicks": 8,
    "createdDateTime": "2024-02-28T21:53:43.759056",
    "updatedDateTime": "2025-02-28T14:09:40.912934",
    "shortURL": "mGNRMcnl4r",
    "originalURL": "https://medium.com/edureka/random-number-and-string-generator-in-java-9c8d8332728f"
  }
]
```

## Caching with Redis
- `@Cacheable("urls")` caches the list of URLs.
- `@Scheduled(cron = "0 0 * * * ?")` updates the cache every hour.
- `@CacheEvict(value = "urls", allEntries = true)` clears the cache.

## Exception Handling
The application has a global exception handler (`GlobalException`) that manages errors like:
- `ShortURLNotFoundException` (404)
- `DuplicateShortURLException` (400)
- Generic exceptions (500)

## Application Shutdown Handling
The `ApplicationShutDownEvent` listener evicts cache when the application stops to prevent stale data.

## Database Schema (PostgreSQL)
```sql
CREATE TABLE shortenurl (
    id UUID PRIMARY KEY,
    clicks BIGINT DEFAULT 0,
    created_date_time TIMESTAMP,
    updated_date_time TIMESTAMP,
    short_url VARCHAR(255) UNIQUE,
    original_url VARCHAR(255)
);
```

## Running the Application
1. Clone the repository.
2. Configure PostgreSQL and Redis in `application.properties`.
3. Build using `gradle build`.
4. Run with `java -jar build/libs/shortenurl-0.0.1-SNAPSHOT.jar`.

## Conclusion
This Spring Boot URL Shortener efficiently manages URLs, caches frequently accessed data using Redis, and updates cache on schedule. The application is robust, with exception handling and graceful shutdown mechanisms.

