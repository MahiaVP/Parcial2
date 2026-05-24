package Code;

public class Location {
    private int id;
    private String book;
    private String author;
    private String section;
    private int row;

    public  Location(int id, String book, String author, String section, int row) {
        this.id = id;
        this.book = book;
        this.author = author;
        this.section = section;
        this.row = row;
    }

    public Location(String book, String author, String section, int row) {
        this.book = book;
        this.author = author;
        this.section = section;
        this.row = row;
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public String getSection() {
        return section;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString(){
        return "|   "+id+"   |   "+book+"   |   "+author+"   |   "+section+"   |   "+row+"   |";
    }
}
