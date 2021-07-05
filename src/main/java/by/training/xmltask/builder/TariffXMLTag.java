package by.training.xmltask.builder;

public enum TariffXMLTag {

    TARIFFS,
    MOBILE_TARIFF,
    INTERNET_TARIFF,

    CALL_PRICE,
    TARIFF_CODE,
    OPERATOR_NAME,

    INTRODUCTION_TIME,
    OFFLINE,
    WITHIN_THE_NETWORK,
    INTERNET_TRAFFIC,
    CITY_NETWORK,
    TRANSMISSION_SPEED,
    PAYROLL,
    CONNECTION_PAY,
    TARIFFICATION;


    private static final char REPLACE_CHAR = '_';
    private static final char NEW_CHAR = '-';

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace(REPLACE_CHAR, NEW_CHAR);
    }
}
