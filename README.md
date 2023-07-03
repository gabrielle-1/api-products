# API Products

API Products is a RESTful API for managing products. It provides endpoints to create, retrieve, update, and delete products.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

To get started with the API, follow the instructions below.

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL (or any other supported relational database)

### Installation

1. Clone the repository:

   ```shell
   git clone https://github.com/gabrielle-1/api-products.git

2. Navigate to the project directory:

   ```shell
   cd api-products
   
3. Build the project using Maven:

   ```shell
   mvn clean install
   
4. Configure the database connection by modifying the application.properties file:

   ```shell
   spring.datasource.url=jdbc:mysql://localhost:3306/products_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
5. Run the application:

   ```shell
   mvn spring-boot:run


By default, the API will be available at http://localhost:8080.


### Usage

Once the application is up and running, you can use any HTTP client to interact with the API. Below are some examples using cURL:

- Create a new product:
  - ```shell
    curl -X POST -H "Content-Type: application/json" -d '{"name":"Product Name","value":9.99}' http://localhost:8080/products
- Retrieve a specific product:
  - ```shell
    curl http://localhost:8080/products/{product_id}
- Update an existing product:
  - ```shell
    curl -X PUT -H "Content-Type: application/json" -d '{"name":"Updated Product Name","value":19.99}' http://localhost:8080/products/{product_id}
- Delete a product:
  - ```shell
    curl -X DELETE http://localhost:8080/products/{product_id}


For detailed information about the available endpoints and request/response formats, refer to the API Documentation section.


### API Documentation
Its in progress.
The API documentation is automatically generated using Swagger. Once the application is running, you can access the Swagger UI by visiting http://localhost:8080/swagger-ui/index.html#/ in your browser. It provides an interactive interface to explore and test the API endpoints.

### Contributing
Contributions to this project are welcome. To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make the necessary modifications.
4. Commit your changes and push the branch.
5. Submit a pull request explaining your changes.

### License

This project is licensed under the MIT License. See the LICENSE file for details.

```css
This Markdown-formatted README includes the table of contents and headings for each section, making it easier to navigate and read the document. Feel free to use and modify this template according to your project's needs.
