# My Spring App

A simple Spring Boot + Thymeleaf web application with an interactive in-memory task manager, deployable to Azure App Service via GitHub Actions.

## How to Run Locally

**Prerequisites:** Java 8+, Maven 3.6+

```bash
# Clone the repository
git clone https://github.com/EmreOzanMemis/my-spring-app.git
cd my-spring-app

# Build and run
mvn spring-boot:run
```

Then open your browser at **http://localhost:8080**.

To run the tests:

```bash
mvn test
```

## Task Management Features

- **View tasks** — the home page (`/`) displays all tasks as a list.
- **Add a task** — type a task in the form at the top and click **Add Task**. The task is stored in memory for the lifetime of the app process.
- **Filter tasks** — type a keyword in the filter box and click **Filter** to narrow the list. Click **Clear** to show all tasks again.

> **Note:** Tasks are stored in memory only — they reset when the application restarts.

## Additional Resources

Trying to get started with Java on Azure? See these resources:

- [Azure Samples](https://github.com/azure-samples?q=java&type=&language=&sort=)
- [Java on App Service Quickstart](https://docs.microsoft.com/en-us/azure/app-service/quickstart-java?tabs=javase&pivots=platform-linux)
- [Java and CosmosDB Tutorial](https://docs.microsoft.com/en-us/azure/app-service/tutorial-java-spring-cosmosdb)
- [Maven plugins](https://docs.microsoft.com/it-it/java/api/overview/azure/maven/azure-webapp-maven-plugin/readme?view=azure-java-stable#quick-start)
