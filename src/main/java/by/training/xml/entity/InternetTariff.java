package by.training.xml.entity;

import java.time.LocalDate;

public class InternetTariff extends Tariff{

    private int internetTraffic;
    private int transmissionSpeed;

    public InternetTariff(int internetTraffic, int transmissionSpeed) {
        this.internetTraffic = internetTraffic;
        this.transmissionSpeed = transmissionSpeed;
    }


    public InternetTariff(int payroll, int connectionPay, LocalDate introductionTime, int internetTraffic, int transmissionSpeed) {
        super(payroll, connectionPay, introductionTime);
        this.internetTraffic = internetTraffic;
        this.transmissionSpeed = transmissionSpeed;
    }

    public int getInternetTraffic() {
        return internetTraffic;
    }

    public void setInternetTraffic(int internetTraffic) {
        this.internetTraffic = internetTraffic;
    }

    public int getTransmissionSpeed() {
        return transmissionSpeed;
    }

    public void setTransmissionSpeed(int transmissionSpeed) {
        this.transmissionSpeed = transmissionSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InternetTariff that = (InternetTariff) o;
        return internetTraffic == that.internetTraffic && transmissionSpeed == that.transmissionSpeed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result + internetTraffic;
        result = prime * result + transmissionSpeed;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InternetTariff{");
        sb.append("internetTraffic=").append(internetTraffic);
        sb.append(", transmissionSpeed=").append(transmissionSpeed);
        sb.append('}');
        return sb.toString();
    }
}
