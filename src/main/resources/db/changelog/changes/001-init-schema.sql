create table if not exists PRICES
(

    ID         int primary key,
    BRAND_ID   int            NOT NULL,
    START_DATE TIMESTAMP      NOT NULL,
    END_DATE   TIMESTAMP      NOT NULL,
    PRICE_LIST int            NOT NULL,
    PRODUCT_ID int            NOT NULL,
    PRIORITY   int            NOT NULL,
    PRICE      numeric(20, 2) NOT NULL,
    CURR       varchar(10)


);

insert into PRICES
values (1, 1, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 1, 35455, 0, 35.50, 'EUR'),
       (2, 1, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 2, 35455, 1, 25.45, 'EUR'),
       (3, 1, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 1, 35455, 1, 30.50, 'EUR'),
       (4, 1, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 1, 35455, 1, 38.95, 'EUR');