package domain.providers;

import domain.properties.Currency;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({"deprecation","unchecked"})
public class CurrencyProvider {

    private CurrencyProvider(){}


    public static final Currency BASE_CURRENCY = new Currency("EUR", 1.00);

    private static Map<String, Currency> currencies = new LinkedHashMap<String, Currency>() {{
                                                            put("EUR", null);
                                                            put("USD", null);
                                                            put("MDL", null);
                                                            put("RUB", null);
                                                            put("RON", null);}};

    private Date dateOfUpdate = null;


    private void getActualExchangeRates() {

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

        dateOfUpdate = new Date();


            ObjectOutputStream out = new ObjectOutputStream(
                                        new FileOutputStream(
                                                new File(this.getClass()
                                                             .getResource("/currencies.bin").toURI())));

            out.writeObject(currencies);
            out.writeObject(dateOfUpdate);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Currency getCurrency(String currencyCode) {

        if (currencies.keySet().stream().noneMatch(key -> key.equals(currencyCode))) {

            System.err.println("Please use one of these currencies: " + currencies.keySet());
            return null;
        }

        Date actualDate = new Date();

        try {
            ObjectInputStream in = new ObjectInputStream(
                                        new FileInputStream(
                                                new File(this.getClass()
                                                             .getResource("/currencies.bin").toURI())));

            currencies = (Map<String, Currency>) in.readObject();
            dateOfUpdate = (Date) in.readObject();
            in.close();

        } catch (Exception e) {
            System.err.println("The rates list is empty!\nUpdating currency rates...");
        }

        if (dateOfUpdate == null || actualDate.getDay() != dateOfUpdate.getDay())
            getActualExchangeRates();

        return currencies.get(currencyCode);
    }

    private static class SingletonHolder {
        private static final CurrencyProvider INSTANCE = new CurrencyProvider();
    }

    public static CurrencyProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
