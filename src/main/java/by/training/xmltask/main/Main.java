package by.training.xmltask.main;

import by.training.xmltask.exception.TariffException;
import by.training.xmltask.parser.TariffsSaxBuilder;


public class Main {
    public static void main(String[] args) throws TariffException {
        TariffsSaxBuilder builder = new TariffsSaxBuilder();
        builder.buildTariffs("src\\main\\resources\\tariffs.xml");
        System.out.println(builder.getTariffs());


    }


}
