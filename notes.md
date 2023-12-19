# Goal
The candidate will implement a JAVA service, which will :
- [x] expose an API to enrich trade data (trade.csv) with product names from the static data file (product.csv)
- [x] translate the product_id into product_name
- [x] perform data validation
  - [x] ensure that date is a valid date in YYYYMMDD format, otherwise discard the row and log an error
  - [x] if the product name is not available, we should still log the missing mapping and set the product Name as "Missing Product Name"

# Technical constraints :
- [x] Please use Spring boot 3+
- [x] Please use Java11+ and Maven
- [x] The service should contain a Readme file to explain how to run the project
  - [x] Please feel free to add in the Readme any ideas that you would have implemented if you had more time / the limitations of your current code

# Assessment criteria
The JAVA service should be production-quality. This means that we will pay attention to:
- [x] the quality of the testing
- [] the architecture of the project. It should be easy to :
  - [x] extend
  - [ ] scale - it should be able to support very large sets of trades / products
  - [x] maintain
  - [x] the code readability

TASKS:
DONE
TEAMA-1 domain
TEAMA-2 rest api
TEAMA-3 matching service
TEAMA-4 validation

IN PROGRESS
TEAMA-5 processing csv improvement - verifying spring batch, check matching trades-products improvement

short summary:
Processing ~1_000_000 trades took ~8s. To support large files MultipartConfigElementConfiguration need to be configured.
After short analysis, there are few possible solutions to implement more efficient api like spring batch, parallel stream for better processing or stream as input not to pass whole csv at once and not to create temp file in memory.

BACKLOG
TEAMA-6 variable type for date, currency and price
TEAMA-7 products not hardcoded in repository to manage/inject tests data better
