package by.training.xmltask.main;

import by.training.xmltask.exception.TariffException;
import by.training.xmltask.parser.TariffsDomBuilder;
import by.training.xmltask.parser.TariffsSaxBuilder;
import by.training.xmltask.parser.TariffsStaxBuilder;


public class Main {
    public static void main(String[] args) throws TariffException {
        TariffsSaxBuilder builder = new TariffsSaxBuilder();
        builder.buildTariffs("src\\main\\resources\\tariffs.xml");
        System.out.println(builder.getTariffs());

        TariffsDomBuilder domBuilder = new TariffsDomBuilder();
        domBuilder.buildTariffs("src\\main\\resources\\tariffs.xml");
        System.out.println(domBuilder.getTariffs());


        TariffsStaxBuilder staxBuilder = new TariffsStaxBuilder();
        staxBuilder.buildTariffs("src\\main\\resources\\tariffs.xml");
        System.out.println(staxBuilder.getTariffs());
    }


}
