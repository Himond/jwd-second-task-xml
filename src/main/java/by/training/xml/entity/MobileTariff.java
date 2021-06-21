package by.training.xml.entity;

import java.time.LocalDate;
import java.util.Objects;

public class MobileTariff extends Tariff{

    private CallPrice callPrice;
    private String tariffCode;
    private OperatorName operator;

    public MobileTariff() {
    }

    public MobileTariff(int payroll, int connectionPay, LocalDate introductionTime, CallPrice callPrice, String tariffCode, OperatorName operator) {
        super(payroll, connectionPay, introductionTime);
        this.callPrice = callPrice;
        this.tariffCode = tariffCode;
        this.operator = operator;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    public String getTariffCode() {
        return tariffCode;
    }

    public void setTariffCode(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public OperatorName getOperator() {
        return operator;
    }

    public void setOperator(OperatorName operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MobileTariff that = (MobileTariff) o;
        return Objects.equals(callPrice, that.callPrice) && Objects.equals(tariffCode, that.tariffCode) && operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), callPrice, tariffCode, operator);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MobileTariff{");
        sb.append("callPrice=").append(callPrice);
        sb.append(", tariffCode='").append(tariffCode).append('\'');
        sb.append(", operator=").append(operator);
        sb.append('}');
        return sb.toString();
    }
}
