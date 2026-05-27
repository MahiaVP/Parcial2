# Library System with Java and PostgreSQL
This project is a library system that lets libraries have control of their books available, as well of the books they have lent. Furthermore, they can also check the physical location of a book. This project helps with organization and the follo-up of every book that they own.
## Used technologies
- Java JDK 17
- PostgreSQL
- Neon database
- dotenv
## How to run the program

>[!NOTE]
>Make sure that you have all the technologies listed above so the program runs correctly

Firts, you will need to create a database at Neon. Then, in sql editor you need to write the following statements so your tables get created.
```sql
CREATE TABLE library (
    id SERIAL PRIMARY KEY,
    book TEXT NOT NULL,
    author TEXT NOT NULL,
    genre TEXT NOT NULL,
    units_available INTEGER NOT NULL
);

CREATE TABLE location (
    id SERIAL PRIMARY KEY,
    book TEXT NOT NULL,
    author TEXT NOT NULL,
    section CHAR(1) NOT NULL,
    row INTEGER NOT NULL
);

CREATE TABLE lent_books (
    id SERIAL PRIMARY KEY,
    book TEXT NOT NULL,
    person TEXT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
>[!IMPORTANT]
>To protect your credentials, you need to create a .env file in the root of your project.>Then you need to include this file in the .gitignore to secure your credentials.\
>To add this file to your .gitignore, you literally just write:\
>**.env**

## .env file
This is what you need in your .env file
```sql
DB_KEY=...
DB_USER=...
DB_URL=...
```
## Project structure
```sql
Parcial2/
├─src/
│  ├─code/
│  │  ├─Book.java
│  │  ├─LentBook.java
│  │  └─Location.java
│  ├─db/
│  │  ├op/
│  │  │ ├─BookDAO.java
│  │  │ ├─Genre.java
│  │  │ ├─LentDAO.java
│  │  │ └─LocationDAO.java
│  │  └─DataConnection.java
│  └─Main.java
└─.env #This is where your .env must be.
```
