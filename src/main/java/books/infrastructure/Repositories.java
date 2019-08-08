package books.infrastructure;

import books.domain.BookRepository;
import books.infrastructure.FileBookRepository;

public final class Repositories{
    public static BookRepository fileBasedBookRepository(){
        return new FileBookRepository();
    }
}