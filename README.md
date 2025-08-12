# Quiz Example using Spring Boot - Microservice

## Overview
A Quiz App Microservice Architecture is usually divided into several small services, where each service has a specific responsibility, and they communicate with each other.
Here’s an overview — suppose your Quiz App has three main services:

#### 1. Question Service
Purpose: Manage quiz questions.

##### Main Responsibilities:
- Create questions
- View question list (Read)
- Update questions
- Delete questions
- Calculate scores
  
##### Endpoints Example:
- GET http://localhost:8080/question/allQuestions
- POST http://localhost:8080/question/add
  ```bash
   {
        "questionTitle": "Which keyword is used to create a subclass in Java test 1?",
        "option1": "class",
        "option2": "interface",
        "option3": "extends",
        "option4": "implements",
        "rightAnswer": "extends",
        "difficultylevel": "Easy",
        "category": "JAVA"
    }
   ```
- GET http://localhost:8080/question/category/Java
- GET http://localhost:8080/question/generate?categoryName=Java&numQuestions=6
- GET http://localhost:8080/question/getQuestions
- POST http://localhost:8080//question/getScore
 ```bash
   [
    {
        "id" : 7,
        "response" : "do-while loop"
    },
    {
        "id" : 4,
        "response" : "throw"
    }
]
   ```

#### 2. Quiz Service

##### Purpose: Create quizzes and handle quiz playing logic.

##### Main Responsibilities:
- Fetch questions from the Question Service (via OpenFeign or RestTemplate)
- Save quiz data
- Accept user submissions

#####Endpoints Example:
- POST http://localhost:8090/quiz/create
 ```bash
  {
    "categoryName" : "Java",
    "numQuestions" : 6,
    "title" : "Java Test 3"

}
   ```
- GET http://localhost:8090/quiz/get/1
- POST http://localhost:8090/quiz/submit/1
  ```bash
  [
      {
          "id" : 5,
          "response" : "do-while loop"
      },
      {
          "id" : 10,
          "response" : "throw"
      },
  ]
   ```

#### 3. API Gateway Service

#### Purpose: Route all requests from a single entry point.

#### Main Responsibilities:
- Forward user requests to the correct microservice
- Handle load balancing

#### Endpoints Example:
- GET http://localhost:8765/question-service/question/allQuestions
- POST http://localhost:8765/quiz-service/quiz/create
   ```bash
    {
      "categoryName" : "Java",
      "numQuestions" : 1,
      "title" : "Java Test 2"
  }
  ```
- GET http://localhost:8765/quiz-service/quiz/get/1
- POST http://localhost:8765/quiz-service/quiz/submit/1
     ```bash
    [
    {
        "id" : 5,
        "response" : "do-while loop"
    },
    {
        "id" : 10,
        "response" : "throw"
    },
  ]
  ```
<img width="611" height="403" alt="Screen Shot 2025-08-12 at 5 43 23 PM" src="https://github.com/user-attachments/assets/34817deb-96c7-4da0-9be8-6b775500987d" />


#### 4. Service Discovery (Eureka Server)

#### Purpose: Manage service locations (dynamic routing without hardcoding URLs).

#### Flow:
- Each microservice registers itself with Eureka
- Gateway routes requests using the service name (e.g., QUIZ-SERVICE, QUESTION-SERVICE, API-GATEWAY)
- <img width="1430" height="577" alt="Screen Shot 2025-08-12 at 9 09 53 PM" src="https://github.com/user-attachments/assets/48949f9c-e8da-4c9e-bb01-7151ec7abadd" />

#### 5. How They Work Together
- The user sends a request via API Gateway.
- The Gateway routes the request to the correct service using the service name.
- The Quiz Service, when it needs questions, calls the Question Service using Feign Client.
- All responses go back to the user through the Gateway.

## Dependency
- [Spring Web]
- [Lombok]
- [DevTools]
- [Spring Data JPA]
- [PostgreSQL Driver]
- [Eureka Server]
- [Eureka Discovery Client]
- [OpenFeign] (#Declarative-HTTP-Client, Load Balancing Support, Service-to-Service Communication)
