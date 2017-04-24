package services.impl;

import dao.ProductDao;
import dao.ShoppingCartDao;
import models.ShoppingCartDetail;
import models.Product;
import models.ShoppingCart;
import services.EmarketDataService;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by An on 3/1/2017.
 */
@Singleton
public class EmarketDataServiceImpl implements EmarketDataService {
    private ProductDao productDao;
    private ShoppingCartDao shoppingCartDao;
    @Inject
    public EmarketDataServiceImpl(ProductDao productDao, ShoppingCartDao shoppingCartDao) {
        this.productDao = productDao;
        this.shoppingCartDao = shoppingCartDao;
    }



    @Override
    public List<Product> getProducts() {

        return this.productDao.getProductList();
    }

    @Override
    public Product getProduct(Integer id) {
        return productDao.get(id);
    }

    @Override
    public List<Product> getProducts(String behavior, String key) {
        return productDao.getProductList(behavior, key);
    }

    @Override
    public ShoppingCart getShoppingCart(String id) {
        return shoppingCartDao.getShoppingCart(id);
    }

    @Override
    public List<ShoppingCartDetail> getShoppingCartDetail(String id) {
        return shoppingCartDao.getShoppingCartDetail(id);
    }

    @Override
    public void addItemToCart(String cartID, Product product) {
        shoppingCartDao.addItemToCart(cartID, product);
    }
}
