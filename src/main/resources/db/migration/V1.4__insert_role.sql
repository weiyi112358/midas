insert into roles (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', 'Y', 'Y', 'Y', 'Y'),
('Manager', '/depts,/departments,/employees,/ems,/acnts,/accounts', 'Y', 'Y', 'Y', 'N'),
('user', '/employees,/ems,/acnts,/accounts', 'Y', 'N', 'N', 'N')
;
commit;

insert into users_roles(user_id,role_id) values(1,1),(1,3);
commit;