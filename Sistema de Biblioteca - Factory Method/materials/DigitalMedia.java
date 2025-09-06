// materials/DigitalMedia.java
package materials;

public class DigitalMedia extends absLibraryItem {

    private final String fileFormat;

    public DigitalMedia(String fileFormat) {
        this.fileFormat = fileFormat;
        if ("mp4".equals(this.fileFormat)) {
            this.author = "Vários autores";
            this.title = "Aula de Padrões de Projeto";
            this.publicationYear = 2024;
        }
    }

    @Override
    public boolean borrow() {
        if (!available) {
            System.out.println("Mídia digital indisponível: " + this.title);
            return false;
        }
        available = false;
        System.out.println("Emprestando mídia digital: " + this.title);
        return true;
    }

    @Override
    public boolean returnItem() {
        if (available) {
            System.out.println("Mídia digital não estava emprestada: " + this.title);
            return false;
        }
        available = true;
        System.out.println("Devolvendo mídia digital: " + this.title);
        return true;
    }

    public String play() {
        return "Reproduzindo a mídia digital.";
    }
}
