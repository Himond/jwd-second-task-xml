package by.training.xml.validator;

import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import by.training.xml.exception.TariffException;
import by.training.xml.handler.TariffErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

public class TariffXmlValidator {

    private static Logger logger = LogManager.getLogger();
    private static final String SCHEMA_NAME = "src\\main\\resources\\tariffs.xsd";

    public static boolean validateXMLFile(String path) throws TariffException {
        final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;

        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(SCHEMA_NAME);

        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(path);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            throw new TariffException(e);
        }

        return true;
    }

}
