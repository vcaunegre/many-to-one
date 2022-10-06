# Many to One

Repository for a demo of a many to one relation in Spring Data JPA, using an H2 database and Spring Web Rest API

# How to install

- Clone the repo in your folder
- Open the project through yout IDE like Eclipe, Intellij or VS Code, and run it

# Owner requests

- GET http://localhost:8080/api/owners

- GET http://localhost:8080/api/owners/1

- POST http://localhost:8080/api/owners HTTP/1.1
  content-Type: application/json

{
"name": "John Doe",
"age": "22",
"gender": "M"
}

- DELETE http://localhost:8080/api/owners/1 HTTP/1.1

# Property requests

- GET http://localhost:8080/api/owners/1/properties

- POST http://localhost:8080/api/owners/1/properties HTTP/1.1
  content-Type: application/json

{
"street": "12 zero street",
"city": "Newtown",
"country":"USA",
"price": 123456
}

- DELETE http://localhost:8080/api/owners/1/properties
