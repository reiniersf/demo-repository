package books.domain;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String isbn;
    private String title;

    public Book(String isbn, String title) {
        this.title = title;
        this.isbn = isbn;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        return 31 * isbn.hashCode() * title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Book && ((Book)obj).getIsbn().equals(this.isbn);
    }

    @Override
    public String toString() {
        return String.format("[Title: %s, ISBN: %s]", this.title, this.isbn);
    }
}
