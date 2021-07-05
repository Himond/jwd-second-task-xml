package by.training.xmltask.builder;

import by.training.xmltask.entity.*;
import by.training.xmltask.exception.TariffException;
import static by.training.xmltask.builder.TariffXMLTag.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;

public class TariffsDomBuilder extends TariffsBuilder{

    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public TariffsDomBuilder() throws TariffException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Configuration Dom error" + e);
            throw new TariffException(e);
        }
    }

    @Override
    public void buildTariffs(String filePath) throws TariffException {
        Document doc;
        try {
            String mobilTariffTag = MOBILE_TARIFF.toString();
            String internetTariffTag = INTERNET_TARIFF.toString();

            doc = docBuilder.parse(filePath);
            Element root = doc.getDocumentElement();
            NodeList mobileTariff = root.getElementsByTagName(mobilTariffTag);
            NodeList internetTariff = root.getElementsByTagName(internetTariffTag);

            for (int i = 0; i < mobileTariff.getLength(); i++) {
                Element tariffElement = (Element) mobileTariff.item(i);
                Tariff tariff = new MobileTariff();
                buildTariff(tariffElement, tariff);
                tariffs.add(tariff);
            }

            for(int i = 0; i < internetTariff.getLength(); i++){
                Element tariffElement = (Element) internetTariff.item(i);
                Tariff tariff = new InternetTariff();
                buildTariff(tariffElement, tariff);
                tariffs.add(tariff);
            }

        } catch (IOException | SAXException e) {
            logger.error("Problems opening files in DOM" + e);
            throw new TariffException(e);
        }

    }

    private void buildTariff(Element element, Tariff tariff){

        var payroll = Integer.parseInt(getElementTextContent(element, PAYROLL.toString()));
        var connectionPay = Integer.parseInt(getElementTextContent(element, CONNECTION_PAY.toString()));
        var introductionTime = LocalDate.parse(getElementTextContent(element, INTRODUCTION_TIME.toString()));

        tariff.setPayroll(payroll);
        tariff.setConnectionPay(connectionPay);
        tariff.setIntroductionTime(introductionTime);

        if (tariff.getClass() == MobileTariff.class){
            MobileTariff mobileTariff = (MobileTariff) tariff;
            String tariffCode = element.getAttribute(TARIFF_CODE.toString());
            String operatorName = element.getAttribute(OPERATOR_NAME.toString());
            CallPrice callPrice= mobileTariff.getCallPrice();

            var tariffication = Integer.parseInt(getElementTextContent(element, TARIFFICATION.toString()));
            var withinTheNetwork = Double.parseDouble(getElementTextContent(element, WITHIN_THE_NETWORK.toString()));
            var offline = Double.parseDouble(getElementTextContent(element, OFFLINE.toString()));
            var cityNetwork = Double.parseDouble(getElementTextContent(element, CITY_NETWORK.toString()));

            callPrice.setTariffication(tariffication);
            callPrice.setWithinTheNetwork(withinTheNetwork);
            callPrice.setOffline(offline);
            callPrice.setCityNetwork(cityNetwork);

            mobileTariff.setTariffCode(tariffCode);
            if(!operatorName.isBlank()){
                mobileTariff.setOperator(OperatorName.valueOf(operatorName));
            }

        }else if(tariff.getClass() == InternetTariff.class){
            InternetTariff internetTariff = (InternetTariff)tariff;
            var internetTraffic = Integer.parseInt(getElementTextContent(element, INTERNET_TRAFFIC.toString()));
            var transmissionSpeed = Integer.parseInt(getElementTextContent(element, TRANSMISSION_SPEED.toString()));

            internetTariff.setInternetTraffic(internetTraffic);
            internetTariff.setTransmissionSpeed(transmissionSpeed);

        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
