package by.training.xml.entity;

import java.util.Objects;

public class CallPrice {

    private int tariffication;
    private double withinTheNetwork;
    private double offline;
    private double cityNetwork;

    public CallPrice() {
    }

    public CallPrice(int tariffication, double withinTheNetwork, double offline, double cityNetwork) {
        this.tariffication = tariffication;
        this.withinTheNetwork = withinTheNetwork;
        this.offline = offline;
        this.cityNetwork = cityNetwork;
    }

    public int getTariffication() {
        return tariffication;
    }

    public void setTariffication(int tariffication) {
        this.tariffication = tariffication;
    }

    public double getWithinTheNetwork() {
        return withinTheNetwork;
    }

    public void setWithinTheNetwork(double withinTheNetwork) {
        this.withinTheNetwork = withinTheNetwork;
    }

    public double getOffline() {
        return offline;
    }

    public void setOffline(double offline) {
        this.offline = offline;
    }

    public double getCityNetwork() {
        return cityNetwork;
    }

    public void setCityNetwork(double cityNetwork) {
        this.cityNetwork = cityNetwork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallPrice callPrice = (CallPrice) o;
        return tariffication == callPrice.tariffication
                && Double.compare(callPrice.withinTheNetwork, withinTheNetwork) == 0
                && Double.compare(callPrice.offline, offline) == 0
                && Double.compare(callPrice.cityNetwork, cityNetwork) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tariffication;
        result = prime * result + Double.hashCode(withinTheNetwork);
        result = prime * result + Double.hashCode(offline);
        result = prime * result + Double.hashCode(cityNetwork);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallPrice{");
        sb.append("tariffication=").append(tariffication);
        sb.append(", withinTheNetwork=").append(withinTheNetwork);
        sb.append(", offline=").append(offline);
        sb.append(", cityNetwork=").append(cityNetwork);
        sb.append('}');
        return sb.toString();
    }
}
