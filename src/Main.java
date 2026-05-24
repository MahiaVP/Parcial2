import Code.Book;
import DB.OP.BookDAO;
import DB.OP.Genre;
import DB.OP.LentDAO;
import DB.OP.LocationDAO;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int k = 1;
        Scanner read = new Scanner(System.in);
        System.out.println("Library System\n");
        do {
            System.out.println("Type the number of the action you want to perform:\n");
            System.out.println("1. Library module.\n2. Lending module.\n3. Location module.\n4. Exit");
            switch (read.nextInt()) {
                case 1:
                    read.nextLine();
                    System.out.println("1. See all books in the library.\n2. Filter by category and check availability.\n3. Add a book to the system.\n4. Search a book with id.\n5. Return");
                    switch (read.nextInt()) {
                        case 1:
                            List<Book> books = BookDAO.getAllBooks();
                            System.out.println("|   id   |         Book         |   Author   |   Genre   |   Units Available   |");
                            for (Book book : books) {
                                System.out.println(book);
                            }
                            break;
                        case 2:
                            read.nextLine();
                            System.out.println("How would you like to search?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                            switch (read.nextInt()) {
                                case 1:
                                    read.nextLine();
                                    System.out.println("Please enter the title:");
                                    List<Book> title = BookDAO.getByTitle(read.nextLine());
                                    break;
                                case 2:
                                    read.nextLine();
                                    System.out.println("Please enter the author:");
                                    String author = read.nextLine();
                                    BookDAO.ReadAuthor(author);
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
                                    BookDAO.ReadGenre(genre);
                                    break;
                                case 4:
                                    continue;
                            }
                            break;
                    }
            }
            System.out.println("1. See all books in the library.\n2. Filter by category and check availability.\n3. Lend a book\n4. Return a book\n5. Add a book to the system.\n6. Find location of a book.\n7. See list of books lent.\n8. See list of locations\n9. Exit\n");
            switch (read.nextInt()) {
                case 1:
                    break;
                case 2:
                    read.nextLine();
                    System.out.println("How would you like to search?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                    switch (read.nextInt()) {
                        case 1:
                            read.nextLine();
                            System.out.println("Please enter the title:");
                            String title = read.nextLine();
                            BookDAO.getByTitle(title);
                            break;
                        case 2:
                            read.nextLine();
                            System.out.println("Please enter the author:");
                            String author = read.nextLine();
                            BookDAO.ReadAuthor(author);
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
                            BookDAO.ReadGenre(genre);
                            break;
                        case 4:
                            continue;
                    }
                    break;
                case 3:
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
                            System.out.println("Please enter the title:");
                            String title = read.nextLine();
                            BookDAO.getByTitle(title);
                            break;
                        case 2:
                            read.nextLine();
                            System.out.println("Please enter the author:");
                            String author = read.nextLine();
                            BookDAO.ReadAuthor(author);
                            break;
                        case 3:
                            read.nextLine();
                            System.out.println("Please enter the genre:");
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
                            BookDAO.ReadGenre(genre);
                            break;
                        case 4:
                            continue;
                        }
                    System.out.println("Write the book id");
                    BookDAO.LendBook(read.nextInt(),name);
                    break;
                case 4:
                    read.nextLine();
                    System.out.println("Write the person's name or type 0 to quit");
                    String var = read.nextLine();
                    if(var.equals("0")) {
                        break;
                    }
                    LentDAO.ReturnBook(var);
                    System.out.println("Type the person's id or type X to quit.");
                    String id = read.nextLine();
                    if(id.equalsIgnoreCase("X")) {
                        break;
                    }
                    LentDAO.Delete_person(Integer.parseInt(id));
                    break;
                case 5:
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
                    BookDAO.InsertBook(b);
                    break;
                case 6:
                    read.nextLine();
                    System.out.println("How would you like to search the book?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                    switch (read.nextInt()) {
                        case 1:
                            read.nextLine();
                            System.out.println("Please enter the title:");
                            title = read.nextLine();
                            BookDAO.getByTitle(title);
                            break;
                        case 2:
                            read.nextLine();
                            System.out.println("Please enter the author:");
                            author = read.nextLine();
                            BookDAO.ReadAuthor(author);
                            break;
                        case 3:
                            read.nextLine();
                            System.out.println("Please enter the genre:\n1. Fantasy\n2. Romance\n3. Science Fiction.\n4. Mystery.\n5. Horror.\n6. Poetry.\n7. Return.");
                            genre = "";
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
                            BookDAO.ReadGenre(genre);
                            break;
                        case 4:
                            continue;
                    }
                    System.out.println("Write the book id or type X to quit:");
                    String t=read.nextLine();
                    if(t.equalsIgnoreCase("X")) {
                        break;
                    }
                    LocationDAO.find_title(Integer.parseInt(t));
                    break;
                case 7:
                    read.nextLine();
                    LentDAO.Read_lent();
                    break;
                case 8:
                    read.nextLine();
                    LocationDAO.Read_Location();
                    break;
                case 9:
                    read.nextLine();
                    System.out.println("Are you sure you want to quit?");

            }
            System.out.println("1. Return\n2. Exit\n");
            k = read.nextInt();
        }while (k == 1) ;
    }
}