package by.training.xmltask.parser;

import by.training.xmltask.exception.TariffException;

public class TariffBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    private TariffBuilderFactory() {
    }

    public static TariffsBuilder createTariffBuilder(String typeParser) throws TariffException {

        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        try {
            return switch (type) {
                case DOM -> new TariffsDomBuilder();
                case STAX -> new TariffsStaxBuilder();
                case SAX -> new TariffsSaxBuilder();
            };
        }catch (IllegalArgumentException e){
            throw new TariffException("Parser with name " + typeParser + " not found", e);
        }

    }
}
