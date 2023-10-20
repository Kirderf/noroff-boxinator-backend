# Boxinator - Scandinavian Themed Box Management System

Boxinator is a backend RESTful service focused on the management of Scandinavian-themed product boxes and user operations. Users can choose from a variety of themed boxes, each offering unique Scandinavian products.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [API Documentation](#api-documentation)
- [Setup & Installation](#setup--installation)
- [Role-Based Access Control (RBAC)](#role-based-access-control-rbac)
- [Contributing](#contributing)
- [License](#license)

## Features

- Comprehensive **User Management**: CRUD operations for users, complete with error handling mechanisms.
- **Product Management**: Detailed product information including stock levels, price, and more.
- **Shipment Management**: Monitor and manage shipments and their histories.
- **Box Themes**: Multiple predefined box themes, each with their own set of unique products.
- **Role-Based Access Control**: Utilizing Keycloak, Boxinator ensures secure access to its various functionalities based on roles and permissions.

## Technologies Used

- **Spring Boot**: For rapid application development.
- **JPA with Hibernate**: For ORM and data persistence.
- **Swagger/OpenAPI**: For API documentation and testing.
- **Lombok**: To reduce Java boilerplate code.
- **Jakarta Persistence**: ORM with advanced JPA mappings.
- **Keycloak**: For role-based access control (RBAC) and user authentication.

## API Documentation

Boxinator uses Swagger for API documentation. Once the project is running, navigate to:

```
http://localhost:8080/swagger-ui.html
```

This will provide you with a detailed overview of all available endpoints, request-response structures, and the ability to test endpoints directly from the browser.

## Setup & Installation

1. Clone this repository:

```bash
git clone https://github.com/Kirderf/noroff-boxinator-backend
```

2. Navigate to the project directory:

```bash
cd boxinator
```

3. Install the necessary dependencies using Gradle:

```bash
./gradlew clean build
```

4. Run the application:

```bash
./gradlew bootRun
```

The service will start on port `8080` by default. This can be changed in the `application.properties` file.

## Role-Based Access Control (RBAC)

Boxinator utilizes Keycloak for managing user authentication and role-based access control. To configure RBAC:

1. Set up a Keycloak instance and create a new realm for Boxinator.
2. Define user roles such as `ADMIN`, `USER`, etc.
3. Configure the Boxinator Spring Boot application to integrate with Keycloak. This involves setting the Keycloak server URL, realm, client ID, etc., in the `application.properties` file.
4. Ensure the necessary role checks are defined using the `@PreAuthorize` annotations in the codebase.

For detailed Keycloak setup and integration, refer to [Keycloak's official documentation](https://www.keycloak.org/documentation.html).

## Contributing

Contributions to Boxinator are welcome! Please ensure you follow the standard Pull Request process and clearly describe any changes. All PRs will undergo a review before being merged.

## License

Boxinator is licensed under the MIT License. See `LICENSE` for more details.

