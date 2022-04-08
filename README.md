# User management : Spring Boot, PostgreSQL, JPA, Rest API

Build Restful Add, Update, Read API for a user management system using Spring Boot, PostgreSQL and JPA.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/faisalGhozzi/user_management_server.git
```

**2. Create PosgreSQL database**
```bash
CREATE DATABASE test
```

**3. Change PostgreSQL username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app**

The app will start running at <http://localhost:8080>

It is also possible to run the app using the jar file
```bash
java -jar .\target\user_management-0.0.1-SNAPSHOT.jar
```

## Explore Rest APIs

The app defines following CRUD APIs.

### Users

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/v1/users | Get all users | |
| GET    | /api/v1/users/{id} | Get user profile by id | |
| POST   | /api/v1/users | Add user | [JSON](#usercreate) |
| PUT    | /api/users/{id} | Update user | [JSON](#userupdate) |

## Sample Valid JSON Request Bodys

##### <a id="usercreate">Create User -> /api/v1/users</a>
```json
{
	"firstName": "John",
	"lastName": "Cooper",
	"email": "john.cooper@gmail.com",
    "birthday": "1956-03-19",
    "createdAt": "2022-04-08",
}
```

##### <a id="userupdate">Update User -> /api/v1/users/{id}</a>
```json
{
	"firstName": "John",
	"lastName": "Cooper",
	"email": "john.cooper@gmail.com",
    "birthday": "1956-03-19",
}
```

