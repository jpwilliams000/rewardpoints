<p><strong>Datasource:</strong><br />
&nbsp; &nbsp; H2 Embedded Database</p>

<p><strong>Database web GUI Access:</strong><br />
&nbsp; &nbsp; http://localhost:8080/h2-console<br />
&nbsp; &nbsp; Driver - org.h2.Driver<br />
&nbsp; &nbsp; JDBC URL - jdbc:h2:mem:dataSetDB<br />
&nbsp; &nbsp; Username - embeddedDB<br />
&nbsp; &nbsp; Password - embeddedPassword</p>

<p><strong>Mock Data</strong><br />
&nbsp; &nbsp; 16 records are located within data.sql file. 

** Optionally, thousands of more records can be generated via the RandomDataGenerator class. Just change the local variable 'number of records' and 'number of customers' to any number desired../>

![](src/main/resources/static/documentationImages/embeddedDBPhoto.png)

<p><strong>Unit Tests:</strong><br />
&nbsp; &nbsp; Currently 80%, can be expanded upon/p>

<p><strong>Performance:</strong><br />
&nbsp; &nbsp; Hashmap o(1)</p>

<p><strong>API Endpoints:</strong><br />
&nbsp; &nbsp; GET http://localhost:8080/customer/points</p>

<p><strong>Sample Response:</strong></p>

No month is indicated if no transactions occured in that month. The points are indicated as 0 if at least one transaction occured, but resulted in no points post summation of monthly total (i.e. purchase of less than <51$ purchase in a month)

![](src/main/resources/static/documentationImages/sampleResponse.png)

