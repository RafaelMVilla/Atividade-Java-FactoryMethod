package factory;

import materials.Magazine;
import materials.iBorrowable;

public class FactoryMagazine implements iFactoryBorrowable{

    private String issueNumber;

    public FactoryMagazine(String issueNumber){
        this.issueNumber = issueNumber;
    }

    @Override
    public iBorrowable create() {
        //Aqui poderia ter uma consulta ao banco de dados para buscar a revista pelo ISBN
        //Mas para simplificar, vamos criar uma revista fixa
        return new Magazine(issueNumber);
    }

}
