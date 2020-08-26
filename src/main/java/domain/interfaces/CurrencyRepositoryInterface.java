package domain.interfaces;

import domain.properties.Currency;

import java.sql.Date;
import java.util.List;

public interface CurrencyRepositoryInterface {

	void save(Currency currency);

	List<Currency> selectAll();

	List<Currency> select(String column, String value);

	Currency selectByCode(String code);

	List<Currency> selectByDateOfUpdate(Date date);

	void updateRate(String code, double rate);

	void deleteAll();

	void delete(String condition);
}
