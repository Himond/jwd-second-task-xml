package by.training.xmltask.entity;

import java.time.LocalDate;

public class MobileTariff extends Tariff{

    private CallPrice callPrice = new CallPrice();
    private String tariffCode;
    private OperatorName operator;

    public MobileTariff() {
        operator = OperatorName.A1;
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
        if (callPrice == null){
            if(that.callPrice != null){
                return false;
            }
        }else if(!callPrice.equals(that.callPrice)){
            return false;
        }
        if (tariffCode == null){
            if(that.tariffCode != null){
                return false;
            }
        }else if(!tariffCode.equals(that.tariffCode)){
            return false;
        }
        if (operator == null){
            if(that.operator != null){
                return false;
            }
        }else if(!operator.equals(that.operator)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + super.hashCode();
        result = prime * result +  ((callPrice == null) ? 0 : callPrice.hashCode());
        result = prime * result +  ((tariffCode == null) ? 0 : tariffCode.hashCode());
        result = prime * result +  ((operator == null) ? 0 : operator.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" MobileTariff{");
        sb.append("callPrice=").append(callPrice);
        sb.append(", tariffCode='").append(tariffCode).append('\'');
        sb.append(", operator=").append(operator);
        sb.append('}');
        return sb.toString();
    }
}
