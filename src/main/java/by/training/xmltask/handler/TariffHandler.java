package by.training.xmltask.handler;

import by.training.xmltask.builder.TariffXMLTag;
import by.training.xmltask.entity.InternetTariff;
import by.training.xmltask.entity.MobileTariff;
import by.training.xmltask.entity.OperatorName;
import by.training.xmltask.entity.Tariff;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TariffHandler extends DefaultHandler {
    private Set<Tariff> tariffs;
    private EnumSet<TariffXMLTag> tagsWithText;
    private Tariff currentTariff;
    private TariffXMLTag currentXmlTag;

    private static final char REPLACE_CHAR = '_';
    private static final char NEW_CHAR = '-';

    public TariffHandler() {
        tariffs = new HashSet<>();
        tagsWithText = EnumSet.range(TariffXMLTag.INTRODUCTION_TIME, TariffXMLTag.TARIFFICATION);
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        var mobilTariff = TariffXMLTag.MOBILE_TARIFF.toString();
        var internetTariff = TariffXMLTag.INTERNET_TARIFF.toString();
        if (mobilTariff.equals(qName)){
            var currentMobilTariff = new MobileTariff();
            currentMobilTariff.setTariffCode(attributes.getValue(0));
            if(attributes.getLength() == 2){
                currentMobilTariff.setOperator(OperatorName.valueOf(attributes.getValue(1)));
            }
            currentTariff = currentMobilTariff;
        }else if(internetTariff.equals(qName)){
            currentTariff = new InternetTariff();
        }else {
            String constantName = toConstantName(qName);
            TariffXMLTag tag = TariffXMLTag.valueOf(constantName);
            if (tagsWithText.contains(tag)) {
                currentXmlTag = tag;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName){
        var mobilTariff = TariffXMLTag.MOBILE_TARIFF.toString();
        var internetTariff = TariffXMLTag.INTERNET_TARIFF.toString();
        if (mobilTariff.equals(qName) || internetTariff.equals(qName)){
            tariffs.add(currentTariff);
            currentTariff = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentXmlTag != null){
            switch (currentXmlTag){
                case PAYROLL -> currentTariff.setPayroll(Integer.parseInt(data));
                case CONNECTION_PAY -> currentTariff.setConnectionPay(Integer.parseInt(data));
                case INTRODUCTION_TIME -> currentTariff.setIntroductionTime(LocalDate.parse(data));
                case TARIFFICATION ->{
                    MobileTariff mobil = (MobileTariff) currentTariff;
                    mobil.getCallPrice().setTariffication(Integer.parseInt(data));
                }
                case WITHIN_THE_NETWORK -> {
                    MobileTariff mobil = (MobileTariff) currentTariff;
                    mobil.getCallPrice().setWithinTheNetwork(Double.parseDouble(data));
                }
                case OFFLINE -> {
                    MobileTariff mobil = (MobileTariff) currentTariff;
                    mobil.getCallPrice().setOffline(Double.parseDouble(data));
                }
                case CITY_NETWORK -> {
                    MobileTariff mobil = (MobileTariff) currentTariff;
                    mobil.getCallPrice().setCityNetwork(Double.parseDouble(data));
                }
                case INTERNET_TRAFFIC -> {
                    InternetTariff internet = (InternetTariff) currentTariff;
                    internet.setInternetTraffic(Integer.parseInt(data));
                }
                case TRANSMISSION_SPEED -> {
                    InternetTariff internet = (InternetTariff) currentTariff;
                    internet.setTransmissionSpeed(Integer.parseInt(data));
                }
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    private String toConstantName(String string) {
        return string.strip()
                .replace(NEW_CHAR, REPLACE_CHAR)
                .toUpperCase();
    }
}
