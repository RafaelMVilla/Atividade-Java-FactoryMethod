// materials/Book.java
package materials;

public class Book extends absLibraryItem {

    private final String bookId;
    public String genre;

    public Book(String bookId) {
        this.bookId = bookId;
        if ("999".equals(this.bookId)) {
            this.author = "J.R.R. Tolkien";
            this.title = "O Senhor dos Aneis";
            this.publicationYear = 1954;
        }
    }

    @Override
    public boolean borrow() {
        if (!available) return false;
        available = false;
        return true;
    }

    @Override
    public boolean returnItem() {
        if (available) return false;
        available = true;
        return true;
    }

    public String readSample() {
        return "Esta é uma amostra do livro: " + this.title;
    }
}
