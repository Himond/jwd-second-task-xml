package by.training.xmltask.parser;

import by.training.xmltask.entity.*;
import by.training.xmltask.exception.TariffException;
import by.training.xmltask.handler.TariffXMLTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.training.xmltask.handler.TariffXMLTag.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class TariffsStaxBuilder extends TariffsBuilder{


    private static final char REPLACE_CHAR = '_';
    private static final char NEW_CHAR = '-';
    private static Logger logger = LogManager.getLogger();
    private final XMLInputFactory inputFactory;

    public TariffsStaxBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildTariffs(String filePath) throws TariffException {
        String name;
        XMLStreamReader reader;
        try(var inputStream = new FileInputStream(filePath)) {

            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();

                    if (name.equals(MOBILE_TARIFF.toString())) {
                        Tariff mobileTariff = new MobileTariff();
                        buildTariff(mobileTariff, reader);
                        tariffs.add(mobileTariff);
                    }

                    if (name.equals(INTERNET_TARIFF.toString())) {
                        Tariff internetTariff = new InternetTariff();
                        buildTariff(internetTariff, reader);
                        tariffs.add(internetTariff);
                    }

                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.error("Problems opening files in Stax" + e);
            throw new TariffException(e);
        } catch (IOException e) {
            throw new TariffException(e);
        }
    }


    private void buildTariff(Tariff tariff, XMLStreamReader reader) throws XMLStreamException, TariffException {

        if(tariff.getClass() == MobileTariff.class){
            String tariffCode = reader.getAttributeValue(null, TARIFF_CODE.toString());
            String operatorName = reader.getAttributeValue(null, OPERATOR_NAME.toString());
            ((MobileTariff) tariff).setTariffCode(tariffCode);
            if(operatorName != null){
                ((MobileTariff) tariff).setOperator(OperatorName.valueOf(operatorName));
            }
        }

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    String constantName = toConstantName(name);
                    TariffXMLTag tag = TariffXMLTag.valueOf(constantName);
                    initializeField(reader, tag, tariff);
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(MOBILE_TARIFF.toString()) || name.equals(INTERNET_TARIFF.toString())) {
                        return;
                    }
                }
            }
        }
    }

    private void initializeField(XMLStreamReader reader, TariffXMLTag tag, Tariff tariff) throws XMLStreamException, TariffException {

        String data = getTextContent(reader)
                .orElseThrow(() -> new TariffException("Unable to get text content"));

        switch (tag){
            case PAYROLL -> tariff.setPayroll(Integer.parseInt(data));
            case CONNECTION_PAY -> tariff.setConnectionPay(Integer.parseInt(data));
            case INTRODUCTION_TIME -> tariff.setIntroductionTime(LocalDate.parse(data));
            case CALL_PRICE -> {
                MobileTariff mobil = (MobileTariff) tariff;
                mobil.setCallPrice(getXmlCallPrice(reader));
            }
            case INTERNET_TRAFFIC -> {
                InternetTariff internet = (InternetTariff) tariff;
                internet.setInternetTraffic(Integer.parseInt(data));
            }
            case TRANSMISSION_SPEED -> {
                InternetTariff internet = (InternetTariff) tariff;
                internet.setTransmissionSpeed(Integer.parseInt(data));
            }
            default -> throw new EnumConstantNotPresentException(
                        tag.getDeclaringClass(), tag.name());
        }
    }

    private CallPrice getXmlCallPrice(XMLStreamReader reader) throws XMLStreamException, TariffException {
        var callPrice = new CallPrice();
        int type;
        String name;

        while (reader.hasNext()) {
            type = reader.next();

            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    TariffXMLTag tag = TariffXMLTag.valueOf(toConstantName(name));
                    String data = getTextContent(reader)
                            .orElseThrow(() -> new TariffException("Unable to get text content"));
                    switch (tag) {
                        case TARIFFICATION -> callPrice.setTariffication(Integer.parseInt(data));
                        case WITHIN_THE_NETWORK -> callPrice.setWithinTheNetwork(Double.parseDouble(data));
                        case OFFLINE -> callPrice.setOffline(Double.parseDouble(data));
                        case CITY_NETWORK -> callPrice.setCityNetwork(Double.parseDouble(data));
                        default -> throw new EnumConstantNotPresentException(
                                tag.getDeclaringClass(), tag.name());
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (TariffXMLTag.valueOf(toConstantName(name)) == CALL_PRICE) {
                        return callPrice;
                    }
                }

            }
        }
        logger.error("Unknown element in tag <call_price>");
        throw new TariffException("Unknown element in tag <call_price>");
    }


    private Optional<String> getTextContent(XMLStreamReader reader) throws XMLStreamException {
        Optional<String> result = Optional.empty();
        if (reader.hasNext()) {
            reader.next();
            String text = reader.getText();
            result = Optional.of(text);
        }
        return result;
    }


    private String toConstantName(String string) {
        return string.strip()
                .replace(NEW_CHAR, REPLACE_CHAR)
                .toUpperCase();
    }
}
