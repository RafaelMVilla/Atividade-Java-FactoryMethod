package factory;

import materials.DigitalMedia;
import materials.iBorrowable;

public class FactoryDigitalMedia implements iFactoryBorrowable {

    private String fileFormat;

    public FactoryDigitalMedia(String fileFormat){
        this.fileFormat = fileFormat;
    }

    @Override
    public iBorrowable create() {
        
        return new DigitalMedia(fileFormat);
    }

}
