INSERT INTO USERS (NAME, EMAIL, PASSWORD, CONTACTS)
VALUES ('Ksu', 'ksu@yandex.ru', '{noop}ksu', 'tel: +7-900-190-31-12'),
       ('Kate', 'kate@mail.ru', '{noop}kate', 'Please, contact me via telegram: @kitty-kate'),
       ('Victor', 'victor@gmail.com', '{noop}victor', 'WhatsApp: +7 934 445 23 23');

INSERT INTO BOOK (USER_ID, AUTHOR, NAME, YEAR_OF_CURRENT_EDITION, DESCRIPTION, LANGUAGE, AVAILABILITY)
VALUES (1, 'L.N. Tolstoy', 'Anna Karenina', 2010, 'A famous romance by L.N. Tolstoy about love and life', 'Russian', true),
       (1, 'A.V. Zaretskiy', 'Professor Fortran''s Encyclopedia', 1991, 'A book for kids to introduce them to the world of computers', 'Russian', true),
       (1, 'F. Scott Fitzgerald', 'The Great Gatsby', 2020, 'A classic novel by F. Scott Fitzgerald', 'English', false),
       (1, 'J.K. Rowling', 'Harry Potter and the Chamber of Secrets', 2015, 'The second book about adventures of Harry Potter in the world of magic', 'Russian', false),
       (2, 'Jane Austen', 'Pride and Prejudice', 2005, 'The most popular novel by Jane Austen, a story of Elizabeth Bennet and Mr. Darcy', 'English', true),
       (2, 'Эмили Бронте', 'Грозовой перевал', 2002, 'Глубокий роман о бедрности, любви и трагедии', 'Русский', true),
       (2, 'Мэри Шелли', 'Франкенштейн', 2008, 'Уже ставшая классикой история о монструозном создании', 'Русский', false),
       (3, 'Henry Campbell Black', 'Black''s Law Dictionary with Pronunciations', 1991, '', 'English', true),
       (3, 'Peggy Kerley, Joanne Banker Hames', 'Civil Litigation', 2019, 'A useful textbook for those who study civil law and procedure', 'English', true),
       (3, 'J.K. Rowling', 'Harry Potter and the Sorcerer''s Stone', 2000, 'The first book of the famous saga about a young wizard', 'Russian', false),
       (3, 'J.K. Rowling', 'Harry Potter and the Half-Blood Prince', 2007, 'The sixth book about adventures of Harry Potter and his friends', 'Russian', false);


INSERT INTO BORROW_LOG (BORROW_DATE, DEADLINE, BOOK_ID, BORROWER_ID, OWNER_ID)
VALUES ('2023-06-20', '2023-10-20', 3, 2, 1),
       ('2023-08-20', now(), 4, 3, 1),
       ('2023-09-20', '2023-11-30', 7, 1, 2),
       ('2023-10-07', now(), 10, 1, 3),
       ('2023-10-07', now(), 11, 2, 3);







