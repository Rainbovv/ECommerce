package domain.interfaces;

import domain.properties.Category;
import domain.properties.Currency;

import java.util.List;

public interface CategoryRepositoryInterface {

	void save(Category category);

	List<Currency> selectAll();

	List<Category> select(String column, String value);

	Category selectById(int value);

	Category selectByName(String name);

	void updateName(String name, String condition);

	void deleteAll();

	void delete(String condition);

}
