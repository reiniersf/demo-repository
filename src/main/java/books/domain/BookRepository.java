package books.domain;

import java.util.Set;

public interface BookRepository {
    public boolean register(Book aNewBook);

    public Set<Book> retrieveAll();
}