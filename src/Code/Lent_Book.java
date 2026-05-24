package Code;

import java.sql.Timestamp;

public class Lent_Book {
    private int id;
    private String book;
    private String person;
    private Timestamp date;

    public int getId() {
        return id;
    }
    public String getBook() {
        return book;
    }

    public String getperson(){
        return person;
    }

    public Timestamp getdate(){
        return date;
    }
    public Lent_Book(int id, String book, String person, Timestamp date) {
        this.id = id;
        this.book = book;
        this.person = person;
        this.date = date;
    }

    public Lent_Book(String book, String person) {
        this.book = book;
        this.person = person;
    }

    @Override
    public String toString(){
        return "|   "+id+"   |   "+book+"   |   "+person+"   |   "+date+"   |";
    }
}
