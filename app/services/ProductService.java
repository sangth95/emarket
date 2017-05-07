package services;

import dao.ProductDao;
import models.Product;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by An on 5/7/2017.
 */
@Singleton
public class ProductService {
    private ProductDao productDao;

    @Inject
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getProducts() {
        return this.productDao.getProductList();
    }

    public Product getProduct(Integer id) {
        return productDao.get(id);
    }

    public List<Product> getProducts(String behavior, String key) {
        return productDao.getProductList(behavior, key);
    }
}
