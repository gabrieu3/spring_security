insert into gb_user(id,email,password) values(1, 'a@a.com','123');
insert into gb_user(id,email,password) values(2, 'b@b.com','123');
commit;
/
select * from gb_user
