import Code.Book;
import DB.OP.BookDAO;
import DB.OP.Genre;
import DB.OP.LentDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int k = 1;
        Scanner read = new Scanner(System.in);
        System.out.println("Library System\n");
        do {
            System.out.println("Type the number of the action you want to perform:\n");
            System.out.println("1. See all books in the library.\n2. Filter by category and check availability.\n3. Lend a book\n4. Return a book\n5. Add a book to the system.\n6. Find location of a book.\n7. See list of books lent.\n8. Exit\n");
            switch (read.nextInt()) {
                case 1:
                    BookDAO.ReadBook();
                    break;
                case 2:
                    read.nextLine();
                    System.out.println("How would you like to search?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                    switch (read.nextInt()) {
                        case 1:
                            read.nextLine();
                            System.out.println("Please enter the title:");
                            String title = read.nextLine();
                            BookDAO.ReadTitle(title);
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
                    System.out.println("Please type the person's name");
                    String name=read.nextLine();
                    System.out.println("How would you like to search the book?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                    switch (read.nextInt()) {
                        case 1:
                            read.nextLine();
                            System.out.println("Please enter the title:");
                            String title = read.nextLine();
                            BookDAO.ReadTitle(title);
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
                            String genre = read.nextLine();
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
                    System.out.println("Write the person's name");
                    LentDAO.ReturnBook(read.nextLine());
                    System.out.println("Type the person's id");
                    LentDAO.Delete_person(read.nextInt());
                    break;
                case 5:
                    read.nextLine();
                    System.out.println("Write the book title:");
                    String title = read.nextLine();
                    System.out.println("Write the book author:");
                    String author = read.nextLine();
                    System.out.println("Write the book genre:");
                    String genre = read.nextLine();
                    System.out.println("Write how many units of the book will be added:");
                    int units = read.nextInt();
                    Book b = new Book(title,author,genre,units);
                    BookDAO.InsertBook(b);
                    break;
                case 6:
                    read.nextLine();


            }
                    System.out.println("1. Return\n2. Exit\n");
                    k = read.nextInt();
        }while (k == 1) ;
    }
}