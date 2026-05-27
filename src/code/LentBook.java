package code;

import java.sql.Timestamp;

public class LentBook {
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

    public String getPerson(){
        return person;
    }

    public Timestamp getdate(){
        return date;
    }
    public LentBook(int id, String book, String person, Timestamp date) {
        this.id = id;
        this.book = book;
        this.person = person;
        this.date = date;
    }

    public LentBook(String book, String person) {
        this.book = book;
        this.person = person;
    }

    @Override
    public String toString(){
        return "|   "+id+"   |   "+book+"   |   "+person+"   |   "+date+"   |";
    }
}
