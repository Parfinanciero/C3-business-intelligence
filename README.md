# 📊 C3-Business-Intelligence

This repository contains the code for the **BI Dashboard**, a microservice designed to process and display key financial metrics through an interactive web interface. Its primary goal is to help users track their income, expenses, and financial goals with processed data and clear visualizations.

---

## 🛠️ Technologies Used

### Backend:
- **Java Spring Boot**: Framework for building robust web applications.
- **Maven**: Dependency management tool.
- **Java Faker**: Generates mock data for testing purposes.
- **WebClient**: For consuming external APIs.

### Frontend:
- **React**: Library for building user interfaces.
- **Vite**: Fast development environment.
- **Axios**: For making backend API requests.
- **CSS Modules**: Encapsulated styling.

---

## 🌟 Key Features

- **Metrics Visualization**: Displays total income and expenses, predictions, and financial goal tracking.
- **Advanced Calculations**: Processes data from external APIs to generate customized metrics.

---

## 📂 Project Structure

### Backend:
```
src/main/java/com/miempresa/microservicio
├── controller   # REST controllers to expose APIs.
├── service      # Business logic.
├── client       # External API consumption.
├── model        # Data models for processing information.
├── config       # Additional configurations (e.g., WebClient).
```

### Frontend:
```
src/
├── components   # Reusable UI components.
├── pages        # Main system views.
├── index.js    
├── styles       # Encapsulated styles.
├── App.jsx      # Main application configuration.
├── main.jsx     # React entry point.
```

---

## 🔄 Future Integrations

- Connection with real APIs from other microservices.
- Optimization of queries and calculations.

---

## 🤝 Team

| Name                | Role               |
|---------------------|--------------------|
| Alejandro Velasquez | Frontend Developer |
| Julian Jaramillo    | Backend Developer  |
| Narciris Mena        | Backend Developer  |
| Julian Alvarez      | Frontend Developer |

---

## 📜 License

This project is licensed under the **MIT** License. See the [LICENSE](LICENSE) file for details.
