package by.training.xmltask.test;

import by.training.xmltask.entity.*;
import by.training.xmltask.exception.TariffException;
import by.training.xmltask.builder.TariffBuilderFactory;
import by.training.xmltask.builder.TariffsDomBuilder;
import by.training.xmltask.builder.TariffsSaxBuilder;
import by.training.xmltask.builder.TariffsStaxBuilder;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TariffTest {

    private String xmlPath = "src\\test\\resources\\validtest.xml";
    private static Set<Tariff> testSet;

    @BeforeClass
    public static void initTest(){
        testSet = new HashSet<>();
        MobileTariff mobile = new MobileTariff();
        mobile.setTariffCode("a12345");
        mobile.setOperator(OperatorName.BEELINE);
        mobile.setPayroll(30);
        mobile.setConnectionPay(10);
        mobile.setIntroductionTime(LocalDate.parse("2020-03-23"));
        CallPrice callPrice = new CallPrice(20, 0.12, 0.15, 0.59);
        mobile.setCallPrice(callPrice);
        InternetTariff internet = new InternetTariff(100, 50, LocalDate.parse("2021-02-11"), 5000, 12500);
        testSet.add(mobile);
        testSet.add(internet);
    }


    @Test
    public void saxParseTest() throws TariffException {
        TariffsSaxBuilder saxBuilder = (TariffsSaxBuilder) TariffBuilderFactory.createTariffBuilder("SAX");
        saxBuilder.buildTariffs(xmlPath);
        Set<Tariff> actual = saxBuilder.getTariffs();
        Assert.assertEquals(actual, testSet);
    }

    @Test
    public void domParseTest() throws TariffException {
        TariffsDomBuilder domBuilder = (TariffsDomBuilder) TariffBuilderFactory.createTariffBuilder("DOM");
        domBuilder.buildTariffs(xmlPath);
        Set<Tariff> actual = domBuilder.getTariffs();
        Assert.assertEquals(actual, testSet);
    }

    @Test
    public void staxParseTest() throws TariffException {
        TariffsStaxBuilder staxBuilder = (TariffsStaxBuilder) TariffBuilderFactory.createTariffBuilder("STAX");
        staxBuilder.buildTariffs(xmlPath);
        Set<Tariff> actual = staxBuilder.getTariffs();
        Assert.assertEquals(actual, testSet);
    }


}
