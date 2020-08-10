package domain.providers;

import domain.properties.Money;

public class MoneyProvider {

    private MoneyProvider() {}

    public Money getMoney(float amount) {

        return new Money(amount);
    }

    private static class SingletonHolder {
        private static final MoneyProvider INSTANCE = new MoneyProvider();
    }

    public static MoneyProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
