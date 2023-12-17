# Trade Enricher
Service allows to enrich input trades collection with products data.

# Local run
```
mvn clean install
```

Run app.

To verify if app is up and running, send request to healthcheck using: `docs/management_api.http`. 

# Versioning
Based on https://semver.org/

# WIP
Request:
curl --request POST --data @trade.csv --header 'Content-Type: text/csv' --header 'Accept: text/csv'
http://server.com/api/v1/enrich

trade.csv:
date,product_id,currency,price
20160101,1,EUR,10.0
20160101,2,EUR,20.1
20160101,3,EUR,30.34
20160101,11,EUR,35.34

product.csv:
product_id, product_name
1,Treasury Bills Domestic
2,Corporate Bonds Domestic
3,REPO Domestic
4,Interest rate swaps International
5,OTC Index Option
6,Currency Options
7,Reverse Repos International
8,REPO International
9,766A_CORP BD
10,766B_CORP BD

Response:
date,product_name,currency,price
20160101,Treasury Bills Domestic,EUR,10
20160101,Corporate Bonds Domestic,EUR,20.1
20160101,REPO Domestic,EUR,30.34
20160101,Missing Product Name,EUR,35.34

# Ideas
## Architecture
Based on my experience, current solution is similar to ports and adapters approach. Second solution I was thinking about is to use secondary ports (infrastructure folders in domain level) as a separate modules.

Files structure:
com.example.tradeservice.domain

#### user-interface package / ui package
- primary port, entry into my application / domain 

#### infrastructure package
- secondary port, 3rd party systems

## Documentation
- C4, plantUml docs

## Naming convention
After analysis, I would name that service: tradeEnricher / tradeEnrichmentService

## Other
To get to know domain better and document flow, I would do an Event Storming session. 


TODO
- TimeService as a separate bean to manipulate time in tests
- public / package scope approach
- application-test.yaml


Resolved [org.springframework.web.multipart.support.MissingServletRequestPartException: Required part 'file' is not present.]

curl -F 'data=@trade.csv' --request POST http://localhost:8080/api/v1/enrich
curl -F 'file=@trade.csv' --request POST http://localhost:8080/api/v1/enrich
