import Code.Book;
import Code.Lent_Book;
import Code.Location;
import DB.OP.BookDAO;
import DB.OP.Genre;
import DB.OP.LentDAO;
import DB.OP.LocationDAO;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Library System\n");
        int k;
        do {
            System.out.println("Type the number of the action you want to perform:\n");
            System.out.println("1. Library module.\n2. Lending module.\n3. Location module.\n4. Exit");
            k= read.nextInt();
            switch (k) { //Choose module
                case 1: //Library module
                    read.nextLine();
                    int l;
                    do {
                        System.out.println("\n     *|   LIBRARY MODULE  |*");
                        System.out.println("1. See all books in the library.\n2. Filter by category and check availability.\n3. Add a book to the system.\n4. Search a book with id.\n5. Return");
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
                                                continue;
                                        }
                                        List<Book> gen = BookDAO.getByGenre(genre);
                                        System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                        for (Book book : gen) {
                                            System.out.println(book);
                                        }
                                        break;
                                    case 4:
                                        continue;
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
                                        continue;
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
                                if(id.equals("X")) {
                                    break;
                                }
                                Book bid=BookDAO.getById(Integer.parseInt(id));
                                if(bid==null) {
                                    System.out.println("!! ID NOT FOUND !!");
                                    break;
                                }
                                System.out.println(bid);
                                break;
                            case 5:
                                continue;
                        }
                    }while (l != 5);

                    break;
                case 2: //Lent module
                    read.nextLine();
                    int m;
                    do {
                        System.out.println("    *|LEND MODULE|*    ");
                        System.out.println("1. Lend a book.\n2. Return a book.\n3. See all lent books.\n4. Search by category.\n5. Search by id.\n6. Return.");
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
                                                continue;
                                        }
                                        List<Book> gen = BookDAO.getByGenre(genre);
                                        System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                                        for (Book book : gen) {
                                            System.out.println(book);
                                        }
                                        break;
                                    case 4:
                                        continue;
                                }
                                read.nextLine();
                                System.out.println("Write the book id");
                                BookDAO.lendBook(read.nextInt(),name);
                                break;
                            case 2://Return book
                                read.nextLine();
                                System.out.println("Write the person's name or type 0 to quit");
                                String var = read.nextLine();
                                if(var.equals("0")) {
                                    break;
                                }
                                List<Lent_Book> lent=LentDAO.returnBook(var);
                                for(Lent_Book lentbook:lent) {
                                    System.out.println(lentbook);
                                }
                                System.out.println("Type the person's id or type X to quit.");
                                String id = read.nextLine();
                                if(id.equalsIgnoreCase("X")) {
                                    break;
                                }
                                LentDAO.Delete_person(Integer.parseInt(id));
                                break;
                            case 3://See all lent books
                                read.nextLine();
                                List<Lent_Book> lb = LentDAO.readAll();
                                for(Lent_Book lentbook:lb) {
                                    System.out.println(lentbook);
                                }
                                break;
                            case 4:
                                read.nextLine();
                                System.out.println("Choose the category or:\n1. Book.\n2. Person.\n3. Return");
                                switch (read.nextInt()) {
                                    case 1:
                                        read.nextLine();
                                        System.out.println("Please enter the title or type 0 to quit:");
                                        String title = read.nextLine();
                                        if (title.equals("0")) {
                                            break;
                                        }
                                        List<Lent_Book> ttl = LentDAO.getByTitle(title);
                                        for(Lent_Book lentbook:ttl) {
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
                                        List<Lent_Book> per = LentDAO.getByPerson(person);
                                        for (Lent_Book lentbook:per) {
                                            System.out.println(lentbook);
                                        }
                                        break;
                                    case 4:
                                        break;
                                }
                                break;
                            case 5:
                                read.nextLine();
                                System.out.println("Type person's id or type X to quit:");
                                String pid = read.nextLine();
                                if(pid.equals("X")) {
                                    break;
                                }
                                Lent_Book lbi = LentDAO.getbyId(Integer.parseInt(pid));
                                if(lbi!=null) {
                                    System.out.println("!! ID NOT FOUND !!");
                                }
                                System.out.println(lbi);
                                break;
                        }
                    }while(m!=6);
                    break;

                case 3://Location module
                    read.nextLine();
                    int n = 0;
                    do{
                        System.out.println("    *| LOCATION MODULE |*   ");
                        System.out.println("1. Find location of a book.\n2. Search by filter.\n3. See all.\n4. Search by id.\n5. Return");
                        read.nextInt(n);
                        switch (n) {
                            case 1:
                                read.nextLine();
                                System.out.println("How would you like to search the book?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                                switch (read.nextInt()) {
                                    case 1:
                                        read.nextLine();
                                        System.out.println("Please enter the title:");
                                        String title = read.nextLine();
                                        List<Book> bk=BookDAO.getByTitle(title);
                                        for(Book book:bk) {
                                            System.out.println(book);
                                        }
                                        break;
                                    case 2:
                                        read.nextLine();
                                        System.out.println("Please enter the author:");
                                        String author = read.nextLine();
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
                                                continue;
                                        }
                                        read.nextLine();
                                        List<Book> book = BookDAO.getByGenre(genre);
                                        for(Book bk2:book) {
                                            System.out.println(bk2);
                                        }
                                        break;
                                    case 4:
                                        continue;
                                }
                                System.out.println("Write the book id or type X to quit:");
                                String t=read.nextLine();
                                if(t.equalsIgnoreCase("X")) {
                                    break;
                                }
                                Location loc = LocationDAO.find_title(Integer.parseInt(t));
                                System.out.println(loc);
                                break;
                            case 2:
                                read.nextLine();
                        }
                    break;
                }while (n!=5);
            }
        }while (k != 4) ;
    }
}