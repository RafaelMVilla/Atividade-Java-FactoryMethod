// materials/absLibraryItem.java
package materials;

public abstract class absLibraryItem implements iBorrowable {

    protected String title;
    protected String author;
    protected int publicationYear;

    protected boolean available = true; // <- estado padrão

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }

    @Override
    public boolean isAvailable() { return available; }
}
