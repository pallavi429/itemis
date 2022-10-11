# Itemis code challenge test

## Task Description
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no exemptions. When I purchase items
I receive a receipt which lists the name of all the items and their price (including tax),
finishing with the total cost of the items,
and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax
rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of
sales tax.

Standalone Sales Tax Java Application (pre-requisites)

* Java 11
* Maven

## To run the application
1. git clone https://github.com/pallavi429/itemis.git
2. mvn clean install

## Project description
1. The project has the following directories:

    a. src/main/java : Sales Tax implementation

    b. src/test/java : Unit and Integration tests

    c. src/main/java/bill : Package for receipt generation.

    d. src/main/java/product : Package for pojo's

    e. src/main/java/util : utility package

    f. src/main/java/exception : Package for custom exceptions

## Parser assumptions
Following assumptions were made while designing item parser utility :
1. No special characters/empty lines.
2. First character of each line represents product quantity.
3. Last string must be price of the product (quantity * unit_price)
4. Last but one string must be "at".

    ex: ***1*** box of chocolates ***at 10.00***
5. If product is imported then string "imported" should be part of line.

    ex: 1 ***imported*** bottle of perfume at 47.50
6. To create the thesaurus we expect exempted items string (Books, Food and Medicine/pills) to be part of item description.
