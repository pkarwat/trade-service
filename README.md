Request:
curl --request POST --data @trade.csv --header 'Content-Type: text/csv' --header 'Accept: text/csv'
http://server.com/api/v1/enrich

Response:
date,product_name,currency,price
20160101,Treasury Bills Domestic,EUR,10
20160101,Corporate Bonds Domestic,EUR,20.1
20160101,REPO Domestic,EUR,30.34
20160101,Missing Product Name,EUR,35.34
