# Dynamic Query Service  

This project provides a backend service for dynamically filtering and paginating query results based on a predefined base query. The frontend only sends filter conditions without knowledge of the base query structure, ensuring security and flexibility.  

## Features  
- Dynamic filtering (`WHERE`, `GROUP BY`, `ORDER BY`, `HAVING`)  
- Pagination support  
- Secure query execution  
- Uses `EntityManager` for native queries  

## Technologies  
- **Spring Boot**  
- **JPA / Hibernate**  
- **EntityManager** for dynamic query execution  

## Usage  
1. Define a base query on the backend.  
2. Apply filters, sorting, and pagination dynamically.  
3. Execute the query using `EntityManager`.  

## Setup  
1. Clone the repository  
2. Configure database settings in `application.properties`  
3. Run the Spring Boot application  

## Example API Request  
```json
{
  "filters": [
    { "column": "p.name", "operator": "LIKE", "value": "%John%" },
    { "column": "j.job_name", "operator": "=", "value": "Engineer" }
  ],
  "orderBy": "p.name DESC",
  "page": 1,
  "size": 10
}
