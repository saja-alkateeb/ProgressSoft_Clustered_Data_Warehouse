# Progresssoft

## SpringBoot 2.7.13-SNAPSHOT, Hibernate 5, Posgresql 15, and java 8

### Clustered Data Warehouse

Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB.


#### Request logic as following:

 - Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
 - Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
 - System should not import same request twice.
 - No rollback allowed, what every rows imported should be saved in DB.

#### Deliverables should be ready to work including:

 - Use Actual Db, you can select between (Postgres, MySql or MongoDB)
 - Workable deployment including sample file (Docker Compose).
 - Maven or Gradle project is required for full source code.
 - Proper error/exception handling.
 - Proper Logging.
 - Proper unit testing with respected Coverage.
 - Proper documentation using md.
 - Delivered over Github.com.
 - Makefile to streamline running application (plus).

##After you clone this project from github, run
1. Open cmd and follow the steps: 

- clean and install mvn project
```
    mvn clean install
```

- run docker compose

```
    docker-compose up
```
2. You can test the project using postman

 - Test Happy Scenario 
 ```
    URL: http://localhost:8080/api/v1/fx-deal
    HTTP request type : POST
    content-type: application/json
    body: 
    [
        {"dealId": 100, "fromCurrencyIsoCode": "USD", "toCurrencyIsoCode": "JOD", "dealTimeStamp": "2023-06-07T21:00:00.000Z", "dealAmountInOrderingCurrency" : 175.15},
        {"dealId": 102, "fromCurrencyIsoCode": "JOD", "toCurrencyIsoCode": "USD", "dealTimeStamp": "2023-06-07T21:00:00.000Z", "dealAmountInOrderingCurrency" : 152.55},
        {"dealId": 103, "fromCurrencyIsoCode": "ERU", "toCurrencyIsoCode": "JOD", "dealTimeStamp": "2023-06-07T21:00:00.000Z", "dealAmountInOrderingCurrency" : 136.21},
        {"dealId": 104, "fromCurrencyIsoCode": "ERU", "toCurrencyIsoCode": "USD", "dealTimeStamp": "2023-06-07T21:00:00.000Z", "dealAmountInOrderingCurrency" : 1005.39}
    ] 
    
    It will respond with 200 status
```

- Test Bad Scenario 

 ```
    URL: http://localhost:8080/api/v1/fx-deal
    HTTP request type : POST
    content-type: application/json
    body: 
        [
            {"dealId": 100, "fromCurrencyIsoCode": "USD", "toCurrencyIsoCode": "JOD", "dealTimeStamp": "2023-06-07T21:00:00.000Z", "dealAmountInOrderingCurrency" : 175.15},
            {"dealId": 105, "fromCurrencyIsoCode": "JODX", "toCurrencyIsoCode": "1USD", "dealTimeStamp": "2023-06-07T21:00:00.000Z", "dealAmountInOrderingCurrency" : 152.55}
        ]
    It will respond with 400 bad request
    {
    "errors": [
        {
            "message": "must match \"^[a-zA-Z]{3}$\"",
            "path": "postFxDeal.fxDealsRequestDetails[1].fromCurrencyIsoCode",
            "value": "JODX"
        },
        {
            "message": "must match \"^[a-zA-Z]{3}$\"",
            "path": "postFxDeal.fxDealsRequestDetails[1].toCurrencyIsoCode",
            "value": "1USD"
        }
    ]
}
```# Clustered-Data-Warehouse
# Clustered-Data-Warehouse
# Clustered-Data-Warehouse
