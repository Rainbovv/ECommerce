package domain.properties;


import domain.providers.CurrencyProvider;

import java.io.Serializable;

public class Money implements Serializable {


    private float amount;
    private Currency currency;


    public Money(float amount) {
        this.amount = amount;
        this.currency = CurrencyProvider.BASE_CURRENCY;
    }


    public Money(float amount, String currencyCode) {
        this.amount = amount;
        this.currency = CurrencyProvider.getInstance().getCurrency(currencyCode);
    }


    public float getAmount() {
        return amount;
    }


    public void setAmount(float amount) {
        this.amount = amount;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {

        return String.format("%.2f %s", amount , currency.getCode());
    }


    public Money toCurrency(String currencyCode) {

        Currency newCurrency = CurrencyProvider.getInstance().getCurrency(currencyCode);

        if (newCurrency == null || currency.getCode().equals(currencyCode)) return this;

        if (currency.getCode().equals(CurrencyProvider.BASE_CURRENCY.getCode())) {

            return new Money(amount * (float)newCurrency.getRate(), currencyCode);
        }

        return new Money(amount / (float)currency.getRate() * (float)newCurrency.getRate(), currencyCode);
    }
}
