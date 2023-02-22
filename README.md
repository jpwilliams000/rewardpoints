Datasource:
    H2 Embedded Database

Database web GUI Access:
    http://localhost:8080/h2-console
    Driver - org.h2.Driver
    JDBC URL - jdbc:h2:mem:dataSetDB
    Username - embeddedDB
    Password - embeddedPassword

Unit Tests:
    Minor coverage, can be easily expanded upon due to small testable java method style.

Performance:
    Hashmap o(1)

API:
    GET http://localhost:8080/customer/points

Sample Response:

