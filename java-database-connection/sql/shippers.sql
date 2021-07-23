select * from shippers;

insert into shippers(shipper_id, company_name, phone) values (99,'test company', '+330123456789');

update shippers set company_name = 'test modified' where shippers.phone= '+330123456789';

delete from shippers where shippers.phone= '+330123456789';

select * from shippers;
