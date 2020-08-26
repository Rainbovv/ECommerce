package domain.repos;

public class Cart /*implements ProductRepository */{

//    private List<Product> products = new ArrayList<>();
//
//    private Money total;
//
//    private Cart(){}
//
//    @Override
//    public void add(Product product) {
//
//        if (product != null) {
//            products.add(product);
//            calculateTotal();
//        }
//        else
//            System.err.println("You're trying to add a null in the cart.\n" +
//                    "Please use a concrete product!");
//    }
//
//    @Override
//    public void delete(Integer productId) {
//        Product product = findById(productId);
//
//        if (product != null) {
//            products.remove(product);
//            calculateTotal();
//        }
//
//        else System.err.println("There is no such a product in the cart!");
//    }
//
//    @Override
//    public void updateQuantity(Integer productId, Integer newQuantity) {
//        Product product = findById(productId);
//        product.setQuantity(newQuantity);
//        calculateTotal();
//    }
//
//    @Override
//    public List<Product> findAll() {
//        return products;
//    }
//
////    @Override
//    public Product findById(Integer productId) {
//
//        for (Product p: products) {
//            if (p.getId().equals(productId)) return p;
//        }
//        return null;
//    }
//
//    @Override
//    public List<Product> findByName(String productName) {
//        List<Product> result = new ArrayList<>();
//
//        for (Product p: products) {
//            if (p.getName().equals(productName)) result.add(p);
//        }
//        return result;
//    }
//
//    @Override
//    public List<Product> findByManufacturer(String manufacturer) {
//        List<Product> result = new ArrayList<>();
//
//        for (Product p: products) {
//            if (p.getManufacturer().equals(manufacturer)) result.add(p);
//        }
//        return result;
//    }
//
//    @Override
//    public List<Product> findByCategory(String category) {
//        List<Product> result = new ArrayList<>();
//
//        for (Product p: products) {
//            if (p.getCategory().getName().equals(category)) result.add(p);
//        }
//        return result;
//    }
//
//    @Override
//    public List<Product> findByExpirationDate(Date expDate) {
//        List<Product> result = new ArrayList<>();
//
//        for (Product p: products) {
//            if (p.getExpDate().equals(expDate)) result.add(p);
//        }
//        return result;
//    }
//
//    @Override
//    public List<Product> findByExpirationDateFrom(Date expDate) {
//        List<Product> result = new ArrayList<>();
//
//        for (Product p: products) {
//            if (p.getExpDate().compareTo(expDate) >= 0)
//                result.add(p);
//        }
//        return result;
//    }
//
//    @Override
//    public List<Product> findByExpirationDateTo(Date expDate) {
//        List<Product> result = new ArrayList<>();
//
//        for (Product p: products) {
//            if (p.getExpDate().compareTo(expDate) < 0)
//                result.add(p);
//        }
//        return result;
//    }
//
//    @Override
//    public List<Product> findByExpirationDateBetween(Date expDateFrom, Date expDateTo) {
//        List<Product> result = new ArrayList<>();
//
//        for (Product p: products) {
//            if (p.getExpDate().compareTo(expDateFrom) >= 0 &&
//                p.getExpDate().compareTo(expDateTo) < 0)
//                result.add(p);
//        }
//        return result;
//    }
//
//
//    private static class SingletonHolder {
//        private final static Cart INSTANCE = new Cart();
//    }
//
//
//    public static Cart getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//
//    /** /////////////////////////// Business Logic /////////////////////////// **/
//    public void calculateTotal(){
//        float total = 0;
//
//        for (Product p: products) {
//            total += p.getPrice().toCurrency("EUR").getAmount() * p.getQuantity();
//        }
//
//        this.total = MoneyProvider.getInstance().getMoney(total);
//    }
//
//    public Money getTotal() {
//        return total;
//    }
//
//    public Money getTotal(String currency) {
//        return total.toCurrency(currency);
//    }
}
