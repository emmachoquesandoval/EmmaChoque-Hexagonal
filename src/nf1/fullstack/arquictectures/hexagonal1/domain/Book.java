package nf1.fullstack.arquictectures.hexagonal1.domain;

public class Book {
    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
