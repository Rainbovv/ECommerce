package domain.providers;

import domain.properties.Currency;
import domain.repos.DataRepo;
import org.jsoup.Jsoup;
import java.util.*;

@SuppressWarnings({"deprecation"})
public class CurrencyProvider{

    private CurrencyProvider(){}


    public static final Currency BASE_CURRENCY = new Currency("EUR", 1.00);

    private Map<String, Currency> currencies = new LinkedHashMap<String, Currency>(){{ put("EUR", null);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        dateOfUpdate = new Date();
        List<Currency> updatedCurrencies = new ArrayList<>(currencies.values());

        DataRepo.getInstance().save(updatedCurrencies);
        DataRepo.getInstance().save(dateOfUpdate);
    }

    public Currency getCurrency(String currencyCode) {

        if (currencies.keySet().stream().noneMatch(key -> key.equals(currencyCode))) {

            System.err.println("Please use one of these currencies: " + currencies.keySet());
            return null;
        }

        List<Currency> updatedCurrencies;

        dateOfUpdate = DataRepo.getInstance().load(Date.class);
        updatedCurrencies = DataRepo.getInstance().load(ArrayList.class);

        if (dateOfUpdate == null || new Date().getDay() != dateOfUpdate.getDay() || updatedCurrencies == null) {
            System.err.println("The rates list is deprecated or empty!\nUpdating currency rates...");
            getActualExchangeRates();

        } else updatedCurrencies.forEach(c -> currencies.put(c.getCode(),c));

        return currencies.get(currencyCode);
    }

    private static class SingletonHolder {
        private static final CurrencyProvider INSTANCE = new CurrencyProvider();
    }

    public static CurrencyProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
