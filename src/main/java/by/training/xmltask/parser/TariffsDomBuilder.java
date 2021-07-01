package by.training.xmltask.parser;

import by.training.xmltask.entity.MobileTariff;
import by.training.xmltask.entity.Tariff;
import by.training.xmltask.exception.TariffException;
import by.training.xmltask.handler.TariffXMLTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
            String mobilTariffTag = TariffXMLTag.MOBILE_TARIFF.toString();
            String internetTariffTag = TariffXMLTag.INTERNET_TARIFF.toString();

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
            // FIXME: 01.07.2021
        } catch (IOException | SAXException e) {
            logger.error("Problems opening files in DOM" + e);
            throw new TariffException(e);
        }

    }

    private Tariff buildTariff(Element element, Tariff tariff){
        return null;
    }
}
