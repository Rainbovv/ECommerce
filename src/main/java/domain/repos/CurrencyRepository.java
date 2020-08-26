package domain.repos;

import domain.interfaces.CurrencyRepositoryInterface;
import domain.properties.Currency;

import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class CurrencyRepository extends DataRepository implements CurrencyRepositoryInterface {

	private CurrencyRepository() {}


	public Currency convertToObject(ResultSet resultSet) {

		Currency currency = null;
		try {
			currency =  new Currency(resultSet.getString("code"),
					resultSet.getDouble("rate"));

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return currency;
	}

	@Override
	public void save(Currency currency) {

		String query = String.format("INSERT INTO currencies (code,rate) VALUES('%s', %s)",
				currency.getCode(), String.valueOf(currency.getRate()).replace(",","." ));

		executeQuery(query, false);
	}

	@Override
	public List<Currency> selectAll() {

		return selectAll("currencies");
	}

	@Override
	public List<Currency> select(String column, String value) {

		return super.select("currencies", column +"="+ value);
	}

	@Override
	public Currency selectByCode(String code) {

		return select("code" , "'"+code+"'").get(0);
	}

	@Override
	public List<Currency> selectByDateOfUpdate(Date date) {

		return select("date_of_update","'"+date+"'");
	}

	@Override
	public void updateRate(String code, double rate) {

		update("currencies", "rate",
				String.valueOf(rate).replace(",","."),
				"code='"+ code+"'");
		update("currencies", "date_of_update", "current_date", "code='"+code+"'");
	}

	@Override
	public void deleteAll() {
		deleteAll("currencies");
	}

	@Override
	public void delete(String condition) {
		delete("currencies", condition);
	}


	private static class SingletonHolder {
		private final static CurrencyRepository INSTANCE = new CurrencyRepository();
	}

	public static CurrencyRepository getInstance() {
		return CurrencyRepository.SingletonHolder.INSTANCE;
	}
}
