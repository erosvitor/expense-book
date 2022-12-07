# About
Project created in the course API RESTful with Kotlin.

## Requirements
* Java 17.x.x
* Maven 3.8.5
* MySQL 8.x.x

## Steps to Setup
1. Create the database
```
CREATE DATABASE expenses;

USE expenses;

CREATE TABLE expenses (
  id INTEGER NOT NULL AUTO_INCREMENT,
  description VARCHAR(100) NOT NULL,
  value DECIMAL(11,2) NOT NULL,
  paid_at DATE NOT NULL,
  PRIMARY KEY (id)
);
```

2. Clone the application
```
git clone ....
```

3. Build the project
```
mvn package
```

4. Run the project
```
java -jar expense-book-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Using the project

Run the Postman and create the following requests

1. Insert a expense
```
POST http://localhost:8080/expense

{
    "description": "Luz",
    "value": 147.80,
    "paid_at": "2022-11-10T00:00:00Z"
}
```

2. Get all expenses
```
GET http://localhost:8080/expense
```

3. Update expense data
```
PUT http://localhost:8080/expense

{
    "id": 1,
    "description": "Luz",
    "value": 125.00,
    "paid_at": "2022-11-10T00:00:00Z"
}
```

4. Delete expense
```
DELETE http://localhost:8080/expense
```

## License
This project is under license from MIT. For more details, see the LICENSE file.
