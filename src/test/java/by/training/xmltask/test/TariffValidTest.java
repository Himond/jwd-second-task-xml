package by.training.xmltask.test;

import by.training.xmltask.exception.TariffException;
import by.training.xmltask.validator.TariffXmlValidator;
import org.junit.Assert;
import org.junit.Test;

public class TariffValidTest {

    private String xmlPath = "src\\test\\resources\\invalidtest.xml";

    @Test
    public void validTest() throws TariffException {

        boolean actual = TariffXmlValidator.validateXMLFile(xmlPath);
        Assert.assertEquals(actual, true);
    }

}
