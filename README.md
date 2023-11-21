## Book-sharing web-application
### Video Demo: <https://youtu.be/tp8ZwVplImI>
### Description
REST API app aimed to digitalize the process of book-sharing among relatively small groups of people.
#### Functions for non-authorised users:
* to look at the list of books available
* to search for a particular book by its name (case-insensitive)
#### Functions for authorised users:
* to look at the list of books with contact details of their owners
* "book" a particular book by choosing a book and entering the deadline
* see the lists of borrowed and lent books with the deadline of return
* add books they are willing to share with others
* update info about their books or delete books from the list
* control profile details
The app also supports the following features:
* The information about meeting a deadline to return the book is renewed automatically
* The user cannot delete the profile without returning all books that were lent by him/her
* The owner of the book should ‘accept’ the information about return of his/her book by another user
### Technologies used:
Java, Intellij DEA, Maven, Spring Boot (including security module), Hibernate, H2 database, Swagger UI, Project Lombok, REST (Jackson)
### Documentation:
Link to Swagger UI: http://localhost:8080/swagger-ui/index.html
Credentials for test:
- ksu@yandex.ru / ksu
- kate@mail.ru / kate
- victor@gmail.com / victor

  
#### Project structure:
* Config package (contains config files of the app)
  - AppConfig (main configuration file that creates and starts the server and is responsible for global settings of the app)
  - OpenApiConfig (the purpose of the file is to add and set Swagger UI to the project)
  - RestExceptionHandler (errors handling on the global level)
  - SecurityConfig (configurates security module, sets rules for getting permissions depending on the authorisation)
* Exception package (contaings files with custom exceptions)
  - AppException (general class from which all other exceptions in the app are inherited)
  - IllegalRequestDataException (exception that processes illegal requests from users)
  - NotFoundException (exception that processes situations when entities requested by users are not found in the database)
* Models
  - BaseEntity (abstract super class from which all models are inherited, generated ids and has some utility methods)
  - Book (entity that represents real books with the owner, author, name, description, year of edition, language and availability for borrowing)
  - BorrowLog (entity that represents logs with info about borrowing books; it contains info about the book, its owner, its borrower, borrow date, deadline of return, status of return (boolean) and info about expiration of the deadline (boolean))
  - User (entity that represents users of the app with the following fields: name, email, password, contacts, books to lend and borrowed books)
  All models are also used to generate tables in the database.
* Repository (package for classes for repository layer which aim is to make requests to the database)
  - BaseRepository (public interface from which all other repository interfaces are inherited; contains general validation for delete methods)
  - BookRepository (repository that deals with "Book" table in the database. It has the following custom methods:
    - "getAvailable" - returns the list of books which are available for borrowing;
    - "getAvailableWithUserDetail" - returns the list of available books with contact details of their owners;
    - "findByNameLikeIgnoreCase" - returns the list of books which name matches the search request of the user;
    - "getByUserId" - returns books that belong to the particular user)
  - BorrowLogRepository (repository that deals with "Borrow_Log" table in the database. It has the following custom methods:
    - "getLogsForBorrowedBooksByUserId" - returns info about books that were borrowed by the user;
    - "getLogsForLentBooksByUserId" - returns info about books that were lent by the user;
    - "getLogsForBorrowedBooksByUserIdNotReturned" - returns info about books that were borrowed and not returned by the user;
    - "getLogsForLentBooksByUserIdNotReturned" - returns info about books that were lent by the user and not returned yet)
  - UserRepository (repository that deals with "Users" table in the database. It has the following custom methods:
    - "getUserByBookId" - returns a user by book id if any;
    - "findByEmailIgnoreCase" - returns a user by his/her email (case-insencitive);
    - "prepareAndSave" - saves a user into the database with encrypted password and email in lower case)
* Service (package where all business logic is located)
  - BookService (a class that contains logic for saving/updating books)
  - BorrowLogService (a class that contains logic for creating and updating borrow logs, e.g. checks availability of the book requested by the user, changes availability status of the book if it is borrowed)
  - UserService (a class that contains logic for deleting user's profile: it checks whether the user has returned all books before deleting)
* 

























