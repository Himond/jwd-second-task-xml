package by.training.xml.test;

import by.training.xml.exception.TariffException;
import by.training.xml.validator.TariffXmlValidator;
import org.junit.Assert;
import org.junit.Test;

public class TariffValidTest {

    private String xmlPath = "src\\main\\resources\\tariffs.xml";

    @Test
    public void validTest() throws TariffException {

        boolean actual = TariffXmlValidator.validateXMLFile(xmlPath);
        Assert.assertEquals(actual, true);
    }

}
