package domain.providers;

import domain.properties.Currency;
import domain.repos.CurrencyRepository;
import org.jsoup.Jsoup;

import java.sql.Date;
import java.util.*;

public class CurrencyProvider{

    private CurrencyProvider(){}


    private final CurrencyRepository currencyRepository = CurrencyRepository.getInstance();

    public static final Currency BASE_CURRENCY = new Currency("EUR", 1.00);

    public Map<String, Currency> currencies = new LinkedHashMap<String, Currency>(){{ put("EUR", null);
                                                                       put("USD", null);
                                                                       put("MDL", null);
                                                                       put("RUB", null);
                                                                       put("RON", null);}};


    public void getActualExchangeRates() {

        try {
            for (String key: currencies.keySet()) {

                String currencyRate = Jsoup.connect("https://www.exchange-rates.org/converter/"+
                                                        BASE_CURRENCY.getCode()+ "/" + key + "/1")
                                            .get()
                                            .getElementsByTag("span")
                                            .get(15)
                                            .toString()
                                            .replaceAll("[^0-9,.]", "")
                                            .substring(2);

                currencies.put(key, new Currency(key, Double.parseDouble(currencyRate)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (currencyRepository.selectAll().size() == 0) currencies.values().forEach(currencyRepository::save);

        else currencies.values().forEach(c -> currencyRepository.updateRate(c.getCode(),c.getRate()));
        currencyRepository.closeConnection();
    }

    public Currency getCurrency(String currencyCode) {

        if (currencies.keySet().stream().noneMatch(key -> key.equals(currencyCode))) {

            System.err.println("Please use one of these currencies: " + currencies.keySet());
            return null;
        }

        if (this.currencies.get(currencyCode) == null) {
            List<Currency> currencies = currencyRepository.selectByDateOfUpdate(new Date(System.currentTimeMillis()));


            if (currencies == null || currencies.size() == 0) {
                System.err.println("The rates list is deprecated or empty!\nUpdating currency rates...");
                getActualExchangeRates();

            } else currencies.forEach(c -> this.currencies.put(c.getCode(), c));
            currencyRepository.closeConnection();
        }

        return this.currencies.get(currencyCode);
    }

    private static class SingletonHolder {
        private static final CurrencyProvider INSTANCE = new CurrencyProvider();
    }

    public static CurrencyProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
