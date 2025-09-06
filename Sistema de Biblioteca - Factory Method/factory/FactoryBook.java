// factory/FactoryBook.java
package factory;

import materials.Book;
import materials.iBorrowable;

public class FactoryBook implements iFactoryBorrowable {

    private final String bookId;

    public FactoryBook(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public iBorrowable create() {
        // Regras de negócio pré-criação (ex.: validar Id, consultar acervo, etc.)
        return new Book(bookId);
    }
}
