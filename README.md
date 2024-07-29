# Challenge-TL-Backend

Welcome 

This is my internal mock, here you will be able to gather information and use it for the front-end

## Tech Stack

For this project I decided to use the next stack:

- Spring boot
- H2 memory the database (Be Careful you can lose the information if reload the app)
- JPA

# How to execute the project

1. Download de project
2. First build the project with `mvn clean install`
3. To run it use: `java -jar target/challenge-tl-backend-0.0.1-SNAPSHOT.jar`

# Data available for test
There are 11 products already populated into the database
Also you have 3 users, for your tests, 1 admin 2 normal users:

| User     | Email                  | Password | isAdmin |
|----------|------------------------|----------|---------|
| Admin    | admin@payu.com         | admin    | true    |
| DonPepe  | pepito.perez@payu.com  | 123456   | false   |
| DxMortem | diego.borrero@payu.com | 123456   | false   |

