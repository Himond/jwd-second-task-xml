package by.training.xmltask.builder;

import by.training.xmltask.exception.TariffException;
import by.training.xmltask.handler.TariffErrorHandler;
import by.training.xmltask.handler.TariffHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;


public class TariffsSaxBuilder extends TariffsBuilder{

    private static Logger logger = LogManager.getLogger();
    private TariffHandler handler = new TariffHandler();
    private XMLReader reader;

    public TariffsSaxBuilder() throws TariffException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setErrorHandler(new TariffErrorHandler());
            reader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Configuration Sax error" + e);
            throw new TariffException(e);
        }
    }

    @Override
    public void buildTariffs(String filePath) throws TariffException {
        try {
            reader.parse(filePath);
            } catch (IOException | SAXException e) {
            logger.error("Problems opening files in XML" + e);
            throw new TariffException(e);
        }
        tariffs = handler.getTariffs();
    }

}
