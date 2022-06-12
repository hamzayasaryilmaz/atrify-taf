![Logo](https://images.ctfassets.net/ksgcgp3n790m/Qxs9kQAKEtBsxVYdrnQvV/01fc49d368630d475a37d782e254e2b7/93ff12af46fd-AW_PagoNxt_POS_RGB.jpg?w=600&q=50)

# PagoNxt Test Automation Framework

This is a test automation framework written for API tests


---
## Tech Stack

**Tools and Technologies:**
* Java Programming Language
* JAVA JDK 17
* Maven
* TestNG
* Hamcrest
* Rest-Assured

* Intellij IDEA
* H2 Database & SQL
* Allure Report
* Lombok
* JavaFaker for Fake Test Data
* Maven SureFire Plugin
  <br /> <ins>Note: java home and maven home should already be set in environment variables</ins>
---

## How to Run Tests
## 1. Step (Can be skipped, since database JDBC url has suffix 'AUTO_SERVER=TRUE' explaind on second step)
Before running the tests, H2 database should be up and running
* Download the H2 Database [H2 Database Jar File](https://search.maven.org/remotecontent?filepath=com/h2database/h2/2.1.212/h2-2.1.212.jar)
* Navigate to directory where the jar file is located
* Run the following command on command line (Replace * with full name of the jar file)
```bash
  java -cp h2-*.jar org.h2.tools.Server
  (e.g: java -cp h2-2.1.212.jar org.h2.tools.Server)
```
## 2. Step
H2 Console will be automatically opened<br />
Please change the JDBC url to the path where the database will be located<br />
(For this project it is located at: src/main/resources/db/PagoNxtTest.mv.db)
It should be like:
* jdbc:h2:tcp://localhost/C:\Users\{user name}\IdeaProjects\pagonxt-taf\src\main\resources\db/PagoNxtTest;AUTO_SERVER=TRUE<br />
  "AUTO_SERVER=TRUE" is for multiple processes can access the same database without having to start the server manually



## 3. Step
To store the test results, test_result table should already be created before running the tests
<br />Simply copy/paste following sql into the H2 Console on browser and execute

```sql
  CREATE TABLE Test_Result (
    Id int primary key auto_increment,
    name varchar(255),
    status varchar(255),
    update_date DATETIME,
    failure_counter int
  );

```

## 4. Step
Run the tests with executing the following command in terminal where the project is located at
```bash
  mvn clean test
  mvn clean test -P {profile name}
  example: mvn clean test -P dev
```
For running single test plese visit the page: </br>
https://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html

## 5. Step
To be able to see the test results run the allure command as below, it will pop-up browser with the test results
```bash
  mvn allure:serve
```


## APIs Used in Project

#### Get all users

```http
  GET https://reqres.in/api/users?page={pageNumber}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `pageNumber`    | `string` | **Optinal**. Your API key  |

#### Get Single User

```http
  GET https://reqres.in/api/users/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `integer` | **Required**. Id of item to fetch |

#### Create New User

```http
  POST https://reqres.in/api/users
```

###### Post Body for Create User

```json
{
  "name": "morpheus",
  "job": "leader"
}
```

#### Update User

```http
  PUT https://reqres.in/api/users/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `integer` | **Required**. Id of item to fetch |

###### Put/Patch Body for Update User

```json
{
  "name": "morpheus",
  "job": "zion resident"
}
```

#### Delete User

```http
  DELETE https://reqres.in/api/users/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`      | `integer` | **Required**. Id of item to fetch |