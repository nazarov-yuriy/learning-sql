insert into AUTOSERVICE_AUTOSERVICE (NAME, ADDRESS) values ('QuickFix',               '221 B Baker St, London, England');
insert into AUTOSERVICE_AUTOSERVICE (NAME, ADDRESS) values ('Knock, Knock and done!', '11 Wall Street New York, NY');

insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values ('Oliver', 'Hughes');
insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values ('Ethan',  'Lee');
insert into AUTOSERVICE_CUSTOMER (FIRST_NAME, LAST_NAME) values ('Cooper', 'Bailey');

insert into AUTOSERVICE_VEHICLE (REG_NUMBER, CUSTOMER_ID) select 'ABC-1234', ID from AUTOSERVICE_CUSTOMER where AUTOSERVICE_CUSTOMER.LAST_NAME = 'Hughes';
insert into AUTOSERVICE_VEHICLE (REG_NUMBER, CUSTOMER_ID) select 'DEF-5678', ID from AUTOSERVICE_CUSTOMER where AUTOSERVICE_CUSTOMER.LAST_NAME = 'Lee';

insert into AUTOSERVICE_ORDER (TIME, AMOUNT, VEHICLE_ID, AUTOSERVICE_ID)
 select 'May 25 15:32:16 2014 UTC', 123.00, f1.ID, f2.ID from
   (select ID from AUTOSERVICE_VEHICLE where REG_NUMBER = 'ABC-1234') as f1 cross join
   (select ID from AUTOSERVICE_AUTOSERVICE where NAME = 'QuickFix') as f2;

insert into AUTOSERVICE_ORDER (TIME, AMOUNT, VEHICLE_ID, AUTOSERVICE_ID)
  select 'May 25 15:32:26 2014 UTC', 123.00, f1.ID, f2.ID from
    (select ID from AUTOSERVICE_VEHICLE where REG_NUMBER = 'DEF-5678') as f1 cross join
    (select ID from AUTOSERVICE_AUTOSERVICE where NAME = 'Knock, Knock and done!') as f2;