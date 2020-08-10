package domain.providers;

import domain.properties.Category;

public class CategoryProvider {

    private CategoryProvider() {}

    public Category getCategory(String name) {

        return new Category(name);
    }

    private static class SingletonHolder {
        private static final CategoryProvider INSTANCE = new CategoryProvider();
    }

    public static CategoryProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
