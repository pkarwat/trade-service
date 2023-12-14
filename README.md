# Trade Enricher
Service allows to enrich input trades collection with products data.

# Local run
```
mvn clean install
```

Run app.

To verify if app is up and running, send request to healthcheck using: `docs/management_api.http`. 

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
- after analysis, I would name that service: tradeEnricher / tradeEnrichmentService
- public / package scope approach
- infrastructure / ui ~ similar to ports/adapters
- C4, plantUml docs
- Event Storming 
