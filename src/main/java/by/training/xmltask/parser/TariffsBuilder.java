package by.training.xmltask.parser;

import by.training.xmltask.entity.Tariff;
import by.training.xmltask.exception.TariffException;

import java.util.HashSet;
import java.util.Set;

public abstract class TariffsBuilder {
    protected Set<Tariff> tariffs;

    public TariffsBuilder() {
        this.tariffs = new HashSet<>();
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public abstract void buildTariffs(String filePath) throws TariffException;
}
