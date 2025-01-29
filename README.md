# webshop-backend

## How to Run the Backend Locally for Testing:
1 - Install Docker: https://docs.docker.com/desktop/setup/install/windows-install/

2 - Run the Backend with Docker Compose:

    docker-compose up -d

## URLS
### Product Management (/api/products)
- Create Product: POST http://localhost:8080/api/products
- Get All Products: GET http://localhost:8080/api/products
- Get Product by ID (e.g., with ID 1): GET http://localhost:8080/api/products/1 
- Update Product by ID (e.g., with ID 1): PUT http://localhost:8080/api/products/1
- Process Payment: POST http://localhost:8080/api/payments/process

### Payment Management (/api/payments)
- Process Payment: POST http://localhost:8080/api/payments/process
