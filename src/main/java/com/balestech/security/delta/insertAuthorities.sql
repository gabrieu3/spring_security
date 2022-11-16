insert into gb_authority(id, authority) values(1,'READ');
insert into gb_authority(id, authority) values(2,'WRITE');
insert into gb_authority(id, authority) values(3,'UPDATE');
commit;



insert into gb_authority(id, authority, user_id) values(1,'ROLE_USER',121);
insert into gb_authority(id, authority, user_id) values(2,'ROLE_ADMIN',121);
insert into gb_authority(id, authority, user_id) values(3,'ROLE_ROOT',2);
insert into gb_authority(id, authority, user_id) values(4,'ROLE_ADMIN',2);
commit;