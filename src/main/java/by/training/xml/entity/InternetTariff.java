package by.training.xml.entity;

import java.time.LocalDate;
import java.util.Objects;

public class InternetTariff extends Tariff{

    private int internetTraffic;

    public InternetTariff(int internetTraffic) {
        this.internetTraffic = internetTraffic;
    }

    public InternetTariff(int payroll, int connectionPay, LocalDate introductionTime, int internetTraffic) {
        super(payroll, connectionPay, introductionTime);
        this.internetTraffic = internetTraffic;
    }

    public int getInternetTraffic() {
        return internetTraffic;
    }

    public void setInternetTraffic(int internetTraffic) {
        this.internetTraffic = internetTraffic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InternetTariff that = (InternetTariff) o;
        return internetTraffic == that.internetTraffic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), internetTraffic);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InternetTariff{");
        sb.append("internetTraffic=").append(internetTraffic);
        sb.append('}');
        return sb.toString();
    }
}
