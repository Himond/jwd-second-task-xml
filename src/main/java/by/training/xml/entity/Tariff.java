package by.training.xml.entity;

import java.time.LocalDate;

public abstract class Tariff {

    private int payroll;
    private int connectionPay;
    private LocalDate introductionTime;

    public Tariff() {
    }

    public Tariff(int payroll, int connectionPay, LocalDate introductionTime) {
        this.payroll = payroll;
        this.connectionPay = connectionPay;
        this.introductionTime = introductionTime;
    }

    public int getPayroll() {
        return payroll;
    }

    public void setPayroll(int payroll) {
        this.payroll = payroll;
    }

    public int getConnectionPay() {
        return connectionPay;
    }

    public void setConnectionPay(int connectionPay) {
        this.connectionPay = connectionPay;
    }

    public LocalDate getIntroductionTime() {
        return introductionTime;
    }

    public void setIntroductionTime(LocalDate introductionTime) {
        this.introductionTime = introductionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        if(payroll != tariff.payroll){
            return false;
        }
        if (connectionPay != tariff.connectionPay){
            return false;
        }
        if (introductionTime == null){
            if(tariff.introductionTime != null){
                return false;
            }
        }else if(!introductionTime.equals(tariff.introductionTime)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((introductionTime == null) ? 0 : introductionTime.hashCode());
        result = prime * result + payroll;
        result = prime * result + connectionPay;
        return result;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tariff{");
        sb.append("payroll=").append(payroll);
        sb.append(", connectionPay=").append(connectionPay);
        sb.append(", introductionTime=").append(introductionTime);
        sb.append('}');
        return sb.toString();
    }
}
