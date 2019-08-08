package books.infrastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import books.domain.Book;
import books.domain.BookRepository;

public class FileBookRepository implements BookRepository {

    private static File entityDBFile = new File(
            System.getProperty("user.dir").concat(File.separator).concat("db.book"));
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean register(Book aNewBook) {
        Set<Book> inventory = new HashSet<>();

        if (entityDBFile.exists()) {
            try (var reader = new ObjectInputStream(new FileInputStream(entityDBFile))) {
                inventory = (Set<Book>) reader.readObject();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        try (var writer = new ObjectOutputStream(new FileOutputStream(entityDBFile))) {
            boolean wasAdded = inventory.add(aNewBook);
            writer.writeObject(inventory);
            writer.flush();
            return wasAdded;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Book> retrieveAll() {
        Set<Book> inventory = new HashSet<>();
        if (entityDBFile.exists()) {
            try (var reader = new ObjectInputStream(new FileInputStream(entityDBFile))) {
                inventory = (Set<Book>) reader.readObject();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return inventory;
    }

	public static void resetDB() {
        try (var writer = new ObjectOutputStream(new FileOutputStream(entityDBFile))) {
            writer.writeObject(new HashSet<>());
            writer.flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
	}

}