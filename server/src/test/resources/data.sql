insert into users(name, email) values ('Test', 'Test@Test')
insert into users(name, email) values ('Test1', 'Test1@Test')
insert into users(name, email) values ('Test2', 'Test2@Test')
insert into users(name, email) values ('Test3', 'Test3@Test')
insert into users(name, email) values ('Test4', 'Test4@Test')
insert into users(name, email) values ('Test5', 'Test5@Test')
insert into users(name, email) values ('Test6', 'Test6@Test')
insert into users(name, email) values ('Test7', 'Test7@Test')
insert into users(name, email) values ('Test8', 'Test8@Test')
insert into users(name, email) values ('Test9', 'Test9@Test')
insert into users(name, email) values ('Test10', 'Test10@Test')
insert into users(name, email) values ('Test11', 'Test11@Test')
insert into users(name, email) values ('Test12', 'Test12@Test')
insert into users(name, email) values ('Test13', 'Test13@Test')
insert into users(name, email) values ('Test14', 'Test14@Test')
insert into users(name, email) values ('Test15', 'Test15@Test')
insert into users(name, email) values ('Test16', 'Test16@Test')
insert into users(name, email) values ('Test17', 'Test17@Test')
insert into users(name, email) values ('Test18', 'Test18@Test')
insert into users(name, email) values ('Test19', 'Test19@Test')



insert into requests(description, requestor_id, created) values ('Test', 1, '2023-12-12')
insert into requests(description, requestor_id, created) values ('Test1', 2, '2023-12-12')


insert into items(name, description, is_available, owner_id, request_id) values ('Test', 'Test', true, 1, 1)
insert into items(name, description, is_available, owner_id, request_id) values ('Test1', 'Test1', true, 2, 2)
insert into items(name, description, is_available, owner_id, request_id) values ('Test2', 'Test2', true, 3, null)
insert into items(name, description, is_available, owner_id, request_id) values ('Test3', 'Test3', true, 4, null)
insert into items(name, description, is_available, owner_id, request_id) values ('Test4', 'Test4', true, 5, null)
insert into items(name, description, is_available, owner_id, request_id) values ('Test5', 'Test5', true, 6, null)
insert into items(name, description, is_available, owner_id, request_id) values ('Test6', 'Test6', false, 6, null)


insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2022-12-12', '2023-12-12', 3, 5, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2022-12-12', '2023-12-12', 4, 6, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2022-12-12', '2023-12-12', 1, 7, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2022-12-12', '2023-12-12', 2, 1, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2022-12-12', '2023-12-12', 4, 1, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2026-12-12', '2027-12-12', 4, 16, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2027-12-12', 4, 9, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2027-12-12', 4, 10, 'WAITING')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2027-12-12', 4, 11, 'REJECTED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2024-12-12', 5, 12, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2024-12-12', 5, 13, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2026-12-12', '2027-12-12', 5, 14, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2027-12-12', 5, 14, 'APPROVED')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2027-12-12', 5, 14, 'WAITING')
insert into bookings(start_date, end_date, item_id, booker_id, status) values ('2023-12-12', '2027-12-12', 5, 14, 'REJECTED')


insert into comments(text, item_id, author_id, created) values ('Test', 1, 1, '2024-12-12')
insert into comments(text, item_id, author_id, created) values ('Test', 2, 2, '2024-12-12')
insert into comments(text, item_id, author_id, created) values ('Test', 2, 2, '2024-12-12')



