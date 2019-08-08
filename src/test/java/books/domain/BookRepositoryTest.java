package books.domain;

import static books.infrastructure.Repositories.fileBasedBookRepository;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import books.infrastructure.FileBookRepository;

public class BookRepositoryTest {

    private BookRepository bookRepository;
    @AfterEach
    void tearDown(){
        FileBookRepository.resetDB();
    }

    @Test void shouldAddANewBookToFile(){
        bookRepository = fileBasedBookRepository();

        boolean wasAdded = bookRepository.register(new Book("238340-2939" ,"Once upon a time"));

        assertTrue(wasAdded);
    }

    @Test void shouldRetrieveAllBooksFromFile(){
       bookRepository = fileBasedBookRepository();
        boolean wasAdded = bookRepository.register(new Book("238340-2939" ,"Once upon a time"));
        assertTrue(wasAdded);

        Set<Book> allBooks = bookRepository.retrieveAll();

        assertTrue(allBooks.size() == 1);
    }

    @Test void shouldNotAddSameBookTwice(){
        bookRepository = fileBasedBookRepository();
        Book theBook = new Book("238340-2939" ,"Once upon a time");
        boolean wasAdded = bookRepository.register(theBook);
        assertTrue(wasAdded);
       
        boolean wasAddedAgain = bookRepository.register(theBook);

        Set<Book> allBooks = bookRepository.retrieveAll();
        assertFalse(wasAdded && wasAddedAgain);
        assertTrue(allBooks.size() == 1);
        
    }
}