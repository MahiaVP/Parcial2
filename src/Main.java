import DB.OP.BookDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int k=1;
        Scanner read = new Scanner(System.in);
        System.out.println("Library System\n");
        do{
            System.out.println("Type the number of the action you want to perform:\n");
            System.out.println("1. See all books in the library.\n2. See availability of a book.\n3. Lend a book\n4. Return a book\n5. Add a book to the system.\n6. Filter by category.\n7. Exit\n");
            switch(read.nextInt()){
                case 1:
                    BookDAO.ReadBook();
                    break;
                case 2:
                    read.nextLine();
                    System.out.println("How would you like to search?\n1. Title\n2. Author\n3. Genre\n4. Return.");
                    switch(read.nextInt()){
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
                    break;

            }
            System.out.println("1. Return\n2.Exit\n");
            k = read.nextInt();
        }while(k==1);
    }
}
