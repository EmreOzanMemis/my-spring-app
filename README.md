# My Spring App

A Spring Boot + Thymeleaf web application with an interactive in-memory task management demo.

---

## 🚀 How to Run Locally

### Prerequisites
- Java 8+
- Maven 3.6+ (or use the included Maven wrapper)

### Start the app

```bash
./mvnw spring-boot:run
```

Then open your browser at [http://localhost:8080](http://localhost:8080).

### Run the tests

```bash
./mvnw test
```

---

## ✅ Task Management Features

| Feature | Endpoint | Description |
|---------|----------|-------------|
| View tasks | `GET /tasks` | Lists all in-memory tasks |
| Add a task | `POST /tasks/add` | Submits a new task via form |
| Filter tasks | `GET /tasks?keyword=foo` | Case-insensitive keyword search |

### How it works
- Tasks are stored in a simple **in-memory `ArrayList`** — no database required.
- The task list resets when the application restarts.
- Navigate to `/tasks` to add tasks, search/filter them, and see the full list.

---

## 🛠 Tech Stack

| Technology | Version | Notes |
|------------|---------|-------|
| Java | 1.8 | Core language |
| Spring Boot | 2.1.2 | Web framework |
| Thymeleaf | - | Server-side HTML templates |
| Bootstrap | 4.2.1 | Responsive UI |
| Maven | 3.6.3 | Build & dependency management |

---

## Azure & CI/CD Resources

- [Azure Samples](https://github.com/azure-samples?q=java&type=&language=&sort=)
- [Java on App Service Quickstart](https://docs.microsoft.com/en-us/azure/app-service/quickstart-java?tabs=javase&pivots=platform-linux)
- [Java and CosmosDB Tutorial](https://docs.microsoft.com/en-us/azure/app-service/tutorial-java-spring-cosmosdb)
- [Maven plugins](https://docs.microsoft.com/it-it/java/api/overview/azure/maven/azure-webapp-maven-plugin/readme?view=azure-java-stable#quick-start)

---

## Files Changed

| File | Reason |
|------|--------|
| `src/main/java/com/mkyong/controller/TaskController.java` | New controller with GET /tasks (list + filter) and POST /tasks/add endpoints using an in-memory ArrayList |
| `src/main/resources/templates/tasks.html` | New Thymeleaf template showing the task list, add-task form, and keyword filter |
| `src/test/java/com/mkyong/controller/TaskControllerTest.java` | MockMvc tests for view, add, and filter task endpoints |
| `README.md` | Added run instructions and task management feature documentation |
