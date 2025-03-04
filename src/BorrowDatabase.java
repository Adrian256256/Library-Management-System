import java.util.ArrayList;

public class BorrowDatabase {
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
