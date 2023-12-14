# Goal
The candidate will implement a JAVA service, which will :
- [ ] expose an API to enrich trade data (trade.csv) with product names from the static data file (product.csv)
- [ ] translate the product_id into product_name
- [ ] perform data validation
  - [ ] ensure that date is a valid date in YYYYMMDD format, otherwise discard the row and log an error
  - [ ] if the product name is not available, we should still log the missing mapping and set the product Name as "Missing Product Name"

# Technical constraints :
- [x] Please use Spring boot 3+
- [x] Please use Java11+ and Maven
- [ ] The service should contain a Readme file to explain how to run the project
  - [ ] Please feel free to add in the Readme any ideas that you would have implemented if you had more time / the limitations of your current code

# Assessment criteria
The JAVA service should be production-quality. This means that we will pay attention to:
- [ ] the quality of the testing
- [ ] the architecture of the project. It should be easy to :
  - [ ] extend
  - [ ] scale - it should be able to support very large sets of trades / products
  - [ ] maintain
  - [ ] the code readability
