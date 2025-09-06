// materials/Magazine.java
package materials;

public class Magazine extends absLibraryItem {

    public String issueNumber;

    public Magazine(String issueNumber) {
        this.issueNumber = issueNumber;
        if ("123".equals(this.issueNumber)) {
            this.author = "Vários autores";
            this.title = "Revista Super Interessante";
            this.publicationYear = 1987;
        }
    }

    @Override
    public boolean borrow() {
        if (!available) {
            System.out.println("Revista indisponível para empréstimo: " + this.title);
            return false;
        }
        available = false;
        System.out.println("Emprestando revista: " + this.title);
        return true;
    }

    @Override
    public boolean returnItem() {
        if (available) {
            System.out.println("Revista não estava emprestada: " + this.title);
            return false;
        }
        available = true;
        System.out.println("Devolvendo revista: " + this.title);
        return true;
    }

    public String flipPage() {
        return "Folheando a revista: " + this.title;
    }
}
