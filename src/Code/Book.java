package Code;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int availability;

    public Book(String title, String author, String genre, int availability) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getAvailability() {
        return availability;
    }


}
