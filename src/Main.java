import code.Book;
import code.LentBook;
import code.Location;
import db.op.BookDAO;
import db.op.Genre;
import db.op.LentDAO;
import db.op.LocationDAO;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("\n   * *LIBRARY SYSTEM* *\n");
        int k;
        do {
            System.out.println("Type the number of the action you want to perform:\n");
            System.out.println("1. Library module.\n2. Lending module.\n3. Location module.\n4. Exit");
            if(read.hasNextInt()) {
                k= read.nextInt();
                switch (k) { //Choose module
                    case 1: //Library module
                        read.nextLine();
                        int l;
                        do {
                            System.out.println("\n     *|   LIBRARY MODULE  |*");
                            System.out.println("1. See all books in the library.\n2. Filter by category and check availability.\n3. Add a book to the system.\n4. Search a book with id.\n5. Return");
                            if(read.hasNextInt()) {
                                l= read.nextInt();
                                switch (l) { //Library module options
                                    case 1: //See all
                                        List<Book> books = BookDAO.getAllBooks();
                                        System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                        for (Book book : books) {
                                            System.out.println(book);
                                        }
                                        break;
                                    case 2: //Category
                                        read.nextLine();
                                        System.out.println("How would you like to search?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                                        if(read.hasNextInt()){
                                            switch (read.nextInt()) { //Choose category
                                                case 1:
                                                    read.nextLine();
                                                    System.out.println("Please enter the title or type 0 to quit:");
                                                    String title = read.nextLine();
                                                    if (title.equals("0")) {
                                                        break;
                                                    }
                                                    List<Book> tle = BookDAO.getByTitle(title);
                                                    System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                                    for (Book book : tle) {
                                                        System.out.println(book);
                                                    }
                                                    break;
                                                case 2:
                                                    read.nextLine();
                                                    System.out.println("Please enter the author or type 0 to quit:");
                                                    String author = read.nextLine();
                                                    if (author.equals("0")) {
                                                        break;
                                                    }
                                                    List<Book> aut = BookDAO.getByAuthor(author);
                                                    System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                                    for (Book book : aut) {
                                                        System.out.println(book);
                                                    }
                                                    break;
                                                case 3:
                                                    read.nextLine();
                                                    System.out.println("Please enter the genre:\n1. Fantasy\n2. Romance\n3. Science Fiction.\n4. Mystery.\n5. Horror.\n6. Poetry.\n7. Return.");
                                                    String genre = "";
                                                    switch (read.nextInt()) {
                                                        case 1:
                                                            genre = String.valueOf(Genre.FANTASY);
                                                            break;
                                                        case 2:
                                                            genre = String.valueOf(Genre.ROMANCE);
                                                            break;
                                                        case 3:
                                                            genre = String.valueOf(Genre.SCI_FI);
                                                            break;
                                                        case 4:
                                                            genre = String.valueOf(Genre.MYSTERY);
                                                            break;
                                                        case 5:
                                                            genre = String.valueOf(Genre.HORROR);
                                                            break;
                                                        case 6:
                                                            genre = String.valueOf(Genre.POETRY);
                                                            break;
                                                        case 7:
                                                            break;
                                                    }
                                                    List<Book> gen = BookDAO.getByGenre(genre);
                                                    System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                                    for (Book book : gen) {
                                                        System.out.println(book);
                                                    }
                                                    break;
                                                case 4:
                                                    break;
                                                default:
                                                    System.out.println("Invalid input");
                                            }
                                        }else{
                                            read.nextLine();
                                            System.out.println("Invalid input, it must be a number");
                                        }
                                        break;
                                    case 3: //Add book
                                        read.nextLine();
                                        System.out.println("Write the book title or type 0 to quit:");
                                        String title = read.nextLine();
                                        if(title.equals("0")) {
                                            break;
                                        }
                                        System.out.println("Write the book author or type 0 to quit:");
                                        String author = read.nextLine();
                                        if(author.equals("0")) {
                                            break;
                                        }
                                        System.out.println("Please enter the genre:\n1. Fantasy\n2. Romance\n3. Science Fiction.\n4. Mystery.\n5. Horror.\n6. Poetry.\n7. Return.");
                                        String genre = "";
                                        if(read.hasNextInt()) {
                                            switch (read.nextInt()) {
                                                case 1:
                                                    genre = String.valueOf(Genre.FANTASY);
                                                    break;
                                                case 2:
                                                    genre = String.valueOf(Genre.ROMANCE);
                                                    break;
                                                case 3:
                                                    genre = String.valueOf(Genre.SCI_FI);
                                                    break;
                                                case 4:
                                                    genre = String.valueOf(Genre.MYSTERY);
                                                    break;
                                                case 5:
                                                    genre = String.valueOf(Genre.HORROR);
                                                    break;
                                                case 6:
                                                    genre = String.valueOf(Genre.POETRY);
                                                    break;
                                                case 7:
                                                    break;
                                                default:
                                                    System.out.println("Invalid input");
                                            }
                                        }else {
                                            read.nextLine();
                                            System.out.println("Invalid input, it must be a number");
                                            break;
                                        }

                                        read.nextLine();
                                        System.out.println("Write how many units of the book will be added or type X to quit:");
                                        String unit = read.nextLine();
                                        if(unit.equalsIgnoreCase("X")) {
                                            break;
                                        }
                                        Book b = new Book(title,author,genre,Integer.parseInt(unit));
                                        BookDAO.insertBook(b);
                                        break;
                                    case 4: //Search by id
                                        read.nextLine();
                                        System.out.println("Write the book id or type X to quit:");
                                        String id = read.nextLine();
                                        if(id.equalsIgnoreCase("X")) {
                                            break;
                                        }
                                        Book bid=BookDAO.getById(Integer.parseInt(id));
                                        if(bid==null) {
                                            System.out.println("!! ID NOT FOUND !!");
                                            break;
                                        }
                                        System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                        System.out.println(bid);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Invalid input");
                                }
                            }else{
                                read.nextLine();
                                System.out.println("Invalid input, it must be a number");
                                l=0;
                            }

                        }while (l != 5);

                        break;
                    case 2: //Lent module
                        read.nextLine();
                        int m;
                        do {
                            System.out.println("    *|LEND MODULE|*    ");
                            System.out.println("1. Lend a book.\n2. Return a book.\n3. See all lent books.\n4. Search by category.\n5. Search by id.\n6. Return.");
                            if(read.hasNextInt()) {
                                m=read.nextInt();
                                switch(m) {
                                    case 1://Lend book
                                        read.nextLine();
                                        System.out.println("Write the person's name or type 0 to quit");
                                        String name = read.nextLine();
                                        if(name.equals("0")) {
                                            break;
                                        }
                                        System.out.println("How would you like to search the book?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                                        if(read.hasNextInt()) {
                                            switch (read.nextInt()) {
                                                case 1:
                                                    read.nextLine();
                                                    System.out.println("Please enter the title or type 0 to quit:");
                                                    String title = read.nextLine();
                                                    if (title.equals("0")) {
                                                        break;
                                                    }
                                                    List<Book> tle = BookDAO.getByTitle(title);
                                                    System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                                    for (Book book : tle) {
                                                        System.out.println(book);
                                                    }
                                                    break;
                                                case 2:
                                                    read.nextLine();
                                                    System.out.println("Please enter the author or type 0 to quit:");
                                                    String author = read.nextLine();
                                                    if (author.equals("0")) {
                                                        break;
                                                    }
                                                    List<Book> aut = BookDAO.getByAuthor(author);
                                                    System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                                    for (Book book : aut) {
                                                        System.out.println(book);
                                                    }
                                                    break;
                                                case 3:
                                                    read.nextLine();
                                                    System.out.println("Please enter the genre:\n1. Fantasy\n2. Romance\n3. Science Fiction.\n4. Mystery.\n5. Horror.\n6. Poetry.\n7. Return.");
                                                    String genre = "";
                                                    if (read.hasNextInt()) {
                                                        switch (read.nextInt()) {
                                                        case 1:
                                                            genre = String.valueOf(Genre.FANTASY);
                                                            break;
                                                        case 2:
                                                            genre = String.valueOf(Genre.ROMANCE);
                                                            break;
                                                        case 3:
                                                            genre = String.valueOf(Genre.SCI_FI);
                                                            break;
                                                        case 4:
                                                            genre = String.valueOf(Genre.MYSTERY);
                                                            break;
                                                        case 5:
                                                            genre = String.valueOf(Genre.HORROR);
                                                            break;
                                                        case 6:
                                                            genre = String.valueOf(Genre.POETRY);
                                                            break;
                                                        case 7:
                                                            break;
                                                        default:
                                                            System.out.println("Invalid input");
                                                    }
                                                    }else{
                                                        read.nextLine();
                                                        System.out.println("Invalid input, it must be a number");
                                                        break;
                                                    }
                                                    List<Book> gen = BookDAO.getByGenre(genre);
                                                    System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                                    for (Book book : gen) {
                                                        System.out.println(book);
                                                    }
                                                    read.nextLine();
                                                    break;
                                                case 4:
                                                    break;
                                                default:
                                                    System.out.println("Invalid input");
                                            }
                                        }else{
                                            read.nextLine();
                                            System.out.println("Invalid input, it must be a number");
                                            break;
                                        }
                                        System.out.println("Write the book id or type X to quit:");
                                        String id = read.nextLine();
                                        if(id.equalsIgnoreCase("X")) {
                                            break;
                                        }
                                        BookDAO.lendBook(Integer.parseInt(id),name);
                                        break;
                                    case 2://Return book
                                        read.nextLine();
                                        System.out.println("Write the person's name or type 0 to quit");
                                        String var = read.nextLine();
                                        if(var.equals("0")) {
                                            break;
                                        }
                                        List<LentBook> lent=LentDAO.returnBook(var);
                                        for(LentBook lentbook:lent) {
                                            System.out.println(lentbook);
                                        }
                                        System.out.println("Type the person's id or type X to quit.");
                                        String idv = read.nextLine();
                                        if(idv.equalsIgnoreCase("X")) {
                                            break;
                                        }
                                        LentDAO.deletePerson(Integer.parseInt(idv));
                                        break;
                                    case 3://See all lent books
                                        read.nextLine();
                                        List<LentBook> lb = LentDAO.readAll();
                                        System.out.println("|   ID  |        PERSON       |   BOOK    |   DATE    |");
                                        for(LentBook lentbook:lb) {
                                            System.out.println(lentbook);
                                        }
                                        break;
                                    case 4:
                                        read.nextLine();
                                        System.out.println("Choose the category or:\n1. Book.\n2. Person.\n3. Return");
                                        if(read.hasNextInt()){
                                            switch (read.nextInt()) {
                                            case 1:
                                                read.nextLine();
                                                System.out.println("Please enter the title or type 0 to quit:");
                                                String title = read.nextLine();
                                                if (title.equals("0")) {
                                                    break;
                                                }
                                                System.out.println("|   ID  |        PERSON       |   BOOK    |   DATE    |");
                                                List<LentBook> ttl = LentDAO.getByTitle(title);
                                                for(LentBook lentbook:ttl) {
                                                    System.out.println(lentbook);
                                                }
                                                break;
                                            case 2:
                                                read.nextLine();
                                                System.out.println("Please enter the person's name or type 0 to quit:");
                                                String person = read.nextLine();
                                                if (person.equals("0")) {
                                                    break;
                                                }
                                                System.out.println("|   ID  |        PERSON       |   BOOK    |   DATE    |");
                                                List<LentBook> per = LentDAO.getByPerson(person);
                                                for (LentBook lentbook:per) {
                                                    System.out.println(lentbook);
                                                }
                                                break;
                                            case 4:
                                                break;
                                            default:
                                                System.out.println("Invalid input");
                                        }
                                        }else{
                                            read.nextLine();
                                            System.out.println("Invalid input, it must be a number");
                                        }
                                        break;
                                    case 5:
                                        read.nextLine();
                                        System.out.println("Type person's id or type X to quit:");
                                        String pid = read.nextLine();
                                        if(pid.equals("X")) {
                                            break;
                                        }
                                        LentBook lbi = LentDAO.getbyId(Integer.parseInt(pid));
                                        if(lbi==null) {
                                            System.out.println("!! ID NOT FOUND !!");
                                        }
                                        System.out.println("|   ID  |        PERSON       |   BOOK    |   DATE    |");
                                        System.out.println(lbi);
                                        break;
                                }
                            }else{
                                read.nextLine();
                                System.out.println("Invalid input");
                                m=0;
                            }
                        }while(m!=6);
                        break;

                    case 3://Location module
                        read.nextLine();
                        int n;
                        do{
                            System.out.println("    *| LOCATION MODULE |*   ");
                            System.out.println("1. Find location of a book.\n2. Search by filter.\n3. See all.\n4. Search by id.\n5. Return");
                            if(read.hasNextInt()){
                                n=read.nextInt();
                                switch (n) {
                                    case 1:
                                        read.nextLine();
                                        System.out.println("How would you like to search the book?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                                        if(read.hasNextInt()){
                                            switch (read.nextInt()) {
                                            case 1:
                                                read.nextLine();
                                                System.out.println("Please enter the title or type 0 to quit:");
                                                String title = read.nextLine();
                                                if (title.equals("0")) {
                                                    break;
                                                }
                                                List<Book> bk=BookDAO.getByTitle(title);
                                                System.out.println("|   ID   |   BOOK   |   AUTHOR   |  UNITS   |");
                                                for(Book book:bk) {
                                                    System.out.println(book);
                                                }
                                                break;
                                            case 2:
                                                read.nextLine();
                                                System.out.println("Please enter the author or type 0 to quit:");
                                                String author = read.nextLine();
                                                if (author.equals("0")) {
                                                    break;
                                                }
                                                System.out.println("|   ID   |   BOOK   |   AUTHOR   |  UNITS   |");
                                                List<Book> bk1=BookDAO.getByAuthor(author);
                                                for(Book book:bk1) {
                                                    System.out.println(book);
                                                }
                                                break;
                                            case 3:
                                                System.out.println("Please enter the genre:\n1. Fantasy\n2. Romance\n3. Science Fiction.\n4. Mystery.\n5. Horror.\n6. Poetry.\n7. Return.");
                                                String genre = "";
                                                read.nextLine();
                                                switch (read.nextInt()) {
                                                    case 1:
                                                        genre = String.valueOf(Genre.FANTASY);
                                                        break;
                                                    case 2:
                                                        genre = String.valueOf(Genre.ROMANCE);
                                                        break;
                                                    case 3:
                                                        genre = String.valueOf(Genre.SCI_FI);
                                                        break;
                                                    case 4:
                                                        genre = String.valueOf(Genre.MYSTERY);
                                                        break;
                                                    case 5:
                                                        genre = String.valueOf(Genre.HORROR);
                                                        break;
                                                    case 6:
                                                        genre = String.valueOf(Genre.POETRY);
                                                        break;
                                                    case 7:
                                                        break;
                                                }
                                                read.nextLine();
                                                List<Book> book = BookDAO.getByGenre(genre);
                                                System.out.println("|   ID   |   BOOK   |   AUTHOR   |  UNITS   |");
                                                for(Book bk2:book) {
                                                    System.out.println(bk2);
                                                }
                                                break;
                                            case 4:
                                                break;
                                        }
                                        }else{
                                            read.nextLine();
                                            System.out.println("Invalid input, it must be a number");
                                            break;
                                        }
                                        System.out.println("Write the book id or type X to quit:");
                                        String t=read.nextLine();
                                        if(t.equalsIgnoreCase("X")) {
                                            break;
                                        }
                                        Location loc = LocationDAO.findTitle(Integer.parseInt(t));
                                        System.out.println("|   ID   |        BOOK         |   AUTHOR   |  SECTION | ROW |");
                                        System.out.println(loc);
                                        break;
                                    case 2:
                                        read.nextLine();
                                        System.out.println("1. Book.\n2. Author.\n3. Section.\n4. Row.\n5. Return");
                                        if(read.hasNextInt()) {
                                            switch (read.nextInt()) {
                                                case 1:
                                                    read.nextLine();
                                                    System.out.println("Please enter the title or type 0 to quit:");
                                                    String title = read.nextLine();
                                                    if (title.equals("0")) {
                                                        break;
                                                    }
                                                    List<Location> lc = LocationDAO.findByTitle(title);
                                                    System.out.println("|   ID   |        BOOK         |   AUTHOR   |  SECTION | ROW |");
                                                    for (Location loc2 : lc) {
                                                        System.out.println(loc2);
                                                    }
                                                    break;
                                                case 2:
                                                    read.nextLine();
                                                    System.out.println("Please enter the author or type 0 to quit:");
                                                    String author = read.nextLine();
                                                    if (author.equals("0")) {
                                                        break;
                                                    }
                                                    List<Location> au = LocationDAO.findByAuthor(author);
                                                    System.out.println("|   ID   |        BOOK         |   AUTHOR   |  SECTION | ROW |");
                                                    for (Location auth : au) {
                                                        System.out.println(auth);
                                                    }
                                                    break;
                                                case 3:
                                                    read.nextLine();
                                                    System.out.println("Please enter the section (A-F) or type 0 to quit:");
                                                    String section = read.nextLine();
                                                    if (section.equals("0")) {
                                                        break;
                                                    }
                                                    List<Location> sec = LocationDAO.findBySection(section);
                                                    System.out.println("|   ID   |        BOOK         |   AUTHOR   |  SECTION | ROW |");
                                                    for (Location st : sec) {
                                                        System.out.println(st);
                                                    }
                                                    break;
                                                case 4:
                                                    read.nextLine();
                                                    System.out.println("Please enter the row (1-4) or type X to quit:");
                                                    String row = read.nextLine();
                                                    if (row.equalsIgnoreCase("X")) {
                                                        break;
                                                    } else if (Integer.parseInt(row) > 4 || Integer.parseInt(row) < 0) {
                                                        System.out.println("!! ROW NOT VALID !!");
                                                        break;
                                                    }
                                                    List<Location> rw = LocationDAO.findByRow(Integer.parseInt(row));
                                                    System.out.println("|   ID   |        BOOK         |   AUTHOR   |  SECTION | ROW |");
                                                    for (Location rOw : rw) {
                                                        System.out.println(rOw);
                                                    }
                                                    break;
                                                case 5:
                                                    break;
                                            }
                                        }else{
                                            read.nextLine();
                                            System.out.println("Invalid input, it must be a number");
                                        }
                                        break;
                                    case 3:
                                        read.nextLine();
                                        List<Location> all = LocationDAO.getAllLocation();
                                        System.out.println("|   ID   |        BOOK         |   AUTHOR   |  SECTION | ROW |");
                                        for(Location loc2 : all) {
                                            System.out.println(loc2);
                                        }
                                        break;
                                    case 4:
                                        read.nextLine();
                                        System.out.println("Please type the ID:");
                                        Location location = LocationDAO.getById(read.nextInt());
                                        if(location==null) {
                                            System.out.println("!! ID NOT FOUND !!");
                                        }
                                        System.out.println("|   ID   |        BOOK         |   AUTHOR   |  SECTION | ROW |");
                                        System.out.println(location);
                                        break;
                                }
                            }else{
                                read.nextLine();
                                System.out.println("Invalid input!");
                                n=0;
                            }
                            break;
                        }while (n != 5);
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }else{
                read.nextLine();
                System.out.println("Invalid input");
                k=1;
            }
        }while (k != 4) ;
    }
}
